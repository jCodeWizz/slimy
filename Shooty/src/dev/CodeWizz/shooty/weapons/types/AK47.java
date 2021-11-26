package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Weapon;

public class AK47 extends Weapon {

	public AK47() {
		this.broken = false;
		this.fullAuto = true;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 5;
		this.reloadTime = 120;
		this.maxAmmo = 30;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.damage = 1;
		
		this.name = "AK 47";
		this.ammoType = Ammo.AR;
		
		this.id = 1;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 1);
	}
}
