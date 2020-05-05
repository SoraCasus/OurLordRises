package com.soracasus.tilegame.entities.creatures;

import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Gnoll extends Creature {

	private Camera camera;
	private Player player;

	public Gnoll (Vec2 position) {
		super(position, "/textures/gnoll.png", 250);
		this.scale = new Vec2(2);

		this.bounds.x = 24;
		this.bounds.y = 32;
		this.bounds.width = 16;
		this.bounds.height = 32;

		camera = Handler.getInstance().getCamera();
	}

	@Override
	public void tick (Input input) {
		if (player == null) {
			player = Handler.getInstance().getWorld().getEntityManager().getPlayer();
		}
		animations.getCurrentAnimation(direction).tick();
		Vec2 point = position.add(direction.getDelta());

		Rectangle hitbox = new Rectangle((int) point.getX(), (int) point.getY(), 64, 64);

		if (hitbox.contains(player.getPosition().getX() + 32, player.getPosition().getY() + 32)) {
			// A collision has happened, now transition to battle system
			System.out.println("Collided with player");
		}
	}

	@Override
	public void render (Graphics g) {
		g.drawImage(animations.getCurrentFrame(direction, delta),
				(int) (position.getX() - camera.getOffset().getX()),
				(int) (position.getY() - camera.getOffset().getY()),
				(int) (32 * scale.getX()),
				(int) (32 * scale.getY()),
				null);

	}
}
