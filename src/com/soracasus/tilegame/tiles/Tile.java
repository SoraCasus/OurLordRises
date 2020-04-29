package com.soracasus.tilegame.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {

	// Statics
	public static final int TILE_SIZE = 64;

	// Todo(Sora): Pull this out into a different class to manage
	// Static referencing child classes may lead to class loading
	// deadlock.
	public static Tile[] tiles = new Tile[256];
	public static Tile grassTile = new GrassTile(0);
	public static Tile stoneTile = new StoneTile(1);
	public static Tile dirtTile = new DirtTile(2);

	// Tile Class
	protected BufferedImage texture;
	protected final int id;

	public Tile (BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		tiles[id] = this;
	}

	public void tick () {

	}

	public void render (Graphics g, int x, int y) {
		g.drawImage(texture, x, y, TILE_SIZE, TILE_SIZE, null);
	}

	public boolean isSolid() {
		return false;
	}

	public BufferedImage getTexture () {
		return texture;
	}
}
