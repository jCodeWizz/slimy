package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Shooty extends AbstractGame {

	public static Shooty inst;
	
	public List<Planet> planets = new CopyOnWriteArrayList<>();
	
	public Shooty() {
		inst = this;
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		for(Planet p : planets) {
			p.update(gc);
		}
		
		if(gc.getInput().isButton(1)) {
			for(Planet p : planets) {
				if(!p.stationary) {
					p.position.x = gc.getInput().getMouseX();
					p.position.y = gc.getInput().getMouseY();
					p.speed.x = 0;
					p.speed.y = 0;
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for(Planet p : planets) {
			p.render(gc, r);
		}
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void init(GameContainer gc) {
		addPlanet(new Planet(gc.getWidth()/2, gc.getHeight()/2, 30, 10000, 0xffebcc34).setStationary(true));
		addPlanet(new Planet(100, 100, 5, 50, 0xff12b0ff, new Vector(0, 1f)));

	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		
		double force = 2;
		
		Vector v = new Vector();

		int dx = -100;
		int dy = -50;
		
		double angle;

		if (dy != 0) {
			angle = Math.atan(dx / dy);
			
			v.x = (float) (Math.toDegrees(Math.sin(angle)) * force);
			v.y = (float) (Math.toDegrees(Math.cos(angle)) * force);
			
			System.out.println("ANGLE: " + Math.toDegrees(angle));
			System.out.println("X: " + v.x);
			System.out.println("Y: " + v.y);
			
			
		} 
		
		
		
		
		
		
		gc.start();
	}
	
	public void addPlanet(Planet p) {
		planets.add(p);
	}
	
	public void removePlanet(Planet p) {
		planets.remove(p);
	}
}
