package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Minigun extends Weapon {

	public Minigun() {
		this.broken = false;
		this.fullAuto = true;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 1;
		this.reloadTime = 360;
		this.maxAmmo = 500;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.damage = 1;
		
		this.name = "MINI GUN";
		this.ammoType = Ammo.AR;
		
		this.rarity = Rarity.Epic;
		
		this.id = 9;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 0);
	}
}
