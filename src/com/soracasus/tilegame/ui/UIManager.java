package com.soracasus.tilegame.ui;

import com.soracasus.tilegame.input.Input;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class UIManager {

	private List<UIObject> objects;

	public UIManager () {
		this.objects = new ArrayList<>();
	}

	public void tick (Input input) {
		for (UIObject o : objects) {
			o.tick(input);
		}
	}

	public void render (Graphics g) {
		for (UIObject o : objects) {
			o.render(g);
		}
	}

	public void onMouseMove (MouseEvent e) {
		for (UIObject o : objects) {
			o.onMouseMove(e);
		}
	}

	public void onMouseRelease (MouseEvent e) {
		for(UIObject o : objects) {
			o.onMouseRelease(e);
		}
	}

	public void addObject (UIObject o) {
		objects.add(o);
	}

	public void removeObject (UIObject o) {
		objects.remove(o);
	}

}
