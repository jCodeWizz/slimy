package dev.CodeWizz.flowy;

import java.util.ArrayList;
import java.util.Random;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;

public class Water {

	public static Random random = new Random();
	public static int R = 4;
	public static float MASS = 18.0f, G = 9.18f, Cw = -0.06f;
	
	public ArrayList<Vector> forces = new ArrayList<>();
	
	public Vector pos;
	public Vector speed;
	public Vector acc;
	
	public boolean remove = false, falling = true;
	
	public Water(int x, int y) {
		pos = new Vector(x, y);
		speed = new Vector();
		acc = new Vector();
		
		forces.add(new Vector(random.nextFloat()*2f-1f, 0));
	}
	
	public void update() {
		if(remove)
			Flowy.p.remove(this);
		
		forces.add(new Vector(0, G * MASS));
		forces.add(new Vector(0.5f * Cw * 2 * speed.x, 0.5f * Cw * 2 * speed.y));
		
		for(Water w : Flowy.p) {
			if(!w.equals(this)) {
				if(WMath.distance(w.pos.x, w.pos.y, pos.x, pos.y) <= R*2) {
					speed.multiply(-1f);
				}
			}
		}
		
		for(Vector f : forces) {
			acc.add(f.devide(MASS));
		}
		speed.add(acc);
		
		int dx = 0;
		int dy = 0;
		if(speed.x != 0)
			dx = (int) (speed.x/Math.abs(speed.x));
		if(speed.y != 0) 
			dy = (int) (speed.y/Math.abs(speed.y));
		
		for(int i = dx; i != (int)speed.x; i+=dx) {
			Cell c = Cell.getCellIndex((int)(pos.x/Cell.WIDTH) + i, (int)(pos.y/Cell.WIDTH));
			if(c != null) {
				if(c.tile != Tile.Ground && c.height <= 0.5f) {
					pos.add(dx, 0);
				} else {
					speed.x = 0f;
					break;
				}
			}
		}
		
		for(int i = dy; i != (int)speed.y; i+=dy) {
			Cell c = Cell.getCellIndex((int)(pos.x/Cell.WIDTH), (int)(pos.y/Cell.WIDTH) + i);
			if(c != null) {
				if(c.tile != Tile.Ground && c.height <= 0.5f) {
					pos.add(0, dy);
					falling = true;
				} else {
					falling = false;
					speed.y = 0f;
					break;
				}
			}
		}
		
		
		
		acc.clear();
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int)pos.x-R, (int)pos.y-R, R*2, R*2, 0xff0000ff, Light.NONE);
	}
}
