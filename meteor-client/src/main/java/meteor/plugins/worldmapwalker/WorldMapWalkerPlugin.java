package meteor.plugins.worldmapwalker;

import com.google.inject.Provides;
import meteor.config.ConfigManager;
import meteor.eventbus.Subscribe;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.plugins.api.entities.Players;
import meteor.plugins.api.movement.Movement;
import meteor.plugins.api.widgets.Widgets;
import meteor.ui.overlay.worldmap.WorldMapOverlay;
import net.runelite.api.*;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetInfo;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

@PluginDescriptor(
        name = "World Map Walker",
        description = "Right click anywhere within the World Map to walk there",
        enabledByDefault = true
)
@Singleton
public class WorldMapWalkerPlugin extends Plugin {

    @Inject
    private ScheduledExecutorService executorService;

    @Inject
    private WorldMapOverlay worldMapOverlay;

    @Inject
    private WorldMapWalkerConfig config;

    @Provides
    public WorldMapWalkerConfig getConfig(ConfigManager configManager) {
        return configManager.getConfig(WorldMapWalkerConfig.class);
    }

    private Point lastMenuOpenedPoint;
    private WorldPoint mapPoint;

    @Override
    public void startup() {
    }

    @Override
    public void shutdown() {
    }

    @Subscribe
    public void onGameTick(GameTick event) {
        if (Movement.isWalking()) {
            return;
        }

        if (mapPoint == null || mapPoint.equals(Players.getLocal().getWorldLocation())) {
            mapPoint = null;
            return;
        }

        logger.debug("Destination is {} {}", mapPoint.getX(), mapPoint.getY());
        Movement.walkTo(mapPoint);
    }

    @Subscribe
    public void onMenuOpened(MenuOpened event) { //TODO: Event doesn't work
        lastMenuOpenedPoint = client.getMouseCanvasPosition();
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event) {
        final Widget map = client.getWidget(WidgetInfo.WORLD_MAP_VIEW);

        if (map == null) {
            return;
        }

        if (map.getBounds().contains(client.getMouseCanvasPosition().getX(), client.getMouseCanvasPosition().getY())) {
            addMenuEntry(event, "Map walk here");
            addMenuEntry(event, "Clear destination");
        }
    }

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked e) {
        if (e.getMenuOption().equals("Map walk here")) {
            mapPoint = calculateMapPoint(client.isMenuOpen() ? lastMenuOpenedPoint : client.getMouseCanvasPosition());
            logger.debug("Walking to: {}", mapPoint.toString());
            var mapWidget = Widgets.get(595, 38);

            if (mapWidget != null) {
                Widgets.get(595, 38).interact("Close");
            }
        }

        if (e.getMenuOption().equals("Clear destination")) {
            logger.info("Stopping walking");
            mapPoint = null;
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged e) {
        if (e.getGameState() == GameState.LOGIN_SCREEN) {
            client.setUsername("");
            client.setPassword("");
        }
    }

    private WorldPoint calculateMapPoint(Point point) {
        float zoom = client.getRenderOverview().getWorldMapZoom();
        RenderOverview renderOverview = client.getRenderOverview();
        final WorldPoint mapPoint = new WorldPoint(renderOverview.getWorldMapPosition().getX(), renderOverview.getWorldMapPosition().getY(), 0);
        final Point middle = worldMapOverlay.mapWorldPointToGraphicsPoint(mapPoint);

        final int dx = (int) ((point.getX() - middle.getX()) / zoom);
        final int dy = (int) ((-(point.getY() - middle.getY())) / zoom);

        return mapPoint.dx(dx).dy(dy);
    }

    private void addMenuEntry(MenuEntryAdded event, String option) {
        List<MenuEntry> entries = new LinkedList<>(Arrays.asList(client.getMenuEntries()));

        MenuEntry entry = new MenuEntry();
        entry.setOption(option);
        entry.setTarget(event.getTarget());
        entry.setOpcode(MenuAction.RUNELITE.getId());
        entries.add(0, entry);

        client.setMenuEntries(entries.toArray(new MenuEntry[0]));
    }
}
