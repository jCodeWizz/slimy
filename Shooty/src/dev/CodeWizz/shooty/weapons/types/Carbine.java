package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Carbine extends Weapon {

	public Carbine() {
		this.broken = false;
		this.fullAuto = true;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 30;
		this.reloadTime = 120;
		this.maxAmmo = 25;
		this.burstDelay = 0;
		this.spread = 0;
		
		
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("USSR", 2, 0);
	}
}
