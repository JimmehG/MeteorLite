package meteor.plugins.menuentryswapper;

import static com.google.common.base.Predicates.alwaysTrue;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import javax.inject.Inject;
import lombok.Getter;
import meteor.PluginManager;
import meteor.callback.ClientThread;
import meteor.config.ConfigManager;
import meteor.eventbus.Subscribe;
import meteor.eventbus.events.ConfigChanged;
import meteor.game.ItemManager;
import meteor.game.ItemVariationMapping;
import meteor.input.KeyManager;
import meteor.menus.MenuManager;
import meteor.menus.WidgetMenuOption;
import meteor.plugins.menuentryswapper.util.AbstractComparableEntry;
import meteor.plugins.menuentryswapper.util.BurningAmuletMode;
import meteor.plugins.menuentryswapper.util.CombatBraceletMode;
import meteor.plugins.menuentryswapper.util.ConstructionCapeMode;
import meteor.plugins.menuentryswapper.util.CraftingCapeMode;
import meteor.plugins.menuentryswapper.util.DigsitePendantMode;
import meteor.plugins.menuentryswapper.util.DuelingRingMode;
import meteor.plugins.menuentryswapper.util.GamesNecklaceMode;
import meteor.plugins.menuentryswapper.util.GloryMode;
import meteor.plugins.menuentryswapper.util.MagicCapeMode;
import meteor.plugins.menuentryswapper.util.MaxCapeEquippedMode;
import meteor.plugins.menuentryswapper.util.NecklaceOfPassageMode;
import meteor.plugins.menuentryswapper.util.RingOfWealthMode;
import meteor.plugins.menuentryswapper.util.SkillsNecklaceMode;
import meteor.plugins.menuentryswapper.util.Swap;
import meteor.plugins.menuentryswapper.util.XericsTalismanMode;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.ItemComposition;
import net.runelite.api.KeyCode;
import net.runelite.api.MenuAction;
import net.runelite.api.MenuEntry;
import net.runelite.api.NPC;
import net.runelite.api.Player;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.api.events.MenuOpened;
import net.runelite.api.events.MenuOptionClicked;
import net.runelite.api.events.PostItemComposition;
import net.runelite.api.events.WidgetMenuOptionClicked;
import net.runelite.api.util.Text;
import net.runelite.api.widgets.WidgetInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

public class ExtendedSwaps {
  private MenuEntrySwapperConfig config;
  @Inject
  private Client client;

  @Inject
  private ClientThread clientThread;

  @Inject
  public ExtendedSwaps(MenuEntrySwapperConfig config) {
    this.config = config;
  }

  @Inject
  private PluginManager pluginManager;

  @Inject
  private ConfigManager configManager;

  @Inject
  private ItemManager itemManager;

  @Inject
  private MenuManager menuManager;

  @Inject
  private KeyManager keyManager;

  @Getter
  private boolean configuringShiftClick = false;

  private final Map<AbstractComparableEntry, Integer> customSwaps = new HashMap<>();
  private final List<Pair<AbstractComparableEntry, AbstractComparableEntry>> prioSwaps = new ArrayList<>();

  private static <T extends Comparable<? super T>> void sortedInsert(List<T> list,
      T value) // NOPMD: UnusedPrivateMethod: false positive
  {
    int idx = Collections.binarySearch(list, value);
    list.add(idx < 0 ? -idx - 1 : idx, value);
  }

  private MenuEntry[] menuEntries;
  List<String> targetList;
  List<String> optionsList;
  private boolean inTobRaid = false;
  private boolean inCoxRaid = false;
  private static final String CONFIGURE = "Configure";
  private static final String SAVE = "Save";
  private static final String RESET = "Reset";
  private static final String MENU_TARGET = "Shift-click";

  private static final String SHIFTCLICK_CONFIG_GROUP = "shiftclick";
  private static final String ITEM_KEY_PREFIX = "item_";

