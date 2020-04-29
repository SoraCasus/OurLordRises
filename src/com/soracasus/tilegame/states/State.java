package com.soracasus.tilegame.states;

import com.soracasus.tilegame.input.Input;

import java.awt.Graphics;

public interface State {

	void tick(Input input);
	void render(Graphics g);


}
