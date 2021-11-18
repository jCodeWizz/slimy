package dev.CodeWizz.shooty;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
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
	private final float mass = 20, gravity = 9.81f/30;
	private final float moveSpeed = 0.5f * mass, friction = -0.5f, bounceConstant = -1.1f;	
	private List<Vector> forces = new CopyOnWriteArrayList<>();
	
	public Player() {
		position = new Vector(400, 400);
		speed = new Vector();
		acc = new Vector();
	}
	
	public void init(GameContainer gc) {
		
	}
	
	public void update(GameContainer gc) {
		speed.add(acc);
		position.add(speed);
		
		acc.clear();
		
		
		if(!forces.isEmpty()) {
			for(Vector vec : forces) {
				acc.add(vec);
			}
			forces.clear();
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			forces.add(new Vector(0, -75));
		}
		
		if(falling) {
			forces.add(new Vector(0, mass * gravity));
		} else {
			forces.add(new Vector(speed.x*friction*mass, 0));
		}
		
		// LUCHT WEERSTAND
		
		forces.add(new Vector(0, 0));
		
		
		
		
		
		acc.devide(mass);
		
		input(gc);
		collision(gc);
		
		
	}
	
	private void collision(GameContainer gc) {
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Box) {
				if(object.getBounds().intersects(getBoundsBottom())) {
					forces.add(new Vector(0, speed.y * bounceConstant * mass));
					falling = false;
					speed.y = 0;
					position.y = object.getY() - h;
					return;
				} else {
					falling = true;
				}
				
				if(object.getBounds().intersects(getBoundsTop())) {
					speed.y = 0;
					position.y = object.getY() + object.getH();
				}
				
				if(object.getBounds().intersects(getBoundsLeft())) {
					speed.x = 0;
					position.x = object.getX() + object.getW();
				}
				
				if(object.getBounds().intersects(getBoundsRight())) {
					speed.x = 0;
					position.x = object.getX() - w;
				}
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
		
		for(Vector vec : forces) {
		//	r.drawLine(0xffff0000, (int) (position.x + 8), (int) (position.y + 8), (int) (position.x + 8 + vec.x*5), (int) (position.y + 8 + vec.y*5));
		}
		
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
