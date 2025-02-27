package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("mw")
@Implements("Friend")
public class Friend extends Buddy {
	@ObfuscatedName("u")
	@ObfuscatedSignature(
		descriptor = "Lej;"
	)
	static ClanSettings field4084;
	@ObfuscatedName("c")
	boolean field4085;
	@ObfuscatedName("b")
	boolean field4086;

	Friend() {
	} // L: 9

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(Lmw;B)I",
		garbageValue = "-44"
	)
	@Export("compareToFriend")
	int compareToFriend(Friend var1) {
		if (super.world == Client.worldId && Client.worldId != var1.world) { // L: 12
			return -1;
		} else if (Client.worldId == var1.world && super.world != Client.worldId) { // L: 13
			return 1;
		} else if (super.world != 0 && var1.world == 0) { // L: 14
			return -1;
		} else if (var1.world != 0 && super.world == 0) { // L: 15
			return 1;
		} else if (this.field4085 && !var1.field4085) { // L: 16
			return -1;
		} else if (!this.field4085 && var1.field4085) { // L: 17
			return 1;
		} else if (this.field4086 && !var1.field4086) { // L: 18
			return -1;
		} else if (!this.field4086 && var1.field4086) { // L: 19
			return 1;
		} else {
			return super.world != 0 ? super.int2 - var1.int2 : var1.int2 - super.int2; // L: 20 21 24
		}
	}

	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "(Lmv;I)I",
		garbageValue = "-1921032172"
	)
	@Export("compareTo_user")
	public int compareTo_user(User var1) {
		return this.compareToFriend((Friend)var1);
	}

	public int compareTo(Object var1) {
		return this.compareToFriend((Friend)var1); // L: 33
	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(B)Lns;",
		garbageValue = "28"
	)
	public static class365 method6296() {
		synchronized(class365.field4189) { // L: 26
			if (class365.field4185 == 0) { // L: 27
				return new class365();
			} else {
				class365.field4189[--class365.field4185].method6627(); // L: 29
				return class365.field4189[class365.field4185]; // L: 30
			}
		}
	}

	@ObfuscatedName("p")
	@ObfuscatedSignature(
		descriptor = "(S)V",
		garbageValue = "337"
	)
	public static void method6294() {
		if (MouseHandler.MouseHandler_instance != null) { // L: 50
			synchronized(MouseHandler.MouseHandler_instance) {
				MouseHandler.MouseHandler_instance = null;
			} // L: 53
		}

	} // L: 55

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "([BIII)I",
		garbageValue = "-696884945"
	)
	public static int method6298(byte[] var0, int var1, int var2) {
		int var3 = -1; // L: 47

		for (int var4 = var1; var4 < var2; ++var4) { // L: 48
			var3 = var3 >>> 8 ^ Buffer.crc32Table[(var3 ^ var0[var4]) & 255]; // L: 49
		}

		var3 = ~var3; // L: 51
		return var3; // L: 52
	}

	@ObfuscatedName("it")
	@ObfuscatedSignature(
		descriptor = "(III)V",
		garbageValue = "2055581970"
	)
	static final void method6288(int var0, int var1) {
		if (Client.menuOptionsCount >= 2 || Client.isItemSelected != 0 || Client.isSpellSelected) { // L: 8795
			if (Client.showMouseOverText) { // L: 8796
				int var2 = UserComparator10.method2543(); // L: 8797
				String var3;
				if (Client.isItemSelected == 1 && Client.menuOptionsCount < 2) { // L: 8799
					var3 = "Use" + " " + Client.selectedItemName + " " + "->";
				} else if (Client.isSpellSelected && Client.menuOptionsCount < 2) { // L: 8800
					var3 = Client.selectedSpellActionName + " " + Client.selectedSpellName + " " + "->";
				} else {
					String var4;
					if (var2 < 0) { // L: 8804
						var4 = ""; // L: 8805
					} else if (Client.menuTargets[var2].length() > 0) { // L: 8808
						var4 = Client.menuActions[var2] + " " + Client.menuTargets[var2];
					} else {
						var4 = Client.menuActions[var2]; // L: 8809
					}

					var3 = var4; // L: 8811
				}

				if (Client.menuOptionsCount > 2) { // L: 8813
					var3 = var3 + UserComparator5.colorStartTag(16777215) + " " + '/' + " " + (Client.menuOptionsCount - 2) + " more options";
				}

				class136.fontBold12.drawRandomAlphaAndSpacing(var3, var0 + 4, var1 + 15, 16777215, 0, Client.cycle / 1000); // L: 8814
			}
		}
	} // L: 8815
}
