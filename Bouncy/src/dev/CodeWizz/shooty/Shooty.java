package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Shooty extends AbstractGame {

	public static Shooty inst;
	
	public Shooty() {
		inst = this;
	}
	
	@Override
	public void update(GameContainer gc, float dt) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {
		
	}

	@Override
	public void init(GameContainer gc) {
		
		gc.handler.addObject(new Ball(gc.getWidth()/2-8, gc.getHeight()/2-8, true));
		gc.handler.addObject(new Ball(gc.getWidth()/2-8, 50, true));
		//gc.handler.addObject(new Ball(gc.getWidth()/2-8+100, gc.getHeight()/2-8, true));
		//gc.handler.addObject(new Ball(gc.getWidth()/2-8-100, gc.getHeight()/2-8, true));
		gc.handler.addObject(new Ball(gc.getWidth()/2-8 - 200, 100, false));
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}
}
