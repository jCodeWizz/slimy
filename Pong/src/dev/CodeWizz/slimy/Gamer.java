package dev.CodeWizz.slimy;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Gamer extends GameObject {

	public Gamer(float x, float y, int w, int h) {
		super(x, y);
		this.id = ID.Box;

		this.w = w;
		this.h = h;
	}
	
	@Override
	public void tick(GameContainer gc) {
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Balrups) {
				y = object.getY() - h/2;
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawRect(getBounds(), 0xffffffff);
	}
}
