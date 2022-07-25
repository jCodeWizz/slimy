package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;

public class Border extends GameObject {

	public Border(float x, float y, float w, float h) {
		super(x, y);
		
		this.w = w;
		this.h = h;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {

	}
}
