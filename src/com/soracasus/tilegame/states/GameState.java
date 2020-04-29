package com.soracasus.tilegame.states;

import com.soracasus.tilegame.entities.creatures.Player;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.tiles.Tile;
import com.soracasus.tilegame.worlds.World;

import java.awt.Graphics;

public class GameState implements State {

	private World world;

	public GameState () {
		world = new World("/world/world1.json");
	}

	@Override
	public void tick (Input input) {
		world.tick(input);
	}

	@Override
	public void render (Graphics g) {
		world.render(g);
	}

}
