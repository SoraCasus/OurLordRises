package com.soracasus.tilegame.entities.statics;

import com.soracasus.tilegame.entities.Entity;
import com.soracasus.tilegame.math.Vec2;

public abstract class StaticEntity extends Entity {


	public StaticEntity (Vec2 position, Vec2 scale) {
		super(position, scale);
	}

	public StaticEntity (Vec2 position) {
		super(position);
	}
}