  private static final WidgetMenuOption FIXED_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(
      CONFIGURE,
      MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB);

  private static final WidgetMenuOption FIXED_INVENTORY_TAB_SAVE = new WidgetMenuOption(SAVE,
      MENU_TARGET, WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB);

  private static final WidgetMenuOption RESIZABLE_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(
      CONFIGURE,
      MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB);

  private static final WidgetMenuOption RESIZABLE_INVENTORY_TAB_SAVE = new WidgetMenuOption(SAVE,
      MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB);

  private static final WidgetMenuOption RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE = new WidgetMenuOption(
      CONFIGURE,
      MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);

  private static final WidgetMenuOption RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE = new WidgetMenuOption(
      SAVE,
      MENU_TARGET, WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB);

  private static final Set<MenuAction> ITEM_MENU_TYPES = ImmutableSet.of(
      MenuAction.ITEM_FIRST_OPTION,
      MenuAction.ITEM_SECOND_OPTION,
      MenuAction.ITEM_THIRD_OPTION,
      MenuAction.ITEM_FOURTH_OPTION,
      MenuAction.ITEM_FIFTH_OPTION,
      MenuAction.EXAMINE_ITEM,
      MenuAction.ITEM_USE
  );

  private static final Set<MenuAction> NPC_MENU_TYPES = ImmutableSet.of(
      MenuAction.NPC_FIRST_OPTION,
      MenuAction.NPC_SECOND_OPTION,
      MenuAction.NPC_THIRD_OPTION,
      MenuAction.NPC_FOURTH_OPTION,
      MenuAction.NPC_FIFTH_OPTION,
      MenuAction.EXAMINE_NPC);

  private final Multimap<String, Swap> swaps = Multimaps
      .synchronizedSetMultimap(LinkedHashMultimap.create());
  private final ArrayListMultimap<String, Integer> optionIndexes = ArrayListMultimap.create();



  public void startUp()
  {
    if (client.getGameState() != GameState.LOGGED_IN)
    {
      return;
    }
    loadSwaps();
  }

  @Subscribe
  private void onGameStateChanged(GameStateChanged event) {
    if (event.getGameState() != GameState.LOGGED_IN) {
      return;
    }
    loadSwaps();
  }

  public void shutDown()
  {
    swaps.clear();
  }

  public Swap swap(String option, Predicate<String> targetPredicate, String swappedOption,
      Supplier<Boolean> enabled)
  {
    Swap swap = new Swap(alwaysTrue(), targetPredicate, swappedOption, enabled, true);
    swaps.put(option, swap);
    return swap;
  }

  public Swap swapContains(String option, Predicate<String> targetPredicate, String swappedOption,
      Supplier<Boolean> enabled)
  {
    Swap swap = new Swap(alwaysTrue(), targetPredicate, swappedOption, enabled, false);
    swaps.put(option, swap);
    return swap;
  }

  @Subscribe
  public void onConfigChanged(ConfigChanged event)
  {
    if (event.getGroup().equals("menuentryswapperextended") && event.getKey()
        .equals("shiftClickCustomization"))
    {
      if (event.getGroup().equals(SHIFTCLICK_CONFIG_GROUP) && event.getKey()
          .startsWith(ITEM_KEY_PREFIX))
      {
        clientThread.invoke(this::resetItemCompositionCache);
      }
      if (!"menuentryswapper".equals(event.getGroup()))
      {
        return;
      }
    }
    loadSwaps();
  }


  private void resetItemCompositionCache()
  {
    itemManager.invalidateItemCompositionCache();
    client.getItemCompositionCache().reset();
  }

  private Integer getSwapConfig(int itemId)
  {
    itemId = ItemVariationMapping.map(itemId);
    String config = configManager
        .getConfiguration(SHIFTCLICK_CONFIG_GROUP, ITEM_KEY_PREFIX + itemId);
    if (config == null || config.isEmpty())
    {
      return null;
    }

    return Integer.parseInt(config);
  }

