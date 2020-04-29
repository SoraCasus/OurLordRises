package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.entities.Entity;
import com.soracasus.tilegame.entities.EnumDirection;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.tiles.Tile;
import com.soracasus.tilegame.worlds.World;

public abstract class Creature extends Entity {

	private static final int DEFAULT_HEALTH = 10;
	private static final float DEFAULT_SPEED = 3.0F;

	protected int health;
	protected float speed;
	protected Vec2 delta;

	public Creature (Vec2 position) {
		super(position);
		this.health = DEFAULT_HEALTH;
		this.speed = DEFAULT_SPEED;
		this.delta = new Vec2();
	}

	public void move () {
		if (!checkEntityCollisions(new Vec2(0, delta.getY()))) {
			moveY(delta.getY());
		}
		if (!checkEntityCollisions(new Vec2(delta.getX(), 0))) {
			moveX(delta.getX());
		}

	}

	private void moveX (float dx) {
		if (dx > 0) {
			// Moving Right
			// Todo(Sora): Extract Method
			int tx = (int) (position.getX() + dx + bounds.x + bounds.width) / Tile.TILE_SIZE;
			if (!collisionWithTile(tx, (int) (position.getY() + bounds.y) / Tile.TILE_SIZE) &&
					!collisionWithTile(tx, (int) (position.getY() + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
				Vec2 delta = new Vec2(dx, 0);
				position = position.add(delta);
			} else {
				position.setX(tx * Tile.TILE_SIZE - bounds.x - bounds.width - 1);
			}
			direction = EnumDirection.RIGHT;
		} else if (dx < 0) {
			// Moving Left
			int tx = (int) (position.getX() + dx + bounds.x) / Tile.TILE_SIZE;
			if (!collisionWithTile(tx, (int) (position.getY() + bounds.y) / Tile.TILE_SIZE) &&
					!collisionWithTile(tx, (int) (position.getY() + bounds.y + bounds.height) / Tile.TILE_SIZE)) {
				Vec2 delta = new Vec2(dx, 0);
				position = position.add(delta);
			} else {
				position.setX(tx * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.x);
			}
			direction = EnumDirection.LEFT;
		}
	}

	private void moveY (float dy) {
		if (dy > 0) {
			// Move Down
			int ty = (int) (position.getY() + dy + bounds.y + bounds.height) / Tile.TILE_SIZE;
			if (!collisionWithTile((int) (position.getX() + bounds.x) / Tile.TILE_SIZE, ty) &&
					!collisionWithTile((int) (position.getX() + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
				Vec2 delta = new Vec2(0, dy);
				this.position = position.add(delta);
			} else {
				position.setY(ty * Tile.TILE_SIZE - bounds.y - bounds.height - 1);
			}
			direction = EnumDirection.DOWN;
		} else if (dy < 0) {
			// Move Up
			int ty = (int) (position.getY() + dy + bounds.y) / Tile.TILE_SIZE;
			if (!collisionWithTile((int) (position.getX() + bounds.x) / Tile.TILE_SIZE, ty) &&
					!collisionWithTile((int) (position.getX() + bounds.x + bounds.width) / Tile.TILE_SIZE, ty)) {
				Vec2 delta = new Vec2(0, dy);
				this.position = position.add(delta);
			} else {
				position.setY(ty * Tile.TILE_SIZE + Tile.TILE_SIZE - bounds.y);
			}
			direction = EnumDirection.UP;
		}
	}

	protected boolean collisionWithTile (int x, int y) {
		World world = Handler.getInstance().getWorld();
		return world.getTile(x, y).isSolid();
	}

	public Vec2 getDelta () {
		return delta;
	}

	public int getHealth () {
		return health;
	}

	public void setHealth (int health) {
		this.health = health;
	}

	public float getSpeed () {
		return speed;
	}

	public void setSpeed (float speed) {
		this.speed = speed;
	}
}
