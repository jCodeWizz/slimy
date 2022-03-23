package dev.CodeWizz.shooty;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.State;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WMath;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.Pickup;
import dev.CodeWizz.shooty.weapons.Slot;
import dev.CodeWizz.shooty.weapons.Weapon;
import dev.CodeWizz.shooty.weapons.types.GrenadeWeapon;
import dev.CodeWizz.shooty.weapons.types.Hands;

public class Player {

	private Vector position, speed, acc, dim;
	private final float vel = 7, maxVel = 12;
	private float friction = -0.075f, mass = 20, airFrictionY = 0.075f, airFrictionX = 0.075f;
	private List<Vector> forces = new CopyOnWriteArrayList<>();
	private ArrayList<ID> gameObjectCollisionID = new ArrayList<>();
	
	public Slot[] slots;
	private int selectedSlot = 0;
		
	
	public Player() {
		position = new Vector();
		speed = new Vector();
		acc = new Vector();
		dim = new Vector(16, 16);
	}

	public void init(GameContainer gc) {
		gameObjectCollisionID.add(ID.Box);
		slots = new Slot[5];
		
		
		
		int betweenslots = 10;
		int totalWidth = (Slot.getW()*slots.length) + (slots.length-1) * betweenslots;
		
		for(int i = 0; i < slots.length; i++) {
			slots[i] = new Slot(gc.getWidth()/2 - totalWidth/2 + i * betweenslots + i * Slot.getW(), 10, new Hands());
		}
		
		slots[0].setWeapon(new GrenadeWeapon(20));
		//slots[1].setWeapon(new Remington());
		//slots[2].setWeapon(new MarksmanRifle());
		//slots[3].setWeapon(new CombatPistol());
		//slots[4].setWeapon(new HeavySniper());
	}

	public void update(GameContainer gc) {
		if (gc.getGameState() == State.Game) {
			
			slots[selectedSlot].update(gc);
			
			if(gc.getInput().getScroll() > 0) {
				if(selectedSlot < slots.length-1) {
					selectedSlot++;
				} else {
					selectedSlot = 0;
				}
			} else if(gc.getInput().getScroll() < 0) {
				if(selectedSlot > 0) {
					selectedSlot--;
				} else {
					selectedSlot = slots.length-1;
				}
			}
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_R)) {
				if(!slots[selectedSlot].getWeapon().isReloading()) {
					slots[selectedSlot].getWeapon().startReload();
				}
			}
			
			if(gc.getInput().isKeyDown(KeyEvent.VK_E)) {
				for(GameObject object : gc.handler.object) {
					if(object.getId() == ID.Crate) {
						if(WMath.distance(position, object.getPosition()) < 24) {
							((Crate)object).loot(gc);
							break;
						}
					}
				}
				
				for(Pickup p : Shooty.picks) {
					if(getBounds().intersects(p.getBounds()) && p.isHasWeapon()) {
						p.pickup();
						break;
					}
				}	
			}
			
			if(gc.getInput().isKey(KeyEvent.VK_E)) {
				for(Pickup p : Shooty.picks) {
					if(getBounds().intersects(p.getBounds()) && p.isHasAmmo()) {
						p.pickup();
						break;
					}
				}	
			}
			
			for(int i = 0; i < slots.length; i++) {
				if(i == selectedSlot)
					slots[i].setSelected(true);
				else
					slots[i].setSelected(false);
			}
			
