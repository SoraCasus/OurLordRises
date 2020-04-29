package com.soracasus.tilegame.states;

import com.soracasus.tilegame.Game;
import com.soracasus.tilegame.Handler;
import com.soracasus.tilegame.assets.Assets;
import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.ui.UIImageButton;
import com.soracasus.tilegame.ui.UIManager;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MenuState implements State {

	private UIManager uiManager;
	private BufferedImage title;
	private BufferedImage dcIcon;

	public MenuState () {
		uiManager = new UIManager();
		Handler.getInstance().getInput().getMouseManager().setUiManager(uiManager);
		loadUI();
	}


	@Override
	public void tick (Input input) {
		uiManager.tick(input);
	}

	@Override
	public void render (Graphics g) {
		g.setColor(new Color(0xffe0d1a8));
		g.fillRect(0, 0,
				Handler.getInstance().getGame().getWidth(),
				Handler.getInstance().getGame().getHeight());
		uiManager.render(g);

		g.drawImage(title,
				(Handler.getInstance().getGame().getWidth() / 2) - 500,
				50, 1000, 100, null);

		g.drawImage(dcIcon,
				(Handler.getInstance().getGame().getWidth() / 2) - 64,
				200, 128, 128, null);
	}

	private void loadUI() {
		float centerX = Handler.getInstance().getGame().getWidth() / 2.0F;
		float centerY = Handler.getInstance().getGame().getHeight() / 2.0F;

		BufferedImage[] playButtonTextures = {
				Assets.getInstance().getTexture("/textures/btn_play0.png"),
				Assets.getInstance().getTexture("/textures/btn_play1.png")
		};

		uiManager.addObject(new UIImageButton(new Vec2(centerX - 96, centerY), new Vec2(128, 64),
				playButtonTextures, () -> {
			Game.setState(new GameState());
			Handler.getInstance().getInput().getMouseManager().setUiManager(null);
		}));

		BufferedImage[] quitButtonTextures = {
				Assets.getInstance().getTexture("/textures/btn_quit0.png"),
				Assets.getInstance().getTexture("/textures/btn_quit1.png")
		};

		uiManager.addObject(new UIImageButton(new Vec2(centerX - 96, centerY + 96), new Vec2(128, 64),
				quitButtonTextures, () -> System.exit(0)));

		title = Assets.getInstance().getTexture("/textures/title.png");

		dcIcon = Assets.getInstance().getTexture("/textures/dcIcon.png");
	}

}
