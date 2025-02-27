package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("jr")
@Implements("SpriteMask")
public class SpriteMask extends DualNode {
	@ObfuscatedName("s")
	@Export("PcmPlayer_stereo")
	public static boolean PcmPlayer_stereo;
	@ObfuscatedName("c")
	@ObfuscatedGetter(
		intValue = -1287182683
	)
	@Export("width")
	public final int width;
	@ObfuscatedName("b")
	@ObfuscatedGetter(
		intValue = 511314941
	)
	@Export("height")
	public final int height;
	@ObfuscatedName("p")
	@Export("xWidths")
	public final int[] xWidths;
	@ObfuscatedName("m")
	@Export("xStarts")
	public final int[] xStarts;

	SpriteMask(int var1, int var2, int[] var3, int[] var4, int var5) {
		this.width = var1; // L: 747
		this.height = var2; // L: 748
		this.xWidths = var3; // L: 749
		this.xStarts = var4; // L: 750
	} // L: 751

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(III)Z",
		garbageValue = "-520538241"
	)
	@Export("contains")
	public boolean contains(int var1, int var2) {
		if (var2 >= 0 && var2 < this.xStarts.length) { // L: 754
			int var3 = this.xStarts[var2]; // L: 755
			if (var1 >= var3 && var1 <= var3 + this.xWidths[var2]) { // L: 756
				return true;
			}
		}

		return false; // L: 758
	}
}
