package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public class Earth extends Planet {
	
	private Moon moon;
	
	public Earth(GameContainer gc) {
		super(gc);
		
		this.mass = 5;
		this.radius = 10;
		
		//paths.add(new Path());
		moon = new Moon(gc, this);
	}
	
	@Override
	public void update(GameContainer gc) {
		super.update(gc);
		moon.update(gc);
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xff2b7ab3, position, radius);
		
		for(Planet p : Shooty.inst.planets) {
			if(p.stationary) {
				r.drawText("Dist: " + WMath.distance(position, p.position), 10, 10);
			}
		}
		
		for(Path path : paths) {
			path.render(gc, r);
		}
		
		moon.render(gc, r);
		
		r.drawText("Speed X: " + speed.x, 10, 30);
		r.drawText("Speed Y: " + speed.y, 10, 50);
	}

	@Override
	public Vector getStartPos(GameContainer gc) {
		return new Vector(gc.getWidth()/2 - 400, gc.getHeight()/2 - 265);
	}

	@Override
	public Vector getStartSpeed(GameContainer gc) {
		return new Vector(1.5f, -1.5f);
	}

	@Override
	public void reset(GameContainer gc) {
		for(Path path : paths) {
			path.selected = false;
		}
		paths.add(new Path());
	}
}
