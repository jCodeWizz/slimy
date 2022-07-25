package dev.CodeWizz.engine.gfx;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.shooty.Shooty;

public class Camera {

	private int x, y;
	private final int bX = 240, bY = 260;
	
	public void update(GameContainer gc) {
		
		
		int tempX = (int) Shooty.inst.getPlayer().getPosition().x - 8 - gc.getWidth() / 2;
		int tempY = (int) Shooty.inst.getPlayer().getPosition().y - 8 - gc.getHeight() / 2;
		
		if(tempX > 0 && tempX < bX) {
			x = tempX;
		}
		if(tempX < 0) {
			x = 0;
		}
		if(tempX > bX) {
			x = bX;
		}
		
		if(tempY > 0 && tempY < bY) {
			y = tempY;
		}
		if(tempY < 0) {
			y = 0;
		}
		if(tempY > bY) {
			y = bY;
		}
		
		
		gc.getRenderer().setCamX(x);
		gc.getRenderer().setCamY(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
}
