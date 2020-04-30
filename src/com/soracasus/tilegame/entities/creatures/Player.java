package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Game;
import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.gfx.Animation;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

	private static final String TEXTURE_PATH = "/textures/player.png";

	private Animation playerUp;
	private Animation playerDown;
	private Animation playerLeft;
	private Animation playerRight;

	private BufferedImage playerUpStill;
	private BufferedImage playerDownStill;
	private BufferedImage playerLeftStill;
	private BufferedImage playerRightStill;

	private Camera camera;

	public Player (Vec2 position) {
		super(position, "/textures/player.png", 250);

		this.scale = new Vec2(2);

		this.bounds.x = 24;
		this.bounds.y = 32;
		this.bounds.width = 16;
		this.bounds.height = 32;

		camera = Handler.getInstance().getCamera();
	}

	@Override
	public void tick (Input input) {
		getInput(input);
		move();
		camera.centerOnEntity(this);
		animations.getCurrentAnimation(direction).tick();
	}

	private void getInput (Input input) {
		float dy = 0.0F;
		float dx = 0.0F;

		if (input.getKeyManager().isUp()) {
			dy = -speed;
		} else if (input.getKeyManager().isDown()) {
			dy = speed;
		}

		if (input.getKeyManager().isLeft()) {
			dx = -speed;
		} else if (input.getKeyManager().isRight()) {
			dx = speed;
		}

		delta = new Vec2(dx, dy);
	}

	@Override
	public void render (Graphics g) {
		g.drawImage(animations.getCurrentFrame(direction, delta),
				(int) (position.getX() - camera.getOffset().getX()),
				(int) (position.getY() - camera.getOffset().getY()),
				(int) (32 * scale.getX()),
				(int) (32 * scale.getY()),
				null);

		if (Game.RENDER_COLLISIONS) {
			g.setColor(Color.RED);
			g.fillRect((int) (position.getX() + bounds.x - camera.getOffset().getX()),
					(int) (position.getY() + bounds.y - camera.getOffset().getY()),
					bounds.width, bounds.height);
		}
	}
}
