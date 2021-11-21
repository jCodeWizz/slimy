package dev.CodeWizz.shooty;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Shooty extends AbstractGame {

	public static Shooty inst;

	public static boolean play = false;

	public List<Planet> planets = new CopyOnWriteArrayList<>();

	public Shooty() {
		inst = this;
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if (play) {
			for (Planet p : planets) {
				p.update(gc);
			}
		}

		if (gc.getInput().isKeyDown(KeyEvent.VK_SPACE)) {
			if (play)
				play = false;
			else
				play = true;
		}
		
		if (gc.getInput().isKeyDown(KeyEvent.VK_R)) {
			for(Planet p : planets) {
				if(!p.stationary) {
					p.reset(gc);
					p.position = p.getStartPos(gc);
					p.speed = p.getStartSpeed(gc);
				}
			}
			
			play = false;
		}
		
		if (gc.getInput().isKeyDown(KeyEvent.VK_ESCAPE)) {
			for(Planet p : planets) {
				p.paths.clear();
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		for (Planet p : planets) {
			p.render(gc, r);
		}
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}

	@Override
	public void init(GameContainer gc) {
		addPlanet(new Sun(gc));
		addPlanet(new Earth(gc));
		addPlanet(new Venus(gc));
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
