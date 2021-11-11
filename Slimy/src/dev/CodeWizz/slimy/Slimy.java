package dev.CodeWizz.slimy;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;

public class Slimy extends AbstractGame {

	private Point[] circlePoints;
	private int radius = 500, amount = 20;
	private boolean ready = false;
	
	public Slimy() {
		
		circlePoints = new Point[amount];
		
		
	}
	
	@Override
	public void update(GameContainer gc, float dt) {
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(ready) {
			for(int i = 0; i < circlePoints.length; i++) {
				for(int j = 0; j < circlePoints.length; j++) {
					if(i != j) {
						r.drawLine(0xffffffff, circlePoints[i].x, circlePoints[i].y, circlePoints[j].x, circlePoints[j].y);
					}
				}
			}
		}
	}
	

	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}

	@Override
	public void init(GameContainer gc) {
		for(int i = 0; i < circlePoints.length; i++) {
			circlePoints[i] = new Point(gc.getWidth()/2 + radius*Math.cos((i*(360/circlePoints.length)*Math.PI)/180), gc.getHeight()/2 + radius*Math.sin((i*(360/circlePoints.length)*Math.PI)/180));
		}
		
		ready = true;
		
	}
	
	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Slimy());
		gc.start();
	}
}

class Point
{
    final int x;
    final int y;

    Point(final double x, final double y)
    {
        this.x = (int) x; // we're dealing with pixels, so just truncate it
        this.y = (int) y;
    }

    @Override
    public String toString()
    {
        return "{" + x + ", " + y + "}";
    }
}