  private void setSwapConfig(int itemId, int index)
  {
    itemId = ItemVariationMapping.map(itemId);
    configManager.setConfiguration(SHIFTCLICK_CONFIG_GROUP, ITEM_KEY_PREFIX + itemId, index);
  }

  private void unsetSwapConfig(int itemId)
  {
    itemId = ItemVariationMapping.map(itemId);
    configManager.unsetConfiguration(SHIFTCLICK_CONFIG_GROUP, ITEM_KEY_PREFIX + itemId);
  }

  @Subscribe
  public void onWidgetMenuOptionClicked(WidgetMenuOptionClicked event)
  {
    if (event.getWidget() == WidgetInfo.FIXED_VIEWPORT_INVENTORY_TAB
        || event.getWidget() == WidgetInfo.RESIZABLE_VIEWPORT_INVENTORY_TAB
        || event.getWidget() == WidgetInfo.RESIZABLE_VIEWPORT_BOTTOM_LINE_INVENTORY_TAB)
    {
      configuringShiftClick =
          event.getMenuOption().equals(CONFIGURE) && Text.removeTags(event.getMenuTarget())
              .equals(MENU_TARGET);
      refreshShiftClickCustomizationMenus();
    }
  }

  @Subscribe
  public void onMenuOpened(MenuOpened event)
  {
    if (!configuringShiftClick)
    {
      return;
    }

    MenuEntry firstEntry = event.getFirstEntry();
    if (firstEntry == null)
    {
      return;
    }

    int widgetId = firstEntry.getParam1();
    if (widgetId != WidgetInfo.INVENTORY.getPackedId())
    {
      return;
    }

    int itemId = firstEntry.getIdentifier();
    if (itemId == -1)
    {
      return;
    }

    ItemComposition itemComposition = itemManager.getItemComposition(itemId);
    MenuAction shiftClickAction = MenuAction.ITEM_USE;
    final int shiftClickActionIndex = itemComposition.getShiftClickActionIndex();

    if (shiftClickActionIndex >= 0)
    {
      shiftClickAction = MenuAction
          .of(MenuAction.ITEM_FIRST_OPTION.getId() + shiftClickActionIndex);
    }

    MenuEntry[] entries = event.getMenuEntries();

    for (MenuEntry entry : entries)
    {
      final MenuAction menuAction = MenuAction.of(entry.getType());

      if (ITEM_MENU_TYPES.contains(menuAction) && entry.getIdentifier() == itemId)
      {
        entry.setType(MenuAction.RUNELITE.getId());

        if (shiftClickAction == menuAction)
        {
          entry.setOption("* " + entry.getOption());
        }
      }
    }

    final MenuEntry resetShiftClickEntry = new MenuEntry();
    resetShiftClickEntry.setOption(RESET);
    resetShiftClickEntry.setTarget(MENU_TARGET);
    resetShiftClickEntry.setIdentifier(itemId);
    resetShiftClickEntry.setParam1(widgetId);
    resetShiftClickEntry.setType(MenuAction.RUNELITE.getId());
    client.setMenuEntries(ArrayUtils.addAll(entries, resetShiftClickEntry));

    Player localPlayer = client.getLocalPlayer();

    if (localPlayer == null)
    {
      return;
    }

  }

  @Subscribe
  public void onMenuEntryAdded(MenuEntryAdded menuEntryAdded)
  {

    menuEntries = client.getMenuEntries();
    swapConstructionMenu(menuEntries);

    if (!config.getEasyConstruction())
    {
      return;
    }

    if ((client.getVarbitValue(2176) != 1)
        && menuEntryAdded.getOpcode() != MenuAction.GAME_OBJECT_FIFTH_OPTION.getId())
    {
      return;
    }
  }

