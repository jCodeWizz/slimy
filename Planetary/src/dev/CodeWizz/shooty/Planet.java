package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public abstract class Planet {

	private double c = 6.67408 * Math.pow(10, -11);
	public Vector position, speed, acc;
	public List<Vector> forces = new CopyOnWriteArrayList<>();
	public String name;
	public float mass;
	public int radius;
	public boolean stationary;
	
	public Planet() {
		position = getStartPos();
		speed = getStartSpeed();
		acc = new Vector();
		
	}
	
	public void update(GameContainer gc) {
		if(!stationary) {
			for(Planet p : Shooty.inst.planets) {
				if(!p.equals(this)) {
					float r = WMath.distance(p.position, position);
					float force = (float) (c * ((p.mass * mass) / (r * r)));
					forces.add(Vector.forceToVector(force, position, p.position));
				}
			}
			
			for(Vector v : forces) {
				acc.add(v);
			}
			
			acc.devide(mass);
			
			speed.add(acc);
			position.add(speed);
			acc.clear();
		}
	}
	
	
	
	public abstract void render(GameContainer gc, Renderer r);
	
	public abstract Vector getStartPos();
	public abstract Vector getStartSpeed();
	
}
