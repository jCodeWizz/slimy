package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Pistol extends Weapon {

	public Pistol() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 8;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "PISTOL";	
		this.ammoType = Ammo.PI;

		this.id = 11;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("UK", 3, 1);
	}
}
