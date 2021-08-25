package meteor.plugins.api.items;

import meteor.plugins.api.game.Game;
import meteor.plugins.api.game.GameThread;
import net.runelite.api.*;
import net.runelite.api.widgets.WidgetInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Inventory {

    public static List<Item> getAll(Predicate<Item> filter) {
        List<Item> items = new ArrayList<>();
        ItemContainer container = Game.getClient().getItemContainer(InventoryID.INVENTORY);
        if (container == null) {
            return items;
        }

        for (Item item : container.getItems()) {
            if (!Game.getClient().isItemDefinitionCached(item.getId())) {
                GameThread.invokeLater(() -> Game.getClient().getItemComposition(item.getId()));
            }

            if (item.getId() != -1 && item.getName() != null && !item.getName().equals("null")) {
                WidgetInfo widgetInfo = WidgetInfo.INVENTORY;
                item.setIdentifier(item.getId());
                item.setWidgetInfo(widgetInfo);
                item.setActionParam(item.getSlot());
                item.setWidgetId(widgetInfo.getId());
                item.setActions(Game.getClient().getItemComposition(item.getId()).getInventoryActions());

                if (filter.test(item)) {
                    items.add(item);
                }
            }
        }

        return items;
    }

    public static List<Item> getAll() {
        return getAll(x -> true);
    }

    public static Item getFirst(Predicate<Item> filter) {
        return getAll(filter).stream().findFirst().orElse(null);
    }

    public static List<Item> getAll(int... ids) {
        return getAll(x -> {
            for (int id : ids) {
                if (id == x.getId()) {
                    return true;
                }
            }

            return false;
        });
    }

    public static List<Item> getAll(String... names) {
        return getAll(x -> {
            if (x.getName() == null) {
                return false;
            }

            for (String name : names) {
                if (name.equals(x.getName())) {
                    return true;
                }
            }

            return false;
        });
    }

    public static Item getFirst(int... ids) {
        return getFirst(x -> {
            for (int id : ids) {
                if (id == x.getId()) {
                    return true;
                }
            }

            return false;
        });
    }

    public static Item getFirst(String... names) {
        return getFirst(x -> {
            if (x.getName() == null) {
                return false;
            }

            for (String name : names) {
                if (name.equals(x.getName())) {
                    return true;
                }
            }

            return false;
        });
    }

    public static boolean contains(Predicate<Item> filter) {
        return getFirst(filter) != null;
    }

    public static boolean contains(int... ids) {
        return contains(x -> {
            for (int id : ids) {
                if (id == x.getId()) {
                    return true;
                }
            }

            return false;
        });
    }

    public static boolean contains(String... names) {
        return contains(x -> {
            if (x.getName() == null) {
                return false;
            }

            for (String name : names) {
                if (name.equals(x.getName())) {
                    return true;
                }
            }

            return false;
        });
    }
}
