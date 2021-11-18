package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;

public class Box extends GameObject {

	private int color, light;
	
	public Box(float x, float y, int w, int h, int color, int light) {
		super(x, y);

		this.w = w;
		this.h = h;
		
		this.id = ID.Box;
	
		this.color = color;
		this.light = light;
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)x, (int)y, (int)w, (int)h, color, light);
	}
}
