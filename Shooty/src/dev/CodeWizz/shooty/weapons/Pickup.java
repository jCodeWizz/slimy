package dev.CodeWizz.shooty.weapons;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.Player;
import dev.CodeWizz.shooty.Shooty;

public class Pickup {
	
	private Weapon w;
	private boolean hasWeapon;
	
	private int x, y;
	
	private Ammo ammoType;
	private int amount;
	private boolean hasAmmo;
	
	public Pickup(int x, int y, Weapon weapon) {
		this.x = x;
		this.y = y;
		
		this.w = weapon;
		hasWeapon = true;
	}
	
	public Pickup(int x, int y, Ammo type, int amount) {
		this.x = x;
		this.y = y;
		
		this.ammoType = type;
		this.amount = amount;
		this.hasAmmo = true;
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

	public void pickup() {
		Player p = Shooty.inst.getPlayer();
		Shooty.picks.remove(this);
		
		if(hasWeapon) {
			for(int i = 0; i < p.slots.length; i++) {
				if(p.slots[i].getWeapon().name.equalsIgnoreCase("Hands")) {
					p.slots[i].setWeapon(w);
					break;
				}
			}
		} else if (hasAmmo){
			if(ammoType == Ammo.PI) {
				Weapon.ammoPI+=amount;
			} else if(ammoType == Ammo.SG) {
				Weapon.ammoSG+=amount;
			} else if(ammoType == Ammo.AR) {
				Weapon.ammoAR+=amount;
			} else if(ammoType == Ammo.SN) {
				Weapon.ammoSN+=amount;
			}
		}
		
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, 16, 16);
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(hasWeapon) {
			r.drawImage(w.getIcon(), x, y, 1);
		} else {
			if(ammoType == Ammo.PI) {
				r.drawImage(Textures.get("icons", 0, 1), x, y);
			} else if(ammoType == Ammo.SG) {
				r.drawImage(Textures.get("icons", 1, 1), x, y);
			} else if(ammoType == Ammo.AR) {
				r.drawImage(Textures.get("icons", 2, 1), x, y);
			} else if(ammoType == Ammo.SN) {
				r.drawImage(Textures.get("icons", 3, 1), x, y);
			}
		}
	}
}
