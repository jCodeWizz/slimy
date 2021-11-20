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
	
	public Vector negative() {
		return new Vector(-x, -y);
	}
	
	public static Vector forceToVector(float force, Vector a, Vector b) {
		Vector v = new Vector();

		int dx = (int) b.x - (int) a.x;
		int dy = (int) b.y - (int) a.y;
		
		double angle;

		if (dy > 0) {
			angle = Math.atan(dx / dy);

			v.x = (float) (Math.toDegrees(Math.sin(angle)) * force);
			v.y = (float) (Math.toDegrees(Math.cos(angle)) * force);
			
			return v;
		} else if(dy < 0) {
			angle = Math.atan(dx / dy);

			v.x = (float) -(Math.toDegrees(Math.sin(angle)) * force);
			v.y = (float) -(Math.toDegrees(Math.cos(angle)) * force);
			
			return v;
		} else {
			v.x = 0;
			if(b.y > a.y) {
				v.y = (float) force;
			} else {
				v.y = (float) -force;
			}
			
			return v;
		}
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
