/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package meteor.plugins.examine;

import com.google.common.annotations.VisibleForTesting;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.ItemComposition;
import net.runelite.api.ItemID;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.api.widgets.WidgetInfo;
import net.runelite.api.widgets.WidgetItem;
import meteor.chat.ChatColorType;
import meteor.chat.ChatMessageBuilder;
import meteor.chat.ChatMessageManager;
import meteor.chat.QueuedMessage;
import meteor.eventbus.Subscribe;
import meteor.game.ItemManager;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.util.QuantityFormatter;
import meteor.util.Text;

import javax.inject.Inject;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;

import static net.runelite.api.widgets.WidgetInfo.*;

@PluginDescriptor(
	name = "Examine",
	description = "Shows additional examine information (eg. GE Average, HA Value)",
	tags = {"npcs", "items", "inventory", "objects", "prices", "high alchemy"}
)
public class ExaminePlugin extends Plugin {

	private final Deque<PendingExamine> pending = new ArrayDeque<>();

	@Inject
	private Client client;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Subscribe
	public void onGameStateChanged(GameStateChanged event) {
		pending.clear();
	}

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event) {
		if (!Text.removeTags(event.getMenuOption()).equals("Examine")) {
			return;
		}

		ExamineType type;
		int id, quantity = -1;
		switch (event.getMenuAction()) {
			case EXAMINE_ITEM: {
				type = ExamineType.ITEM;
				id = event.getId();

				int widgetId = event.getWidgetId();
				int widgetGroup = TO_GROUP(widgetId);
				int widgetChild = TO_CHILD(widgetId);
				Widget widget = client.getWidget(widgetGroup, widgetChild);
				WidgetItem widgetItem = widget.getWidgetItem(event.getActionParam());
				quantity = widgetItem != null && widgetItem.getId() >= 0 ? widgetItem.getQuantity() : 1;

				// Examine on inventory items with more than 100000 quantity is handled locally and shows the item stack
				// count, instead of sending the examine packet, so that you can see how many items are in the stack.
				// Replace that message with one that formats the quantity using the quantity formatter instead.
				if (quantity >= 100_000) {
					int itemId = event.getId();
					final ChatMessageBuilder message = new ChatMessageBuilder()
							.append(QuantityFormatter.formatNumber(quantity)).append(" x ")
							.append(itemManager.getItemComposition(itemId).getName());
					chatMessageManager.queue(QueuedMessage.builder()
							.type(ChatMessageType.ITEM_EXAMINE)
							.runeLiteFormattedMessage(message.build())
							.build());
					event.consume();
				}
				break;
			}
			case EXAMINE_ITEM_GROUND:
				type = ExamineType.ITEM;
				id = event.getId();
				break;
			case CC_OP_LOW_PRIORITY: {
				type = ExamineType.ITEM_BANK_EQ;
				int[] qi = findItemFromWidget(event.getWidgetId(), event.getActionParam());
				if (qi == null) {
					return;
				}
				quantity = qi[0];
				id = qi[1];
				break;
			}
			case EXAMINE_OBJECT:
				type = ExamineType.OBJECT;
				id = event.getId();
				break;
			case EXAMINE_NPC:
				type = ExamineType.NPC;
				id = event.getId();
				break;
			default:
				return;
		}

		PendingExamine pendingExamine = new PendingExamine();
		pendingExamine.setType(type);
		pendingExamine.setId(id);
		pendingExamine.setQuantity(quantity);
		pendingExamine.setCreated(Instant.now());
		pending.push(pendingExamine);
	}

	@Subscribe
	public void onChatMessage(ChatMessage event) {
		ExamineType type;
		switch (event.getType()) {
			case ITEM_EXAMINE:
				type = ExamineType.ITEM;
				break;
			case OBJECT_EXAMINE:
				type = ExamineType.OBJECT;
				break;
			case NPC_EXAMINE:
				type = ExamineType.NPC;
				break;
			case GAMEMESSAGE:
				type = ExamineType.ITEM_BANK_EQ;
				break;
			default:
				return;
		}

		if (pending.isEmpty()) {
			return;
		}

		PendingExamine pendingExamine = pending.pop();

		if (pendingExamine.getType() != type) {
			logger.debug("Type mismatch for pending examine: {} != {}", pendingExamine.getType(), type);
			pending.clear(); // eh
			return;
		}

		logger.debug("Got examine for {} {}: {}", pendingExamine.getType(), pendingExamine.getId(),
				event.getMessage());

		// If it is an item, show the price of it
		if (pendingExamine.getType() == ExamineType.ITEM
				|| pendingExamine.getType() == ExamineType.ITEM_BANK_EQ) {
			final int itemId = pendingExamine.getId();
			final int itemQuantity = pendingExamine.getQuantity();

			if (itemId == ItemID.COINS_995) {
				return;
			}

			final ItemComposition itemComposition = itemManager.getItemComposition(itemId);
			getItemPrice(itemComposition.getId(), itemComposition, itemQuantity);
		}
	}

	private int[] findItemFromWidget(int widgetId, int actionParam) {
		int widgetGroup = TO_GROUP(widgetId);
		int widgetChild = TO_CHILD(widgetId);
		Widget widget = client.getWidget(widgetGroup, widgetChild);

		if (widget == null) {
			return null;
		}

		if (WidgetInfo.EQUIPMENT.getGroupId() == widgetGroup) {
			Widget widgetItem = widget.getChild(1);
			if (widgetItem != null) {
				return new int[]{widgetItem.getItemQuantity(), widgetItem.getItemId()};
			}
		} else if (WidgetInfo.SMITHING_INVENTORY_ITEMS_CONTAINER.getGroupId() == widgetGroup) {
			Widget widgetItem = widget.getChild(2);
			if (widgetItem != null) {
				return new int[]{widgetItem.getItemQuantity(), widgetItem.getItemId()};
			}
		} else if (WidgetInfo.BANK_INVENTORY_ITEMS_CONTAINER.getGroupId() == widgetGroup
				|| WidgetInfo.RUNE_POUCH_ITEM_CONTAINER.getGroupId() == widgetGroup) {
			Widget widgetItem = widget.getChild(actionParam);
			if (widgetItem != null) {
				return new int[]{widgetItem.getItemQuantity(), widgetItem.getItemId()};
			}
		} else if (WidgetInfo.BANK_ITEM_CONTAINER.getGroupId() == widgetGroup
				|| WidgetInfo.CLUE_SCROLL_REWARD_ITEM_CONTAINER.getGroupId() == widgetGroup
				|| WidgetInfo.LOOTING_BAG_CONTAINER.getGroupId() == widgetGroup
				|| WidgetID.SEED_VAULT_INVENTORY_GROUP_ID == widgetGroup
				|| WidgetID.SEED_BOX_GROUP_ID == widgetGroup
				|| WidgetID.PLAYER_TRADE_SCREEN_GROUP_ID == widgetGroup
				|| WidgetID.PLAYER_TRADE_INVENTORY_GROUP_ID == widgetGroup) {
			Widget widgetItem = widget.getChild(actionParam);
			if (widgetItem != null) {
				return new int[]{widgetItem.getItemQuantity(), widgetItem.getItemId()};
			}
		} else if (WidgetID.SHOP_GROUP_ID == widgetGroup) {
			Widget widgetItem = widget.getChild(actionParam);
			if (widgetItem != null) {
				return new int[]{1, widgetItem.getItemId()};
			}
		} else if (WidgetID.SEED_VAULT_GROUP_ID == widgetGroup) {
			Widget widgetItem = client.getWidget(SEED_VAULT_ITEM_CONTAINER).getChild(actionParam);
			if (widgetItem != null) {
				return new int[]{widgetItem.getItemQuantity(), widgetItem.getItemId()};
			}
		}

		return null;
	}

	@VisibleForTesting
	void getItemPrice(int id, ItemComposition itemComposition, int quantity) {
		// quantity is at least 1
		quantity = Math.max(1, quantity);
		final int gePrice = itemManager.getItemPrice(id);
		final int alchPrice = itemComposition.getHaPrice();

		if (gePrice > 0 || alchPrice > 0) {
			final ChatMessageBuilder message = new ChatMessageBuilder()
					.append(ChatColorType.NORMAL)
					.append("Price of ")
					.append(ChatColorType.HIGHLIGHT);

			if (quantity > 1) {
				message
						.append(QuantityFormatter.formatNumber(quantity))
						.append(" x ");
			}

			message
					.append(itemComposition.getName())
					.append(ChatColorType.NORMAL)
					.append(":");

			if (gePrice > 0) {
				message
						.append(ChatColorType.NORMAL)
						.append(" GE average ")
						.append(ChatColorType.HIGHLIGHT)
						.append(QuantityFormatter.formatNumber((long) gePrice * quantity));

				if (quantity > 1) {
					message
							.append(ChatColorType.NORMAL)
							.append(" (")
							.append(ChatColorType.HIGHLIGHT)
							.append(QuantityFormatter.formatNumber(gePrice))
							.append(ChatColorType.NORMAL)
							.append("ea)");
				}
			}

			if (alchPrice > 0) {
				message
						.append(ChatColorType.NORMAL)
						.append(" HA value ")
						.append(ChatColorType.HIGHLIGHT)
						.append(QuantityFormatter.formatNumber((long) alchPrice * quantity));

				if (quantity > 1) {
					message
							.append(ChatColorType.NORMAL)
							.append(" (")
							.append(ChatColorType.HIGHLIGHT)
							.append(QuantityFormatter.formatNumber(alchPrice))
							.append(ChatColorType.NORMAL)
							.append("ea)");
				}
			}

			chatMessageManager.queue(QueuedMessage.builder()
					.type(ChatMessageType.ITEM_EXAMINE)
					.runeLiteFormattedMessage(message.build())
					.build());
		}
	}
}
