package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Planet {

	private final double c = 6.67408 * Math.pow(10, -7);

	private List<Vector> forces = new CopyOnWriteArrayList<>();
	public Vector position, speed, acc;
	public float mass, r;
	public boolean stationary;
	public int color;

	public Planet(float x, float y, float r, float mass, int color) {
		position = new Vector(x, y);
		speed = new Vector();
		acc = new Vector();

		this.r = r;
		this.mass = mass;
		this.color = color;
	}
	
	public Planet(float x, float y, float r, float mass, int color, Vector s) {
		position = new Vector(x, y);
		speed = new Vector(s.x, s.y);
		acc = new Vector();

		this.r = r;
		this.mass = mass;
		this.color = color;
	}

	public void update(GameContainer gc) {
		if (!stationary) {
			position.add(speed);
			acc.clear();

			if (!forces.isEmpty()) {
				for (Vector vec : forces) {
					acc.add(vec);
				}
				forces.clear();
			}

			for (Planet p : Shooty.inst.planets) {
				if(!p.equals(this)) {
					double force = c * ((mass * p.mass) / (r * r));
					Vector v = new Vector();

					int dx = (int) p.position.x - (int) position.x;
					int dy = (int) p.position.y - (int) position.y;
					
					double angle;

					if (dy > 0) {
						angle = Math.atan(dx / dy);

						v.x = (float) (Math.toDegrees(Math.sin(angle)) * force);
						v.y = (float) (Math.toDegrees(Math.cos(angle)) * force);
						
						forces.add(v);
					} else if(dy < 0) {
						angle = Math.atan(dx / dy);

						v.x = (float) -(Math.toDegrees(Math.sin(angle)) * force);
						v.y = (float) -(Math.toDegrees(Math.cos(angle)) * force);
						
						forces.add(v);
					} else {
						v.x = 0;
						if(p.position.y > position.y) {
							v.y = (float) force;
						} else {
							v.y = (float) -force;
						}
					}
				}

			}

			// CALCULATE FORCES

			acc.devide(mass);

			speed.add(acc);
		}
	}

	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(color, position, (int) this.r);
	}

	public Planet setStationary(boolean s) {
		this.stationary = s;
		return this;
	}
}
