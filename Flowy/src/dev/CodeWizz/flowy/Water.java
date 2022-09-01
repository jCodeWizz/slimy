package dev.CodeWizz.flowy;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Vector;

public class Water {

	public static int WIDTH = 2, HEIGHT = 2;
	
	public Vector pos;
	public Vector speed;
	public Vector acc;
	
	public Water(int x, int y) {
		pos = new Vector(x, y);
		speed = new Vector();
		acc = new Vector();
	}
	
	public void update() {
		speed.add(acc);
		pos.add(speed);
		
		acc.clear();
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)pos.x, (int)pos.y, WIDTH, HEIGHT, 0xff0000ff, Light.NONE);
	}
}
