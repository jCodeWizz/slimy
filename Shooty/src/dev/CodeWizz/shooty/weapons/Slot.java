package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.types.Hands;

public class Slot {

	private Weapon weapon;
	private int x, y;
	private boolean selected;
	
	
	public Slot(int x, int y) {
		this.x = x;
		this.y = y;
		weapon = new Hands();
	}
	
	public Slot(int x, int y, Weapon weapon) {
		this.x = x;
		this.y = y;
		this.weapon = weapon;
	}
	
	public void update(GameContainer gc) {
		weapon.update(gc);
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(selected) {
			r.drawImageUI(Textures.get("slotselected"), x-1, y-1, 2);
		} else {
			r.drawImageUI(Textures.get("slot"), x, y, 2);
		}
		r.drawImageUI(weapon.getIcon(), x+2, y+2, 2);
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
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

	public static int getW() {
		return Textures.get("slot").getW() * 2;
	}

	public int getH() {
		return Textures.get("slot").getH() * 2;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
