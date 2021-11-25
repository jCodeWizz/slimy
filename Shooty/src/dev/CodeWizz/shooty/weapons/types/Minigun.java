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
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 500;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "MINI GUN";
		this.ammoType = Ammo.AR;
		
		this.rarity = Rarity.Epic;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 0);
	}
}
