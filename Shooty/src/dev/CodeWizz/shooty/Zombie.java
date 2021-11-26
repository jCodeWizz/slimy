package dev.CodeWizz.shooty;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.shooty.weapons.DamageIndicator;

public class Zombie extends GameObject {

	private float vel = 2;
	
	public Zombie(float x, float y) {
		super(x, y);

		this.gameObjectCollisionID.add(ID.Box);
		this.gameObjectCollisionID.add(ID.Zombie);

		this.canMove = true;

		gravity = 0f;
		friction = -0.075f;
		bounce = 0f;
		mass = 20;
		airFrictionY = 0.1f;
		airFrictionX = 0.1f;
		
		this.id = ID.Zombie;
		
		this.health = 10;
	
	}
	
	@Override
	public void damage(GameContainer gc, float damage) {
		if(!hurt) {
			super.damage(gc, damage);
			bloodParticles((int)position.x, (int)position.y);
			Shooty.addIndicator(new DamageIndicator((int)position.x, (int)position.y, damage));
		}
	}

	@Override
	public void tick(GameContainer gc) {
		Vector v = Shooty.inst.getPlayer().getPosition();
		
		speed.x = v.x > position.x ? vel : -vel;
		speed.y = v.y > position.y ? vel : -vel;
		
		super.tick(gc);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xff5c6e4c, position, 8);
		if(hurt) 
			r.fillCircle(0xC88c5858, position, 8);		
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)position.x - (int)w/2, (int)position.y - (int)h/2, (int)w, (int)h);
	}
}
