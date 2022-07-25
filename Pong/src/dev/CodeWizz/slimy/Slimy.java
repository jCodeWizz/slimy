package dev.CodeWizz.slimy;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Slimy extends AbstractGame {

	public static int score = 0;
	
	public Slimy() {
		
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
		gc.handler.addObject(new Ball(gc.getWidth()/2-4, gc.getHeight()/2-4).resetBall(gc));
		gc.handler.addObject(new Player(gc.getWidth()-48, gc.getHeight()/2, 8, 48));
		gc.handler.addObject(new Gamer(50, gc.getHeight()/2, 8, 48));
	
	}

	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Slimy());
		gc.start();
	}
}
