package osrs;

import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedGetter;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ku")
@Implements("AbstractArchive")
public abstract class AbstractArchive {
	@ObfuscatedName("y")
	@ObfuscatedSignature(
		descriptor = "Lpt;"
	)
	@Export("gzipDecompressor")
	static GZipDecompressor gzipDecompressor;
	@ObfuscatedName("an")
	@ObfuscatedGetter(
		intValue = 1947791089
	)
	static int field3843;
	@ObfuscatedName("by")
	@ObfuscatedSignature(
		descriptor = "[Lpa;"
	)
	@Export("worldSelectFlagSprites")
	static IndexedSprite[] worldSelectFlagSprites;
	@ObfuscatedName("h")
	@ObfuscatedGetter(
		intValue = -1495351037
	)
	@Export("groupCount")
	int groupCount;
	@ObfuscatedName("g")
	@Export("groupIds")
	int[] groupIds;
	@ObfuscatedName("e")
	@Export("groupNameHashes")
	int[] groupNameHashes;
	@ObfuscatedName("a")
	@ObfuscatedSignature(
		descriptor = "Lpu;"
	)
	@Export("groupNameHashTable")
    IntHashTable groupNameHashTable;
	@ObfuscatedName("u")
	@Export("groupCrcs")
	int[] groupCrcs;
	@ObfuscatedName("k")
	@Export("groupVersions")
	int[] groupVersions;
	@ObfuscatedName("f")
	@Export("fileCounts")
	int[] fileCounts;
	@ObfuscatedName("l")
	@Export("fileIds")
	int[][] fileIds;
	@ObfuscatedName("q")
	@Export("fileNameHashes")
	int[][] fileNameHashes;
	@ObfuscatedName("x")
	@ObfuscatedSignature(
		descriptor = "[Lpu;"
	)
	@Export("fileNameHashTables")
	IntHashTable[] fileNameHashTables;
	@ObfuscatedName("z")
	@Export("groups")
	Object[] groups;
	@ObfuscatedName("i")
	@Export("files")
	Object[][] files;
	@ObfuscatedName("ah")
	@ObfuscatedGetter(
		intValue = 880297365
	)
	@Export("hash")
	public int hash;
	@ObfuscatedName("ao")
	@Export("releaseGroups")
	boolean releaseGroups;
	@ObfuscatedName("ab")
	@Export("shallowFiles")
	boolean shallowFiles;

	static {
		gzipDecompressor = new GZipDecompressor(); // L: 23
		field3843 = 0; // L: 27
	}

	AbstractArchive(boolean var1, boolean var2) {
		this.releaseGroups = var1; // L: 32
		this.shallowFiles = var2; // L: 33
	} // L: 34

