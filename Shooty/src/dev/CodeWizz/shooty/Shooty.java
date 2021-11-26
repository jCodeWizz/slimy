package dev.CodeWizz.shooty;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.Box;
import dev.CodeWizz.shooty.weapons.Ammo;
import dev.CodeWizz.shooty.weapons.DamageIndicator;
import dev.CodeWizz.shooty.weapons.Pickup;
import dev.CodeWizz.shooty.weapons.types.Minigun;

public class Shooty extends AbstractGame {

	private static List<DamageIndicator> indis = new CopyOnWriteArrayList<>();
	public static List<Pickup> picks = new CopyOnWriteArrayList<>();
	
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
		
		for(Pickup p : picks) {
			p.render(gc, r);
		}
		
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
		picks.add(new Pickup(-100, -100, new Minigun()));
		picks.add(new Pickup(-100, -100, Ammo.AR, 10000));
		
		Random r = new Random();
		gc.handler.addObject(new Box(100, 100, 100, 100, 0xffffffff, Light.FULL));
		
		for(int i = 0; i < 10; i++) {
			gc.handler.addObject(new Crate(32*i, 0));
		}
		
		for(int i = 0; i < 35; i++) {
			gc.handler.addObject(new Zombie(10 + (r.nextInt(50 + 50) - 50), 10 + (r.nextInt(50 + 50) - 50)));
		}
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
