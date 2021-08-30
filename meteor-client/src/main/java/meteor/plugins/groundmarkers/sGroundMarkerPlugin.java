/*
 * Copyright (c) 2018, TheLonelyDev <https://github.com/TheLonelyDev>
 * Copyright (c) 2018, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package meteor.plugins.groundmarkers;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.inject.Provides;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import meteor.eventbus.events.ConfigChanged;
import net.runelite.api.Client;
import static net.runelite.api.Constants.CHUNK_SIZE;
import net.runelite.api.GameState;
import net.runelite.api.MenuEntry;
import net.runelite.api.MenuAction;
import net.runelite.api.Tile;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.FocusChanged;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.MenuOptionClicked;
import meteor.eventbus.EventBus;
import meteor.util.Text;
import meteor.config.ConfigManager;
import meteor.eventbus.Subscribe;
import meteor.input.KeyManager;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.ui.overlay.OverlayManager;
import meteor.util.ColorUtil;

@PluginDescriptor(
	name = "Ground Markers",
	description = "Enable marking of tiles using the Shift key",
	tags = {"overlay", "tiles"}
)
public class sGroundMarkerPlugin extends Plugin
{
	private static final String CONFIG_GROUP = "groundMarker";
	private static final String MARK = "Mark tile";
	private static final Pattern GROUP_MATCHER = Pattern.compile(".*ark tile \\(Group (\\d{1,2})\\)");
	private static final String UNMARK = "Unmark tile";
	private static final String WALK_HERE = "Walk here";
	private static final String REGION_PREFIX = "region_";

	private static final Gson GSON = new Gson();

	@Getter(AccessLevel.PACKAGE)
	@Setter(AccessLevel.PACKAGE)
	private boolean hotKeyPressed;

	@Getter(AccessLevel.PACKAGE)
	private final List<sGroundMarkerWorldPoint> points = new ArrayList<>();

	@Inject
	private Client client;

	@Inject
	private GroundMarkerInputListener inputListener;

	@Inject
	private ConfigManager configManager;

	@Inject
	private sGroundMarkerConfig config;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private sGroundMarkerOverlay overlay;

	@Inject
	private sGroundMarkerMinimapOverlay minimapOverlay;

	@Inject
	private EventBus eventBus;

	@Inject
	private GroundMarkerSharingManager sharingManager;

	@Inject
	private KeyManager keyManager;

	public void savePoints(int regionId, Collection<GroundMarkerPoint> points)
	{
		if (points == null || points.isEmpty())
		{
			configManager.unsetConfiguration(CONFIG_GROUP, REGION_PREFIX + regionId);
			return;
		}

		String json = GSON.toJson(points);
		configManager.setConfiguration(CONFIG_GROUP, REGION_PREFIX + regionId, json);
	}

	public Collection<GroundMarkerPoint> getPoints(int regionId)
	{
		String json = configManager.getConfiguration(CONFIG_GROUP, REGION_PREFIX + regionId);
		if (Strings.isNullOrEmpty(json))
		{
			return Collections.emptyList();
		}
		return GSON.fromJson(json, new GroundMarkerListTypeToken().getType());
	}

	private static class GroundMarkerListTypeToken extends TypeToken<List<GroundMarkerPoint>>
	{
	}

	@Provides
	public sGroundMarkerConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(sGroundMarkerConfig.class);
	}

	public void loadPoints()
	{
		points.clear();

		int[] regions = client.getMapRegions();

		if (regions == null)
		{
			return;
		}

		for (int regionId : regions)
		{
			// load points for region
			logger.debug("Loading points for region {}", regionId);
			Collection<GroundMarkerPoint> regionPoints = getPoints(regionId);
			Collection<sGroundMarkerWorldPoint> worldPoints = translateToWorld(regionPoints);
			points.addAll(worldPoints);
		}
	}

	/**
	 * Translate a collection of ground marker points to world points, accounting for instances
	 *
	 * @param points
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Collection<sGroundMarkerWorldPoint> translateToWorld(Collection<GroundMarkerPoint> points)
	{
		if (points.isEmpty())
		{
			return Collections.emptyList();
		}

		List<sGroundMarkerWorldPoint> worldPoints = new ArrayList<>();
		for (GroundMarkerPoint point : points)
		{
			int regionId = point.getRegionId();
			int regionX = point.getRegionX();
			int regionY = point.getRegionY();
			int z = point.getZ();

			WorldPoint worldPoint = WorldPoint.fromRegion(regionId, regionX, regionY, z);

			if (!client.isInInstancedRegion())
			{
				worldPoints.add(new sGroundMarkerWorldPoint(point, worldPoint));
				continue;
			}

			// find instance chunks using the template point. there might be more than one.
			int[][][] instanceTemplateChunks = client.getInstanceTemplateChunks();
			for (int x = 0; x < instanceTemplateChunks[z].length; ++x)
			{
				for (int y = 0; y < instanceTemplateChunks[z][x].length; ++y)
				{
					int chunkData = instanceTemplateChunks[z][x][y];
					int rotation = chunkData >> 1 & 0x3;
					int templateChunkY = (chunkData >> 3 & 0x7FF) * CHUNK_SIZE;
					int templateChunkX = (chunkData >> 14 & 0x3FF) * CHUNK_SIZE;
					if (worldPoint.getX() >= templateChunkX && worldPoint.getX() < templateChunkX + CHUNK_SIZE
						&& worldPoint.getY() >= templateChunkY && worldPoint.getY() < templateChunkY + CHUNK_SIZE)
					{
						WorldPoint p = new WorldPoint(client.getBaseX() + x * CHUNK_SIZE + (worldPoint.getX() & (CHUNK_SIZE - 1)),
							client.getBaseY() + y * CHUNK_SIZE + (worldPoint.getY() & (CHUNK_SIZE - 1)),
							worldPoint.getPlane());
						p = rotate(p, rotation);
						worldPoints.add(new sGroundMarkerWorldPoint(point, p));
					}
				}
			}
		}
		return worldPoints;
	}

	/**
	 * Rotate the chunk containing the given point to rotation 0
	 *
	 * @param point    point
	 * @param rotation rotation
	 * @return world point
	 */
	private static WorldPoint rotateInverse(WorldPoint point, int rotation)
	{
		return rotate(point, 4 - rotation);
	}

	/**
	 * Rotate the coordinates in the chunk according to chunk rotation
	 *
	 * @param point    point
	 * @param rotation rotation
	 * @return world point
	 */
	private static WorldPoint rotate(WorldPoint point, int rotation)
	{
		int chunkX = point.getX() & -CHUNK_SIZE;
		int chunkY = point.getY() & -CHUNK_SIZE;
		int x = point.getX() & (CHUNK_SIZE - 1);
		int y = point.getY() & (CHUNK_SIZE - 1);
		switch (rotation)
		{
			case 1:
				return new WorldPoint(chunkX + y, chunkY + (CHUNK_SIZE - 1 - x), point.getPlane());
			case 2:
				return new WorldPoint(chunkX + (CHUNK_SIZE - 1 - x), chunkY + (CHUNK_SIZE - 1 - y), point.getPlane());
			case 3:
				return new WorldPoint(chunkX + (CHUNK_SIZE - 1 - y), chunkY + x, point.getPlane());
		}
		return point;
	}

	@Subscribe
	private void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() != GameState.LOGGED_IN)
		{
			return;
		}

		// map region has just been updated
		loadPoints();
	}

	@Subscribe
	private void onFocusChanged(FocusChanged focusChanged)
	{
		if (!focusChanged.isFocused())
		{
			hotKeyPressed = false;
		}
	}

	@Subscribe
	private void onMenuEntryAdded(MenuEntryAdded event)
	{
		if (hotKeyPressed && event.getOption().equals(WALK_HERE))
		{
			final Tile selectedSceneTile = client.getSelectedSceneTile();

			if (selectedSceneTile == null)
			{
				return;
			}

			MenuEntry[] menuEntries = client.getMenuEntries();

			int lastIndex = menuEntries.length;
			menuEntries = Arrays.copyOf(menuEntries, lastIndex + config.getAmount().toInt());

			final Tile tile = client.getSelectedSceneTile();
			if (tile == null)
			{
				return;
			}
			final WorldPoint loc = WorldPoint.fromLocalInstance(client, tile.getLocalLocation());
			if (loc == null)
			{
				return;
			}
			final int regionId = loc.getRegionID();

			for (int i = config.getAmount().toInt(); i > 0; i--)
			{
				MenuEntry menuEntry = menuEntries[lastIndex] = new MenuEntry();

				final GroundMarkerPoint point = new GroundMarkerPoint(regionId, loc.getRegionX(), loc.getRegionY(), client.getPlane(), i);
				final Optional<GroundMarkerPoint> stream = getPoints(regionId).stream().filter(x -> x.equals(point)).findAny();
				final String option = (stream.isPresent() && stream.get().getGroup() == i) ? UNMARK : MARK;
				menuEntry.setOption(ColorUtil.prependColorTag(Text.removeTags(option + (i == 1 ? "" : " (Group " + i + ")")), getColor(i)));
				menuEntry.setTarget(event.getTarget());
				menuEntry.setType(MenuAction.RUNELITE.getId());

				lastIndex++;
			}

			client.setMenuEntries(menuEntries);
		}
	}

	@Subscribe
	private void onMenuOptionClicked(MenuOptionClicked event)
	{
		if (!event.getMenuOption().contains(MARK) && !event.getMenuOption().contains(UNMARK))
		{
			return;
		}

		int group = 1;
		Matcher m = GROUP_MATCHER.matcher(event.getMenuOption());
		if (m.matches())
		{
			group = Integer.parseInt(m.group(1));
		}

		Tile target = client.getSelectedSceneTile();

		if (target == null)
		{
			return;
		}
		markTile(target.getLocalLocation(), group);
	}

	@Override
	public void startup()
	{
		overlayManager.add(overlay);
		overlayManager.add(minimapOverlay);
		if (config.showImportExport())
		{
			sharingManager.addImportExportMenuOptions();
		}
		if (config.showClear())
		{
			sharingManager.addClearMenuOption();
		}
		keyManager.registerKeyListener(inputListener, this.getClass());
		loadPoints();
		eventBus.register(sharingManager);
	}

	@Override
	public void shutdown()
	{
		overlayManager.remove(overlay);
		overlayManager.remove(minimapOverlay);
		keyManager.unregisterKeyListener(inputListener);
		points.clear();
	}

	private void markTile(LocalPoint localPoint, int group)
	{
		if (localPoint == null)
		{
			return;
		}

		WorldPoint worldPoint = WorldPoint.fromLocalInstance(client, localPoint);

		if (worldPoint == null)
		{
			return;
		}

		int regionId = worldPoint.getRegionID();
		List<GroundMarkerPoint> pointArea = new ArrayList<GroundMarkerPoint>();
		int size = config.tileSize().getSize();
		int offset = size / 2;

		for (int x = 0; x < size; x++)
		{
			for (int y = 0; y < size; y++)
			{
				pointArea.add(new GroundMarkerPoint(regionId, worldPoint.getRegionX() + x - offset, worldPoint.getRegionY() + y - offset, client.getPlane(), group));
			}
		}

		List<GroundMarkerPoint> points = new ArrayList<>(getPoints(regionId));
		for(GroundMarkerPoint pt : pointArea) {
			if (points.contains(pt)) {
				GroundMarkerPoint old = points.get(points.indexOf(pt));
				points.remove(pt);

				if (old.getGroup() != group) {
					points.add(pt);
				}
			} else {
				points.add(pt);
			}
		}
		savePoints(regionId, points);
		loadPoints();
	}

	public Color getColor(int group)
	{
		Color color = config.markerColor();
		switch (group)
		{
			case 2:
				color = config.markerColor2();
				break;
			case 3:
				color = config.markerColor3();
				break;
			case 4:
				color = config.markerColor4();
				break;
			case 5:
				color = config.markerColor5();
				break;
			case 6:
				color = config.markerColor6();
				break;
			case 7:
				color = config.markerColor7();
				break;
			case 8:
				color = config.markerColor8();
				break;
			case 9:
				color = config.markerColor9();
				break;
			case 10:
				color = config.markerColor10();
				break;
			case 11:
				color = config.markerColor11();
				break;
			case 12:
				color = config.markerColor12();
		}

		return color;
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event)
	{
		if (event.getGroup().equals(sGroundMarkerConfig.GROUND_MARKER_CONFIG_GROUP)
				&& (event.getKey().equals(sGroundMarkerConfig.SHOW_IMPORT_EXPORT_KEY_NAME)
				|| event.getKey().equals(sGroundMarkerConfig.SHOW_CLEAR_KEY_NAME)))
		{
			// Maintain consistent menu option order by removing everything then adding according to config
			sharingManager.removeMenuOptions();

			if (config.showImportExport())
			{
				sharingManager.addImportExportMenuOptions();
			}
			if (config.showClear())
			{
				sharingManager.addClearMenuOption();
			}
		}
	}
}
