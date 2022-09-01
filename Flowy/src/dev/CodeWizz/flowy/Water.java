package dev.CodeWizz.flowy;

import java.util.ArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public class Water {

	public static int R = 2;
	public static float MASS = 18.0f, G = 9.18f, Cw = -0.06f;
	
	public ArrayList<Vector> forces = new ArrayList<>();
	
	public Vector pos;
	public Vector speed;
	public Vector acc;
	
	public Water(int x, int y) {
		pos = new Vector(x, y);
		speed = new Vector();
		acc = new Vector();
	}
	
	public void update() {
		forces.add(new Vector(0, G * MASS));
		forces.add(new Vector(0.5f * Cw * 2 * speed.x * speed.x, 0.5f * Cw * 2 * speed.y * speed.y));
		
		for(Water w : Flowy.p) {
			if(WMath.distance(w.pos.x, w.pos.y, pos.x, pos.y) <= R*2) {
				
			}
		}
		
		for(Vector f : forces) {
			acc.add(f.devide(MASS));
		}
		
		speed.add(acc);
		pos.add(speed);
		
		acc.clear();
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)pos.x, (int)pos.y, R, R, 0xff0000ff, Light.NONE);
	}
}
