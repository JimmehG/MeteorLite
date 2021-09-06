/*
 * Copyright (c) 2018, https://openosrs.com Dutta64
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
package meteor.plugins.lizardmenshaman;

import com.google.inject.Provides;
import meteor.config.ConfigManager;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import meteor.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(
	name = "Lizardman Shamans",
	enabledByDefault = false,
	description = "Display an overlay for spawn explosion tiles",
	tags = {"lizardman", "shaman", "lizard"}
)
public class LizardmanShamanPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private LizardmanShamanOverlay overlay;

	public static final int LIZARDMAN_SHAMAN_SPAWN_EXPLOSION = 7159;

	@Provides
	public LizardmanShamanConfig getConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LizardmanShamanConfig.class);
	}

	@Override
	public void startup()
	{
		overlayManager.add(overlay);
	}

	@Override
	public void shutdown()
	{
		overlayManager.remove(overlay);
	}
}