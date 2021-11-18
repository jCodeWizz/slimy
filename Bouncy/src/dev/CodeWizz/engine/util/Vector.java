package dev.CodeWizz.engine.util;

public class Vector {

	public float x, y;
	
	public Vector(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector() {
		this.x = 0;
		this.y = 0;
	}
	
	public void devide(float dev) {
		this.x /= dev;
		this.y /= dev;
	}
	
	public void add(Vector vec) {
		this.x += vec.x;
		this.y += vec.y;
	}
	
	public void multiply(Vector vec) {
		this.x *= vec.x;
		this.y *= vec.y;
	}
	
	public void clear() {
		this.x = 0;
		this.y = 0;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