  @Subscribe
  public void onMenuOptionClicked(MenuOptionClicked event)
  {
    if (event.getMenuAction() != MenuAction.RUNELITE || event.getWidgetId() != WidgetInfo.INVENTORY
        .getPackedId())
    {
      return;
    }

    int itemId = event.getId();

    if (itemId == -1)
    {
      return;
    }

    String option = event.getMenuOption();
    String target = event.getMenuTarget();
    ItemComposition itemComposition = itemManager.getItemComposition(itemId);

    if (option.equals(RESET) && target.equals(MENU_TARGET))
    {
      unsetSwapConfig(itemId);
      return;
    }

    if (!itemComposition.getName().equals(Text.removeTags(target)))
    {
      return;
    }

    if (option.equals("Use")) //because "Use" is not in inventoryActions
    {
      setSwapConfig(itemId, -1);
    }
    else
    {
      String[] inventoryActions = itemComposition.getInventoryActions();

      for (int index = 0; index < inventoryActions.length; index++)
      {
        if (option.equals(inventoryActions[index]))
        {
          setSwapConfig(itemId, index);
          break;
        }
      }
    }
  }

  private void swapMenuEntry(int index, MenuEntry menuEntry)
  {
    final int eventId = menuEntry.getIdentifier();
    final MenuAction menuAction = MenuAction.of(menuEntry.getType());
    final String option = Text.removeTags(menuEntry.getOption()).toLowerCase();
    final String target = Text.removeTags(menuEntry.getTarget()).toLowerCase();
    final NPC hintArrowNpc = client.getHintArrowNpc();

    if (hintArrowNpc != null
        && hintArrowNpc.getIndex() == eventId
        && NPC_MENU_TYPES.contains(menuAction))
    {
      return;
    }

    if (shiftModifier() && (menuAction == MenuAction.ITEM_FIRST_OPTION
        || menuAction == MenuAction.ITEM_SECOND_OPTION
        || menuAction == MenuAction.ITEM_THIRD_OPTION
        || menuAction == MenuAction.ITEM_FOURTH_OPTION
        || menuAction == MenuAction.ITEM_FIFTH_OPTION
        || menuAction == MenuAction.ITEM_USE))
    {
      return;
    }

    Collection<Swap> swaps = this.swaps.get(option);
    for (Swap swap : swaps)
    {
      if (swap.getTargetPredicate().test(target) && swap.getEnabled().get())
      {
        if (swap(swap.getSwappedOption(), target, index, swap.isStrict()))
        {
          break;
        }
      }
    }
  }

  @Subscribe
  public void onClientTick(ClientTick clientTick)
  {
    // The menu is not rebuilt when it is open, so don't swap or else it will
    // repeatedly swap entries
    if (client.getGameState() == GameState.LOGGED_IN && !client.isMenuOpen())
    {
      MenuEntry[] menuEntries = client.getMenuEntries();

      // Build option map for quick lookup in findIndex
      int idx = 0;
      optionIndexes.clear();
      for (MenuEntry entry : menuEntries)
      {
        String option = Text.removeTags(entry.getOption()).toLowerCase();
        optionIndexes.put(option, idx++);
      }

      // Perform swaps
      idx = 0;
      for (MenuEntry entry : menuEntries)
      {
        swapMenuEntry(idx++, entry);
      }
    }
  }

  @Subscribe
  public void onPostItemComposition(PostItemComposition event)
  {
    ItemComposition itemComposition = event.getItemComposition();
    Integer option = getSwapConfig(itemComposition.getId());

    if (option != null)
    {
      itemComposition.setShiftClickActionIndex(option);
    }
  }

  private boolean swap(String option, String target, int index, boolean strict)
  {
    MenuEntry[] menuEntries = client.getMenuEntries();

    // find option to swap with
    int optionIdx = findIndex(menuEntries, index, option, target, strict);

    if (optionIdx >= 0)
    {
      swap(optionIndexes, menuEntries, optionIdx, index);
      return true;
    }

    return false;
  }

