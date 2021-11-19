package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Ball extends GameObject {

	public Ball(float x, float y) {
		super(x, y);

		w = 16;
		h = 16;
		
		this.canMove = true;
		
		this.mass = 5;
		this.bounce = -0.2f;
		this.friction = -0.05f;
		this.airFrictionX = 0.05f;
		
		this.id = ID.Bullet;
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)position.x, (int)position.y, (int)w, (int)h, 0xffffff00, Light.NONE);
	}
}
