package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.shooty.Shooty;

public class DamageIndicator {

	private int x, y;
	private float damage;
	private int counter = 0;
	
	public DamageIndicator(int x, int y, float damage) {
		this.x = x;
		this.y = y;
		
		this.damage = damage;
	}
	
	public void update(GameContainer gc) {
		y-=1;
		
		counter++;
		if(counter >= 60)
			Shooty.removeIndicator(this);
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(damage == 1) {
			r.drawText((int)damage + "", x, y, 0xffff9900, false);
		} else if(damage == 2) {
			r.drawText((int)damage + "", x, y, 0xffff8800, false);
		} else if(damage > 2) {
			r.drawText((int)damage + "", x, y, 0xffff0000, false);
		}
	}
}
