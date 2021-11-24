package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public class Moon {

	private double c = 6.67408 * Math.pow(10, -3);
	public Vector position, speed, acc;
	public List<Path> paths = new CopyOnWriteArrayList<>();
	public List<Vector> forces = new CopyOnWriteArrayList<>();
	public String name;
	public float mass = 5;
	public int radius = 3;
	public boolean stationary = false;
	private Planet p;
	private int counter = 0;

	public Moon(GameContainer gc, Planet p) {
		this.p = p;
		position = getStartPos(gc);
		speed = getStartSpeed(gc);
		acc = new Vector();

		paths.add(new Path());
		
	}

	public void update(GameContainer gc) {
		if (!stationary) {
			float force = (float) (c * ((p.mass * mass) / (radius * radius)));
			forces.add(Vector.forceToVector(force, position, p.position));

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
			
			if(counter < 10) {
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

	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xffffffff, position, radius);
		
		
		for(Path path : paths) {
			path.render(gc, r);
		}
		
		r.drawText("Moon: ", 10, 80);
		r.drawText("Dist: " + WMath.distance(p.position, position), 10, 110);
		r.drawText("Speed dx: " + (speed.x - p.speed.x), 10, 130);
		r.drawText("Speed dy: " + (speed.y - p.speed.y), 10, 150);
	}

	public void reset(GameContainer gc) {
		position = getStartPos(gc);
		speed = getStartSpeed(gc);
	
		for(Path path : paths) {
			path.selected = false;
		}
		
		
		paths.add(new Path());
	}

	public Vector getStartPos(GameContainer gc) {
		return new Vector(p.getStartPos(gc).x - 35, p.getStartPos(gc).y);
	}

	public Vector getStartSpeed(GameContainer gc) {
		return p.getStartSpeed(gc);
	}

}
