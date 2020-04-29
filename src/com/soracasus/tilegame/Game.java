package com.soracasus.tilegame;

import com.soracasus.tilegame.display.Display;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.states.GameState;
import com.soracasus.tilegame.states.MenuState;
import com.soracasus.tilegame.states.State;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game {

	public static final boolean RENDER_COLLISIONS = false;

	private Display display;

	private Input input;

	private Camera camera;

	private String title;
	private int width;
	private int height;

	private boolean running = false;
	private Thread thread;

	private BufferStrategy bs;
	private Graphics g;

	private static State currentState = null;

	private State gameState;
	private State menuState;

	public Game (String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	private void init () {
		display = new Display(title, width, height);
		input = new Input(display);
		camera = new Camera(new Vec2(0, 0));

		Handler.getInstance().setGame(this);
		Handler.getInstance().setCamera(camera);
		Handler.getInstance().setInput(input);

		menuState = new MenuState();
		gameState = new GameState();
		setState(menuState);
	}

	private void tick () {
		input.tick();
		if (currentState != null) {
			currentState.tick(input);
		}
	}

	private void render () {
		bs = display.getCanvas().getBufferStrategy();
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// Clear screen
		g.clearRect(0, 0, width, height);

		// Render here
		if (currentState != null) {
			currentState.render(g);
		}


		// End render
		bs.show();
		g.dispose();
	}

	private void run () {
		init();

		final int fps = 60;
		final double timePerTick = 1e9 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		// int ticks = 0;

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if (delta >= 1) {
				tick();
				render();
			//	ticks++;
				delta--;
			}

			if (timer >= 1e9) {
			//	ticks = 0;
				timer = 0;
				// Log framerate
			}
		}

		stop();
	}

	public synchronized void start () {
		if (running) return;

		running = true;

		thread = new Thread(this::run, "MAIN_GAME_LOOP");
		thread.start();
	}

	public synchronized void stop () {
		if (!running) return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void setState (State state) {
		currentState = state;
	}

	public static State getState () {
		return currentState;
	}

	public Camera getCamera () {
		return camera;
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}
}
