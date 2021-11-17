package dev.CodeWizz.shooty;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.hud.Button;

public class Shooty extends AbstractGame {

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
		gc.gethMan().addComponent(new Button(gc.getWidth()/2-75, gc.getHeight()/2-75, 150, 50, "Singleplayer") {
			@Override
			public void click(GameContainer gc) {

			}
		});
		
		gc.gethMan().addComponent(new Button(gc.getWidth()/2-75, gc.getHeight()/2-25 + 50, 150, 50, "Quit") {
			@Override
			public void click(GameContainer gc) {
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args) {
		GameContainer gc = new GameContainer(new Shooty());
		GameContainer.showInfo();
		gc.start();
	}
}
