package com.soracasus.tilegame.input;

import com.soracasus.tilegame.math.Vec2;
import com.soracasus.tilegame.ui.UIManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseManager implements MouseListener, MouseMotionListener {

	private boolean leftPressed;
	private boolean rightPressed;

	private Vec2 mousePos;

	private UIManager uiManager;

	public MouseManager () {
		mousePos = new Vec2();
		uiManager = null;
	}

	public void setUiManager (UIManager uiManager) {
		this.uiManager = uiManager;
	}

	public boolean isLeftPressed () {
		return leftPressed;
	}

	public boolean isRightPressed () {
		return rightPressed;
	}

	public Vec2 getMousePos () {
		return mousePos;
	}

	@Override
	public void mouseClicked (MouseEvent e) {

	}

	@Override
	public void mousePressed (MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = true;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = true;
		}
	}

	@Override
	public void mouseReleased (MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			leftPressed = false;
		} else if (e.getButton() == MouseEvent.BUTTON3) {
			rightPressed = false;
		}

		if(uiManager != null) {
			uiManager.onMouseRelease(e);
		}
	}

	@Override
	public void mouseEntered (MouseEvent e) {

	}

	@Override
	public void mouseExited (MouseEvent e) {

	}

	@Override
	public void mouseDragged (MouseEvent e) {

	}

	@Override
	public void mouseMoved (MouseEvent e) {
		mousePos.setX(e.getX());
		mousePos.setY(e.getY());

		if(uiManager != null) {
			uiManager.onMouseMove(e);
		}
	}
}
