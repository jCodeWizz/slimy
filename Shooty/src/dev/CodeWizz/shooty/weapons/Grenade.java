package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.Vector;

public class Grenade extends GameObject {

	private int counter = 0;
	
	public Grenade(float x, float y, Vector v) {
		super(x, y);
		
		this.canMove = true;
		
		gravity = 0f;
		friction = -0.075f;
		bounce = -0.2f;
		mass = 50;
		this.forces.add(v);
		airFrictionY = 0.075f;
		airFrictionX = 0.075f;
		
		this.gameObjectCollisionID.add(ID.Box);
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		super.tick(gc);
		
		counter++;
		if(counter >= 150) {
			gc.handler.addObject(new Explosion(position.x, position.y, 100));
			gc.handler.removeObject(this);
		}
	
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get("grenades", 1, 1), (int)position.x, (int)position.y);
	}
}
