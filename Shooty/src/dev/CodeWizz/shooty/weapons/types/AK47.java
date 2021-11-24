package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Weapon;

public class AK47 extends Weapon {

	public AK47() {
		this.broken = false;
		this.fullAuto = true;
		this.burst = false;
		
		this.amountOfBullets = 1;
		this.refireTime = 10;
		this.reloadTime = 120;
		this.maxAmmo = 30;
		this.burstDelay = 0;
		this.spread = 0;
		
		this.name = "AK 47";
	}
	
	@Override
	public Image getIcon() {
		return Textures.get("G", 0, 1);
	}
}
