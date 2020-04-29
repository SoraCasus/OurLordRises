package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Game;
import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.assets.Assets;
import com.soracasus.tilegame.assets.SpriteSheet;
import com.soracasus.tilegame.gfx.Animation;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Creature {

	private static final String TEXTURE_PATH = "/textures/Player_Sprite_Sheet.png";

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
		super(position);

		this.scale = new Vec2(2);

		this.bounds.x = 24;
		this.bounds.y = 32;
		this.bounds.width = 16;
		this.bounds.height = 32;

		SpriteSheet sheet = new SpriteSheet(Assets.getInstance().getTexture(TEXTURE_PATH));

		BufferedImage[][] animations = Assets.getInstance().loadPlayerMoveAnim(sheet);
		BufferedImage[] stills = Assets.getInstance().loadPlayerStills(sheet);
		playerDownStill = stills[0];
		playerLeftStill = stills[1];
		playerRightStill = stills[2];
		playerUpStill = stills[3];

		playerDown = new Animation(250, animations[0]);
		playerLeft = new Animation(250, animations[1]);
		playerRight = new Animation(250, animations[2]);
		playerUp = new Animation(250, animations[3]);

		camera = Handler.getInstance().getCamera();
	}

	@Override
	public void tick (Input input) {
		getInput(input);
		move();
		camera.centerOnEntity(this);
		getCurrentAnimation().tick();
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
		if (delta.getX() == 0 && delta.getY() == 0) {
			g.drawImage(getCurrentStill(),
					(int) (position.getX() - camera.getOffset().getX()),
					(int) (position.getY() - camera.getOffset().getY()),
					(int) (32 * scale.getX()),
					(int) (32 * scale.getY()),
					null);
		} else {
			g.drawImage(getCurrentAnimation().getCurrentFrame(),
					(int) (position.getX() - camera.getOffset().getX()),
					(int) (position.getY() - camera.getOffset().getY()),
					(int) (32 * scale.getX()),
					(int) (32 * scale.getY()),
					null);
		}
		if (Game.RENDER_COLLISIONS) {
			g.setColor(Color.RED);
			g.fillRect((int) (position.getX() + bounds.x - camera.getOffset().getX()),
					(int) (position.getY() + bounds.y - camera.getOffset().getY()),
					bounds.width, bounds.height);
		}
	}

	// Todo(Sora): Abstract this
	private BufferedImage getCurrentStill() {
		switch (direction) {
			case UP: {
				return playerUpStill;
			}
			case DOWN: {
				return playerDownStill;
			}
			case LEFT: {
				return playerLeftStill;
			}
			case RIGHT: {
				return playerRightStill;
			}
		}
		return null;
	}

	// Todo(Sora): Abstract this
	private Animation getCurrentAnimation () {
		switch (direction) {
			case UP: {
				return playerUp;
			}
			case DOWN: {
				return playerDown;
			}
			case LEFT: {
				return playerLeft;
			}
			case RIGHT: {
				return playerRight;
			}
		}

		return playerDown;
	}
}
