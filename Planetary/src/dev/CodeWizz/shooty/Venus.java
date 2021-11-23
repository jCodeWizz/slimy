package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Venus extends Planet {

	public Venus(GameContainer gc) {
		super(gc);

		this.mass = 5;
		this.radius = 10;
		
		paths.add(new Path());
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xffb2b9c2, position, radius);
		for(Path path : paths) {
			path.render(gc, r);
		}
	}

	@Override
	public void reset(GameContainer gc) {
		for(Path path : paths) {
			path.selected = false;
		}
		paths.add(new Path());
	}

	@Override
	public Vector getStartPos(GameContainer gc) {
		return new Vector(gc.getWidth()/2 - 160, gc.getHeight()/2 - 200);
	}

	@Override
	public Vector getStartSpeed(GameContainer gc) {
		return new Vector(3f, 0f);
	}
}
