package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class SawedOff extends Weapon {

	public SawedOff() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 0;
		this.reloadTime = 30;
		this.maxAmmo = 1;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "SAWED OFF";
		this.ammoType = Ammo.SG;
		
		this.rarity = Rarity.Uncommon;
		
		this.id = 13;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("US", 2, 1);
	}
}
