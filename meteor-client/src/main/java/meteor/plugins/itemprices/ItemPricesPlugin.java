/*
 * Copyright (c) 2018 Charlie Waters
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
package meteor.plugins.itemprices;

import com.google.inject.Provides;
import javax.inject.Inject;
import meteor.config.ConfigManager;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.ui.overlay.OverlayManager;

@PluginDescriptor(
    name = "Item Prices",
    description = "Show prices on hover for items in your inventory and bank",
    tags = {"bank", "inventory", "overlay", "high", "alchemy", "grand", "exchange", "tooltips"}
)
public class ItemPricesPlugin extends Plugin {

  @Inject
  private OverlayManager overlayManager;

  @Inject
  private ItemPricesOverlay overlay;

  @Provides
  @Override
  public ItemPricesConfig getConfig(ConfigManager configManager) {
    return configManager.getConfig(ItemPricesConfig.class);
  }

  @Override
  public void startup() {
    overlayManager.add(overlay);
  }

  @Override
  public void shutdown() {
    overlayManager.remove(overlay);
  }
}
