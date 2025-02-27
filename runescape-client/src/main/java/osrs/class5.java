package osrs;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("s")
public class class5 implements class2 {
	@ObfuscatedName("c")
	final MessageDigest field14;

	@ObfuscatedSignature(
		descriptor = "(Ln;)V"
	)
	class5(class8 var1) {
		this.field14 = this.method36(); // L: 14
	} // L: 15

	@ObfuscatedName("c")
	boolean method32(int var1, String var2, long var3) {
		byte[] var5 = this.method35(var2, var3); // L: 18
		return method40(var5) >= var1; // L: 19
	}

	@ObfuscatedName("m")
	byte[] method35(String var1, long var2) {
		StringBuilder var4 = new StringBuilder(); // L: 56
		var4.append(var1).append(Long.toHexString(var2)); // L: 57
		this.field14.reset(); // L: 58

		try {
			this.field14.update(var4.toString().getBytes("UTF-8")); // L: 60
		} catch (UnsupportedEncodingException var6) { // L: 62
			var6.printStackTrace(); // L: 63
		}

		return this.field14.digest(); // L: 65
	}

	@ObfuscatedName("t")
	MessageDigest method36() {
		try {
			return MessageDigest.getInstance("SHA-256"); // L: 70
		} catch (NoSuchAlgorithmException var2) { // L: 72
			var2.printStackTrace(); // L: 73
			return null; // L: 74
		}
	}

	@ObfuscatedName("b")
	static int method40(byte[] var0) {
		int var1 = 0; // L: 23
		byte[] var2 = var0; // L: 25

		for (int var3 = 0; var3 < var2.length; ++var3) { // L: 26
			byte var4 = var2[var3]; // L: 27
			int var5 = method33(var4); // L: 29
			var1 += var5; // L: 30
			if (var5 != 8) { // L: 31
				break;
			}
		}

		return var1; // L: 37
	}

	@ObfuscatedName("p")
	static int method33(byte var0) {
		int var1 = 0; // L: 41
		if (var0 == 0) { // L: 42
			var1 = 8; // L: 43
		} else {
			for (int var2 = var0 & 255; (var2 & 128) == 0; var2 <<= 1) { // L: 46 47 49
				++var1; // L: 48
			}
		}

		return var1; // L: 52
	}
}
