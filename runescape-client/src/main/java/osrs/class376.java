package osrs;

import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ng")
public class class376 {
	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "Lng;"
	)
	static final class376 field4231;
	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "Lng;"
	)
	static final class376 field4227;
	@ObfuscatedName("p")
	String field4229;

	static {
		field4231 = new class376("application/json"); // L: 4
		field4227 = new class376("text/plain"); // L: 5
	}

	class376(String var1) {
		this.field4229 = var1; // L: 9
	} // L: 10

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(I)Ljava/lang/String;",
		garbageValue = "511276144"
	)
	public String method6793() {
		return this.field4229; // L: 13
	}
}
