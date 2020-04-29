package com.soracasus.tilegame;

import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.worlds.World;

public class Handler {

	private static final Handler INSTANCE = new Handler();

	public static Handler getInstance () {
		return INSTANCE;
	}

	private Game game;
	private World world;
	private Camera camera;
	private Input input;

	private Handler () {

	}

	public Input getInput () {
		return input;
	}

	public void setInput (Input input) {
		this.input = input;
	}

	public Game getGame () {
		return game;
	}

	public void setGame (Game game) {
		this.game = game;
	}

	public World getWorld () {
		return world;
	}

	public void setWorld (World world) {
		this.world = world;
	}

	public int getWidth () {
		return game.getWidth();
	}

	public int getHeight () {
		return game.getHeight();
	}

	public Camera getCamera () {
		return camera;
	}

	public void setCamera (Camera camera) {
		this.camera = camera;
	}
}