			physics(gc);
		}
	}

	public void render(GameContainer gc, Renderer r) {
		if(slots[selectedSlot].getWeapon().isLaser()) {
			r.drawObstructedLine(0xffff0000, (int)position.x, (int)position.y, gc.getInput().getMouseX(), gc.getInput().getMouseY());
		}
		
		
		r.fillCircle(0xffffffff, position, (int)dim.x/2);
		//r.drawRect(getBounds(), 0xffff0000);
	}

	public void renderUI(GameContainer gc, Renderer r) {
		for(int i = 0; i < slots.length; i++) {
			slots[i].render(gc, r);
		}
		
		r.setFont(Font.STANDARD);
		
		r.drawImageUI(Textures.get("info"), 10, 10, 2);
		
		if(slots[selectedSlot].getWeapon().getAmmoType() == Ammo.PI) {
			r.drawImageUI(Textures.get("icons", 0, 1), 15, 20);
			r.drawImageUI(Textures.get("icons", 1, 1), 10, 35);
			r.drawImageUI(Textures.get("icons", 2, 1), 10, 50);
			r.drawImageUI(Textures.get("icons", 3, 1), 10, 65);
			
			r.drawText(Weapon.ammoPI + "", 40, 22, 2, 0xffffffff);
			r.drawText(Weapon.ammoSG + "", 35, 37, 2, 0xffffffff);
			r.drawText(Weapon.ammoAR + "", 35, 52, 2, 0xffffffff);
			r.drawText(Weapon.ammoSN + "", 35, 67, 2, 0xffffffff);
			
		} else if(slots[selectedSlot].getWeapon().getAmmoType() == Ammo.SG) {
			r.drawImageUI(Textures.get("icons", 0, 1), 10, 20);
			r.drawImageUI(Textures.get("icons", 1, 1), 15, 35);
			r.drawImageUI(Textures.get("icons", 2, 1), 10, 50);
			r.drawImageUI(Textures.get("icons", 3, 1), 10, 65);
			
			r.drawText(Weapon.ammoPI + "", 35, 22, 2, 0xffffffff);
			r.drawText(Weapon.ammoSG + "", 40, 37, 2, 0xffffffff);
			r.drawText(Weapon.ammoAR + "", 35, 52, 2, 0xffffffff);
			r.drawText(Weapon.ammoSN + "", 35, 67, 2, 0xffffffff);
			
		} else if(slots[selectedSlot].getWeapon().getAmmoType() == Ammo.AR) {
			r.drawImageUI(Textures.get("icons", 0, 1), 10, 20);
			r.drawImageUI(Textures.get("icons", 1, 1), 10, 35);
			r.drawImageUI(Textures.get("icons", 2, 1), 15, 50);
			r.drawImageUI(Textures.get("icons", 3, 1), 10, 65);
			
			r.drawText(Weapon.ammoPI + "", 35, 22, 2, 0xffffffff);
			r.drawText(Weapon.ammoSG + "", 35, 37, 2, 0xffffffff);
			r.drawText(Weapon.ammoAR + "", 40, 52, 2, 0xffffffff);
			r.drawText(Weapon.ammoSN + "", 35, 67, 2, 0xffffffff);
			
		} else if(slots[selectedSlot].getWeapon().getAmmoType() == Ammo.SN) {
			r.drawImageUI(Textures.get("icons", 0, 1), 10, 20);
			r.drawImageUI(Textures.get("icons", 1, 1), 10, 35);
			r.drawImageUI(Textures.get("icons", 2, 1), 10, 50);
			r.drawImageUI(Textures.get("icons", 3, 1), 15, 65);
			
			r.drawText(Weapon.ammoPI + "", 35, 22, 2, 0xffffffff);
			r.drawText(Weapon.ammoSG + "", 35, 37, 2, 0xffffffff);
			r.drawText(Weapon.ammoAR + "", 35, 52, 2, 0xffffffff);
			r.drawText(Weapon.ammoSN + "", 40, 67, 2, 0xffffffff);
			
		} else {
			r.drawImageUI(Textures.get("icons", 0, 1), 10, 20);
			r.drawImageUI(Textures.get("icons", 1, 1), 10, 35);
			r.drawImageUI(Textures.get("icons", 2, 1), 10, 50);
			r.drawImageUI(Textures.get("icons", 3, 1), 10, 65);
			
			r.drawText(Weapon.ammoPI + "", 35, 22, 2, 0xffffffff);
			r.drawText(Weapon.ammoSG + "", 35, 37, 2, 0xffffffff);
			r.drawText(Weapon.ammoAR + "", 35, 52, 2, 0xffffffff);
			r.drawText(Weapon.ammoSN + "", 35, 67, 2, 0xffffffff);

			
		}
		
		if(slots[selectedSlot].getWeapon().getAmmo() == 0) {
			if(slots[selectedSlot].getWeapon().isReloading()) {
				r.drawText("RELOADING", 20, 14, 1, 0xff00ff00);
			} else {
				r.drawText("RELOAD!", 20, 14, 1, 0xffff0000);
			}
		}
		
		
		
		
		
		r.fillRectUI(14, 14, (int) WMath.remap(slots[selectedSlot].getWeapon().getAmmo(), 0, slots[selectedSlot].getWeapon().getMaxAmmo(), 0, 50), 5, 0xffffffff, Light.NONE);
	}
	
	private void collisionX(GameContainer gc) {
		boolean collided = false;
		if (speed.x > 0) {
			for (int i = 0; i < (int)speed.x; i++) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x + 1 - (int)dim.x/2, (int) position.y  - (int)dim.y/2, (int) dim.x, (int) dim.y).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					
					speed.x = 0;
					continue;
				} else {
					position.x++;
				}
			}
		} else if (speed.x < 0) {
			for (int i = 0; i > (int)speed.x; i--) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x - 1  - (int)dim.x/2, (int) position.y  - (int)dim.y/2, (int) dim.x, (int) dim.y).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					speed.x = 0;
					continue;
				} else {
					position.x--;
				}
			}
		}
	}
	
	private void physics(GameContainer gc) {
		acc.clear();

		if (!forces.isEmpty()) {
			for (Vector vec : forces) {
				acc.add(vec);
			}
			forces.clear();
		}

		forces.add(new Vector(speed.x * friction * mass, 0));
		forces.add(new Vector(0, speed.y * friction * mass));

		// LUCHT WEERSTAND

		if (speed.y > 0) {
			forces.add(new Vector(0, 0.5f * -airFrictionY * speed.y * speed.y));
		} else {
			forces.add(new Vector(0, 0.5f * airFrictionY * speed.y * speed.y));
		}

		if (speed.x > 0) {
			forces.add(new Vector(0.5f * -airFrictionX * speed.x * speed.x, 0));
		} else {
			forces.add(new Vector(0.5f * airFrictionX * speed.x * speed.x, 0));
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_A) && !gc.getInput().isKey(KeyEvent.VK_D)) {
			if(speed.x > -maxVel) {
				forces.add(new Vector(-vel, 0));
			}
		} else if(gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isKey(KeyEvent.VK_A)) {
			if(speed.x < maxVel) {
				forces.add(new Vector(vel, 0));
			}
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_W) && !gc.getInput().isKey(KeyEvent.VK_S)) {
			if(speed.y > -maxVel) {
				forces.add(new Vector(0, -vel));
			}
		} else if(gc.getInput().isKey(KeyEvent.VK_S) && !gc.getInput().isKey(KeyEvent.VK_W)) {
			if(speed.y < maxVel) {
				forces.add(new Vector(0, vel));
			}
		}
			
		
		
		acc.devide(mass);
		speed.add(acc);

		collisionX(gc);
		collisionY(gc);

		if (speed.x > -0.1f && speed.x < 0.1f)
			speed.x = 0;

		if (speed.y > -0.1f && speed.y < 0.1f)
			speed.y = 0;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)position.x  - (int)dim.x/2, (int)position.y - (int)dim.y/2,  (int)dim.x, (int)dim.y);
	}
	
	private void collisionY(GameContainer gc) {
		boolean collided = false;
		if (speed.y > 0) {
			for (int i = 0; i < (int)speed.y; i++) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x  - (int)dim.x/2, (int) position.y + 1  - (int)dim.y/2, (int) dim.x, (int) dim.y).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					
					speed.y = 0;
					continue;
				} else {
					position.y++;
				}
			}
		} else if (speed.y < 0) {
			for (int i = 0; i > (int)speed.y; i--) {
				for (GameObject object : gc.handler.object) {
					if (gameObjectCollisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x  - (int)dim.x/2, (int) position.y - 1  - (int)dim.y/2, (int) dim.x, (int) dim.y).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					speed.y = 0;
					continue;
				} else {
					position.y--;
				}
			}
		}
	}

	public Vector getPosition() {
		return position;
	}

	public void setPosition(Vector position) {
		this.position = position;
	}

	public Vector getSpeed() {
		return speed;
	}

	public void setSpeed(Vector speed) {
		this.speed = speed;
	}

	public Vector getAcc() {
		return acc;
	}

	public void setAcc(Vector acc) {
		this.acc = acc;
	}

	public List<Vector> getForces() {
		return forces;
	}

	public void setForces(List<Vector> forces) {
		this.forces = forces;
	}

	public float getVel() {
		return vel;
	}

}