  private int findIndex(MenuEntry[] entries, int limit, String option, String target,
      boolean strict)
  {
    if (strict)
    {
      List<Integer> indexes = optionIndexes.get(option);

      // We want the last index which matches the target, as that is what is top-most
      // on the menu
      for (int i = indexes.size() - 1; i >= 0; --i)
      {
        int idx = indexes.get(i);
        MenuEntry entry = entries[idx];
        String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase();

        // Limit to the last index which is prior to the current entry
        if (idx < limit && entryTarget.equals(target))
        {
          return idx;
        }
      }
    }
    else
    {
      // Without strict matching we have to iterate all entries up to the current limit...
      for (int i = limit - 1; i >= 0; i--)
      {
        MenuEntry entry = entries[i];
        String entryOption = Text.removeTags(entry.getOption()).toLowerCase();
        String entryTarget = Text.removeTags(entry.getTarget()).toLowerCase();

        if (entryOption.contains(option.toLowerCase()) && entryTarget.equals(target))
        {
          return i;
        }
      }

    }

    return -1;
  }

  private void swap(ArrayListMultimap<String, Integer> optionIndexes, MenuEntry[] entries,
      int index1, int index2)
  {
    MenuEntry entry1 = entries[index1],
        entry2 = entries[index2];

    int temp = entry1.getType();
    entry1.setType(entry2.getType());
    entry2.setType(temp);

    entries[index1] = entry2;
    entries[index2] = entry1;

    client.setMenuEntries(entries);

    // Update optionIndexes
    String option1 = Text.removeTags(entry1.getOption()).toLowerCase(),
        option2 = Text.removeTags(entry2.getOption()).toLowerCase();

    List<Integer> list1 = optionIndexes.get(option1),
        list2 = optionIndexes.get(option2);

    // call remove(Object) instead of remove(int)
    list1.remove((Integer) index1);
    list2.remove((Integer) index2);

    sortedInsert(list1, index2);
    sortedInsert(list2, index1);
  }