	@ObfuscatedName("p")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "-2090166080"
	)
	@Export("loadRegionFromGroup")
	void loadRegionFromGroup(int var1) {
	} // L: 123

	@ObfuscatedName("m")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "2024016984"
	)
	@Export("loadGroup")
	void loadGroup(int var1) {
	} // L: 209

	@ObfuscatedName("n")
	@ObfuscatedSignature(
		descriptor = "(IB)I",
		garbageValue = "35"
	)
	@Export("groupLoadPercent")
	int groupLoadPercent(int var1) {
		return this.groups[var1] != null ? 100 : 0; // L: 179 180
	}

	@ObfuscatedName("aq")
	@ObfuscatedSignature(
		descriptor = "([BI)V",
		garbageValue = "-1691736536"
	)
	@Export("decodeIndex")
	void decodeIndex(byte[] var1) {
		int var3 = var1.length; // L: 38
		int var2 = Friend.method6298(var1, 0, var3); // L: 40
		this.hash = var2; // L: 42
		Buffer var4 = new Buffer(ViewportMouse.decompressBytes(var1)); // L: 43
		int var5 = var4.readUnsignedByte(); // L: 44
		if (var5 >= 5 && var5 <= 7) { // L: 45
			if (var5 >= 6) { // L: 46
				var4.readInt(); // L: 47
			}

			int var6 = var4.readUnsignedByte(); // L: 50
			if (var5 >= 7) { // L: 51
				this.groupCount = var4.method7561();
			} else {
				this.groupCount = var4.readUnsignedShort(); // L: 52
			}

			int var7 = 0; // L: 53
			int var8 = -1; // L: 54
			this.groupIds = new int[this.groupCount]; // L: 55
			int var9;
			if (var5 >= 7) { // L: 56
				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 57
					this.groupIds[var9] = var7 += var4.method7561(); // L: 58
					if (this.groupIds[var9] > var8) { // L: 59
						var8 = this.groupIds[var9];
					}
				}
			} else {
				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 63
					this.groupIds[var9] = var7 += var4.readUnsignedShort(); // L: 64
					if (this.groupIds[var9] > var8) { // L: 65
						var8 = this.groupIds[var9];
					}
				}
			}

			this.groupCrcs = new int[var8 + 1]; // L: 68
			this.groupVersions = new int[var8 + 1]; // L: 69
			this.fileCounts = new int[var8 + 1]; // L: 70
			this.fileIds = new int[var8 + 1][]; // L: 71
			this.groups = new Object[var8 + 1]; // L: 72
			this.files = new Object[var8 + 1][]; // L: 73
			if (var6 != 0) { // L: 74
				this.groupNameHashes = new int[var8 + 1]; // L: 75

				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 76
					this.groupNameHashes[this.groupIds[var9]] = var4.readInt();
				}

				this.groupNameHashTable = new IntHashTable(this.groupNameHashes); // L: 77
			}

			for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 79
				this.groupCrcs[this.groupIds[var9]] = var4.readInt();
			}

			for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 80
				this.groupVersions[this.groupIds[var9]] = var4.readInt();
			}

			for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 81
				this.fileCounts[this.groupIds[var9]] = var4.readUnsignedShort();
			}

			int var10;
			int var11;
			int var12;
			int var13;
			int var14;
			if (var5 >= 7) { // L: 82
				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 83
					var10 = this.groupIds[var9]; // L: 84
					var11 = this.fileCounts[var10]; // L: 85
					var7 = 0; // L: 86
					var12 = -1; // L: 87
					this.fileIds[var10] = new int[var11]; // L: 88

					for (var13 = 0; var13 < var11; ++var13) { // L: 89
						var14 = this.fileIds[var10][var13] = var7 += var4.method7561(); // L: 90
						if (var14 > var12) { // L: 91
							var12 = var14;
						}
					}

					this.files[var10] = new Object[var12 + 1]; // L: 93
				}
			} else {
				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 97
					var10 = this.groupIds[var9]; // L: 98
					var11 = this.fileCounts[var10]; // L: 99
					var7 = 0; // L: 100
					var12 = -1; // L: 101
					this.fileIds[var10] = new int[var11]; // L: 102

					for (var13 = 0; var13 < var11; ++var13) { // L: 103
						var14 = this.fileIds[var10][var13] = var7 += var4.readUnsignedShort(); // L: 104
						if (var14 > var12) { // L: 105
							var12 = var14;
						}
					}

					this.files[var10] = new Object[var12 + 1]; // L: 107
				}
			}

			if (var6 != 0) { // L: 110
				this.fileNameHashes = new int[var8 + 1][]; // L: 111
				this.fileNameHashTables = new IntHashTable[var8 + 1]; // L: 112

				for (var9 = 0; var9 < this.groupCount; ++var9) { // L: 113
					var10 = this.groupIds[var9]; // L: 114
					var11 = this.fileCounts[var10]; // L: 115
					this.fileNameHashes[var10] = new int[this.files[var10].length]; // L: 116

					for (var12 = 0; var12 < var11; ++var12) { // L: 117
						this.fileNameHashes[var10][this.fileIds[var10][var12]] = var4.readInt();
					}

					this.fileNameHashTables[var10] = new IntHashTable(this.fileNameHashes[var10]); // L: 118
				}
			}

		} else {
			throw new RuntimeException("");
		}
	} // L: 121

	@ObfuscatedName("aw")
	@ObfuscatedSignature(
		descriptor = "(IIB)[B",
		garbageValue = "-53"
	)
	@Export("takeFile")
	public byte[] takeFile(int var1, int var2) {
		return this.takeFileEncrypted(var1, var2, (int[])null); // L: 126
	}

	@ObfuscatedName("ay")
	@ObfuscatedSignature(
		descriptor = "(II[II)[B",
		garbageValue = "2102188024"
	)
	@Export("takeFileEncrypted")
	public byte[] takeFileEncrypted(int var1, int var2, int[] var3) {
		if (var1 >= 0 && var1 < this.files.length && this.files[var1] != null && var2 >= 0 && var2 < this.files[var1].length) { // L: 130
			if (this.files[var1][var2] == null) { // L: 131
				boolean var4 = this.buildFiles(var1, var3); // L: 132
				if (!var4) { // L: 133
					this.loadGroup(var1); // L: 134
					var4 = this.buildFiles(var1, var3); // L: 135
					if (!var4) { // L: 136
						return null;
					}
				}
			}

			byte[] var5 = GrandExchangeOfferOwnWorldComparator.method1147(this.files[var1][var2], false); // L: 139
			if (this.shallowFiles) {
				this.files[var1][var2] = null; // L: 140
			}

			return var5; // L: 141
		} else {
			return null;
		}
	}

	@ObfuscatedName("ae")
	@ObfuscatedSignature(
		descriptor = "(IIS)Z",
		garbageValue = "-22135"
	)
	@Export("tryLoadFile")
	public boolean tryLoadFile(int var1, int var2) {
		if (var1 >= 0 && var1 < this.files.length && this.files[var1] != null && var2 >= 0 && var2 < this.files[var1].length) { // L: 145
			if (this.files[var1][var2] != null) { // L: 146
				return true;
			} else if (this.groups[var1] != null) { // L: 147
				return true;
			} else {
				this.loadGroup(var1); // L: 148
				return this.groups[var1] != null; // L: 149
			}
		} else {
			return false; // L: 150
		}
	}

	@ObfuscatedName("ak")
	@ObfuscatedSignature(
		descriptor = "(IB)Z",
		garbageValue = "-43"
	)
	public boolean method5595(int var1) {
		if (this.files.length == 1) { // L: 154
			return this.tryLoadFile(0, var1);
		} else if (this.files[var1].length == 1) {
			return this.tryLoadFile(var1, 0); // L: 155
		} else {
			throw new RuntimeException(); // L: 156
		}
	}

	@ObfuscatedName("ad")
	@ObfuscatedSignature(
		descriptor = "(II)Z",
		garbageValue = "-2108715945"
	)
	@Export("tryLoadGroup")
	public boolean tryLoadGroup(int var1) {
		if (this.groups[var1] != null) { // L: 160
			return true;
		} else {
			this.loadGroup(var1); // L: 161
			return this.groups[var1] != null; // L: 162
		}
	}

	@ObfuscatedName("bp")
	@ObfuscatedSignature(
		descriptor = "(B)Z",
		garbageValue = "89"
	)
	@Export("isFullyLoaded")
	public boolean isFullyLoaded() {
		boolean var1 = true; // L: 167

		for (int var2 = 0; var2 < this.groupIds.length; ++var2) { // L: 168
			int var3 = this.groupIds[var2]; // L: 169
			if (this.groups[var3] == null) { // L: 170
				this.loadGroup(var3); // L: 171
				if (this.groups[var3] == null) { // L: 172
					var1 = false;
				}
			}
		}

		return var1; // L: 175
	}

	@ObfuscatedName("bd")
	@ObfuscatedSignature(
		descriptor = "(II)[B",
		garbageValue = "1730057328"
	)
	@Export("takeFileFlat")
	public byte[] takeFileFlat(int var1) {
		if (this.files.length == 1) { // L: 184
			return this.takeFile(0, var1);
		} else if (this.files[var1].length == 1) {
			return this.takeFile(var1, 0); // L: 185
		} else {
			throw new RuntimeException(); // L: 186
		}
	}

	@ObfuscatedName("ba")
	@ObfuscatedSignature(
		descriptor = "(IIB)[B",
		garbageValue = "51"
	)
	@Export("getFile")
	public byte[] getFile(int var1, int var2) {
		if (var1 >= 0 && var1 < this.files.length && this.files[var1] != null && var2 >= 0 && var2 < this.files[var1].length) { // L: 190
			if (this.files[var1][var2] == null) { // L: 191
				boolean var3 = this.buildFiles(var1, (int[])null); // L: 192
				if (!var3) { // L: 193
					this.loadGroup(var1); // L: 194
					var3 = this.buildFiles(var1, (int[])null); // L: 195
					if (!var3) { // L: 196
						return null;
					}
				}
			}

			byte[] var4 = GrandExchangeOfferOwnWorldComparator.method1147(this.files[var1][var2], false); // L: 199
			return var4; // L: 200
		} else {
			return null;
		}
	}

	@ObfuscatedName("bq")
	@ObfuscatedSignature(
		descriptor = "(II)[B",
		garbageValue = "1978488509"
	)
	@Export("getFileFlat")
	public byte[] getFileFlat(int var1) {
		if (this.files.length == 1) { // L: 204
			return this.getFile(0, var1);
		} else if (this.files[var1].length == 1) {
			return this.getFile(var1, 0); // L: 205
		} else {
			throw new RuntimeException(); // L: 206
		}
	}

	@ObfuscatedName("bg")
	@ObfuscatedSignature(
		descriptor = "(II)[I",
		garbageValue = "1166188734"
	)
	@Export("getGroupFileIds")
	public int[] getGroupFileIds(int var1) {
		return var1 >= 0 && var1 < this.fileIds.length ? this.fileIds[var1] : null; // L: 212 213 215
	}

	@ObfuscatedName("br")
	@ObfuscatedSignature(
		descriptor = "(IB)I",
		garbageValue = "-1"
	)
	@Export("getGroupFileCount")
	public int getGroupFileCount(int var1) {
		return this.files[var1].length; // L: 219
	}

	@ObfuscatedName("bi")
	@ObfuscatedSignature(
		descriptor = "(I)I",
		garbageValue = "-949601640"
	)
	@Export("getGroupCount")
	public int getGroupCount() {
		return this.files.length; // L: 223
	}

	@ObfuscatedName("bm")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-1889298595"
	)
	@Export("clearGroups")
	public void clearGroups() {
		for (int var1 = 0; var1 < this.groups.length; ++var1) { // L: 227
			this.groups[var1] = null;
		}

	} // L: 228

	@ObfuscatedName("bs")
	@ObfuscatedSignature(
		descriptor = "(II)V",
		garbageValue = "1137038905"
	)
	@Export("clearFilesGroup")
	public void clearFilesGroup(int var1) {
		for (int var2 = 0; var2 < this.files[var1].length; ++var2) { // L: 231
			this.files[var1][var2] = null;
		}

	} // L: 232

	@ObfuscatedName("bv")
	@ObfuscatedSignature(
		descriptor = "(B)V",
		garbageValue = "-6"
	)
	@Export("clearFiles")
	public void clearFiles() {
		for (int var1 = 0; var1 < this.files.length; ++var1) { // L: 235
			if (this.files[var1] != null) { // L: 236
				for (int var2 = 0; var2 < this.files[var1].length; ++var2) { // L: 237
					this.files[var1][var2] = null;
				}
			}
		}

	} // L: 240

	@ObfuscatedName("bb")
	@ObfuscatedSignature(
		descriptor = "(I[II)Z",
		garbageValue = "16711680"
	)
	@Export("buildFiles")
	boolean buildFiles(int var1, int[] var2) {
		if (this.groups[var1] == null) { // L: 243
			return false;
		} else {
			int var3 = this.fileCounts[var1]; // L: 244
			int[] var4 = this.fileIds[var1]; // L: 245
			Object[] var5 = this.files[var1]; // L: 246
			boolean var6 = true; // L: 247

			for (int var7 = 0; var7 < var3; ++var7) { // L: 248
				if (var5[var4[var7]] == null) { // L: 249
					var6 = false; // L: 250
					break;
				}
			}

			if (var6) { // L: 254
				return true;
			} else {
				byte[] var21;
				if (var2 == null || var2[0] == 0 && var2[1] == 0 && var2[2] == 0 && var2[3] == 0) { // L: 256
					var21 = GrandExchangeOfferOwnWorldComparator.method1147(this.groups[var1], false); // L: 261
				} else {
					var21 = GrandExchangeOfferOwnWorldComparator.method1147(this.groups[var1], true); // L: 257
					Buffer var8 = new Buffer(var21); // L: 258
					var8.xteaDecrypt(var2, 5, var8.array.length); // L: 259
				}

				byte[] var25 = ViewportMouse.decompressBytes(var21); // L: 264
				if (this.releaseGroups) { // L: 282
					this.groups[var1] = null;
				}

				int var10;
				if (var3 > 1) { // L: 283
					int var22 = var25.length; // L: 284
					--var22; // L: 285
					var10 = var25[var22] & 255;
					var22 -= var10 * var3 * 4; // L: 286
					Buffer var11 = new Buffer(var25); // L: 287
					int[] var12 = new int[var3]; // L: 288
					var11.offset = var22; // L: 289

					int var14;
					int var15;
					for (int var13 = 0; var13 < var10; ++var13) { // L: 290
						var14 = 0; // L: 291

						for (var15 = 0; var15 < var3; ++var15) { // L: 292
							var14 += var11.readInt(); // L: 293
							var12[var15] += var14; // L: 294
						}
					}

					byte[][] var23 = new byte[var3][]; // L: 297

					for (var14 = 0; var14 < var3; ++var14) { // L: 298
						var23[var14] = new byte[var12[var14]]; // L: 299
						var12[var14] = 0; // L: 300
					}

					var11.offset = var22; // L: 302
					var14 = 0; // L: 303

					int var17;
					for (var15 = 0; var15 < var10; ++var15) { // L: 304
						int var24 = 0; // L: 305

						for (var17 = 0; var17 < var3; ++var17) { // L: 306
							var24 += var11.readInt(); // L: 307
							System.arraycopy(var25, var14, var23[var17], var12[var17], var24); // L: 308
							var12[var17] += var24; // L: 309
							var14 += var24; // L: 310
						}
					}

					for (var15 = 0; var15 < var3; ++var15) { // L: 313
						if (!this.shallowFiles) { // L: 314
							var17 = var4[var15]; // L: 316
							byte[] var19 = var23[var15]; // L: 318
							Object var18;
							if (var19 == null) { // L: 320
								var18 = null; // L: 321
							} else if (var19.length > 136) { // L: 324
								DirectByteArrayCopier var20 = new DirectByteArrayCopier(); // L: 326
								var20.set(var19); // L: 327
								var18 = var20; // L: 328
							} else {
								var18 = var19; // L: 333
							}

							var5[var17] = var18; // L: 335
						} else {
							var5[var4[var15]] = var23[var15]; // L: 337
						}
					}
				} else if (!this.shallowFiles) { // L: 341
					var10 = var4[0]; // L: 343
					Object var26;
					if (var25 == null) { // L: 346
						var26 = null; // L: 347
					} else if (var25.length > 136) { // L: 350
						DirectByteArrayCopier var27 = new DirectByteArrayCopier(); // L: 352
						var27.set(var25); // L: 353
						var26 = var27; // L: 354
					} else {
						var26 = var25; // L: 359
					}

					var5[var10] = var26; // L: 361
				} else {
					var5[var4[0]] = var25; // L: 363
				}

				return true; // L: 365
			}
		}
	}

	@ObfuscatedName("bc")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)I",
		garbageValue = "1240338847"
	)
	@Export("getGroupId")
	public int getGroupId(String var1) {
		var1 = var1.toLowerCase(); // L: 369
		return this.groupNameHashTable.get(Skills.hashString(var1)); // L: 370
	}

	@ObfuscatedName("bx")
	@ObfuscatedSignature(
		descriptor = "(ILjava/lang/String;I)I",
		garbageValue = "1416207334"
	)
	@Export("getFileId")
	public int getFileId(int var1, String var2) {
		var2 = var2.toLowerCase(); // L: 374
		return this.fileNameHashTables[var1].get(Skills.hashString(var2)); // L: 375
	}

	@ObfuscatedName("bt")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;Ljava/lang/String;B)Z",
		garbageValue = "-124"
	)
	@Export("isValidFileName")
	public boolean isValidFileName(String var1, String var2) {
		var1 = var1.toLowerCase(); // L: 379
		var2 = var2.toLowerCase(); // L: 380
		int var3 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 381
		if (var3 < 0) { // L: 382
			return false;
		} else {
			int var4 = this.fileNameHashTables[var3].get(Skills.hashString(var2)); // L: 383
			return var4 >= 0; // L: 384
		}
	}

	@ObfuscatedName("bh")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;Ljava/lang/String;B)[B",
		garbageValue = "34"
	)
	@Export("takeFileByNames")
	public byte[] takeFileByNames(String var1, String var2) {
		var1 = var1.toLowerCase(); // L: 389
		var2 = var2.toLowerCase(); // L: 390
		int var3 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 391
		int var4 = this.fileNameHashTables[var3].get(Skills.hashString(var2)); // L: 392
		return this.takeFile(var3, var4); // L: 393
	}

	@ObfuscatedName("bn")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;Ljava/lang/String;B)Z",
		garbageValue = "27"
	)
	@Export("tryLoadFileByNames")
	public boolean tryLoadFileByNames(String var1, String var2) {
		var1 = var1.toLowerCase(); // L: 397
		var2 = var2.toLowerCase(); // L: 398
		int var3 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 399
		int var4 = this.fileNameHashTables[var3].get(Skills.hashString(var2)); // L: 400
		return this.tryLoadFile(var3, var4); // L: 401
	}

	@ObfuscatedName("bj")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)Z",
		garbageValue = "-397687589"
	)
	@Export("tryLoadGroupByName")
	public boolean tryLoadGroupByName(String var1) {
		var1 = var1.toLowerCase(); // L: 405
		int var2 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 406
		return this.tryLoadGroup(var2); // L: 407
	}

	@ObfuscatedName("by")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;B)V",
		garbageValue = "-41"
	)
	@Export("loadRegionFromName")
	public void loadRegionFromName(String var1) {
		var1 = var1.toLowerCase(); // L: 411
		int var2 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 412
		if (var2 >= 0) { // L: 413
			this.loadRegionFromGroup(var2); // L: 414
		}
	} // L: 415

	@ObfuscatedName("bk")
	@ObfuscatedSignature(
		descriptor = "(Ljava/lang/String;I)I",
		garbageValue = "1233825396"
	)
	@Export("groupLoadPercentByName")
	public int groupLoadPercentByName(String var1) {
		var1 = var1.toLowerCase(); // L: 418
		int var2 = this.groupNameHashTable.get(Skills.hashString(var1)); // L: 419
		return this.groupLoadPercent(var2); // L: 420
	}

	@ObfuscatedName("hs")
	@ObfuscatedSignature(
		descriptor = "(I)V",
		garbageValue = "-2059538703"
	)
	static final void method5693() {
		for (PendingSpawn var0 = (PendingSpawn) Client.pendingSpawns.last(); var0 != null; var0 = (PendingSpawn) Client.pendingSpawns.previous()) { // L: 7978 7979 8022
			if (var0.hitpoints > 0) { // L: 7980
				--var0.hitpoints;
			}

			boolean var1;
			int var2;
			int var3;
			ObjectComposition var4;
			if (var0.hitpoints == 0) { // L: 7981
				if (var0.objectId >= 0) { // L: 7983
					var2 = var0.objectId; // L: 7985
					var3 = var0.field1080; // L: 7986
					var4 = class148.getObjectDefinition(var2); // L: 7988
					if (var3 == 11) { // L: 7989
						var3 = 10;
					}

					if (var3 >= 5 && var3 <= 8) { // L: 7990
						var3 = 4;
					}

					var1 = var4.method3491(var3); // L: 7991
					if (!var1) { // L: 7993
						continue;
					}
				}

				DevicePcmPlayerProvider.addPendingSpawnToScene(var0.plane, var0.type, var0.x, var0.y, var0.objectId, var0.field1087, var0.field1080); // L: 7995
				var0.remove(); // L: 7996
			} else {
				if (var0.delay > 0) { // L: 8000
					--var0.delay;
				}

				if (var0.delay == 0 && var0.x >= 1 && var0.y >= 1 && var0.x <= 102 && var0.y <= 102) { // L: 8001
					if (var0.id >= 0) { // L: 8003
						var2 = var0.id; // L: 8005
						var3 = var0.field1075; // L: 8006
						var4 = class148.getObjectDefinition(var2); // L: 8008
						if (var3 == 11) { // L: 8009
							var3 = 10;
						}

						if (var3 >= 5 && var3 <= 8) { // L: 8010
							var3 = 4;
						}

						var1 = var4.method3491(var3); // L: 8011
						if (!var1) { // L: 8013
							continue;
						}
					}

					DevicePcmPlayerProvider.addPendingSpawnToScene(var0.plane, var0.type, var0.x, var0.y, var0.id, var0.orientation, var0.field1075); // L: 8015
					var0.delay = -1; // L: 8016
					if (var0.id == var0.objectId && var0.objectId == -1) { // L: 8017
						var0.remove();
					} else if (var0.objectId == var0.id && var0.field1087 == var0.orientation && var0.field1080 == var0.field1075) { // L: 8018
						var0.remove();
					}
				}
			}
		}

	} // L: 8024
}
