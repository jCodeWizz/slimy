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
	
	private Vector position, speed, acc;
	private int w = 16, h = 16;
	private final float moveSpeed = 0.5f, deMoveSpeed = 0.5f;
	
	private List<Vector> forces = new CopyOnWriteArrayList<>();
	
	public Player() {
		position = new Vector(100, 100);
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
		
		input(gc);
		collision(gc);
	}
	
	private void collision(GameContainer gc) {
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Box) {
				if(object.getBounds().intersects(getBoundsBottom())) {
					speed.y = 0;
					position.y = object.getY() - h;
				}
				
				if(object.getBounds().intersects(getBoundsTop())) {
					speed.y = 0;
					position.y = object.getY() - object.getH();
				}
				
				if(object.getBounds().intersects(getBoundsLeft())) {
					position.x = object.getX() + object.getW();
					speed.x = 0;
				}
				
				if(object.getBounds().intersects(getBoundsRight())) {
					position.x = object.getX() - w;
					speed.x = 0;
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
		
		if(!gc.getInput().isKey(KeyEvent.VK_D) && !gc.getInput().isKey(KeyEvent.VK_A)) {
			if(speed.x > 0) {
				forces.add(new Vector(-deMoveSpeed, 0));
			} else if(speed.x < 0) {
				forces.add(new Vector(deMoveSpeed, 0));
			}
			
			if(speed.x > -2 && speed.x < 2) {
				speed.x = 0;
				acc.x = 0;
			}
		}
		
		if(gc.getInput().isKey(KeyEvent.VK_W)) {
			if(speed.y > -5) {
				forces.add(new Vector(0, -moveSpeed));
			}
		} 
		
		if(gc.getInput().isKey(KeyEvent.VK_S)) {
			if(speed.y < 5) {
				forces.add(new Vector(0, moveSpeed));
			}
		}
		
		if(!gc.getInput().isKey(KeyEvent.VK_W) && !gc.getInput().isKey(KeyEvent.VK_S)) {
			if(speed.y > 0) {
				forces.add(new Vector(0, -moveSpeed));
			} else if(speed.y < 0) {
				forces.add(new Vector(0, moveSpeed));
			}
			
			if(speed.y > -2 && speed.y < 2) {
				speed.y = 0;
				acc.y = 0;
			}
		}
		

	
	
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect((int) position.x, (int) position.y, w, h, 0xffffffff, Light.NONE);
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
}
