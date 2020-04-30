package com.soracasus.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Animation {

	private int speed;
	private int index;
	private long lastTime;
	private long timer;
	private BufferedImage[] frames;
	private BufferedImage still;

	public Animation (int speed, BufferedImage[] frames, BufferedImage still) {
		this.speed = speed;
		this.frames = frames;
		this.index = 0;
		this.timer = 0;
		this.lastTime = System.currentTimeMillis();
		this.still = still;
	}

	public void tick() {
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();

		if(timer > speed) {
			index++;
			timer = 0;
			if(index >= frames.length) {
				index = 0;
			}
		}
	}

	public BufferedImage getCurrentFrame() {
		return frames[index];
	}

	public BufferedImage getStill () {
		return still;
	}
}
