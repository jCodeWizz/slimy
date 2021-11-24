package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Weapon;

public class Hands extends Weapon {

	public Hands() {
		this.broken = true;
		this.fullAuto = false;
		this.burst = false;
		
		this.amountOfBullets = 0;
		this.refireTime = 0;
		this.reloadTime = 0;
		this.maxAmmo = 0;
		this.burstDelay = 0;
		this.spread = 0;
	}
	
	
	@Override
	public Image getIcon() {
		return Textures.get("hands");
	}
}
