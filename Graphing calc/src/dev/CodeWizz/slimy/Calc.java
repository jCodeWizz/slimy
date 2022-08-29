package dev.CodeWizz.slimy;

import java.awt.event.KeyEvent;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Calc extends AbstractGame {

	private float[] values;
	
	public Calc() {
		
	}
	
	public float function(float value) {
		return (float) ( 20f * (Math.sin(value/20f)*Math.sin(value/20f) + 20f*Math.sin(value/20f)*Math.cos(value/20f)));
	}
	
	public float function2(float value) {
		return (float) ( 20f*Math.tan(value/20f));
	}

	@Override
	public void update(GameContainer gc, float dt) {
		if(gc.getInput().isKey(KeyEvent.VK_LEFT)) {
			gc.camera.setX(gc.camera.getX() - 10);
		}
		if(gc.getInput().isKey(KeyEvent.VK_RIGHT)) {
			gc.camera.setX(gc.camera.getX() + 10);
		}
		if(gc.getInput().isKey(KeyEvent.VK_DOWN)) {
			gc.camera.setY(gc.camera.getY() + 10);
		}
		if(gc.getInput().isKey(KeyEvent.VK_UP)) {
			gc.camera.setY(gc.camera.getY() - 10);
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		drawFunction(r, values);
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}
	
	public void drawFunction(Renderer r, float[] values) {
		for(int i = 0; i < values.length; i++) {
			if(i < values.length-1) {
				r.drawLine(0xffff0000, i, (int)values[i], i+1, (int)values[i+1]);
			}
		}
	}

	@Override
	public void init(GameContainer gc) {
		values = new float[1920];
		for(float i = 0; i < values.length; i++) {
			values[(int)i] = function2	(i);
		}
	}

	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Calc());
		gc.start();
	}
}
