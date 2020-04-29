package com.soracasus.tilegame.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

	private boolean[] keys;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;

	public KeyManager () {
		keys = new boolean[256];
	}

	public void tick () {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_D];
	}

	@Override
	public void keyTyped (KeyEvent e) {

	}

	@Override
	public void keyPressed (KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased (KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public boolean[] getKeys () {
		return keys;
	}

	public boolean isUp () {
		return up;
	}

	public boolean isDown () {
		return down;
	}

	public boolean isLeft () {
		return left;
	}

	public boolean isRight () {
		return right;
	}
}
