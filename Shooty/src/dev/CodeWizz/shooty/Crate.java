package dev.CodeWizz.shooty;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Pickup;
import dev.CodeWizz.shooty.weapons.Weapon;
import dev.CodeWizz.shooty.weapons.types.AK47;
import dev.CodeWizz.shooty.weapons.types.AR16;
import dev.CodeWizz.shooty.weapons.types.Carbine;
import dev.CodeWizz.shooty.weapons.types.CombatPistol;
import dev.CodeWizz.shooty.weapons.types.Famas;
import dev.CodeWizz.shooty.weapons.types.GrenadeLauncher;
import dev.CodeWizz.shooty.weapons.types.HeavySniper;
import dev.CodeWizz.shooty.weapons.types.MarksmanRifle;
import dev.CodeWizz.shooty.weapons.types.Minigun;
import dev.CodeWizz.shooty.weapons.types.OldPistol;
import dev.CodeWizz.shooty.weapons.types.Pistol;
import dev.CodeWizz.shooty.weapons.types.Remington;
import dev.CodeWizz.shooty.weapons.types.SawedOff;
import dev.CodeWizz.shooty.weapons.types.Turret;
import dev.CodeWizz.shooty.weapons.types.TwoBarrelShotgun;

public class Crate extends GameObject {

	private List<Pickup> loot = new CopyOnWriteArrayList<>();
	
	private int weaponAmount;
	private int ammoAmount;
	
	private Random r;
	
	public Crate(float x, float y) {
		super(x, y);
		
		this.id = ID.Crate;
		
		r = new Random();
		
		weaponAmount = r.nextInt(2) + 1;
		ammoAmount = r.nextInt(3) + 1;
		
		if(weaponAmount > 0) {
			for(int i = 0; i < weaponAmount; i++) {
				Weapon w = selectRandomWeapon();
				loot.add(new Pickup((int)x, (int)y, w.getAmmoType(), 30));
				loot.add(new Pickup((int)x, (int)y, w.getAmmoType(), 30));
				
				loot.add(new Pickup((int)x, (int)y, w));
			}
		}
		
		for(int i = 0; i < ammoAmount; i++) {
			int random = r.nextInt(Ammo.values().length);
			int amount = r.nextInt(4) + 1;
			loot.add(new Pickup((int)x, (int)y, Ammo.values()[random], amount*30));
			
		}
	}
	
	private Weapon selectRandomWeapon() {
		int w = r.nextInt(15) + 1;
		
		if(w == 1) {
			return new AK47();
		} else if(w == 2) {
			return new AR16();
		} else if(w == 3) {
			return new Carbine();
		} else if(w == 4) {
			return new CombatPistol();
		} else if(w == 5) {
			return new Famas();
		} else if(w == 6) {
			return new GrenadeLauncher();
		} else if(w == 7) {
			return new HeavySniper();
		} else if(w == 8) {
			return new MarksmanRifle();
		} else if(w == 9) {
			return new Minigun();
		} else if(w == 10) {
			return new OldPistol();
		} else if(w == 11) {
			return new Pistol();
		} else if(w == 12) {
			return new Remington();
		} else if(w == 13) {
			return new SawedOff();
		} else if(w == 14) { 
			return new Turret();
		} else if(w == 15) {
			return new TwoBarrelShotgun();
		} else {
			return selectRandomWeapon();
		}
	}
	
	
	public void loot(GameContainer gc) {
		for(Pickup p : loot) {
			p.setX(p.getX() + (r.nextInt(20) - 10));
			p.setY(p.getY() + (r.nextInt(20) - 10));
			Shooty.picks.add(p);
			gc.handler.removeObject(this);
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.drawImage(Textures.get("crate"), (int)position.x, (int)position.y);
	}
}
