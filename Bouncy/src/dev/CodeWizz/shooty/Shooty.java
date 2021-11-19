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
		
	}

	@Override
	public void init(GameContainer gc) {
		
		player.init(gc);
		
		gc.handler.addObject(new Box(0, gc.getHeight()/2, gc.getWidth(), 32, 0xffa17c9c, Light.NONE));
		gc.handler.addObject(new Box(0, gc.getHeight()-32, gc.getWidth(), 32, 0xffa17c9c, Light.NONE));
		gc.handler.addObject(new Box(gc.getWidth()/2-8, gc.getHeight()/2, 16, gc.getHeight()/2, 0xffa17c9c, Light.NONE));
		gc.handler.addObject(new Box(0, gc.getHeight()/2, 16, gc.getHeight()/2, 0xffa17c9c, Light.NONE));
		
		gc.handler.addObject(new Ball(400, 400));
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
