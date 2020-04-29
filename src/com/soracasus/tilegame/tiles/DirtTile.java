package com.soracasus.tilegame.tiles;

import com.soracasus.tilegame.assets.Assets;

public class DirtTile extends Tile {

	public DirtTile (int id) {
		super(Assets.getInstance().getTerrain().crop(64, 0, 32, 32), id);
	}

}
