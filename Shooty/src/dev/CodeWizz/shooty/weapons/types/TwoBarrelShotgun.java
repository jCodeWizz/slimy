package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Weapon;

public class TwoBarrelShotgun extends Weapon {

	public TwoBarrelShotgun() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 12;
		this.refireTime = 60;
		this.reloadTime = 180;
		this.maxAmmo = 2;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "TWO BARREL SHOTGUN";
		this.ammoType = Ammo.SG;
		
		this.id = 16;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 3);
	}
}
