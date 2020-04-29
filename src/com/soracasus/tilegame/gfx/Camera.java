package com.soracasus.tilegame.gfx;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.entities.Entity;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.tiles.Tile;

public class Camera {

	private Vec2 offset;

	public Camera(Vec2 offset) {
		this.offset = offset;
	}

	private void checkBlankSpace() {

		float minX = 0;
		float maxX = Handler.getInstance().getWorld().getWidth() * Tile.TILE_SIZE;
		maxX -= Handler.getInstance().getWidth();


		 if(offset.getX() < minX) {
		 	offset.setX(minX);
		 } else if (offset.getX() > maxX) {
			offset.setX(maxX);
		 }

		 float minY = 0;
		 float maxY = Handler.getInstance().getWorld().getHeight()  * Tile.TILE_SIZE;
		 maxY -= Handler.getInstance().getHeight();

		 if(offset.getY() < minY) {
		 	offset.setY(minY);
		 } else if (offset.getY() > maxY) {
		 	offset.setY(maxY);
		 }
	}

	public void centerOnEntity(Entity target) {
		offset.setX(target.getPosition().getX() - Handler.getInstance().getWidth() / 2.0F + 32);
		offset.setY(target.getPosition().getY() - Handler.getInstance().getHeight() / 2.0F + 32);
		checkBlankSpace();
	}

	public void move(Vec2 delta) {
		offset = offset.add(delta);
	}

	public Vec2 getOffset () {
		return offset;
	}
}
