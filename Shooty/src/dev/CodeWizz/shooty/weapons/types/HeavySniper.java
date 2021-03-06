package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class HeavySniper extends Weapon {

	public HeavySniper() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 5;
		this.burstDelay = 0;
		this.spread = 0;
		this.damage = 25f;
		
		this.name = "HEAVY SNIPER";
		this.ammoType = Ammo.SN;
		
		this.rarity = Rarity.Rare;
		
		this.id = 7;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("USSR", 3, 2);
	}
}
