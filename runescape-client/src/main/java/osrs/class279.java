package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;
import net.runelite.rs.ScriptOpcodes;

@ObfuscatedName("jb")
public class class279 {
	@ObfuscatedName("c")
	public static final short[] field3209;
	@ObfuscatedName("b")
	public static final short[][] field3208;
	@ObfuscatedName("p")
	public static final short[] field3211;
	@ObfuscatedName("m")
	public static final short[][] field3210;
	@ObfuscatedName("h")
	@ObfuscatedSignature(
		descriptor = "Lkx;"
	)
	@Export("NetCache_currentResponse")
	public static NetFileRequest NetCache_currentResponse;

	static {
		field3209 = new short[]{6798, 8741, 25238, 4626, 4550}; // L: 4
		field3208 = new short[][]{{6798, 107, 10283, 16, 4797, 7744, 5799, 4634, -31839, 22433, 2983, -11343, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010}, {8741, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 25239, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010}, {25238, 8742, 12, -1506, -22374, 7735, 8404, 1701, -27106, 24094, 10153, -8915, 4783, 1341, 16578, -30533, 8, 5281, 10438, 3650, -27322, -21845, 200, 571, 908, 21830, 28946, -15701, -14010}, {4626, 11146, 6439, 12, 4758, 10270}, {4550, 4537, 5681, 5673, 5790, 6806, 8076, 4574, 17050, 0, 127, -31821, -17991}}; // L: 5
		field3211 = new short[]{-10304, 9104, -1, -1, -1}; // L: 12
		field3210 = new short[][]{{6554, 115, 10304, 28, 5702, 7756, 5681, 4510, -31835, 22437, 2859, -11339, 16, 5157, 10446, 3658, -27314, -21965, 472, 580, 784, 21966, 28950, -15697, -14002}, {9104, 10275, 7595, 3610, 7975, 8526, 918, -26734, 24466, 10145, -6882, 5027, 1457, 16565, -30545, 25486, 24, 5392, 10429, 3673, -27335, -21957, 192, 687, 412, 21821, 28835, -15460, -14019}, new short[0], new short[0], new short[0]}; // L: 13
	}

	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "(BI)C",
		garbageValue = "1285186796"
	)
	public static char method5406(byte var0) {
		int var1 = var0 & 255; // L: 66
		if (var1 == 0) { // L: 67
			throw new IllegalArgumentException("" + Integer.toString(var1, 16));
		} else {
			if (var1 >= 128 && var1 < 160) { // L: 68
				char var2 = class328.cp1252AsciiExtension[var1 - 128]; // L: 69
				if (var2 == 0) { // L: 70
					var2 = '?';
				}

				var1 = var2; // L: 71
			}

			return (char)var1; // L: 73
		}
	}

	@ObfuscatedName("t")
	@ObfuscatedSignature(
		descriptor = "(ILbn;ZB)I",
		garbageValue = "-19"
	)
	static int method5405(int var0, Script var1, boolean var2) {
		int var3 = -1; // L: 563
		Widget var4;
		if (var0 >= 2000) { // L: 565
			var0 -= 1000; // L: 566
			var3 = Interpreter.Interpreter_intStack[--IsaacCipher.Interpreter_intStackSize]; // L: 567
			var4 = ChatChannel.getWidget(var3); // L: 568
		} else {
			var4 = var2 ? WorldMapArea.scriptDotWidget : Messages.scriptActiveWidget; // L: 570
		}

		if (var0 == ScriptOpcodes.CC_SETPOSITION) { // L: 571
			IsaacCipher.Interpreter_intStackSize -= 4; // L: 572
			var4.rawX = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize]; // L: 573
			var4.rawY = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 1]; // L: 574
			var4.xAlignment = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 2]; // L: 575
			var4.yAlignment = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 3]; // L: 576
			SecureRandomCallable.invalidateWidget(var4); // L: 577
			class295.client.alignWidget(var4); // L: 578
			if (var3 != -1 && var4.type == 0) { // L: 579
				class115.revalidateWidgetScroll(MouseRecorder.Widget_interfaceComponents[var3 >> 16], var4, false);
			}

			return 1; // L: 580
		} else if (var0 == ScriptOpcodes.CC_SETSIZE) { // L: 582
			IsaacCipher.Interpreter_intStackSize -= 4; // L: 583
			var4.rawWidth = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize]; // L: 584
			var4.rawHeight = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 1]; // L: 585
			var4.widthAlignment = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 2]; // L: 586
			var4.heightAlignment = Interpreter.Interpreter_intStack[IsaacCipher.Interpreter_intStackSize + 3]; // L: 587
			SecureRandomCallable.invalidateWidget(var4); // L: 588
			class295.client.alignWidget(var4); // L: 589
			if (var3 != -1 && var4.type == 0) { // L: 590
				class115.revalidateWidgetScroll(MouseRecorder.Widget_interfaceComponents[var3 >> 16], var4, false);
			}

			return 1; // L: 591
		} else if (var0 == ScriptOpcodes.CC_SETHIDE) { // L: 593
			boolean var5 = Interpreter.Interpreter_intStack[--IsaacCipher.Interpreter_intStackSize] == 1; // L: 594
			if (var5 != var4.isHidden) { // L: 595
				var4.isHidden = var5; // L: 596
				SecureRandomCallable.invalidateWidget(var4); // L: 597
			}

			return 1; // L: 599
		} else if (var0 == ScriptOpcodes.CC_SETNOCLICKTHROUGH) { // L: 601
			var4.noClickThrough = Interpreter.Interpreter_intStack[--IsaacCipher.Interpreter_intStackSize] == 1; // L: 602
			return 1; // L: 603
		} else if (var0 == ScriptOpcodes.CC_SETNOSCROLLTHROUGH) { // L: 605
			var4.noScrollThrough = Interpreter.Interpreter_intStack[--IsaacCipher.Interpreter_intStackSize] == 1; // L: 606
			return 1; // L: 607
		} else {
			return 2; // L: 609
		}
	}
}
