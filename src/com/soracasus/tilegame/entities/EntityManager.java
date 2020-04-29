package com.soracasus.tilegame.entities;

import com.soracasus.tilegame.entities.creatures.Player;
import com.soracasus.tilegame.input.Input;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {

	private Player player;
	private List<Entity> entities;

	public EntityManager (Player player) {
		this.entities = new ArrayList<>();
		this.player = player;
		entities.add(player);
	}

	public void tick (Input input) {
		for (Entity e : entities) {
			e.tick(input);
		}
		entities.sort((a, b) -> {
			float aH = a.getPosition().getY() + (32 * a.getScale().getY());
			float bH = b.getPosition().getY() + (32 * b.getScale().getY());

			return Float.compare(aH, bH);
		});
	}

	public void render (Graphics g) {
		for (Entity e : entities) {
			e.render(g);
		}
	}

	public void addEntity (Entity e) {
		entities.add(e);
	}

	public List<Entity> getEntities () {
		return entities;
	}
}
