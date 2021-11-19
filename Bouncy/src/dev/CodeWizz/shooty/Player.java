package dev.CodeWizz.shooty;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Vector;

public class Player {
	
	private boolean falling;
	private Vector position, speed, acc;
	private int w = 16, h = 16;
	private final float mass = 20, gravity = 9.81f/20;
	private final float moveSpeed = 0.5f * mass, friction = -0.1f, bounceConstant = -0.5f, airFrictionX = 0.05f, airFrcitionY = 0.02f;	
	private List<Vector> forces = new CopyOnWriteArrayList<>();
	private ArrayList<ID> collisionID = new ArrayList<>();
	
	public Player() {
		position = new Vector(200, 400);
		speed = new Vector();
		acc = new Vector();
		
		collisionID.add(ID.Box);
		collisionID.add(ID.Bullet);
	}
	
	public void init(GameContainer gc) {
		
	}
	
	public void update(GameContainer gc) {
		
		acc.clear();
		
		
		if(!forces.isEmpty()) {
			for(Vector vec : forces) {
				acc.add(vec);
			}
			forces.clear();
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			forces.add(new Vector(0, -175));
		}
		
		if(falling) {
			forces.add(new Vector(0, mass * gravity));
		} else {
			forces.add(new Vector(speed.x*friction*mass, 0));
		}
		
		// LUCHT WEERSTAND
		
		if(speed.y > 0) {
			forces.add(new Vector(0, 0.5f*-airFrcitionY*speed.y*speed.y));
		} else {
			forces.add(new Vector(0, 0.5f*airFrcitionY*speed.y*speed.y));

		}
		
		if(speed.x > 0) {
			forces.add(new Vector(0.5f*-airFrictionX*speed.x*speed.x, 0));
		} else {
			forces.add(new Vector(0.5f*airFrictionX*speed.x*speed.x, 0));
		}
		
		
		
		acc.devide(mass);
		
		speed.add(acc);
		input(gc);
		collisionX(gc);
		collisionY(gc);
		
		if(speed.x > -0.1f && speed.x < 0.1f)
			speed.x = 0;
		
		if(speed.y > -0.1f && speed.y < 0.1f)
			speed.y = 0;
		
	}
	
	private void collisionX(GameContainer gc) {
		boolean collided = false;

		if (speed.x > 0) {
			for (int i = 0; i < (int)speed.x; i++) {
				for (GameObject object : gc.handler.object) {
					if (collisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x + 1, (int) position.y, (int) w, (int) h).intersects(object.getBounds())) {
							
							if(object.canMove()) {
								collision(object);
							} else {
								collided = true;
							}
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(speed.x * bounceConstant * mass, 0));
					speed.x = 0;
					continue;
				} else {
					position.x++;
				}
			}
		} else if (speed.x < 0) {
			for (int i = 0; i > (int)speed.x; i--) {
				for (GameObject object : gc.handler.object) {
					if (collisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x - 1, (int) position.y, (int) w, (int) h).intersects(object.getBounds())) {
							
							if(object.canMove()) {
								collision(object);
							} else {
								collided = true;
							}
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(speed.x * bounceConstant * mass, 0));
					speed.x = 0;
					continue;
				} else {
					position.x--;
				}
			}
		}
	}
	
	private void collision(GameObject object) {
		forces.add(new Vector(mass * object.getMass() * speed.x * -0.1f, 0));
		object.getForces().add(new Vector(mass * object.getMass() * speed.x * 0.01f, 0));
	}
	
	private void collisionY(GameContainer gc) {
		boolean collided = false;

		if (speed.y >= 1) {
			for (int i = 0; i < (int)speed.y; i++) {
				for (GameObject object : gc.handler.object) {
					if (collisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x, (int) position.y + 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(0, speed.y * bounceConstant * mass));
					speed.y = 0;
					falling = false;
					continue;
				} else {
					position.y++;
					falling = true;
				}
			}
		} else if (speed.y <= -1) {
			for (int i = 0; i > (int)speed.y; i--) {
				for (GameObject object : gc.handler.object) {
					if (collisionID.contains(object.getId())) {
						if (new Rectangle((int) position.x, (int) position.y - 1, (int) w, (int) h).intersects(object.getBounds())) {
							collided = true;
							continue;
						}
					}
				}

				if (collided) {
					forces.add(new Vector(0, speed.y * bounceConstant * mass));
					speed.y = 0;
					falling = false;
					continue;
				} else {
					position.y--;
					falling = true;
				}
			}
		} else if(speed.y > - 1 && speed.y < 1) {
			for (GameObject object : gc.handler.object) {
				if (collisionID.contains(object.getId())) {
					if (new Rectangle((int) position.x, (int) position.y + 1, (int) w, (int) h).intersects(object.getBounds())) {
						collided = true;
						continue;
					}
				}
			}

			if (collided) {
				speed.y = 0;
				falling = false;
				return;
			} else {
				position.y++;
				falling = true;
			}
		}
	}
	
	private void input(GameContainer gc) {
		if(gc.getInput().isKey(KeyEvent.VK_A)) {
			if(speed.x > -5) {
				forces.add(new Vector(-moveSpeed, 0));
			}
		} 
		
		if(gc.getInput().isKey(KeyEvent.VK_D)) {
			if(speed.x < 5) {
				forces.add(new Vector(moveSpeed, 0));
			}
		}
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int) position.x, (int) position.y, w, h, 0xffff00dd, Light.NONE);
		
		//for(Vector vec : forces) {
		//	r.drawLine(0xffff0000, (int) (position.x + 8), (int) (position.y + 8), (int) (position.x + 8 + vec.x*5), (int) (position.y + 8 + vec.y*5));
		//}
		
		//r.drawLine(0xffffff00, (int) (position.x + 8), (int) (position.y + 8), (int) (position.x + 8 + acc.x*200 ), (int) (position.y + 8 + acc.y*200));
		
		r.drawText("Gas x: " + speed.x, 10, 10);
		r.drawText("Gas y: " + speed.y, 10, 30);
	}
	public Rectangle getBoundsLeft() {
		return new Rectangle((int) position.x, (int) position.y + 2, (int) (w / 2), (int) h - 4);
	}

	public Rectangle getBoundsRight() {
		return new Rectangle((int) position.x + (int) (w / 2), (int) position.y + 2, (int) (w / 2), (int) h - 4);
	}

	public Rectangle getBoundsBottom() {
		return new Rectangle((int) position.x + 2, (int) position.y + (int) (h / 2), (int) w - 4, (int) (h / 2));
	}

	public Rectangle getBoundsTop() {
		return new Rectangle((int) position.x + 2, (int) position.y, (int) w - 4, (int) (h / 2));
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
}