  private void removeShiftClickCustomizationMenus()
  {
    menuManager.removeManagedCustomMenu(FIXED_INVENTORY_TAB_CONFIGURE);
    menuManager.removeManagedCustomMenu(FIXED_INVENTORY_TAB_SAVE);
    menuManager.removeManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE);
    menuManager.removeManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE);
    menuManager.removeManagedCustomMenu(RESIZABLE_INVENTORY_TAB_CONFIGURE);
    menuManager.removeManagedCustomMenu(RESIZABLE_INVENTORY_TAB_SAVE);
  }

  private void refreshShiftClickCustomizationMenus()
  {
    removeShiftClickCustomizationMenus();
    if (configuringShiftClick)
    {
      menuManager.addManagedCustomMenu(FIXED_INVENTORY_TAB_SAVE);
      menuManager.addManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_SAVE);
      menuManager.addManagedCustomMenu(RESIZABLE_INVENTORY_TAB_SAVE);
    }
    else
    {
      menuManager.addManagedCustomMenu(FIXED_INVENTORY_TAB_CONFIGURE);
      menuManager.addManagedCustomMenu(RESIZABLE_BOTTOM_LINE_INVENTORY_TAB_CONFIGURE);
      menuManager.addManagedCustomMenu(RESIZABLE_INVENTORY_TAB_CONFIGURE);
    }
  }

  private boolean shiftModifier()
  {
    return client.isKeyPressed(KeyCode.KC_SHIFT);
  }

  private Predicate<String> targetSwap(String string)
  {
    return (in) -> in.toLowerCase().contains(string);
  }

  private void addSwaps()
  {

    for (String option : new String[]{"attack", "talk-to"})
    {
      swapContains(option, (s) -> true, "pickpocket", config::swapPickpocket);
    }
    swap("remove", targetSwap("burning amulet"), "chaos temple", () ->
        config.getBurningAmulet() && config.getBurningAmuletMode() == BurningAmuletMode.CHAOS_TEMPLE);
    swap("remove", targetSwap("burning amulet"), "bandit camp", () ->
        config.getBurningAmulet() && config.getBurningAmuletMode() == BurningAmuletMode.BANDIT_CAMP);
    swap("remove", targetSwap("burning amulet"), "lava maze", () ->
        config.getBurningAmulet() && config.getBurningAmuletMode() == BurningAmuletMode.LAVA_MAZE);

    swap("remove", targetSwap("combat bracelet"), "warriors' guild", () ->
        config.getCombatBracelet() && config.getCombatBraceletMode() == CombatBraceletMode.WARRIORS_GUILD);
    swap("remove", targetSwap("combat bracelet"), "champions' guild", () ->
        config.getCombatBracelet() && config.getCombatBraceletMode() == CombatBraceletMode.CHAMPIONS_GUILD);
    swap("remove", targetSwap("combat bracelet"), "edgeville monastery", () ->
        config.getCombatBracelet() && config.getCombatBraceletMode() == CombatBraceletMode.EDGEVILLE_MONASTERY);
    swap("remove", targetSwap("combat bracelet"), "ranging guild", () ->
        config.getCombatBracelet() && config.getCombatBraceletMode() == CombatBraceletMode.RANGING_GUILD);

    swap("remove", targetSwap("games necklace"), "burthorpe", () ->
        config.getGamesNecklace() && config.getGamesNecklaceMode() == GamesNecklaceMode.BURTHORPE);
    swap("remove", targetSwap("games necklace"), "barbarian outpost", () ->
        config.getGamesNecklace() && config.getGamesNecklaceMode() == GamesNecklaceMode.BARBARIAN_OUTPOST);
    swap("remove", targetSwap("games necklace"), "corporeal beast", () ->
        config.getGamesNecklace() && config.getGamesNecklaceMode() == GamesNecklaceMode.CORPOREAL_BEAST);
    swap("remove", targetSwap("games necklace"), "tears of guthix", () ->
        config.getGamesNecklace() && config.getGamesNecklaceMode() == GamesNecklaceMode.TEARS_OF_GUTHIX);
    swap("remove", targetSwap("games necklace"), "wintertodt camp", () ->
        config.getGamesNecklace() && config.getGamesNecklaceMode() == GamesNecklaceMode.WINTER);

    swap("remove", targetSwap("ring of dueling"), "duel arena", () ->
        config.getDuelingRing() && config.getDuelingRingMode() == DuelingRingMode.DUEL_ARENA);
    swap("remove", targetSwap("ring of dueling"), "castle wars", () ->
        config.getDuelingRing() && config.getDuelingRingMode() == DuelingRingMode.CASTLE_WARS);
    swap("remove", targetSwap("ring of dueling"), "ferox enclave", () ->
        config.getDuelingRing() && config.getDuelingRingMode() == DuelingRingMode.FEROX_ENCLAVE);

    swap("remove", targetSwap("amulet of glory"), "edgeville", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.EDGEVILLE);
    swap("remove", targetSwap("amulet of glory"), "karamja", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.KARAMJA);
    swap("remove", targetSwap("amulet of glory"), "al kharid", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.AL_KHARID);
    swap("remove", targetSwap("amulet of glory"), "draynor village", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.DRAYNOR_VILLAGE);
    swap("remove", targetSwap("amulet of eternal glory"), "edgeville", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.EDGEVILLE);
    swap("remove", targetSwap("amulet of eternal glory"), "karamja", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.KARAMJA);
    swap("remove", targetSwap("amulet of eternal glory"), "al kharid", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.AL_KHARID);
    swap("remove", targetSwap("amulet of eternal glory"), "draynor village", () ->
        config.getGlory() && config.getGloryMode() == GloryMode.DRAYNOR_VILLAGE);

    swap("remove", targetSwap("skills necklace"), "fishing guild", () ->
        config.getSkillsNecklace() && config.getSkillsNecklaceMode() == SkillsNecklaceMode.FISHING_GUILD);
    swap("remove", targetSwap("skills necklace"), "mining guild", () ->
        config.getSkillsNecklace() && config.getSkillsNecklaceMode() == SkillsNecklaceMode.MINING_GUILD);
    swap("remove", targetSwap("skills necklace"), "farming guild", () ->
        config.getSkillsNecklace() && config.getSkillsNecklaceMode() == SkillsNecklaceMode.FARMING_GUILD);
    swap("remove", targetSwap("skills necklace"), "cooking guild", () ->
        config.getSkillsNecklace() && config.getSkillsNecklaceMode() == SkillsNecklaceMode.COOKING_GUILD);
    swap("remove", targetSwap("skills necklace"), "woodcutting guild", () ->
        config.getSkillsNecklace() & config.getSkillsNecklaceMode() == SkillsNecklaceMode.WOODCUTTING_GUILD);
    swap("remove", targetSwap("skills necklace"), "crafting guild", () ->
        config.getSkillsNecklace() && config.getSkillsNecklaceMode() == SkillsNecklaceMode.CRAFTING_GUILD);

    swap("remove", targetSwap("necklace of passage"), "wizards' tower", () ->
        config.getNecklaceofPassage() && config.getNecklaceofPassageMode() == NecklaceOfPassageMode.WIZARDS_TOWER);
    swap("remove", targetSwap("necklace of passage"), "the outpost", () ->
        config.getNecklaceofPassage() && config.getNecklaceofPassageMode() == NecklaceOfPassageMode.THE_OUTPOST);
    swap("remove", targetSwap("necklace of passage"), "eagles' eyrie", () ->
        config.getNecklaceofPassage() && config.getNecklaceofPassageMode() == NecklaceOfPassageMode.EAGLES_EYRIE);

    swap("remove", targetSwap("digsite pendant"), "digsite", () ->
        config.getDigsitePendant() && config.getDigsitePendantMode() == DigsitePendantMode.DIGSITE);
    swap("remove", targetSwap("digsite pendant"), "fossil island", () ->
        config.getDigsitePendant() && config.getDigsitePendantMode() == DigsitePendantMode.FOSSIL_ISLAND);
    swap("remove", targetSwap("digsite pendant"), "lithkren dungeon", () ->
        config.getDigsitePendant() && config.getDigsitePendantMode() == DigsitePendantMode.LITHKREN);

    swap("remove", targetSwap("ring of wealth"), "miscellania", () ->
        config.getRingofWealth() && config.getRingofWealthMode() == RingOfWealthMode.MISCELLANIA);
    swap("remove", targetSwap("ring of wealth"), "grand exchange", () ->
        config.getRingofWealth() && config.getRingofWealthMode() == RingOfWealthMode.GRAND_EXCHANGE);
    swap("remove", targetSwap("ring of wealth"), "falador", () ->
        config.getRingofWealth() && config.getRingofWealthMode() == RingOfWealthMode.FALADOR);
    swap("remove", targetSwap("ring of wealth"), "dondakan", () ->
        config.getRingofWealth() && config.getRingofWealthMode() == RingOfWealthMode.DONDAKAN);

    swap("remove", targetSwap("talisman"), "xeric's glade", () ->
        config.getXericsTalisman() && config.getXericsTalismanMode() == XericsTalismanMode.XERICS_GLADE);
    swap("remove", targetSwap("talisman"), "xeric's lookout", () ->
        config.getXericsTalisman() && config.getXericsTalismanMode() == XericsTalismanMode.XERICS_LOOKOUT);
    swap("remove", targetSwap("talisman"), "xeric's inferno", () ->
        config.getXericsTalisman() && config.getXericsTalismanMode() == XericsTalismanMode.XERICS_INFERNO);
    swap("remove", targetSwap("talisman"), "xeric's heart", () ->
        config.getXericsTalisman() && config.getXericsTalismanMode() == XericsTalismanMode.XERICS_HEART);
    swap("remove", targetSwap("talisman"), "xeric's honour", () ->
        config.getXericsTalisman() && config.getXericsTalismanMode() == XericsTalismanMode.XERICS_HONOUR);

    swap("wear", targetSwap("crafting cape"), "teleport", () ->
        config.getCraftingCapeMode() == CraftingCapeMode.INVENTORY || config.getCraftingCapeMode() == CraftingCapeMode.ALWAYS);
    swap("remove", targetSwap("crafting cape"), "teleport", () ->
        config.getCraftingCapeMode() == CraftingCapeMode.EQUIPPED || config.getCraftingCapeMode() == CraftingCapeMode.ALWAYS);

    swap("wear", targetSwap("construct."), "tele to poh", () ->
        config.getConstructionCapeMode() == ConstructionCapeMode.INVENTORY || config.getConstructionCapeMode() == ConstructionCapeMode.ALWAYS);
    swap("remove", targetSwap("construct."), "tele to poh", () ->
        config.getConstructionCapeMode() == ConstructionCapeMode.EQUIPPED || config.getConstructionCapeMode() == ConstructionCapeMode.ALWAYS);

    swap("wear", targetSwap("magic cape"), "spellbook", () ->
        config.getMagicCapeMode() == MagicCapeMode.INVENTORY || config.getMagicCapeMode() == MagicCapeMode.ALWAYS);
    swap("remove", targetSwap("magic cape"), "spellbook", () ->
        config.getMagicCapeMode() == MagicCapeMode.EQUIPPED || config.getMagicCapeMode() == MagicCapeMode.ALWAYS);

    swap("remove", targetSwap("max cape"), "tele to poh", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.TELE_TO_POH);
    swap("remove", targetSwap("max cape"), "crafting guild", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.CRAFTING_GUILD);
    swap("remove", targetSwap("max cape"), "warriors' guild", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.WARRIORS_GUILD);
    swap("remove", targetSwap("max cape"), "fishing teleports", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.FISHING_TELEPORTS);
    swap("remove", targetSwap("max cape"), "poh portals", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.POH_PORTRALS);
    swap("remove", targetSwap("max cape"), "other teleports", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.OTHER_TELEPORTS);
    swap("remove", targetSwap("max cape"), "spellbook", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.SPELLBOOK);
    swap("remove", targetSwap("max cape"), "features", () ->
        config.getMaxCapeEquippedMode() == MaxCapeEquippedMode.FEATURES);
  }

  private void loadSwaps()
  {
    addSwaps();
    loadConstructionItems();
  }

  private void loadConstructionItems()
  {
    targetList = config.getConstructionMode().getTargetList();
    optionsList = config.getConstructionMode().getOptionsList();
  }

  private void swapConstructionMenu(MenuEntry[] menuEntries)
  {
    for (MenuEntry menuEntry : menuEntries)
    {
      if (validConstructionSwap(menuEntry))
      {
        createConstructionMenu(menuEntry);
      }
    }
  }

  public boolean validConstructionSwap(MenuEntry menuEntry)
  {
    return (matchesConstructionOption(menuEntry) && matchesConstructionTarget(menuEntry));
  }

  public boolean matchesConstructionOption(MenuEntry menuEntry)
  {
    return config.getConstructionMode().getOptionsList().stream()
        .anyMatch(Text.standardize(menuEntry.getOption())::contains);
  }

  public boolean matchesConstructionTarget(MenuEntry menuEntry)
  {
    return config.getConstructionMode().getTargetList().stream()
        .anyMatch(Text.standardize(menuEntry.getTarget())::contains);
  }

  private void createConstructionMenu(MenuEntry menuEntry)
  {
    MenuEntry[] newEntries = new MenuEntry[1];

    newEntries[0] = menuEntry;

    client.setMenuEntries(newEntries);
  }
}