package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Famas extends Weapon {

	public Famas() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = true;
		
		this.amountOfBullets = 3;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 30;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.damage = 1;
		
		this.name = "FAMAS";
		this.ammoType = Ammo.AR;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 3, 1);
	}
}
