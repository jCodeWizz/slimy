package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class AR16 extends Weapon {

	public AR16() {
		this.broken = false;
		this.fullAuto = true;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 7;
		this.reloadTime = 120;
		this.maxAmmo = 30;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "AR 16";
		this.ammoType = Ammo.AR;
		
		this.damage = 1.5f;
		
		this.rarity = Rarity.Uncommon;
		
		this.id = 1;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("UK", 0, 1);
	}
}
