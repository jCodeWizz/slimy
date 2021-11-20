package dev.CodeWizz.shooty;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Shooty extends AbstractGame {

	public static Shooty inst;
	
	public static boolean play = false;
	
	public List<Planet> planets = new CopyOnWriteArrayList<>();
	
	public Shooty() {
		inst = this;
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		if(play) {
			for(Planet p : planets) {
				p.update(gc);
			}
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			if(play)
				play = false;
			else
				play = true;
		}
		
		if(gc.getInput().isButton(1) && gc.getInput().isKey(KeyEvent.VK_SHIFT)) {
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
		
		// SUN
		addPlanet(new Planet(gc.getWidth()/2, gc.getHeight()/2, 40, 100000000, 0xffebcc34).setStationary(true));
		
		// VENUS
		
		addPlanet(new Planet(gc.getWidth()/2 + gc.getWidth()/4 - 175, gc.getHeight()/2, 3, 40, 0xfff58742, new Vector(0, 3f)));
		
		// EARTH
		addPlanet(new Planet(gc.getWidth()/2 + gc.getWidth()/4 - 100, gc.getHeight()/2, 5, 50, 0xff12b0ff, new Vector(0, 3f)));
		
		// MARS
		addPlanet(new Planet(gc.getWidth()/2 + gc.getWidth()/4 - 50, gc.getHeight()/2, 3, 40, 0xffa6501b, new Vector(0, 3f)));
		
		// JUPITER
		addPlanet(new Planet(gc.getWidth()/2 + gc.getWidth()/4 + 50, gc.getHeight()/2, 15, 150, 0xffd99368, new Vector(0, 3f)));
			
		// URANUS
		addPlanet(new Planet(gc.getWidth()/2 + gc.getWidth()/4 + 125, gc.getHeight()/2, 5, 60, 0xff87e0d1, new Vector(0, 3f)));
		
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}
	
	public void addPlanet(Planet p) {
		planets.add(p);
	}
	
	public void removePlanet(Planet p) {
		planets.remove(p);
	}
}
