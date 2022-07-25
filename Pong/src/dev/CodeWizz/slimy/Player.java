package dev.CodeWizz.slimy;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Player extends GameObject {

	public Player(float x, float y, int w, int h) {
		super(x, y);
		
		this.id = ID.Box;
		
		this.w = w;
		this.h = h;
	}
	
	@Override
	public void tick(GameContainer gc) {
		y = gc.getInput().getMouseY()-h/2;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRect(getBounds(), 0xffffffff);
	}
	
	
}
