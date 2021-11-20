package dev.CodeWizz.shooty;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;
import dev.CodeWizz.engine.util.WDebug;

public class Shooty extends AbstractGame {

	public static Shooty inst;

	public static boolean play = false;

	public List<Planet> planets = new CopyOnWriteArrayList<>();

	public Shooty() {
		inst = this;
		
		WDebug.log(1.9f * (float)Math.pow(10, 30));
	
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

		if (gc.getInput().isButton(1) && gc.getInput().isKey(KeyEvent.VK_SHIFT)) {
			for (Planet p : planets) {
				if (!p.stationary) {
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
		for (Planet p : planets) {
			p.render(gc, r);
		}
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}

	@Override
	public void init(GameContainer gc) {
		addPlanetO(gc);
	}

	private void addPlanetO(GameContainer gc) {
		// SUN
		addPlanet(new Planet(gc.getWidth() / 2, gc.getHeight() / 2, 80, 100000000, 0xffebcc34, "SUN")
				.setStationary(true));

		// MERCURY
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 225, gc.getHeight() / 2, 6, 20, 0xffd9c7a5,
				new Vector(1f, 3f), "MERCURY"));

		// VENUS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 175, gc.getHeight() / 2, 6, 40, 0xfff58742,
				new Vector(0, 3f), "VENUS"));

		// EARTH
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 100, gc.getHeight() / 2, 10, 50, 0xff12b0ff,
				new Vector(0, 3f), "EARTH"));

		// MARS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 50, gc.getHeight() / 2, 6, 40, 0xffa6501b,
				new Vector(0, 3f), "MARS"));

		// JUPITER
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4, gc.getHeight() / 2, 30, 80, 0xffd99368,
				new Vector(0, 0.5f), "JUPITER"));

		// SATURN
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 75, gc.getHeight() / 2, 30, 80, 0xffdeb871,
				new Vector(0, 1f), "SATURN"));

		// URANUS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 125, gc.getHeight() / 2, 10, 60, 0xff87e0d1,
				new Vector(0, 3f), "URANUS"));

		// NEPTUNE
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 175, gc.getHeight() / 2, 10, 60, 0xff3c447a,
				new Vector(0, 3f), "NEPTUNE"));

	}

	private void addPlanetsSameSS(GameContainer gc) {
		// SUN
		addPlanet(new Planet(gc.getWidth() / 2, gc.getHeight() / 2, 40, 100000000, 0xffebcc34, "SUN")
				.setStationary(true));

		// MERCURY
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 225, gc.getHeight() / 2, 5, 60, 0xffd9c7a5,
				new Vector(0, 3f), "MERCURY"));

		// VENUS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 175, gc.getHeight() / 2, 5, 60, 0xfff58742,
				new Vector(0, 3f), "VENUS"));

		// EARTH
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 100, gc.getHeight() / 2, 5, 60, 0xff12b0ff,
				new Vector(0, 3f), "EARTH"));

		// MARS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 - 50, gc.getHeight() / 2, 5, 60, 0xffa6501b,
				new Vector(0, 3f), "MARS"));

		// JUPITER
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4, gc.getHeight() / 2, 5, 60, 0xffd99368,
				new Vector(0, 3f), "JUPITER"));

		// SATURN
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 75, gc.getHeight() / 2, 5, 60, 0xffdeb871,
				new Vector(0, 3f), "SATURN"));

		// URANUS
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 125, gc.getHeight() / 2, 5, 60, 0xff87e0d1,
				new Vector(0, 3f), "URANUS"));

		// NEPTUNE
		addPlanet(new Planet(gc.getWidth() / 2 + gc.getWidth() / 4 + 175, gc.getHeight() / 2, 5, 60, 0xff3c447a,
				new Vector(0, 3f), "NEPTUNE"));
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
