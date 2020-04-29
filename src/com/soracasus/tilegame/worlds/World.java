package com.soracasus.tilegame.worlds;

import com.soracasus.tilegame.Game;
import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.entities.EntityManager;
import com.soracasus.tilegame.entities.creatures.Gnoll;
import com.soracasus.tilegame.entities.creatures.Player;
import com.soracasus.tilegame.entities.statics.TreeEntity;
import com.soracasus.tilegame.gfx.Camera;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.tiles.Tile;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.reflect.generics.tree.Tree;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class World {

	private int width;
	private int height;
	private EntityManager entityManager;

	private Camera camera;

	private int[][] tileIds;

	public World (String filePath) {
		try {
			loadFromFile(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Handler.getInstance().setWorld(this);
		camera = Handler.getInstance().getCamera();
		entityManager.addEntity(new TreeEntity(new Vec2(200, 200)));
	}

	public void tick (Input input) {
		entityManager.tick(input);
	}

	public void render (Graphics g) {
		int xStart = Math.max(0, (int) (camera.getOffset().getX() / Tile.TILE_SIZE));
		int xEnd = Math.min(width, (int)((camera.getOffset().getX() + Handler.getInstance().getWidth()) / Tile.TILE_SIZE) + 1);

		int yStart = Math.max(0, (int)(camera.getOffset().getY() / Tile.TILE_SIZE));
		int yEnd = Math.min(height, (int)((camera.getOffset().getY() + Handler.getInstance().getHeight()) / Tile.TILE_SIZE) + 1);


		for (int y = yStart; y < yEnd; y++) {
			for (int x = xStart; x < xEnd; x++) {
				int _x = (int) (x * Tile.TILE_SIZE - camera.getOffset().getX());
				int _y = (int) (y * Tile.TILE_SIZE - camera.getOffset().getY());
				getTile(x, y).render(g, _x, _y);
			}
		}

		entityManager.render(g);
	}

	public Tile getTile (int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) {
			return Tile.tiles[0];
		}



		Tile t = Tile.tiles[tileIds[y][x]];
		if(t == null) {
			return Tile.tiles[0];
		}
		return t;
	}

	private void loadFromFile (String file) throws IOException {
		StringBuilder jsonFile = new StringBuilder();
		try (BufferedReader reader = getReader(file)) {
			String line;
			while ((line = reader.readLine()) != null) {
				jsonFile.append(line);
			}
		}

		JSONObject worldObj = new JSONObject(jsonFile.toString());
		loadTiles(worldObj);
		loadObjects(worldObj);
	}

	private void loadObjects (JSONObject worldObj) {
		JSONObject objectLayer = worldObj.getJSONArray("layers").getJSONObject(1);
		JSONArray objects = objectLayer.getJSONArray("objects");

		for (int i = 0; i < objects.length(); i++) {
			JSONObject obj = objects.getJSONObject(i);
			parseObject(obj);
		}
	}

	private void parseObject (JSONObject obj) {
		if (obj.getString("name").equals("player_spawn")) {
			int x = obj.getInt("x") * 2;
			int y = obj.getInt("y") * 2;
			Player player = new Player(new Vec2(x, y));
			entityManager = new EntityManager(player);
		} else if (obj.getString("name").equals("gnoll_spawn")) {
			int x = obj.getInt("x") * 2;
			int y = obj.getInt("y") * 2;
			Gnoll gnoll = new Gnoll(new Vec2(x, y));
			entityManager.addEntity(gnoll);
		}
	}

	private void loadTiles (JSONObject worldObj) {
		this.width = worldObj.getInt("width");
		this.height = worldObj.getInt("height");

		this.tileIds = new int[height][width];

		JSONArray ids = worldObj.getJSONArray("layers").getJSONObject(0).getJSONArray("data");
		int index = 0;
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileIds[y][x] = (ids.getInt(index++) - 1);
			}
		}
	}

	private BufferedReader getReader (String file) {
		InputStreamReader isReader = new InputStreamReader(this.getClass().getResourceAsStream(file));
		return new BufferedReader(isReader);
	}

	public int getWidth () {
		return width;
	}

	public int getHeight () {
		return height;
	}

	public EntityManager getEntityManager () {
		return entityManager;
	}
}
