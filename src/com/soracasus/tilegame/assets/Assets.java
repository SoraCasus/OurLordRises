package com.soracasus.tilegame.assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class Assets {

	private static final Assets INSTANCE = new Assets();

	public static Assets getInstance () {
		return INSTANCE;
	}

	private HashMap<String, BufferedImage> textures;
	private SpriteSheet terrain;

	private Assets () {
		textures = new HashMap<>();
		terrain = new SpriteSheet(getTexture("/textures/terrain.png"));
	}

	public BufferedImage[][] loadPlayerMoveAnim (SpriteSheet sheet) {
		return new BufferedImage[][]{
				// Down
				{
						sheet.crop(0, 0, 32, 32),
						sheet.crop(64, 0, 32, 32)
				},
				// Left
				{
						sheet.crop(0, 32, 32, 32),
						sheet.crop(64, 32, 32, 32)
				},
				// Right
				{
						sheet.crop(0, 64, 32, 32),
						sheet.crop(64, 64, 32, 32) },
				// Up
				{
						sheet.crop(0, 96, 32, 32),
						sheet.crop(64, 96, 32, 32)
				}
		};
	}

	public BufferedImage[] loadPlayerStills (SpriteSheet sheet) {
		return new BufferedImage[]{
				sheet.crop(32, 0, 32, 32),
				sheet.crop(32, 32, 32, 32),
				sheet.crop(32, 64, 32, 32),
				sheet.crop(32, 96, 32, 32),
		};
	}

	public BufferedImage getTexture (String path) {
		if (!textures.containsKey(path)) {
			// Attempt to load the texture
			BufferedImage image = ImageLoader.loadImage(path);
			if (image == null) {
				return null;
			}
			textures.put(path, image);
		}
		return textures.get(path);
	}

	public SpriteSheet getTerrain () {
		return terrain;
	}
}
