package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("mi")
@Implements("Ignored")
public class Ignored extends User {
	@ObfuscatedName("pz")
	@ObfuscatedSignature(
		descriptor = "Loo;"
	)
	@Export("HitSplatDefinition_cached")
	static class404 HitSplatDefinition_cached;
	@ObfuscatedName("c")
	@ObfuscatedGetter(
		intValue = 1285120553
	)
	@Export("id")
	int id;

	Ignored() {
	} // L: 6

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(Lmi;B)I",
		garbageValue = "109"
	)
	@Export("compareTo_ignored")
	int compareTo_ignored(Ignored var1) {
		return this.id - var1.id; // L: 9
	}

	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "(Lmv;I)I",
		garbageValue = "-1921032172"
	)
	@Export("compareTo_user")
	public int compareTo_user(User var1) {
		return this.compareTo_ignored((Ignored)var1); // L: 13
	}

	public int compareTo(Object var1) {
		return this.compareTo_ignored((Ignored)var1); // L: 17
	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(I)J",
		garbageValue = "-376840192"
	)
	public static final synchronized long getServerTime() {
		long var0 = System.currentTimeMillis(); // L: 14
		if (var0 < class415.field4466) { // L: 15
			class415.field4467 += class415.field4466 - var0; // L: 16
		}

		class415.field4466 = var0; // L: 18
		return var0 + class415.field4467; // L: 19
	}
}
