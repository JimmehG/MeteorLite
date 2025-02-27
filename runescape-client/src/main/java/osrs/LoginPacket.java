package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ir")
@Implements("LoginPacket")
public class LoginPacket implements class251 {
	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	public static final LoginPacket field3032;
	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	static final LoginPacket field3031;
	@ObfuscatedName("p")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	public static final LoginPacket field3038;
	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	public static final LoginPacket field3037;
	@ObfuscatedName("t")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	public static final LoginPacket field3034;
	@ObfuscatedName("s")
	@ObfuscatedSignature(
		descriptor = "Lir;"
	)
	static final LoginPacket field3035;
	@ObfuscatedName("w")
	@ObfuscatedSignature(
		descriptor = "[Lir;"
	)
	@Export("LoginPacket_indexedValues")
	static final LoginPacket[] LoginPacket_indexedValues;
	@ObfuscatedName("j")
	@ObfuscatedGetter(
		intValue = -1815606843
	)
	@Export("id")
	public final int id;

	static {
		field3032 = new LoginPacket(14, 0); // L: 5
		field3031 = new LoginPacket(15, 4); // L: 6
		field3038 = new LoginPacket(16, -2); // L: 7
		field3037 = new LoginPacket(18, -2); // L: 8
		field3034 = new LoginPacket(19, -2); // L: 9
		field3035 = new LoginPacket(27, 0); // L: 10
		LoginPacket_indexedValues = new LoginPacket[32]; // L: 12
		LoginPacket[] var0 = new LoginPacket[]{field3031, field3037, field3038, field3034, field3032, field3035}; // L: 17
		LoginPacket[] var1 = var0; // L: 19

		for (int var2 = 0; var2 < var1.length; ++var2) { // L: 20
			LoginPacket_indexedValues[var1[var2].id] = var1[var2]; // L: 21
		}

	} // L: 23

	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "0"
	)
	LoginPacket(int var1, int var2) {
		this.id = var1; // L: 26
	} // L: 27

	@ObfuscatedName("v")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/CharSequence;B)Ljava/lang/String;",
		garbageValue = "100"
	)
	public static String method5019(CharSequence var0) {
		int var2 = var0.length(); // L: 176
		char[] var3 = new char[var2]; // L: 178

		for (int var4 = 0; var4 < var2; ++var4) { // L: 179
			var3[var4] = '*';
		}

		String var1 = new String(var3); // L: 180
		return var1; // L: 182
	}
}
