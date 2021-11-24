package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Weapon;

public class TwoBarrelShotgun extends Weapon {

	public TwoBarrelShotgun() {
		this.broken = false;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 12;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 2;
		this.burstDelay = 0;
		this.spread = 0;
		
		
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 3);
	}
}
