package com.soracasus.tilegame.entities.statics;

import com.soracasus.tilegame.Game;
import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.assets.Assets;
import com.soracasus.tilegame.assets.SpriteSheet;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TreeEntity extends StaticEntity {

	private BufferedImage texture;

	public TreeEntity (Vec2 position) {
		super(position, new Vec2(3));

		bounds.x = 32;
		bounds.y = 64;
		bounds.width = 32;
		bounds.height = 32;

		SpriteSheet sheet = Assets.getInstance().getTerrain();
		texture = sheet.crop(15 * 32, 0, 32, 32);
	}

	@Override
	public void tick (Input input) {

	}

	@Override
	public void render (Graphics g) {
		g.drawImage(texture,
				(int)(position.getX() - Handler.getInstance().getCamera().getOffset().getX()),
				(int)(position.getY() - Handler.getInstance().getCamera().getOffset().getY()),
				(int)(32 * scale.getX()),
				(int)(32 * scale.getY()),
				null);
		if (Game.RENDER_COLLISIONS) {
			g.setColor(Color.RED);
			g.fillRect((int) (position.getX() + bounds.x - Handler.getInstance().getCamera().getOffset().getX()),
					(int) (position.getY() + bounds.y - Handler.getInstance().getCamera().getOffset().getY()),
					bounds.width, bounds.height);
		}
	}
}
