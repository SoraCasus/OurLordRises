package com.soracasus.tilegame.tiles;

import com.soracasus.tilegame.assets.Assets;

import java.awt.image.BufferedImage;

public class GrassTile extends Tile {

	public GrassTile (int id) {
		super(Assets.getInstance().getTerrain().crop(0, 0, 32, 32), id);
	}
}
