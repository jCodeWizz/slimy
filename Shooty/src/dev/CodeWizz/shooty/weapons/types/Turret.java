package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Turret extends Weapon {

	public Turret() {
		this.broken = true;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 8;
		this.burstDelay = 0;
		this.spread = 0;
		
		
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("USSR", 3, 1);
	}
}
