/*
 * Copyright (c) 2018, Lotto <https://github.com/devLotto>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package meteor.plugins.stretchedmode;

import meteor.config.Config;
import meteor.config.ConfigGroup;
import meteor.config.ConfigItem;
import meteor.config.Range;
import meteor.config.Units;

@ConfigGroup("stretchedmode")
public interface StretchedModeConfig extends Config
{
	@ConfigItem(
		keyName = "keepAspectRatio",
		name = "Keep aspect ratio",
		description = "Keeps the aspect ratio when stretching."
	)
	default boolean keepAspectRatio()
	{
		return true;
	}

	@ConfigItem(
		keyName = "increasedPerformance",
		name = "Increased performance mode",
		description = "Uses a fast algorithm when stretching, lowering quality but increasing performance."
	)
	default boolean increasedPerformance()
	{
		return true;
	}

	@ConfigItem(
		keyName = "integerScaling",
		name = "Integer Scaling",
		description = "Forces use of a whole number scale factor when stretching."
	)
	default boolean integerScaling()
	{
		return false;
	}

	@Range(
			min = 25,
			max = 500
	)
	@ConfigItem(
		keyName = "scalingFactor",
		name = "Resizable Scaling",
		description = "In resizable mode, the game is reduced in size this much before it's stretched."
	)
	@Units(Units.PERCENT)
	default int scalingFactor()
	{
		return 50;
	}
}
