package com.soracasus.tilegame.ui;

import com.soracasus.tilegame.input.Input;
import com.soracasus.tilegame.math.Vec2;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

public abstract class UIObject {

	protected Vec2 position;
	protected Vec2 size;
	protected Rectangle bounds;
	protected boolean hovering;

	public UIObject (Vec2 position, Vec2 size) {
		this.position = position;
		this.size = size;
		this.hovering = false;
		this.bounds = new Rectangle((int) position.getX(), (int) position.getY(), (int) size.getX(), (int) size.getY());
	}

	public abstract void tick (Input input);

	public abstract void render (Graphics g);

	public abstract void onClick();

	public void onMouseMove (MouseEvent e) {
		hovering = bounds.contains(e.getX(), e.getY());
	}

	public void onMouseRelease (MouseEvent e) {
		if(hovering) {
			onClick();
		}
	}

	public Vec2 getPosition () {
		return position;
	}

	public void setPosition (Vec2 position) {
		this.position = position;
	}

	public Vec2 getSize () {
		return size;
	}

	public void setSize (Vec2 size) {
		this.size = size;
	}

	public boolean isHovering () {
		return hovering;
	}

	public void setHovering (boolean hovering) {
		this.hovering = hovering;
	}
}
