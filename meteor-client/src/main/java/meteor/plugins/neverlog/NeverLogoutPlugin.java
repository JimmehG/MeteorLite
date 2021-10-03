/*
 * Copyright (c) 2018, OpenOSRS <https://github.com/open-osrs>
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

package meteor.plugins.neverlog;

import java.util.Random;
import javax.inject.Inject;

import meteor.eventbus.Subscribe;
import meteor.plugins.Plugin;
import meteor.plugins.PluginDescriptor;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;

@PluginDescriptor(
    name = "Never Logout",
    description = "Prevents automatic session logout",
    tags = {"actions", "overlay"},
    enabledByDefault = false
)
public class NeverLogoutPlugin extends Plugin {

  @Inject
  private Client client;

  private int randomTick;

  private final Random random = new Random();

  @Subscribe
  private void onGametick(GameTick gameTick) {
    if (randomTick == -1) {
      if (client.getKeyboardIdleTicks() > randomTick) {
        generateRandomTick();
        client.setKeyboardIdleTicks(0);
      }
    }
    if (client.getMouseIdleTicks() > randomTick) {
      generateRandomTick();
      client.setMouseIdleTicks(0);
    }
  }

  private void generateRandomTick() {
    randomTick = 11900 + random.nextInt(3000);
  }
}
