package osrs;

import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("or")
@Implements("IterableNodeHashTableIterator")
public class IterableNodeHashTableIterator implements Iterator {
	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "Loz;"
	)
	@Export("hashTable")
	IterableNodeHashTable hashTable;
	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "Lnw;"
	)
	@Export("head")
    Node head;
	@ObfuscatedName("p")
	@Export("index")
	int index;
	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "Lnw;"
	)
	@Export("last")
    Node last;

	@ObfuscatedSignature(
		descriptor = "(Loz;)V"
	)
	IterableNodeHashTableIterator(IterableNodeHashTable var1) {
		this.last = null; // L: 10
		this.hashTable = var1; // L: 13
		this.start(); // L: 14
	} // L: 15

	@ObfuscatedName("c")
	@Export("start")
	void start() {
		this.head = this.hashTable.buckets[0].previous; // L: 18
		this.index = 1; // L: 19
		this.last = null; // L: 20
	} // L: 21

	public Object next() {
		Node var1;
		if (this.hashTable.buckets[this.index - 1] != this.head) { // L: 25
			var1 = this.head; // L: 26
			this.head = var1.previous; // L: 27
			this.last = var1; // L: 28
			return var1; // L: 29
		} else {
			do {
				if (this.index >= this.hashTable.size) { // L: 31
					return null; // L: 39
				}

				var1 = this.hashTable.buckets[this.index++].previous; // L: 32
			} while(var1 == this.hashTable.buckets[this.index - 1]); // L: 33

			this.head = var1.previous; // L: 34
			this.last = var1; // L: 35
			return var1; // L: 36
		}
	}

	public boolean hasNext() {
		if (this.hashTable.buckets[this.index - 1] != this.head) { // L: 43
			return true;
		} else {
			while (this.index < this.hashTable.size) { // L: 44
				if (this.hashTable.buckets[this.index++].previous != this.hashTable.buckets[this.index - 1]) { // L: 45
					this.head = this.hashTable.buckets[this.index - 1].previous; // L: 46
					return true; // L: 47
				}

				this.head = this.hashTable.buckets[this.index - 1]; // L: 50
			}

			return false; // L: 53
		}
	}

	public void remove() {
		this.last.remove(); // L: 58
		this.last = null; // L: 59
	} // L: 60
}
