package com.soracasus.tilegame.tiles;

import com.soracasus.tilegame.assets.Assets;

public class StoneTile extends Tile {
	public StoneTile (int id) {
		super(Assets.getInstance().getTerrain().crop(32, 0, 32, 32), id);
	}

	@Override
	public boolean isSolid () {
		return true;
	}
}
