package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.Box;
import dev.CodeWizz.shooty.weapons.DamageIndicator;

public class Shooty extends AbstractGame {

	private static List<DamageIndicator> indis = new CopyOnWriteArrayList<>();
	
	public static Shooty inst;
	private Player player;

	public Shooty() {
		inst = this;
		player = new Player();
	}

	@Override
	public void update(GameContainer gc, float dt) {
		player.update(gc);
		
		for(DamageIndicator i : indis) {
			i.update(gc);
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		player.render(gc, r);
		
		
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		player.renderUI(gc, r);
		
		for(DamageIndicator i : indis) {
			i.render(gc, r);
		}
	}

	@Override
	public void init(GameContainer gc) {
		player.init(gc);
		
		
		gc.handler.addObject(new Box(100, 100, 100, 100, 0xffffffff, Light.FULL));
		gc.handler.addObject(new Zombie(10, 10));
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public static void addIndicator(DamageIndicator i) {
		indis.add(i);
	}
	
	public static void removeIndicator(DamageIndicator i) {
		indis.remove(i);
	}
}
