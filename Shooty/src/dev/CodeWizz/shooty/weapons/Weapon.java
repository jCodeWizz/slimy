package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.shooty.Shooty;

public abstract class Weapon {

	protected int ammo, counter, spread, amountOfBullets, bulletsToFire, counter2, burstDelay, refireTime, reloadTime, reloadCounter, maxAmmo;
	protected float damage;	
	protected boolean broken, fullAuto, burst, fireBullets, reloading, canFire;
	
	public Weapon() {
		
	}
	
	public void update(GameContainer gc) {
		if(!broken) {
			if(fireBullets && !reloading) {
				if(counter2 < burstDelay) {
					counter2++;
				} else {
					counter2 = 0;
					if(bulletsToFire == 0) {
						fireBullets = false;
						fireBullet(gc, new Vector(gc.getInput().getMouseX(), gc.getInput().getMouseY()));
					} else {
						bulletsToFire--;
						fireBullet(gc, new Vector(gc.getInput().getMouseX(), gc.getInput().getMouseY()));
					}
				}
			}
			
			
			if(reloading) {
				if(reloadCounter < reloadTime) {
					reloadCounter++;
				} else {
					reloadCounter = 0;
					ammo = maxAmmo;
					reloading = false;
				}
			}
			
			if(counter < refireTime) {
				counter++;
			} else {
				canFire = true;
			}
			
			if(fullAuto && !reloading) {
				if(gc.getInput().isButton(1) && canFire) {
					fire(gc);
				}
			} else {
				if(gc.getInput().isButtonDown(1) && canFire) {
					fire(gc);
				}
			}
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		
	}
	
	public boolean fire(GameContainer gc) {
		if(!broken) {
			if(ammo > 0 && !reloading) {
				ammo--;
				counter = 0;
				canFire = false;
				
				if(amountOfBullets > 1) {
					if(burst) {
						fireBullets = true;
						bulletsToFire = amountOfBullets - 1;
						
					} else {
						for(int i = 0; i < amountOfBullets; i++) {
							fireBullet(gc, new Vector(gc.getInput().getMouseX(), gc.getInput().getMouseY()));
						}
					}
				} else {
					fireBullet(gc, new Vector(gc.getInput().getMouseX(), gc.getInput().getMouseY()));
				}

				return true;
			} else if (!reloading){
				reload();
				return false;
			}
		}
		
		return false;
	}
	
	
	private void fireBullet(GameContainer gc, Vector pos) {
		float angle = (float) Math.atan2(pos.y - Shooty.inst.getPlayer().getPosition().y,pos.x - Shooty.inst.getPlayer().getPosition().x);
		gc.handler.addObject(new Bullet(Shooty.inst.getPlayer().getPosition().x, Shooty.inst.getPlayer().getPosition().y, new Vector((float)Math.cos(angle), (float)Math.sin(angle)), 2));
	}
	
	public abstract Image getIcon();
	
	
	public boolean reload() {
		// find bullets;
		
		
		// take bullets;
		
		
		// add bullets;
		reloading = true;
		reloadCounter = 0;
		
		return false;
	}

	public int getAmmo() {
		return ammo;
	}

	public void setAmmo(int ammo) {
		this.ammo = ammo;
	}

	public int getSpread() {
		return spread;
	}

	public void setSpread(int spread) {
		this.spread = spread;
	}

	public int getAmountOfBullets() {
		return amountOfBullets;
	}

	public void setAmountOfBullets(int amountOfBullets) {
		this.amountOfBullets = amountOfBullets;
	}

	public int getBurstDelay() {
		return burstDelay;
	}

	public void setBurstDelay(int burstDelay) {
		this.burstDelay = burstDelay;
	}

	public int getRefireTime() {
		return refireTime;
	}

	public void setRefireTime(int refireTime) {
		this.refireTime = refireTime;
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}

	public void setMaxAmmo(int maxAmmo) {
		this.maxAmmo = maxAmmo;
	}

	public float getDamage() {
		return damage;
	}

	public void setDamage(float damage) {
		this.damage = damage;
	}

	public boolean isBroken() {
		return broken;
	}

	public void setBroken(boolean broken) {
		this.broken = broken;
	}

	public boolean isFullAuto() {
		return fullAuto;
	}

	public void setFullAuto(boolean fullAuto) {
		this.fullAuto = fullAuto;
	}

	public boolean isBurst() {
		return burst;
	}

	public void setBurst(boolean burst) {
		this.burst = burst;
	}

	public boolean isFireBullets() {
		return fireBullets;
	}

	public void setFireBullets(boolean fireBullets) {
		this.fireBullets = fireBullets;
	}

	public boolean isReloading() {
		return reloading;
	}

	public void setReloading(boolean reloading) {
		this.reloading = reloading;
	}

	public boolean isCanFire() {
		return canFire;
	}

	public void setCanFire(boolean canFire) {
		this.canFire = canFire;
	}
}
