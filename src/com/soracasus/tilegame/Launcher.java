package com.soracasus.tilegame;

import com.soracasus.tilegame.display.Display;

public class Launcher {

	public static void main(String[] args) {
		Game game = new Game("Chapter 1: Our Lord Rises",  1280, 720);
		game.start();
	}

}
