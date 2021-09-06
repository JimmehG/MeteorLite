package meteor.plugins.sockethealing;

import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigItem;
import meteor.config.ConfigSection;
import meteor.config.Range;

import java.awt.*;

@ConfigGroup("sockethealing")
public interface SocketHealingConfig extends Config {

    @ConfigSection(
        name = "Overlays",
        description = "",
        position = 98
    )
    String overlays = "Overlays";

    @ConfigSection(
        name = "General",
        description = "",
        position = 99
    )
    String general = "General";

    @Range(min = -50, max = 50)
    @ConfigItem(
        position = 1,
        keyName = "getIndicatorXOffset",
        name = "Hp Overlay X Offset",
        description = "This is the horizontal offset of the health overlay.",
        section = overlays
    )
    default int getIndicatorXOffset() {
        return -8;
    }

    @Range(min = -50, max = 50)
    @ConfigItem(
        position = 2,
        keyName = "getIndicatorYOffset",
        name = "Hp Overlay Y Offset",
        description = "This is the vertical offset of the health overlay.",
        section = overlays
    )
    default int getIndicatorYOffset() {
        return 5;
    }

    @Range(max = 10, min = -20)
    @ConfigItem(
        position = 3,
        keyName = "overlayOffset",
        name = "Player Overlay Height",
        description = "This adjusts the height of the health overlay.",
        section = overlays
    )
    default int overlayOffset() {
        return -15;
    }

    @Range(max = 20, min = 1)
    @ConfigItem(
        position = 4,
        keyName = "refreshRate",
        name = "Socket Refresh Rate",
        description = "This is how many ticks you would like in-between updating the information.",
        section = general
    )
    default int refreshRate() {
        return 5;
    }

    @ConfigItem(
        name = "Font Type",
        keyName = "healingFontType",
        description = "Dynamically change the font for all health overlays",
        position = 5,
        section = overlays
    )
    default SocketFontType healingFontType() {
        return SocketFontType.CUSTOM;
    }

    @Range(max = 20, min = 10)
    @ConfigItem(
        position = 6,
        keyName = "fontSize",
        name = "Font Size",
        description = "Shows total damage done to a boss",
        section = overlays
    )
    default int fontSize() { return 12; }

    @Range(max = 100, min = 50)
    @ConfigItem(
        position = 7,
        keyName = "greenZone",
        name = "High Hp",
        description = "Sets the bottom amount for the health to be displayed as high hp. (Max 100, Min 50)",
        section = overlays
    )
    default int greenZone() {
        return 75;
    }

    @ConfigItem(
        position = 8,
        keyName = "greenZoneColor",
        name = "High Hp Color",
        description = "Sets the color the high hp is set to",
        section = overlays
    )
    default Color greenZoneColor() {
        return Color.GREEN;
    }

    @Range(max = 75, min = 25)
    @ConfigItem(
        position = 9,
        keyName = "orangeZone",
        name = "Middle Hp",
        description = "Sets the bottom amount for the health to be displayed as middle hp. (Max 75, Min 25)",
        section = overlays
    )
    default int orangeZone() {
        return 50;
    }

    @ConfigItem(
        position = 10,
        keyName = "orangeZoneColor",
        name = "Middle Hp Color",
        description = "Sets the color the middle hp range is set to",
        section = overlays
    )
    default Color orangeZoneColor() {
        return Color.ORANGE;
    }

    @ConfigItem(
        position = 11,
        keyName = "redZoneColor",
        name = "Low Hp Color",
        description = "Sets the color the lower hp range is set to",
        section = overlays
    )
    default Color redZoneColor() { return Color.RED; }

    @Range(max = 255, min = 0)
    @ConfigItem(
        position = 12,
        keyName = "opacity",
        name = "Highlight Opacity",
        description = "Sets the opacity for the highlight styles. (Max 255, Min 0)",
        section = overlays
    )
    default int opacity() {
        return 255;
    }

    @Range(max = 5, min = 1)
    @ConfigItem(
        position = 13,
        keyName = "hpThiCC",
        name = "Highlight Width",
        description = "Sets the width for the highlight styles. (Max 5, Min 1)",
        section = overlays
    )
    default int hpThiCC() {
        return 2;
    }

    @ConfigItem(
        position = 14,
        keyName = "highlightHull",
        name = "Highlight Hull",
        description = "Configures whether or not selected players should be highlighted by hull",
        section = overlays
    )
    default boolean highlightHull() {
        return true;
    }

    @ConfigItem(
        position = 15,
        keyName = "highlightOutline",
        name = "Highlight Outline",
        description = "Configures whether or not selected players outlines should be highlighted",
        section = overlays
    )
    default boolean highlightOutline() {
        return false;
    }

    @Range(max = 4, min = 0)
    @ConfigItem(
        position = 16,
        keyName = "glow",
        name = "Outline Glow",
        description = "Sets the glow for the outline highlight style. (Max 4, Min 0)",
        section = overlays
    )
    default int glow() {
        return 4;
    }

    @ConfigItem(
        position = 17,
        keyName = "displayHealth",
        name = "Display Health On All Players*",
        description = "Turns off the hp displayed. Will still allow you to highlight custom players.",
        section = general
    )
    default boolean displayHealth() {
        return true;
    }

    @ConfigItem(
        position = 18,
        keyName = "highlightPlayerNames",
        name = "Highlighted Player Names",
        description = "Names listed here will be added to hull highlight list",
        section = general
    )
    default String highlightedPlayerNames() { return ""; }

    @ConfigItem(
        position = 19,
        keyName = "hpPlayerNames",
        name = "Display Health On Players",
        description = "Names listed here will be added to the display hp list",
        section = general
    )
    default String hpPlayerNames() { return ""; }

    public enum SocketFontType {
        REGULAR, BOLD, SMALL, CUSTOM;
    }
}
