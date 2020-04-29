package com.soracasus.tilegame.entities;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Entity {

	protected Vec2 position;
	protected Vec2 scale;
	protected Rectangle bounds;
	protected EnumDirection direction;

	public Entity (Vec2 position, Vec2 scale) {
		this.position = position;
		this.scale = scale;
		this.bounds = new Rectangle(0, 0, (int) (32 * scale.getX()), (int) (32 * scale.getY()));
		this.direction = EnumDirection.DOWN;
	}

	public Entity (Vec2 position) {
		this(position, new Vec2(1));
	}

	public abstract void tick (Input input);

	public abstract void render (Graphics g);

	public boolean checkEntityCollisions (Vec2 offset) {
		for (Entity e : Handler.getInstance().getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(new Vec2()).intersects(getCollisionBounds(offset))) {
				return true;
			}
		}
		return false;
	}

	public Rectangle getCollisionBounds (Vec2 offset) {
		return new Rectangle(
				(int) (position.getX() + bounds.x + offset.getX()),
				(int) (position.getY() + bounds.y + offset.getY()),
				bounds.width,
				bounds.height);
	}

	public Vec2 getPosition () {
		return position;
	}

	public void setPosition (Vec2 position) {
		this.position = position;
	}

	public Vec2 getScale () {
		return scale;
	}

	public void setScale (Vec2 scale) {
		this.scale = scale;
	}
}
