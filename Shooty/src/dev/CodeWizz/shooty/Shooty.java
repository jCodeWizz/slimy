package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.object.Box;

public class Shooty extends AbstractGame {

	
	public static Shooty inst;
	private Player player;

	public Shooty() {
		inst = this;
		player = new Player();
	}

	@Override
	public void update(GameContainer gc, float dt) {
		player.update(gc);
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		player.render(gc, r);
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		player.renderUI(gc, r);
	}

	@Override
	public void init(GameContainer gc) {
		player.init(gc);
		
		
		gc.handler.addObject(new Box(100, 100, 100, 100, 0xffffffff, Light.NONE));
		
	}

	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}
}
