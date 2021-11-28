package dev.CodeWizz.shooty.weapons.types;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.shooty.Player;
import dev.CodeWizz.shooty.Shooty;
import dev.CodeWizz.shooty.weapons.Grenade;
import dev.CodeWizz.shooty.weapons.Weapon;

public class GrenadeWeapon extends Weapon {

	private int amount;
	
	
	public GrenadeWeapon(int amount) {
		this.broken = true;
		
		this.amount = amount;
		
		this.name = "GRENADE";
	}

	
	@Override
	public void update(GameContainer gc) {
		if(gc.getInput().isButtonDown(1)) {
			launch(gc);
		}
	}
	
	private void launch(GameContainer gc) {
		Player player = Shooty.inst.getPlayer();
		Vector p = player.getPosition();
		gc.handler.addObject(new Grenade(p.x, p.y, Vector.forceToVector(2, p, new Vector(gc.getInput().getMouseX(), gc.getInput().getMouseY()))));
		
		amount--;
		
		if(amount == 0) {
			for(int i = 0; i < player.slots.length; i++) {
				if(player.slots[i].getWeapon().equals(this)) {
					player.slots[i].setWeapon(new Hands());
				}
			}
		}
	}

	@Override
	public Image getIcon() {
		return Textures.get("grenades", 1, 1);
	}
}
