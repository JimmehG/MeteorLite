/*
 * Copyright (c) 2017, Robin <robin.weymans@gmail.com>
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
package meteor.plugins.implings;

import java.awt.Color;
import meteor.config.Alpha;
import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigItem;
import meteor.config.ConfigSection;

/**
 * @author robin
 */
@ConfigGroup("implings")
public interface ImplingsConfig extends Config {

  @ConfigSection(
      name = "Impling Type Settings",
      description = "Configuration for each type of impling",
      position = 99
  )
  String implingSection = "implings";

  @ConfigItem(
      position = 1,
      keyName = "showbaby",
      name = "Baby implings",
      description = "Configures whether or not Baby impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showBaby() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 2,
      keyName = "babyColor",
      name = "Baby impling color",
      description = "Text color for Baby implings",
      section = implingSection
  )
  default Color getBabyColor() {
    return new Color(177, 143, 179);
  }

  @ConfigItem(
      position = 3,
      keyName = "showyoung",
      name = "Young implings",
      description = "Configures whether or not Young impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showYoung() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 4,
      keyName = "youngColor",
      name = "Young impling color",
      description = "Text color for Young implings",
      section = implingSection
  )
  default Color getYoungColor() {
    return new Color(175, 164, 136);
  }

  @ConfigItem(
      position = 5,
      keyName = "showgourmet",
      name = "Gourmet implings",
      description = "Configures whether or not Gourmet impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showGourmet() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 6,
      keyName = "gourmetColor",
      name = "Gourmet impling color",
      description = "Text color for Gourmet implings",
      section = implingSection
  )
  default Color getGourmetColor() {
    return new Color(169, 131, 98);
  }

  @ConfigItem(
      position = 7,
      keyName = "showearth",
      name = "Earth implings",
      description = "Configures whether or not Earth impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showEarth() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 8,
      keyName = "earthColor",
      name = "Earth impling color",
      description = "Text color for Earth implings",
      section = implingSection
  )
  default Color getEarthColor() {
    return new Color(62, 86, 64);
  }

  @ConfigItem(
      position = 9,
      keyName = "showessence",
      name = "Essence implings",
      description = "Configures whether or not Essence impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showEssence() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 10,
      keyName = "essenceColor",
      name = "Essence impling color",
      description = "Text color for Essence implings",
      section = implingSection
  )
  default Color getEssenceColor() {
    return new Color(32, 89, 90);
  }

  @ConfigItem(
      position = 11,
      keyName = "showeclectic",
      name = "Eclectic implings",
      description = "Configures whether or not Eclectic impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showEclectic() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 12,
      keyName = "eclecticColor",
      name = "Eclectic impling color",
      description = "Text color for Eclectic implings",
      section = implingSection
  )
  default Color getEclecticColor() {
    return new Color(145, 155, 69);
  }

  @ConfigItem(
      position = 13,
      keyName = "shownature",
      name = "Nature implings",
      description = "Configures whether or not Nature impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showNature() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 14,
      keyName = "natureColor",
      name = "Nature impling color",
      description = "Text color for Nature implings",
      section = implingSection
  )
  default Color getNatureColor() {
    return new Color(92, 138, 95);
  }

  @ConfigItem(
      position = 15,
      keyName = "showmagpie",
      name = "Magpie implings",
      description = "Configures whether or not Magpie impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showMagpie() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 16,
      keyName = "magpieColor",
      name = "Magpie impling color",
      description = "Text color for Magpie implings",
      section = implingSection
  )
  default Color getMagpieColor() {
    return new Color(142, 142, 19);
  }

  @ConfigItem(
      position = 17,
      keyName = "showninja",
      name = "Ninja implings",
      description = "Configures whether or not Ninja impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showNinja() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 18,
      keyName = "ninjaColor",
      name = "Ninja impling color",
      description = "Text color for Ninja implings",
      section = implingSection
  )
  default Color getNinjaColor() {
    return new Color(71, 70, 75);
  }

  @ConfigItem(
      position = 19,
      keyName = "showCrystal",
      name = "Crystal implings",
      description = "Configures whether or not Crystal implings are displayed",
      section = implingSection
  )
  default ImplingMode showCrystal() {
    return ImplingMode.NONE;
  }

  @Alpha
  @ConfigItem(
      position = 20,
      keyName = "crystalColor",
      name = "Crystal impling color",
      description = "Text color for Crystal implings",
      section = implingSection
  )
  default Color getCrystalColor() {
    return new Color(93, 188, 210);
  }

  @ConfigItem(
      position = 21,
      keyName = "showdragon",
      name = "Dragon implings",
      description = "Configures whether or not Dragon impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showDragon() {
    return ImplingMode.HIGHLIGHT;
  }

  @Alpha
  @ConfigItem(
      position = 22,
      keyName = "dragonColor",
      name = "Dragon impling color",
      description = "Text color for Dragon implings",
      section = implingSection
  )
  default Color getDragonColor() {
    return new Color(210, 85, 75);
  }

  @ConfigItem(
      position = 23,
      keyName = "showlucky",
      name = "Lucky implings",
      description = "Configures whether or not Lucky impling tags are displayed",
      section = implingSection
  )
  default ImplingMode showLucky() {
    return ImplingMode.HIGHLIGHT;
  }

  @Alpha
  @ConfigItem(
      position = 24,
      keyName = "luckyColor",
      name = "Lucky impling color",
      description = "Text color for Lucky implings",
      section = implingSection
  )
  default Color getLuckyColor() {
    return new Color(102, 7, 101);
  }

  @ConfigItem(
      position = 25,
      keyName = "showspawn",
      name = "Show Spawn locations",
      description = "Configures whether or not spawn locations are displayed in Puro Puro"
  )
  default boolean showSpawn() {
    return false;
  }

  @Alpha
  @ConfigItem(
      position = 26,
      keyName = "spawnColor",
      name = "Impling spawn color",
      description = "Text color for impling spawns in Puro Puro"
  )
  default Color getSpawnColor() {
    return Color.WHITE;
  }

  @ConfigItem(
      position = 27,
      keyName = "showname",
      name = "Show name on minimap",
      description = "Configures whether or not impling names are displayed on minimap"
  )
  default boolean showName() {
    return false;
  }

  enum ImplingMode {
    NONE("None"),
    HIGHLIGHT("Highlight");

    private final String name;

    ImplingMode(String name) {
      this.name = name;
    }

    @Override
    public String toString() {
      return name;
    }
  }
}
