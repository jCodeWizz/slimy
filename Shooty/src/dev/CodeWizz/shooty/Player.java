package dev.CodeWizz.shooty;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.State;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.shooty.weapons.Weapon;
import dev.CodeWizz.shooty.weapons.types.Pistol;

public class Player {

	private Vector position, speed, acc, dim;
	private final float vel = 7, maxVel = 12;
	private float friction = -0.075f, mass = 20, airFrictionY = 0.075f, airFrictionX = 0.075f;
	private List<Vector> forces = new CopyOnWriteArrayList<>();
	private ArrayList<ID> gameObjectCollisionID = new ArrayList<>();
	private Weapon weapon;
	
	
	
	public Player() {
		position = new Vector();
		speed = new Vector();
		acc = new Vector();
		dim = new Vector(16, 16);
	}

	public void init(GameContainer gc) {
		gameObjectCollisionID.add(ID.Box);
		weapon = new Pistol();
	}

	public void update(GameContainer gc) {
		if (gc.getGameState() == State.Game) {
			
			if(gc.getInput().isButton(1)) {
				//float angle = (float) Math.atan2(gc.getInput().getMouseY() - position.y,gc.getInput().getMouseX() - position.x);
				//gc.handler.addObject(new Bullet(position.x, position.y, new Vector((float)Math.cos(angle), (float)Math.sin(angle)), 2));
			}
			
			weapon.update(gc);
			
			physics(gc);
			
			
		}
	}

	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0xffffffff, position, (int)dim.x/2);
		r.drawRect(getBounds(), 0xffff0000);
		
		weapon.render(gc, r);
	}

	public void renderUI(GameContainer gc, Renderer r) {
		r.drawImageUI(Textures.get("gunholder"), 0, gc.getHeight()-Textures.get("gunholder").getH()*2, 2);
		r.drawImageUI(weapon.getIcon(), 3, gc.getHeight()-weapon.getIcon().getH()*2-6, 2);
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
