package com.soracasus.tilegame.math;

public class Vec2 {

	private float x;
	private float y;

	public Vec2 (float x, float y) {
		this.x = x;
		this.y = y;
	}

	public Vec2 (float s) {
		this(s, s);
	}

	public Vec2 () {
		this(0);
	}

	public Vec2 add (Vec2 other) {
		float x = this.x + other.x;
		float y = this.y + other.y;
		return new Vec2(x, y);
	}

	public Vec2 sub (Vec2 other) {
		float x = this.x - other.x;
		float y = this.y - other.y;
		return new Vec2(x, y);
	}

	public Vec2 mul (Vec2 other) {
		float x = this.x * other.x;
		float y = this.y * other.y;
		return new Vec2(x, y);
	}

	public Vec2 mul (float s) {
		return this.mul(new Vec2(s));
	}

	public float dot (Vec2 other) {
		return x * other.x + y * other.y;
	}

	public float lengthSquare () {
		return x * x + y * y;
	}

	public float length () {
		return (float) Math.sqrt(lengthSquare());
	}

	public void normalize () {
		float l = length();
		this.x /= l;
		this.y /= l;
	}

	public float getX () {
		return x;
	}

	public void setX (float x) {
		this.x = x;
	}

	public float getY () {
		return y;
	}

	public void setY (float y) {
		this.y = y;
	}
}
