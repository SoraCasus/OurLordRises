package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;

public class Gnoll extends Creature {

	private Camera camera;

	public Gnoll (Vec2 position) {
		super(position, "/textures/gnoll.png", 250);
		this.scale = new Vec2(2);

		this.bounds.x = 24;
		this.bounds.y = 32;
		this.bounds.width = 16;
		this.bounds.height = 32;

		camera = Handler.getInstance().getCamera();
	}

	@Override
	public void tick (Input input) {
		animations.getCurrentAnimation(direction).tick();
	}

	@Override
	public void render (Graphics g) {
		g.drawImage(animations.getCurrentFrame(direction, delta),
				(int) (position.getX() - camera.getOffset().getX()),
				(int) (position.getY() - camera.getOffset().getY()),
				(int) (32 * scale.getX()),
				(int) (32 * scale.getY()),
				null);

	}
}
