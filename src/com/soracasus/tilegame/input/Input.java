package com.soracasus.tilegame.input;

import com.soracasus.tilegame.display.Display;


public class Input {
	private KeyManager keyManager;
	private MouseManager mouseManager;

	public Input (Display display) {
		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
	}

	public void tick () {
		keyManager.tick();
	}

	public KeyManager getKeyManager () {
		return keyManager;
	}

	public MouseManager getMouseManager () {
		return mouseManager;
	}
}
