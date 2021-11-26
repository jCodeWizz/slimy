package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class CombatPistol extends Weapon {

	public CombatPistol() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = true;
		this.laser = true;
		
		this.amountOfBullets = 2;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 16;
		this.burstDelay = 5;
		this.spread = 0;
		
		this.damage = 1.5f;
		
		this.name = "COMBAT PISTOL";
		this.ammoType = Ammo.PI;
	
		this.rarity = Rarity.Uncommon;
	
		this.id = 4;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 3, 2);
	}
}
