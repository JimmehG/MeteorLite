package osrs;

import net.runelite.mapping.*;

import java.util.*;

@ObfuscatedName("ov")
@Implements("WorldMap")
public class WorldMap {
	@ObfuscatedName("r")
	@ObfuscatedSignature(
		descriptor = "Lot;"
	)
	@Export("fontNameVerdana11")
	static final FontName fontNameVerdana11;
	@ObfuscatedName("o")
	@ObfuscatedSignature(
		descriptor = "Lot;"
	)
	@Export("fontNameVerdana13")
	static final FontName fontNameVerdana13;
	@ObfuscatedName("v")
	@ObfuscatedSignature(
		descriptor = "Lot;"
	)
	@Export("fontNameVerdana15")
	static final FontName fontNameVerdana15;
	@ObfuscatedName("j")
	@ObfuscatedSignature(
		descriptor = "Lku;"
	)
	@Export("WorldMap_archive")
	AbstractArchive WorldMap_archive;
	@ObfuscatedName("w")
	@ObfuscatedSignature(
		descriptor = "Lku;"
	)
	@Export("WorldMap_geographyArchive")
	AbstractArchive WorldMap_geographyArchive;
	@ObfuscatedName("n")
	@ObfuscatedSignature(
		descriptor = "Lku;"
	)
	@Export("WorldMap_groundArchive")
	AbstractArchive WorldMap_groundArchive;
	@ObfuscatedName("d")
	@ObfuscatedSignature(
		descriptor = "Llx;"
	)
	@Export("font")
	Font font;
	@ObfuscatedName("h")
	@Export("fonts")
	HashMap fonts;
	@ObfuscatedName("g")
	@ObfuscatedSignature(
		descriptor = "[Lpa;"
	)
	@Export("mapSceneSprites")
	IndexedSprite[] mapSceneSprites;
	@ObfuscatedName("e")
	@Export("details")
	HashMap details;
	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "Lhr;"
	)
	@Export("mainMapArea")
    WorldMapArea mainMapArea;
	@ObfuscatedName("u")
	@ObfuscatedSignature(
		descriptor = "Lhr;"
	)
	@Export("currentMapArea")
    WorldMapArea currentMapArea;
	@ObfuscatedName("k")
	@ObfuscatedSignature(
		descriptor = "Lhr;"
	)
    WorldMapArea field4321;
	@ObfuscatedName("f")
	@ObfuscatedSignature(
		descriptor = "Lhe;"
	)
	@Export("worldMapManager")
    WorldMapManager worldMapManager;
	@ObfuscatedName("l")
	@ObfuscatedSignature(
		descriptor = "Lok;"
	)
	@Export("cacheLoader")
    WorldMapArchiveLoader cacheLoader;
	@ObfuscatedName("q")
	@ObfuscatedGetter(
		intValue = 988816303
	)
	@Export("centerTileX")
	int centerTileX;
	@ObfuscatedName("x")
	@ObfuscatedGetter(
		intValue = 742897399
	)
	@Export("centerTileY")
	int centerTileY;
	@ObfuscatedName("z")
	@ObfuscatedGetter(
		intValue = 574868181
	)
	@Export("worldMapTargetX")
	int worldMapTargetX;
	@ObfuscatedName("i")
	@ObfuscatedGetter(
		intValue = 67441045
	)
	@Export("worldMapTargetY")
	int worldMapTargetY;
	@ObfuscatedName("y")
	@Export("zoom")
	float zoom;
	@ObfuscatedName("ah")
	@Export("zoomTarget")
	float zoomTarget;
	@ObfuscatedName("ao")
	@ObfuscatedGetter(
		intValue = -862108599
	)
	@Export("worldMapDisplayWidth")
	int worldMapDisplayWidth;
	@ObfuscatedName("ab")
	@ObfuscatedGetter(
		intValue = -732997845
	)
	@Export("worldMapDisplayHeight")
	int worldMapDisplayHeight;
	@ObfuscatedName("an")
	@ObfuscatedGetter(
		intValue = -216475433
	)
	@Export("worldMapDisplayX")
	int worldMapDisplayX;
	@ObfuscatedName("ax")
	@ObfuscatedGetter(
		intValue = 1517340013
	)
	@Export("worldMapDisplayY")
	int worldMapDisplayY;
	@ObfuscatedName("am")
	@ObfuscatedGetter(
		intValue = -420508859
	)
	@Export("maxFlashCount")
	int maxFlashCount;
	@ObfuscatedName("az")
	@ObfuscatedGetter(
		intValue = 621335613
	)
	@Export("cyclesPerFlash")
	int cyclesPerFlash;
	@ObfuscatedName("au")
	@Export("perpetualFlash")
	boolean perpetualFlash;
	@ObfuscatedName("av")
	@Export("flashingElements")
	HashSet flashingElements;
	@ObfuscatedName("ap")
	@ObfuscatedGetter(
		intValue = -1416499467
	)
	@Export("flashCount")
	int flashCount;
	@ObfuscatedName("ac")
	@ObfuscatedGetter(
		intValue = -836292671
	)
	@Export("flashCycle")
	int flashCycle;
	@ObfuscatedName("aj")
	@ObfuscatedGetter(
		intValue = -1744644145
	)
	int field4340;
	@ObfuscatedName("af")
	@ObfuscatedGetter(
		intValue = -1265576405
	)
	int field4335;
	@ObfuscatedName("ar")
	@ObfuscatedGetter(
		intValue = -1227072721
	)
	int field4342;
	@ObfuscatedName("ag")
	@ObfuscatedGetter(
		intValue = 1034600775
	)
	int field4343;
	@ObfuscatedName("al")
	@ObfuscatedGetter(
		longValue = 1846955281034182293L
	)
	long field4311;
	@ObfuscatedName("aa")
	@ObfuscatedGetter(
		intValue = -221870639
	)
	int field4345;
	@ObfuscatedName("as")
	@ObfuscatedGetter(
		intValue = -381850671
	)
	int field4346;
	@ObfuscatedName("at")
	boolean field4334;
	@ObfuscatedName("aw")
	@Export("enabledElements")
	HashSet enabledElements;
	@ObfuscatedName("ay")
	@Export("enabledCategories")
	HashSet enabledCategories;
	@ObfuscatedName("ae")
	@Export("enabledElementIds")
	HashSet enabledElementIds;
	@ObfuscatedName("ak")
	HashSet field4351;
	@ObfuscatedName("ad")
	@Export("elementsDisabled")
	boolean elementsDisabled;
	@ObfuscatedName("bp")
	@ObfuscatedGetter(
		intValue = -1859070629
	)
	int field4353;
	@ObfuscatedName("ba")
	@Export("menuOpcodes")
	final int[] menuOpcodes;
	@ObfuscatedName("bq")
	List field4356;
	@ObfuscatedName("bg")
	@Export("iconIterator")
	Iterator iconIterator;
	@ObfuscatedName("br")
	HashSet field4358;
	@ObfuscatedName("bi")
	@ObfuscatedSignature(
		descriptor = "Lju;"
	)
	@Export("mouseCoord")
	Coord mouseCoord;
	@ObfuscatedName("bm")
	@Export("showCoord")
	public boolean showCoord;
	@ObfuscatedName("bw")
	@ObfuscatedSignature(
		descriptor = "Lpl;"
	)
	@Export("sprite")
	SpritePixels sprite;
	@ObfuscatedName("bl")
	@ObfuscatedGetter(
		intValue = 878562977
	)
	@Export("cachedPixelsPerTile")
	int cachedPixelsPerTile;
	@ObfuscatedName("bz")
	@ObfuscatedGetter(
		intValue = 1300287889
	)
	@Export("minCachedTileX")
	int minCachedTileX;
	@ObfuscatedName("bu")
	@ObfuscatedGetter(
		intValue = -658949633
	)
	@Export("minCachedTileY")
	int minCachedTileY;
	@ObfuscatedName("bs")
	@ObfuscatedGetter(
		intValue = -1815289921
	)
	int field4365;

	static {
		fontNameVerdana11 = FontName.FontName_verdana11; // L: 46
		fontNameVerdana13 = FontName.FontName_verdana13; // L: 47
		fontNameVerdana15 = FontName.FontName_verdana15; // L: 48
	}

	public WorldMap() {
		this.worldMapTargetX = -1; // L: 60
		this.worldMapTargetY = -1; // L: 61
		this.worldMapDisplayWidth = -1; // L: 64
		this.worldMapDisplayHeight = -1; // L: 65
		this.worldMapDisplayX = -1; // L: 66
		this.worldMapDisplayY = -1; // L: 67
		this.maxFlashCount = 3; // L: 68
		this.cyclesPerFlash = 50; // L: 69
		this.perpetualFlash = false; // L: 70
		this.flashingElements = null; // L: 71
		this.flashCount = -1; // L: 72
		this.flashCycle = -1; // L: 73
		this.field4340 = -1; // L: 74
		this.field4335 = -1; // L: 75
		this.field4342 = -1; // L: 76
		this.field4343 = -1; // L: 77
		this.field4334 = true; // L: 81
		this.enabledElements = new HashSet(); // L: 84
		this.enabledCategories = new HashSet(); // L: 85
		this.enabledElementIds = new HashSet(); // L: 86
		this.field4351 = new HashSet(); // L: 87
		this.elementsDisabled = false; // L: 88
		this.field4353 = 0; // L: 89
		this.menuOpcodes = new int[]{1008, 1009, 1010, 1011, 1012}; // L: 91
		this.field4358 = new HashSet(); // L: 94
		this.mouseCoord = null; // L: 95
		this.showCoord = false; // L: 96
		this.minCachedTileX = -1; // L: 99
		this.minCachedTileY = -1; // L: 100
		this.field4365 = -1; // L: 101
	}

	@ObfuscatedName("c")
	@ObfuscatedSignature(
		descriptor = "(Lku;Lku;Lku;Llx;Ljava/util/HashMap;[Lpa;I)V",
		garbageValue = "1284621273"
	)
	@Export("init")
	public void init(AbstractArchive var1, AbstractArchive var2, AbstractArchive var3, Font var4, HashMap var5, IndexedSprite[] var6) {
		this.mapSceneSprites = var6; // L: 105
		this.WorldMap_archive = var1; // L: 106
		this.WorldMap_geographyArchive = var2; // L: 107
		this.WorldMap_groundArchive = var3; // L: 108
		this.font = var4; // L: 109
		this.fonts = new HashMap(); // L: 110
		this.fonts.put(WorldMapLabelSize.WorldMapLabelSize_small, var5.get(fontNameVerdana11)); // L: 111
		this.fonts.put(WorldMapLabelSize.WorldMapLabelSize_medium, var5.get(fontNameVerdana13)); // L: 112
		this.fonts.put(WorldMapLabelSize.WorldMapLabelSize_large, var5.get(fontNameVerdana15)); // L: 113
		this.cacheLoader = new WorldMapArchiveLoader(var1); // L: 114
		int var7 = this.WorldMap_archive.getGroupId(WorldMapCacheName.field2768.name); // L: 115
		int[] var8 = this.WorldMap_archive.getGroupFileIds(var7); // L: 116
		this.details = new HashMap(var8.length); // L: 117

		for (int var9 = 0; var9 < var8.length; ++var9) { // L: 118
			Buffer var10 = new Buffer(this.WorldMap_archive.takeFile(var7, var8[var9])); // L: 119
			WorldMapArea var11 = new WorldMapArea(); // L: 120
			var11.read(var10, var8[var9]); // L: 121
			this.details.put(var11.getInternalName(), var11); // L: 122
			if (var11.getIsMain()) { // L: 123
				this.mainMapArea = var11; // L: 124
			}
		}

		this.setCurrentMapArea(this.mainMapArea); // L: 127
		this.field4321 = null; // L: 128
	} // L: 129

	@ObfuscatedName("b")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1522497610"
	)
	public void method6902() {
		WorldMapRegion.WorldMapRegion_cachedSprites.demote(5); // L: 133
	} // L: 135

	@ObfuscatedName("p")
	@ObfuscatedSignature(
		descriptor = "(IIZIIIII)V",
		garbageValue = "39727185"
	)
	@Export("onCycle")
	public void onCycle(int var1, int var2, boolean var3, int var4, int var5, int var6, int var7) {
		if (this.cacheLoader.isLoaded()) { // L: 138
			this.smoothZoom(); // L: 141
			this.scrollToTarget(); // L: 142
			if (var3) { // L: 143
				int var8 = (int)Math.ceil((double)((float)var6 / this.zoom)); // L: 146
				int var9 = (int)Math.ceil((double)((float)var7 / this.zoom)); // L: 147
				List var10 = this.worldMapManager.method4617(this.centerTileX - var8 / 2 - 1, this.centerTileY - var9 / 2 - 1, var8 / 2 + this.centerTileX + 1, var9 / 2 + this.centerTileY + 1, var4, var5, var6, var7, var1, var2); // L: 148
				HashSet var11 = new HashSet(); // L: 149

				Iterator var12;
				AbstractWorldMapIcon var13;
				ScriptEvent var14;
				WorldMapEvent var15;
				for (var12 = var10.iterator(); var12.hasNext(); class285.runScriptEvent(var14)) { // L: 150 163
					var13 = (AbstractWorldMapIcon)var12.next(); // L: 151
					var11.add(var13); // L: 153
					var14 = new ScriptEvent(); // L: 154
					var15 = new WorldMapEvent(var13.getElement(), var13.coord1, var13.coord2); // L: 155
					var14.setArgs(new Object[]{var15, var1, var2}); // L: 156
					if (this.field4358.contains(var13)) { // L: 157
						var14.setType(17); // L: 158
					} else {
						var14.setType(15); // L: 161
					}
				}

				var12 = this.field4358.iterator(); // L: 166

				while (var12.hasNext()) {
					var13 = (AbstractWorldMapIcon)var12.next(); // L: 167
					if (!var11.contains(var13)) { // L: 169
						var14 = new ScriptEvent(); // L: 170
						var15 = new WorldMapEvent(var13.getElement(), var13.coord1, var13.coord2); // L: 171
						var14.setArgs(new Object[]{var15, var1, var2}); // L: 172
						var14.setType(16); // L: 173
						class285.runScriptEvent(var14); // L: 174
					}
				}

				this.field4358 = var11; // L: 178
			}
		}
	} // L: 139 144 179

	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "(IIZZI)V",
		garbageValue = "2076670007"
	)
	public void method7062(int var1, int var2, boolean var3, boolean var4) {
		long var5 = Ignored.getServerTime(); // L: 182
		this.method6886(var1, var2, var4, var5); // L: 183
		if (this.hasTarget() || !var4 && !var3) { // L: 184
			this.method7050(); // L: 198
		} else {
			if (var4) { // L: 185
				this.field4342 = var1; // L: 186
				this.field4343 = var2; // L: 187
				this.field4340 = this.centerTileX; // L: 188
				this.field4335 = this.centerTileY; // L: 189
			}

			if (this.field4340 != -1) { // L: 191
				int var7 = var1 - this.field4342; // L: 192
				int var8 = var2 - this.field4343; // L: 193
				this.setWorldMapPosition(this.field4340 - (int)((float)var7 / this.zoomTarget), (int)((float)var8 / this.zoomTarget) + this.field4335, false); // L: 194
			}
		}

		if (var4) { // L: 200
			this.field4311 = var5; // L: 201
			this.field4345 = var1; // L: 202
			this.field4346 = var2; // L: 203
		}

	} // L: 205

	@ObfuscatedName("t")
	void method6886(int var1, int var2, boolean var3, long var4) {
		if (this.currentMapArea != null) { // L: 208
			int var6 = (int)((float)this.centerTileX + ((float)(var1 - this.worldMapDisplayX) - (float)this.getDisplayWith() * this.zoom / 2.0F) / this.zoom); // L: 209
			int var7 = (int)((float)this.centerTileY - ((float)(var2 - this.worldMapDisplayY) - (float)this.getDisplayHeight() * this.zoom / 2.0F) / this.zoom); // L: 210
			this.mouseCoord = this.currentMapArea.coord(var6 + this.currentMapArea.getRegionLowX() * 64, var7 + this.currentMapArea.getRegionLowY() * 64); // L: 211
			if (this.mouseCoord != null && var3) { // L: 212
				boolean var8 = Client.staffModLevel >= 2; // L: 215
				if (var8 && KeyHandler.KeyHandler_pressedKeys[82] && KeyHandler.KeyHandler_pressedKeys[81]) { // L: 217
					BuddyRankComparator.method2547(this.mouseCoord.x, this.mouseCoord.y, this.mouseCoord.plane, false); // L: 218
				} else {
					boolean var9 = true; // L: 221
					if (this.field4334) { // L: 222
						int var10 = var1 - this.field4345; // L: 223
						int var11 = var2 - this.field4346; // L: 224
						if (var4 - this.field4311 > 500L || var10 < -25 || var10 > 25 || var11 < -25 || var11 > 25) { // L: 225
							var9 = false; // L: 226
						}
					}

					if (var9) { // L: 229
						PacketBufferNode var12 = HitSplatDefinition.getPacketBufferNode(ClientPacket.CLICKWORLDMAP, Client.packetWriter.isaacCipher); // L: 230
						var12.packetBuffer.writeIntME(this.mouseCoord.packed()); // L: 231
						Client.packetWriter.addNode(var12); // L: 232
						this.field4311 = 0L; // L: 233
					}
				}
			}
		} else {
			this.mouseCoord = null; // L: 238
		}

	} // L: 239

	@ObfuscatedName("s")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "988730717"
	)
	@Export("smoothZoom")
	void smoothZoom() {
		if (Messages.field1273 != null) { // L: 242
			this.zoom = this.zoomTarget; // L: 243
		} else {
			if (this.zoom < this.zoomTarget) { // L: 246
				this.zoom = Math.min(this.zoomTarget, this.zoom + this.zoom / 30.0F); // L: 247
			}

			if (this.zoom > this.zoomTarget) { // L: 249
				this.zoom = Math.max(this.zoomTarget, this.zoom - this.zoom / 30.0F); // L: 250
			}

		}
	} // L: 244 252

	@ObfuscatedName("j")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-1397883931"
	)
	@Export("scrollToTarget")
	void scrollToTarget() {
		if (this.hasTarget()) { // L: 255
			int var1 = this.worldMapTargetX - this.centerTileX; // L: 258
			int var2 = this.worldMapTargetY - this.centerTileY; // L: 259
			if (var1 != 0) { // L: 260
				var1 /= Math.min(8, Math.abs(var1)); // L: 261
			}

			if (var2 != 0) { // L: 263
				var2 /= Math.min(8, Math.abs(var2)); // L: 264
			}

			this.setWorldMapPosition(var1 + this.centerTileX, var2 + this.centerTileY, true); // L: 266
			if (this.centerTileX == this.worldMapTargetX && this.centerTileY == this.worldMapTargetY) { // L: 267
				this.worldMapTargetX = -1; // L: 268
				this.worldMapTargetY = -1; // L: 269
			}

		}
	} // L: 256 271

	@ObfuscatedName("w")
	@ObfuscatedSignature(
		descriptor = "(IIZI)V",
		garbageValue = "2028510413"
	)
	@Export("setWorldMapPosition")
	final void setWorldMapPosition(int var1, int var2, boolean var3) {
		this.centerTileX = var1; // L: 274
		this.centerTileY = var2; // L: 275
		Ignored.getServerTime(); // L: 276
		if (var3) {
			this.method7050(); // L: 277
		}

	} // L: 278

	@ObfuscatedName("n")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-69"
	)
	final void method7050() {
		this.field4343 = -1; // L: 281
		this.field4342 = -1; // L: 282
		this.field4335 = -1; // L: 283
		this.field4340 = -1; // L: 284
	} // L: 285

	@ObfuscatedName("r")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1277786220"
	)
	@Export("hasTarget")
	boolean hasTarget() {
		return this.worldMapTargetX != -1 && this.worldMapTargetY != -1; // L: 288
	}

	@ObfuscatedName("o")
	@ObfuscatedSignature(
		descriptor = "(IIII)Lhr;",
		garbageValue = "1760003503"
	)
	@Export("mapAreaAtCoord")
	public WorldMapArea mapAreaAtCoord(int var1, int var2, int var3) {
		Iterator var4 = this.details.values().iterator(); // L: 292

		WorldMapArea var5;
		do {
			if (!var4.hasNext()) {
				return null; // L: 300
			}

			var5 = (WorldMapArea)var4.next(); // L: 293
		} while(!var5.containsCoord(var1, var2, var3)); // L: 295

		return var5; // L: 296
	}

	@ObfuscatedName("v")
	@ObfuscatedSignature(
		descriptor = "(IIIZB)V",
		garbageValue = "48"
	)
	public void method6870(int var1, int var2, int var3, boolean var4) {
		WorldMapArea var5 = this.mapAreaAtCoord(var1, var2, var3); // L: 304
		if (var5 == null) { // L: 305
			if (!var4) { // L: 306
				return; // L: 309
			}

			var5 = this.mainMapArea; // L: 307
		}

		boolean var6 = false; // L: 311
		if (var5 != this.field4321 || var4) { // L: 312
			this.field4321 = var5; // L: 313
			this.setCurrentMapArea(var5); // L: 314
			var6 = true; // L: 315
		}

		if (var6 || var4) { // L: 317
			this.jump(var1, var2, var3); // L: 318
		}

	} // L: 320

	@ObfuscatedName("d")
	@ObfuscatedSignature(
		descriptor = "(IB)V",
		garbageValue = "-89"
	)
	@Export("setCurrentMapAreaId")
	public void setCurrentMapAreaId(int var1) {
		WorldMapArea var2 = this.getMapArea(var1); // L: 323
		if (var2 != null) { // L: 324
			this.setCurrentMapArea(var2); // L: 325
		}

	} // L: 327

	@ObfuscatedName("h")
	@ObfuscatedSignature(
		descriptor = "(B)I",
		garbageValue = "9"
	)
	@Export("currentMapAreaId")
	public int currentMapAreaId() {
		return this.currentMapArea == null ? -1 : this.currentMapArea.getId(); // L: 330 331 333
	}

	@ObfuscatedName("g")
	@ObfuscatedSignature(
		descriptor = "(I)Lhr;",
		garbageValue = "1700798054"
	)
	@Export("getCurrentMapArea")
	public WorldMapArea getCurrentMapArea() {
		return this.currentMapArea; // L: 337
	}

	@ObfuscatedName("e")
	@ObfuscatedSignature(
		descriptor = "(Lhr;B)V",
		garbageValue = "-120"
	)
	@Export("setCurrentMapArea")
	void setCurrentMapArea(WorldMapArea var1) {
		if (this.currentMapArea == null || var1 != this.currentMapArea) { // L: 341
			this.initializeWorldMapManager(var1); // L: 344
			this.jump(-1, -1, -1); // L: 345
		}
	} // L: 342 346

	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "(Lhr;B)V",
		garbageValue = "2"
	)
	@Export("initializeWorldMapManager")
	void initializeWorldMapManager(WorldMapArea var1) {
		this.currentMapArea = var1; // L: 349
		this.worldMapManager = new WorldMapManager(this.mapSceneSprites, this.fonts, this.WorldMap_geographyArchive, this.WorldMap_groundArchive); // L: 350
		this.cacheLoader.reset(this.currentMapArea.getInternalName()); // L: 351
	} // L: 352

	@ObfuscatedName("u")
	@ObfuscatedSignature(
		descriptor = "(Lhr;Lju;Lju;ZI)V",
		garbageValue = "-665404074"
	)
	public void method6876(WorldMapArea var1, Coord var2, Coord var3, boolean var4) {
		if (var1 != null) { // L: 355
			if (this.currentMapArea == null || var1 != this.currentMapArea) { // L: 358
				this.initializeWorldMapManager(var1); // L: 359
			}

			if (!var4 && this.currentMapArea.containsCoord(var2.plane, var2.x, var2.y)) { // L: 361
				this.jump(var2.plane, var2.x, var2.y); // L: 365
			} else {
				this.jump(var3.plane, var3.x, var3.y); // L: 362
			}

		}
	} // L: 356 367

	@ObfuscatedName("k")
	@ObfuscatedSignature(
		descriptor = "(IIII)V",
		garbageValue = "471705549"
	)
	@Export("jump")
	void jump(int var1, int var2, int var3) {
		if (this.currentMapArea != null) { // L: 370
			int[] var4 = this.currentMapArea.position(var1, var2, var3); // L: 373
			if (var4 == null) { // L: 374
				var4 = this.currentMapArea.position(this.currentMapArea.getOriginPlane(), this.currentMapArea.getOriginX(), this.currentMapArea.getOriginY()); // L: 375
			}

			this.setWorldMapPosition(var4[0] - this.currentMapArea.getRegionLowX() * 64, var4[1] - this.currentMapArea.getRegionLowY() * 64, true); // L: 377
			this.worldMapTargetX = -1; // L: 378
			this.worldMapTargetY = -1; // L: 379
			this.zoom = this.getZoomFromPercentage(this.currentMapArea.getZoom()); // L: 380
			this.zoomTarget = this.zoom; // L: 381
			this.field4356 = null; // L: 382
			this.iconIterator = null; // L: 383
			this.worldMapManager.clearIcons(); // L: 384
		}
	} // L: 371 385

	@ObfuscatedName("f")
	@ObfuscatedSignature(
		descriptor = "(IIIIIB)V",
		garbageValue = "-21"
	)
	@Export("draw")
	public void draw(int var1, int var2, int var3, int var4, int var5) {
		int[] var6 = new int[4]; // L: 388
		Rasterizer2D.Rasterizer2D_getClipArray(var6); // L: 389
		Rasterizer2D.Rasterizer2D_setClip(var1, var2, var3 + var1, var2 + var4); // L: 390
		Rasterizer2D.Rasterizer2D_fillRectangle(var1, var2, var3, var4, -16777216); // L: 391
		int var7 = this.cacheLoader.getPercentLoaded(); // L: 392
		if (var7 < 100) { // L: 393
			this.drawLoading(var1, var2, var3, var4, var7); // L: 394
		} else {
			if (!this.worldMapManager.isLoaded()) { // L: 397
				this.worldMapManager.load(this.WorldMap_archive, this.currentMapArea.getInternalName(), Client.isMembersWorld); // L: 398
				if (!this.worldMapManager.isLoaded()) { // L: 399
					return; // L: 400
				}
			}

			if (this.flashingElements != null) { // L: 403
				++this.flashCycle; // L: 404
				if (this.flashCycle % this.cyclesPerFlash == 0) { // L: 405
					this.flashCycle = 0; // L: 406
					++this.flashCount; // L: 407
				}

				if (this.flashCount >= this.maxFlashCount && !this.perpetualFlash) { // L: 409
					this.flashingElements = null; // L: 410
				}
			}

			int var8 = (int)Math.ceil((double)((float)var3 / this.zoom)); // L: 413
			int var9 = (int)Math.ceil((double)((float)var4 / this.zoom)); // L: 414
			this.worldMapManager.drawTiles(this.centerTileX - var8 / 2, this.centerTileY - var9 / 2, var8 / 2 + this.centerTileX, var9 / 2 + this.centerTileY, var1, var2, var3 + var1, var2 + var4); // L: 415
			boolean var10;
			if (!this.elementsDisabled) { // L: 416
				var10 = false; // L: 417
				if (var5 - this.field4353 > 100) { // L: 418
					this.field4353 = var5; // L: 419
					var10 = true; // L: 420
				}

				this.worldMapManager.drawElements(this.centerTileX - var8 / 2, this.centerTileY - var9 / 2, var8 / 2 + this.centerTileX, var9 / 2 + this.centerTileY, var1, var2, var3 + var1, var2 + var4, this.field4351, this.flashingElements, this.flashCycle, this.cyclesPerFlash, var10); // L: 422
			}

			this.method7006(var1, var2, var3, var4, var8, var9); // L: 424
			var10 = Client.staffModLevel >= 2; // L: 427
			if (var10 && this.showCoord && this.mouseCoord != null) { // L: 429
				this.font.draw("Coord: " + this.mouseCoord, Rasterizer2D.Rasterizer2D_xClipStart + 10, Rasterizer2D.Rasterizer2D_yClipStart + 20, 16776960, -1);
			}

			this.worldMapDisplayWidth = var8; // L: 430
			this.worldMapDisplayHeight = var9; // L: 431
			this.worldMapDisplayX = var1; // L: 432
			this.worldMapDisplayY = var2; // L: 433
			Rasterizer2D.Rasterizer2D_setClipArray(var6); // L: 434
		}
	} // L: 395 435

	@ObfuscatedName("l")
	@ObfuscatedSignature(
		descriptor = "(IIIIIIB)Z",
		garbageValue = "54"
	)
	boolean method6879(int var1, int var2, int var3, int var4, int var5, int var6) {
		if (this.sprite == null) { // L: 438
			return true;
		} else if (this.sprite.subWidth == var1 && this.sprite.subHeight == var2) { // L: 439
			if (this.worldMapManager.pixelsPerTile != this.cachedPixelsPerTile) { // L: 440
				return true;
			} else if (this.field4365 != Client.field559) {
				return true; // L: 441
			} else if (var3 <= 0 && var4 <= 0) { // L: 442
				return var3 + var1 < var5 || var2 + var4 < var6; // L: 443
			} else {
				return true; // L: 444
			}
		} else {
			return true;
		}
	}

	@ObfuscatedName("q")
	@ObfuscatedSignature(
		descriptor = "(IIIIIII)V",
		garbageValue = "-1766155108"
	)
	void method7006(int var1, int var2, int var3, int var4, int var5, int var6) {
		if (Messages.field1273 != null) { // L: 448
			int var7 = 512 / (this.worldMapManager.pixelsPerTile * 2); // L: 449
			int var8 = var3 + 512; // L: 450
			int var9 = var4 + 512; // L: 451
			float var10 = 1.0F; // L: 452
			var8 = (int)((float)var8 / var10); // L: 453
			var9 = (int)((float)var9 / var10); // L: 454
			int var11 = this.getDisplayX() - var5 / 2 - var7; // L: 455
			int var12 = this.getDisplayY() - var6 / 2 - var7; // L: 456
			int var13 = var1 - (var7 + var11 - this.minCachedTileX) * this.worldMapManager.pixelsPerTile; // L: 457
			int var14 = var2 - this.worldMapManager.pixelsPerTile * (var7 - (var12 - this.minCachedTileY)); // L: 458
			if (this.method6879(var8, var9, var13, var14, var3, var4)) { // L: 459
				if (this.sprite != null && this.sprite.subWidth == var8 && this.sprite.subHeight == var9) { // L: 460
					Arrays.fill(this.sprite.pixels, 0); // L: 463
				} else {
					this.sprite = new SpritePixels(var8, var9); // L: 461
				}

				this.minCachedTileX = this.getDisplayX() - var5 / 2 - var7; // L: 464
				this.minCachedTileY = this.getDisplayY() - var6 / 2 - var7; // L: 465
				this.cachedPixelsPerTile = this.worldMapManager.pixelsPerTile; // L: 466
				Messages.field1273.method5800(this.minCachedTileX, this.minCachedTileY, this.sprite, (float)this.cachedPixelsPerTile / var10); // L: 467
				this.field4365 = Client.field559; // L: 468
				var13 = var1 - (var7 + var11 - this.minCachedTileX) * this.worldMapManager.pixelsPerTile; // L: 469
				var14 = var2 - this.worldMapManager.pixelsPerTile * (var7 - (var12 - this.minCachedTileY)); // L: 470
			}

			Rasterizer2D.Rasterizer2D_fillRectangleAlpha(var1, var2, var3, var4, 0, 128); // L: 472
			if (var10 == 1.0F) { // L: 473
				this.sprite.method7809(var13, var14, 192); // L: 474
			} else {
				this.sprite.method7776(var13, var14, (int)(var10 * (float)var8), (int)((float)var9 * var10), 192); // L: 477
			}
		}

	} // L: 480

	@ObfuscatedName("x")
	@ObfuscatedSignature(
		descriptor = "(IIIII)V",
		garbageValue = "-748187349"
	)
	@Export("drawOverview")
	public void drawOverview(int var1, int var2, int var3, int var4) {
		if (this.cacheLoader.isLoaded()) { // L: 483
			if (!this.worldMapManager.isLoaded()) { // L: 486
				this.worldMapManager.load(this.WorldMap_archive, this.currentMapArea.getInternalName(), Client.isMembersWorld); // L: 487
				if (!this.worldMapManager.isLoaded()) { // L: 488
					return; // L: 489
				}
			}

			this.worldMapManager.drawOverview(var1, var2, var3, var4, this.flashingElements, this.flashCycle, this.cyclesPerFlash); // L: 492
		}
	} // L: 484 493

	@ObfuscatedName("z")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "802712307"
	)
	@Export("setZoomPercentage")
	public void setZoomPercentage(int var1) {
		this.zoomTarget = this.getZoomFromPercentage(var1); // L: 496
	} // L: 497

	@ObfuscatedName("i")
	@ObfuscatedSignature(
		descriptor = "(IIIIIS)V",
		garbageValue = "29297"
	)
	@Export("drawLoading")
	void drawLoading(int var1, int var2, int var3, int var4, int var5) {
		byte var6 = 20; // L: 500
		int var7 = var3 / 2 + var1; // L: 501
		int var8 = var4 / 2 + var2 - 18 - var6; // L: 502
		Rasterizer2D.Rasterizer2D_fillRectangle(var1, var2, var3, var4, -16777216); // L: 503
		Rasterizer2D.Rasterizer2D_drawRectangle(var7 - 152, var8, 304, 34, -65536); // L: 504
		Rasterizer2D.Rasterizer2D_fillRectangle(var7 - 150, var8 + 2, var5 * 3, 30, -65536); // L: 505
		this.font.drawCentered("Loading...", var7, var6 + var8, -1, -1); // L: 506
	} // L: 507

	@ObfuscatedName("y")
	@ObfuscatedSignature(
		descriptor = "(II)F",
		garbageValue = "2134906459"
	)
	@Export("getZoomFromPercentage")
	float getZoomFromPercentage(int var1) {
		if (var1 == 25) { // L: 510
			return 1.0F;
		} else if (var1 == 37) { // L: 511
			return 1.5F;
		} else if (var1 == 50) {
			return 2.0F; // L: 512
		} else if (var1 == 75) { // L: 513
			return 3.0F;
		} else {
			return var1 == 100 ? 4.0F : 8.0F; // L: 514 515
		}
	}

	@ObfuscatedName("ah")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "-1992180022"
	)
	@Export("getZoomLevel")
	public int getZoomLevel() {
		if ((double)this.zoomTarget == 1.0D) { // L: 519
			return 25;
		} else if (1.5D == (double)this.zoomTarget) { // L: 520
			return 37;
		} else if (2.0D == (double)this.zoomTarget) { // L: 521
			return 50;
		} else if ((double)this.zoomTarget == 3.0D) {
			return 75; // L: 522
		} else {
			return (double)this.zoomTarget == 4.0D ? 100 : 200; // L: 523 524
		}
	}

	@ObfuscatedName("ao")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "1"
	)
	@Export("loadCache")
	public void loadCache() {
		this.cacheLoader.load(); // L: 528
	} // L: 529

	@ObfuscatedName("ab")
	@ObfuscatedSignature(
		descriptor = "(I)Z",
		garbageValue = "1720124749"
	)
	@Export("isCacheLoaded")
	public boolean isCacheLoaded() {
		return this.cacheLoader.isLoaded(); // L: 532
	}

	@ObfuscatedName("an")
	@ObfuscatedSignature(
		descriptor = "(II)Lhr;",
		garbageValue = "505103684"
	)
	@Export("getMapArea")
	public WorldMapArea getMapArea(int var1) {
		Iterator var2 = this.details.values().iterator(); // L: 536

		WorldMapArea var3;
		do {
			if (!var2.hasNext()) { // L: 543
				return null; // L: 544
			}

			var3 = (WorldMapArea)var2.next(); // L: 537
		} while(var3.getId() != var1); // L: 539

		return var3; // L: 540
	}

	@ObfuscatedName("ax")
	@ObfuscatedSignature(
		descriptor = "(III)V",
		garbageValue = "-808391872"
	)
	@Export("setWorldMapPositionTarget")
	public void setWorldMapPositionTarget(int var1, int var2) {
		if (this.currentMapArea != null && this.currentMapArea.containsPosition(var1, var2)) { // L: 548
			this.worldMapTargetX = var1 - this.currentMapArea.getRegionLowX() * 64; // L: 551
			this.worldMapTargetY = var2 - this.currentMapArea.getRegionLowY() * 64; // L: 552
		}
	} // L: 549 553

	@ObfuscatedName("am")
	@ObfuscatedSignature(
		descriptor = "(IIB)V",
		garbageValue = "-93"
	)
	@Export("setWorldMapPositionTargetInstant")
	public void setWorldMapPositionTargetInstant(int var1, int var2) {
		if (this.currentMapArea != null) { // L: 556
			this.setWorldMapPosition(var1 - this.currentMapArea.getRegionLowX() * 64, var2 - this.currentMapArea.getRegionLowY() * 64, true); // L: 559
			this.worldMapTargetX = -1; // L: 560
			this.worldMapTargetY = -1; // L: 561
		}
	} // L: 557 562

	@ObfuscatedName("az")
	@ObfuscatedSignature(
		descriptor = "(IIIB)V",
		garbageValue = "39"
	)
	@Export("jumpToSourceCoord")
	public void jumpToSourceCoord(int var1, int var2, int var3) {
		if (this.currentMapArea != null) { // L: 565
			int[] var4 = this.currentMapArea.position(var1, var2, var3); // L: 568
			if (var4 != null) { // L: 569
				this.setWorldMapPositionTarget(var4[0], var4[1]); // L: 570
			}

		}
	} // L: 566 572

	@ObfuscatedName("au")
	@ObfuscatedSignature(
		descriptor = "(IIIB)V",
		garbageValue = "0"
	)
	@Export("jumpToSourceCoordInstant")
	public void jumpToSourceCoordInstant(int var1, int var2, int var3) {
		if (this.currentMapArea != null) { // L: 575
			int[] var4 = this.currentMapArea.position(var1, var2, var3); // L: 578
			if (var4 != null) { // L: 579
				this.setWorldMapPositionTargetInstant(var4[0], var4[1]); // L: 580
			}

		}
	} // L: 576 582

	@ObfuscatedName("av")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "636016196"
	)
	@Export("getDisplayX")
	public int getDisplayX() {
		return this.currentMapArea == null ? -1 : this.centerTileX + this.currentMapArea.getRegionLowX() * 64; // L: 585 586 588
	}

	@ObfuscatedName("ap")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "880174523"
	)
	@Export("getDisplayY")
	public int getDisplayY() {
		return this.currentMapArea == null ? -1 : this.centerTileY + this.currentMapArea.getRegionLowY() * 64; // L: 592 593 595
	}

	@ObfuscatedName("ac")
	@ObfuscatedSignature(
		descriptor = "(I)Lju;",
		garbageValue = "-2092461394"
	)
	@Export("getDisplayCoord")
	public Coord getDisplayCoord() {
		return this.currentMapArea == null ? null : this.currentMapArea.coord(this.getDisplayX(), this.getDisplayY()); // L: 599 600 602
	}

	@ObfuscatedName("aj")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "-943773093"
	)
	@Export("getDisplayWith")
	public int getDisplayWith() {
		return this.worldMapDisplayWidth; // L: 606
	}

	@ObfuscatedName("af")
	@ObfuscatedSignature(
		descriptor = "(B)I",
		garbageValue = "-70"
	)
	@Export("getDisplayHeight")
	public int getDisplayHeight() {
		return this.worldMapDisplayHeight; // L: 610
	}

	@ObfuscatedName("ar")
	@ObfuscatedSignature(
		descriptor = "(IB)V",
		garbageValue = "20"
	)
	@Export("setMaxFlashCount")
	public void setMaxFlashCount(int var1) {
		if (var1 >= 1) { // L: 614
			this.maxFlashCount = var1; // L: 615
		}

	} // L: 617

	@ObfuscatedName("ag")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "1923539935"
	)
	@Export("resetMaxFlashCount")
	public void resetMaxFlashCount() {
		this.maxFlashCount = 3; // L: 620
	} // L: 621

	@ObfuscatedName("al")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "2076930032"
	)
	@Export("setCyclesPerFlash")
	public void setCyclesPerFlash(int var1) {
		if (var1 >= 1) { // L: 624
			this.cyclesPerFlash = var1; // L: 625
		}

	} // L: 627

	@ObfuscatedName("aa")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-1874637129"
	)
	@Export("resetCyclesPerFlash")
	public void resetCyclesPerFlash() {
		this.cyclesPerFlash = 50; // L: 630
	} // L: 631

	@ObfuscatedName("as")
	@ObfuscatedSignature(
		descriptor = "(ZI)V",
		garbageValue = "-1694447666"
	)
	@Export("setPerpetualFlash")
	public void setPerpetualFlash(boolean var1) {
		this.perpetualFlash = var1; // L: 634
	} // L: 635

	@ObfuscatedName("at")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "1277168471"
	)
	@Export("flashElement")
	public void flashElement(int var1) {
		this.flashingElements = new HashSet(); // L: 638
		this.flashingElements.add(var1); // L: 639
		this.flashCount = 0; // L: 640
		this.flashCycle = 0; // L: 641
	} // L: 642

	@ObfuscatedName("ai")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "-797221037"
	)
	@Export("flashCategory")
	public void flashCategory(int var1) {
		this.flashingElements = new HashSet(); // L: 645
		this.flashCount = 0; // L: 646
		this.flashCycle = 0; // L: 647

		for (int var2 = 0; var2 < class408.WorldMapElement_count; ++var2) { // L: 648
			if (class78.WorldMapElement_get(var2) != null && class78.WorldMapElement_get(var2).category == var1) { // L: 649 652
				this.flashingElements.add(class78.WorldMapElement_get(var2).objectId); // L: 653
			}
		}

	} // L: 656

	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-72"
	)
	@Export("stopCurrentFlashes")
	public void stopCurrentFlashes() {
		this.flashingElements = null; // L: 659
	} // L: 660

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(ZB)V",
		garbageValue = "-36"
	)
	@Export("setElementsDisabled")
	public void setElementsDisabled(boolean var1) {
		this.elementsDisabled = !var1; // L: 663
	} // L: 664

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(IZI)V",
		garbageValue = "-1255767815"
	)
	@Export("disableElement")
	public void disableElement(int var1, boolean var2) {
		if (!var2) { // L: 667
			this.enabledElements.add(var1); // L: 668
		} else {
			this.enabledElements.remove(var1); // L: 671
		}

		this.method6910(); // L: 673
	} // L: 674

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(IZB)V",
		garbageValue = "-96"
	)
	@Export("setCategoryDisabled")
	public void setCategoryDisabled(int var1, boolean var2) {
		if (!var2) { // L: 677
			this.enabledCategories.add(var1); // L: 678
		} else {
			this.enabledCategories.remove(var1); // L: 681
		}

		for (int var3 = 0; var3 < class408.WorldMapElement_count; ++var3) { // L: 683
			if (class78.WorldMapElement_get(var3) != null && class78.WorldMapElement_get(var3).category == var1) { // L: 684 687
				int var4 = class78.WorldMapElement_get(var3).objectId; // L: 688
				if (!var2) { // L: 689
					this.enabledElementIds.add(var4); // L: 690
				} else {
					this.enabledElementIds.remove(var4); // L: 693
				}
			}
		}

		this.method6910(); // L: 697
	} // L: 698

	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "(S)Z",
		garbageValue = "23313"
	)
	@Export("getElementsDisabled")
	public boolean getElementsDisabled() {
		return !this.elementsDisabled; // L: 701
	}

	@ObfuscatedName("ad")
	@ObfuscatedSignature(
		descriptor = "(IB)Z",
		garbageValue = "-81"
	)
	@Export("isElementDisabled")
	public boolean isElementDisabled(int var1) {
		return !this.enabledElements.contains(var1); // L: 705
	}

	@ObfuscatedName("bp")
	@ObfuscatedSignature(
		descriptor = "(II)Z",
		garbageValue = "-264694018"
	)
	@Export("isCategoryDisabled")
	public boolean isCategoryDisabled(int var1) {
		return !this.enabledCategories.contains(var1); // L: 709
	}

	@ObfuscatedName("bd")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "878025403"
	)
	void method6910() {
		this.field4351.clear(); // L: 713
		this.field4351.addAll(this.enabledElements); // L: 714
		this.field4351.addAll(this.enabledElementIds); // L: 715
	} // L: 716

	@ObfuscatedName("ba")
	@ObfuscatedSignature(
		descriptor = "(IIIIIIB)V",
		garbageValue = "1"
	)
	@Export("addElementMenuOptions")
	public void addElementMenuOptions(int var1, int var2, int var3, int var4, int var5, int var6) {
		if (this.cacheLoader.isLoaded()) { // L: 719
			int var7 = (int)Math.ceil((double)((float)var3 / this.zoom)); // L: 722
			int var8 = (int)Math.ceil((double)((float)var4 / this.zoom)); // L: 723
			List var9 = this.worldMapManager.method4617(this.centerTileX - var7 / 2 - 1, this.centerTileY - var8 / 2 - 1, var7 / 2 + this.centerTileX + 1, var8 / 2 + this.centerTileY + 1, var1, var2, var3, var4, var5, var6); // L: 724
			if (!var9.isEmpty()) { // L: 725
				Iterator var10 = var9.iterator();

				boolean var13;
				do {
					if (!var10.hasNext()) { // L: 728
						return; // L: 744
					}

					AbstractWorldMapIcon var11 = (AbstractWorldMapIcon)var10.next(); // L: 729
					WorldMapElement var12 = class78.WorldMapElement_get(var11.getElement()); // L: 731
					var13 = false; // L: 732

					for (int var14 = this.menuOpcodes.length - 1; var14 >= 0; --var14) { // L: 733
						if (var12.menuActions[var14] != null) { // L: 734
							class11.insertMenuItemNoShift(var12.menuActions[var14], var12.menuTargetName, this.menuOpcodes[var14], var11.getElement(), var11.coord1.packed(), var11.coord2.packed()); // L: 735
							var13 = true; // L: 736
						}
					}
				} while(!var13); // L: 739

			}
		}
	} // L: 720 726 740

	@ObfuscatedName("bq")
	@ObfuscatedSignature(
		descriptor = "(ILju;I)Lju;",
		garbageValue = "-998971600"
	)
	public Coord method6988(int var1, Coord var2) {
		if (!this.cacheLoader.isLoaded()) { // L: 747
			return null; // L: 748
		} else if (!this.worldMapManager.isLoaded()) { // L: 750
			return null; // L: 751
		} else if (!this.currentMapArea.containsPosition(var2.x, var2.y)) { // L: 753
			return null; // L: 754
		} else {
			HashMap var3 = this.worldMapManager.buildIcons(); // L: 756
			List var4 = (List)var3.get(var1); // L: 757
			if (var4 != null && !var4.isEmpty()) { // L: 758
				AbstractWorldMapIcon var5 = null; // L: 761
				int var6 = -1; // L: 762
				Iterator var7 = var4.iterator(); // L: 763

				while (true) {
					AbstractWorldMapIcon var8;
					int var11;
					do {
						if (!var7.hasNext()) {
							return var5.coord2; // L: 778
						}

						var8 = (AbstractWorldMapIcon)var7.next(); // L: 764
						int var9 = var8.coord2.x - var2.x; // L: 766
						int var10 = var8.coord2.y - var2.y; // L: 767
						var11 = var9 * var9 + var10 * var10; // L: 768
						if (var11 == 0) { // L: 769
							return var8.coord2; // L: 770
						}
					} while(var11 >= var6 && var5 != null); // L: 772

					var5 = var8; // L: 773
					var6 = var11; // L: 774
				}
			} else {
				return null; // L: 759
			}
		}
	}

	@ObfuscatedName("bg")
	@ObfuscatedSignature(
		descriptor = "(IILju;Lju;I)V",
		garbageValue = "-1595615747"
	)
	@Export("worldMapMenuAction")
	public void worldMapMenuAction(int var1, int var2, Coord var3, Coord var4) {
		ScriptEvent var5 = new ScriptEvent(); // L: 782
		WorldMapEvent var6 = new WorldMapEvent(var2, var3, var4); // L: 783
		var5.setArgs(new Object[]{var6}); // L: 784
		switch(var1) { // L: 785
		case 1008:
			var5.setType(10); // L: 788
			break;
		case 1009:
			var5.setType(11); // L: 793
			break; // L: 794
		case 1010:
			var5.setType(12); // L: 803
			break; // L: 804
		case 1011:
			var5.setType(13); // L: 798
			break; // L: 799
		case 1012:
			var5.setType(14); // L: 808
		}

		class285.runScriptEvent(var5); // L: 812
	} // L: 813

	@ObfuscatedName("br")
	@ObfuscatedSignature(
		descriptor = "(I)Lij;",
		garbageValue = "-542110150"
	)
	@Export("iconStart")
	public AbstractWorldMapIcon iconStart() {
		if (!this.cacheLoader.isLoaded()) { // L: 816
			return null; // L: 817
		} else if (!this.worldMapManager.isLoaded()) { // L: 819
			return null; // L: 820
		} else {
			HashMap var1 = this.worldMapManager.buildIcons(); // L: 822
			this.field4356 = new LinkedList(); // L: 823
			Iterator var2 = var1.values().iterator(); // L: 824

			while (var2.hasNext()) {
				List var3 = (List)var2.next(); // L: 825
				this.field4356.addAll(var3); // L: 827
			}

			this.iconIterator = this.field4356.iterator(); // L: 830
			return this.iconNext(); // L: 831
		}
	}

	@ObfuscatedName("bi")
	@ObfuscatedSignature(
		descriptor = "(I)Lij;",
		garbageValue = "-1822141066"
	)
	@Export("iconNext")
	public AbstractWorldMapIcon iconNext() {
		if (this.iconIterator == null) { // L: 835
			return null; // L: 836
		} else {
			AbstractWorldMapIcon var1;
			do {
				if (!this.iconIterator.hasNext()) { // L: 838
					return null; // L: 844
				}

				var1 = (AbstractWorldMapIcon)this.iconIterator.next(); // L: 839
			} while(var1.getElement() == -1); // L: 840

			return var1; // L: 841
		}
	}
}
