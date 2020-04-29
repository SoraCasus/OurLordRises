package com.soracasus.tilegame.display;

import com.soracasus.tilegame.assets.Assets;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Dimension;

public class Display {

	private JFrame frame;
	private Canvas canvas;

	private String title;
	private int width;
	private int height;

	public Display(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;

		createDisplay();
	}

	private void createDisplay() {
		Dimension d = new Dimension(width, height);

		frame = new JFrame(title);
		frame.setSize(d);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(d);
		canvas.setMaximumSize(d);
		canvas.setMinimumSize(d);
		canvas.setFocusable(false);

		frame.add(canvas);
		frame.pack();

		frame.setIconImage(Assets.getInstance().getTexture("/textures/dcIcon.png"));
	}

	public JFrame getFrame () {
		return frame;
	}

	public Canvas getCanvas () {
		return canvas;
	}
}
