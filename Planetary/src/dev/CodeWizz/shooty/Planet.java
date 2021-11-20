package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public abstract class Planet {

	private double c = 6.67408 * Math.pow(10, -4);
	public Vector position, speed, acc;
	public List<Vector> forces = new CopyOnWriteArrayList<>();
	public String name;
	public float mass;
	public int radius;
	public boolean stationary;

	public List<Path> paths = new CopyOnWriteArrayList<>();
	public List<Planet> moons = new CopyOnWriteArrayList<>();
	
	
	
	private int counter;

	public Planet(GameContainer gc) {
		position = getStartPos(gc);
		speed = getStartSpeed(gc);
		acc = new Vector();
	}

	public void update(GameContainer gc) {
		if (!stationary) {
			for (Planet p : Shooty.inst.planets) {
				if (!p.equals(this)) {
					float r = WMath.distance(p.position, position);
					float force = (float) (c * ((p.mass * mass) / (r * r)));
					forces.add(Vector.forceToVector(force, position, p.position));
				}
			}

			if (!forces.isEmpty()) {
				for (Vector v : forces) {
					acc.add(v);
				}
				forces.clear();
			}

			acc.devide(mass);

			speed.add(acc);
			position.add(speed);
			acc.clear();
			
			if(counter < 2) {
				counter++;
			} else {
				for(Path path : paths) {
					if(path.selected)
						path.addPoint(new Vector(position.x, position.y));
				}
				counter = 0;
			}
			
			

		}
	}

	public abstract void render(GameContainer gc, Renderer r);

	public abstract void reset(GameContainer gc);

	public abstract Vector getStartPos(GameContainer gc);

	public abstract Vector getStartSpeed(GameContainer gc);

}
