package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.gfx.particles.Particle;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;

public class Bullet extends GameObject {

	private final int particleCount = 8;;
	
	public Bullet(float x, float y, Vector dir, float speed) {
		super(x, y);
	
		this.id = ID.Bullet;
		
		this.velX = dir.x * speed;
		this.velY = dir.y * speed;
		
		w = 4;
		h = 4;
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		x+=velX;
		y+=velY;
		
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Box) {
				if(getBounds().intersects(object.getBounds())) {
					gc.handler.removeObject(this);
					for(int i = 0; i < particleCount; i++) {
						Particle.add(new Particle(x + 2, y + 2, 0xffffff00, 1, 60, 2));
						
					}
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)x, (int)y, (int)w, (int)h, 0xffffff00, Light.FULL);
	}
}
