package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.assets.Assets;
import com.soracasus.tilegame.assets.SpriteSheet;
import com.soracasus.tilegame.gfx.Animation;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Gnoll extends Creature {

	private static final String TEXTURE_PATH = "/textures/gnoll.png";

	private Animation animUp;
	private Animation animDown;
	private Animation animLeft;
	private Animation animRight;

	private BufferedImage texUpStill;
	private BufferedImage texDownStill;
	private BufferedImage texLeftStill;
	private BufferedImage texRightStill;

	private Camera camera;

	public Gnoll (Vec2 position) {
		super(position);
		this.scale = new Vec2(2);

		this.bounds.x = 24;
		this.bounds.y = 32;
		this.bounds.width = 16;
		this.bounds.height = 32;

		SpriteSheet sheet = new SpriteSheet(Assets.getInstance().getTexture(TEXTURE_PATH));

		BufferedImage[][] animations = Assets.getInstance().loadPlayerMoveAnim(sheet);
		BufferedImage[] stills = Assets.getInstance().loadPlayerStills(sheet);
		texDownStill = stills[0];
		texLeftStill = stills[1];
		texRightStill = stills[2];
		texUpStill = stills[3];

		animDown = new Animation(250, animations[0]);
		animLeft = new Animation(250, animations[1]);
		animRight = new Animation(250, animations[2]);
		animUp = new Animation(250, animations[3]);

		camera = Handler.getInstance().getCamera();
	}

	@Override
	public void tick (Input input) {
		getCurrentAnimation().tick();
	}

	@Override
	public void render (Graphics g) {
		if (delta.getX() == 0 && delta.getY() == 0) {
			g.drawImage(getCurrentStill(),
					(int) (position.getX()- camera.getOffset().getX()),
					(int) (position.getY() - camera.getOffset().getY()),
					(int) (32 * scale.getX()),
					(int) (32 * scale.getY()),
					null);
		} else {
			g.drawImage(getCurrentAnimation().getCurrentFrame(),
					(int) position.getX(),
					(int) position.getY(),
					(int) (32 * scale.getX()),
					(int) (32 * scale.getY()),
					null);
		}
	}

	// Todo(Sora): Abstract this
	private BufferedImage getCurrentStill () {
		switch (direction) {
			case UP: {
				return texUpStill;
			}
			case DOWN: {
				return texDownStill;
			}
			case LEFT: {
				return texLeftStill;
			}
			case RIGHT: {
				return texRightStill;
			}
		}
		return null;
	}

	// Todo(Sora): Abstract this
	private Animation getCurrentAnimation () {
		switch (direction) {
			case UP: {
				return animUp;
			}
			case DOWN: {
				return animDown;
			}
			case LEFT: {
				return animLeft;
			}
			case RIGHT: {
				return animRight;
			}
		}

		return animDown;
	}

}
