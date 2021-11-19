package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;

public class Ball extends GameObject {

	private final double c = 6.67408;
	private boolean stuck;
	
	public Ball(float x, float y, boolean stuck) {
		super(x, y);

		this.stuck = stuck;
		
		w = 16;
		h = 16;
		
		this.canMove = true;
		
		this.mass = 5;
		this.bounce = 0;
		this.friction = 0;
		this.airFrictionX = 0;
		this.airFrictionY = 0;
		this.gravity = 0;
		
		this.id = ID.Bullet;
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		super.tick(gc);

	
		if(!stuck) {
			for(GameObject object : gc.handler.object) {
				if(object.getId() == ID.Bullet && !object.equals(this)) {
					Vector v = new Vector();
					float force = (float) (c * ((mass * object.getMass() / (8*8))));
					
					int dx = (int) (position.x - object.getPosition().x);
					int dy = (int) (position.y - object.getPosition().y);
					
					double angle = Math.atan(dy / dx);
					
					v.x = (float) (force / Math.tan(angle));
					v.y = (float) (Math.cos(angle) * force);
					
					forces.add(v);
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)position.x, (int)position.y, (int)w, (int)h, 0xffffff00, Light.NONE);
	}
}
