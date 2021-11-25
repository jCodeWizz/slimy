package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Rarity;
import dev.CodeWizz.shooty.weapons.Weapon;

public class GrenadeLauncher extends Weapon {

	public GrenadeLauncher() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 30;
		this.reloadTime = 240;
		this.maxAmmo = 1;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "GRENADE LAUNCHER";
		
		this.rarity = Rarity.Uncommon;
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("US", 1, 3);
	}
}
