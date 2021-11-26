package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class MarksmanRifle extends Weapon {

	public MarksmanRifle() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		this.laser = true;
		
		this.amountOfBullets = 1;
		this.refireTime = 0;
		this.reloadTime = 120;
		this.maxAmmo = 15;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.damage = 2;
		
		this.name = "SEMI-AUTO";
		this.ammoType = Ammo.SN;
		
		this.rarity = Rarity.Epic;
		
		this.id = 8;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 2, 0);
	}
}
