package dev.CodeWizz.shooty.weapons;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.gfx.particles.Particle;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;

public class Bullet extends GameObject {

	private final int particleCount = 8;
	
	public Bullet(float x, float y, Vector v, float speed) {
		super(x, y);

		this.gameObjectCollisionID.add(ID.Box);
		
		v.multiply(speed);
		v.multiply(mass);
		this.speed = v;
		
		this.canMove = true;
		
		gravity = 0f; 
		friction = 0f; 
		bounce = 0f; 
		mass = 20; 
		airFrictionY = 0f; 
		airFrictionX = 0f;
		
		w = 4;
		h = 4;
	}
	
	@Override
	public void tick(GameContainer gc) {
		if(!getBounds().intersects(new Rectangle(gc.camera.getX(), gc.camera.getY(), gc.getWidth(), gc.getHeight()))) {
			gc.handler.removeObject(this);
		}
		super.tick(gc);
	}

	
	
	@Override
	public void collided(GameContainer gc, GameObject object) {
		if(object.getId() == ID.Box) {
			gc.handler.removeObject(this);
			for(int i = 0; i < particleCount; i++) {
				Particle.add(new Particle(position.x + 2, position.y + 2, 0xffffff00, 1, 60, 2));
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)position.x, (int)position.y, (int)w, (int)h, 0xffffff00, Light.NONE);
	}
}
