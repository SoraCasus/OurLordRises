package com.soracasus.tilegame.ui;

import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class UIImageButton extends UIObject {

	private BufferedImage[] images;
	private IClickListener clicker;

	public UIImageButton (Vec2 position, Vec2 size, BufferedImage[] images, IClickListener clicker) {
		super(position, size);
		this.images = images;
		this.clicker = clicker;
	}

	@Override
	public void tick (Input input) { }

	@Override
	public void render (Graphics g) {
		if(hovering) {
			g.drawImage(images[1], (int)position.getX(), (int)position.getY(),
					(int)size.getX(), (int)size.getY(), null);
		} else {
			g.drawImage(images[0], (int)position.getX(), (int)position.getY(),
					(int)size.getX(), (int)size.getY(), null);
		}
	}

	@Override
	public void onClick () {
		clicker.onClick();
	}
}
