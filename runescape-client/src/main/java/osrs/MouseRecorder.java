package osrs;

import net.runelite.mapping.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@ObfuscatedName("cd")
@Implements("MouseRecorder")
public class MouseRecorder implements Runnable {
	@ObfuscatedName("j")
	@ObfuscatedSignature(
		descriptor = "[[Ljm;"
	)
	@Export("Widget_interfaceComponents")
	public static Widget[][] Widget_interfaceComponents;
	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "Lme;"
	)
	static Bounds field1044;
	@ObfuscatedName("c")
	@Export("isRunning")
	boolean isRunning;
	@ObfuscatedName("b")
	@Export("lock")
	Object lock;
	@ObfuscatedName("p")
	@ObfuscatedGetter(
		intValue = 1539237497
	)
	@Export("index")
	int index;
	@ObfuscatedName("m")
	@Export("xs")
	int[] xs;
	@ObfuscatedName("t")
	@Export("ys")
	int[] ys;
	@ObfuscatedName("s")
	@Export("millis")
	long[] millis;

	MouseRecorder() {
		this.isRunning = true; // L: 7
		this.lock = new Object(); // L: 8
		this.index = 0; // L: 9
		this.xs = new int[500]; // L: 10
		this.ys = new int[500]; // L: 11
		this.millis = new long[500]; // L: 12
	} // L: 14

	public void run() {
		for (; this.isRunning; Bounds.method6608(50L)) { // L: 18
			synchronized(this.lock) { // L: 19
				if (this.index < 500) {
					this.xs[this.index] = MouseHandler.MouseHandler_x; // L: 21
					this.ys[this.index] = MouseHandler.MouseHandler_y;
					this.millis[this.index] = MouseHandler.MouseHandler_millis;
					++this.index;
				}
			}
		}

	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "([BB)Lpl;",
		garbageValue = "-5"
	)
	public static final SpritePixels method2161(byte[] var0) {
		BufferedImage var1 = null; // L: 20

		try {
			var1 = ImageIO.read(new ByteArrayInputStream(var0)); // L: 22
			int var2 = var1.getWidth(); // L: 23
			int var3 = var1.getHeight(); // L: 24
			int[] var4 = new int[var2 * var3]; // L: 25
			PixelGrabber var5 = new PixelGrabber(var1, 0, 0, var2, var3, var4, 0, var2); // L: 26
			var5.grabPixels(); // L: 27
			return new SpritePixels(var4, var2, var3);
		} catch (IOException var7) {
		} catch (InterruptedException var8) {
		}

		return new SpritePixels(0, 0);
	}

	@ObfuscatedName("p")
	@ObfuscatedSignature(
		descriptor = "(IIB)I",
		garbageValue = "-41"
	)
	static int method2160(int var0, int var1) {
		ItemContainer var2 = (ItemContainer)ItemContainer.itemContainers.get((long)var0); // L: 28
		if (var2 == null) { // L: 29
			return 0;
		} else if (var1 == -1) { // L: 30
			return 0;
		} else {
			int var3 = 0; // L: 31

			for (int var4 = 0; var4 < var2.quantities.length; ++var4) { // L: 32
				if (var2.ids[var4] == var1) {
					var3 += var2.quantities[var4]; // L: 33
				}
			}

			return var3; // L: 35
		}
	}

	@ObfuscatedName("s")
	public static int method2159(long var0) {
		return (int)(var0 >>> 0 & 127L); // L: 68
	}
}
