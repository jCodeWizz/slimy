package dev.CodeWizz.slimy;

import java.util.Random;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Ball extends GameObject {

	public static float speed = 1f;
	private int counter = 0;
	
	public Ball(float x, float y) {
		super(x, y);
		
		this.id = ID.Balrups;
	
		this.w = 8;
		this.h = 8;
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		if(y+8 > gc.getHeight())
			flipY();
		else if(y < 0)
			flipY();
		else if(x < 0)
			flipX();
		else if(x+8 > gc.getWidth())
			score(gc);
		
		for(GameObject go : gc.handler.object) {
			if(go.getId() == ID.Box) {
				if(this.getBounds().intersects(go.getBounds())) {
					flipX();
				}
			}
		}
		
		if(counter < 120) {
			counter++;
		} else {
			counter = 0;
			speed+=0.5f;
		}
		
		x+=velX*speed;
		y+=velY*speed;
		
	}
	
	public GameObject resetBall(GameContainer gc) {
		x = gc.getWidth()/2-w/2;
		y = gc.getHeight()/2-h/2;
		
		Random r = new Random();
		
		velX = r.nextFloat() + 1f;
		velY = r.nextFloat()*2 - 1f;
		
		speed = 1f;
		
		return this;
	}
	
	private void score(GameContainer gc) {
		Slimy.score++;
		resetBall(gc);
	}
	
	private void flipX() {
		velX*=-1;
	}
	
	private void flipY() {
		velY*=-1;
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)x, (int)y, 8, 8, 0xffffffff, Light.NONE);
		
		r.drawText("SCORE: " + Slimy.score, 0, 0);
	}
}
