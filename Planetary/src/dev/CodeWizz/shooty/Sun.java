package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Sun extends Planet {
	
	public Sun(GameContainer gc) {
		super(gc);
		
		this.mass = 50000;
		this.radius = 40;
		this.stationary = true;
		
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xfffcdb03, position, radius);
	}

	@Override
	public Vector getStartPos(GameContainer gc) {
		return new Vector(gc.getWidth()/2, gc.getHeight()/2);
	}

	@Override
	public Vector getStartSpeed(GameContainer gc) {
		return new Vector(0, 0);
	}

	@Override
	public void reset(GameContainer gc) {
		
	}
}
