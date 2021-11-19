package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;

public class Ball extends GameObject {

	private final double c = 6.67408 * Math.pow(10, -2);
	private boolean stuck;
	
	public Ball(float x, float y, boolean stuck) {
		super(x, y);

		this.stuck = stuck;
		
		w = 16;
		h = 16;
		
		this.canMove = true;
		
		this.mass = 5;
		if(stuck)
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
					float force = (float) (c * ((mass * object.getMass()) / (8*8)));
					
					int dx = (int) (position.x - object.getPosition().x);
					int dy = (int) (position.y - object.getPosition().y);
					
					// INIT VALUES: x1 400 y1 = 400 ;; x2 = 600 y2 = 100
					
					// INIT VALUES: dx = -200 ;; dy =300
					
					// ANGLE: -0.7854981633974483
					
					double angle = 1;
					
					if(dx == 0) {
						angle = (Math.atan(dy / -1));
					}else if(dy == 0) {
						angle = (Math.atan(-1 / dx));
					} else {
						angle = (Math.atan(dy / dx));
					}
					
					

					v.x = (float) (Math.cos(angle) * force);
					v.y = (float) (Math.sin(angle) * force);
					
					forces.add(v);
				}
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)position.x, (int)position.y, (int)w, (int)h, 0xffffff00, Light.NONE);


		
		
		for(GameObject object : gc.handler.object) {
			if(!object.equals(this)) {
		//		r.drawLine(0xffff0000, (int)position.x, (int)position.y, (int)object.getPosition().x, (int)object.getPosition().y);
			}
		}
		
		for(Vector v : forces) {
			r.drawLine(0xffff0000, (int)position.x + 8, (int)position.y + 8, (int)position.x + 8 + (int)v.x * 100, (int)position.y + 8 + (int)v.y * 100);
		}
	}
}
