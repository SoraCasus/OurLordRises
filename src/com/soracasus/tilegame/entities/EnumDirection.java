package com.soracasus.tilegame.entities;

import com.soracasus.tilegame.math.Vec2;

public enum EnumDirection {
	UP(new Vec2(0.0F, -32.0F)),
	DOWN(new Vec2(0.0F, 32.0F)),
	LEFT(new Vec2(-32.0F, 0.0F)),
	RIGHT(new Vec2(32.0F, 0.0F));

	private Vec2 delta;

	EnumDirection (Vec2 delta) {
		this.delta = delta;
	}

	public Vec2 getDelta () {
		return this.delta;
	}
}
