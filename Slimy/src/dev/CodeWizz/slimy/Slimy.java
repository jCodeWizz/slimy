package dev.CodeWizz.slimy;

import dev.CodeWizz.engine.AbstractGame;
import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.hud.DropDown;
import dev.CodeWizz.engine.hud.IDropDownListener;
import dev.CodeWizz.engine.hud.ISliderListener;
import dev.CodeWizz.engine.hud.Slider;

public class Slimy extends AbstractGame implements ISliderListener, IDropDownListener {

	private Point[] circlePoints;
	private int radius = 500, amount = 2, gap = 1;
	private boolean ready = false;

	private GameContainer gc;
	
	private Slider sliderRadius;
	private Slider sliderGap;
	private DropDown dropMenu;

	public Slimy() {

		circlePoints = new Point[amount];

	}

	@Override
	public void update(GameContainer gc, float dt) {
		radius = (int) (sliderRadius.getValue() * 50 + 100);

		circlePoints = new Point[amount];
		gap = (int) (sliderGap.getValue()/200* 10);
		
		for (int i = 0; i < circlePoints.length; i++) {
			circlePoints[i] = new Point(
					gc.getWidth() / 2 + radius * Math.cos((i * (360 / circlePoints.length) * Math.PI) / 180),
					gc.getHeight() / 2 + radius * Math.sin((i * (360 / circlePoints.length) * Math.PI) / 180));
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if (ready) {
			for (int i = 0; i < circlePoints.length; i++) {
				for (int j = 0; j < circlePoints.length; j++) {
					if (j - i > 0) {
						if (j - i < amount / 2 - gap || j - i > amount / 2 + gap) {
							r.drawLine(0xff000000, circlePoints[i].x, circlePoints[i].y, circlePoints[j].x,
									circlePoints[j].y);
						}
					}
				}
			}

		}
	}

	public int getPointRelativeTo(int a, int b) {
		return a + b > amount ? a + b - amount : a + b;
	}

	@Override
	public void renderUI(GameContainer gc, Renderer r) {

	}

	@Override
	public void init(GameContainer gc) {
		this.gc = gc;

		for (int i = 0; i < circlePoints.length; i++) {
			circlePoints[i] = new Point(
					gc.getWidth() / 2 + radius * Math.cos((i * (360 / circlePoints.length) * Math.PI) / 180),
					gc.getHeight() / 2 + radius * Math.sin((i * (360 / circlePoints.length) * Math.PI) / 180));
		}

		ready = true;
		
		sliderGap = new Slider(10, 10, 200, this);
		sliderRadius = new Slider(10, 35, 200, this);
		dropMenu = new DropDown(10, 60, 200, 23, this, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 15, 18, 20, 24, 30, 36, 40, 45, 60, 72, 90, 120, 180);

		gc.gethMan().addComponent(sliderGap);
		gc.gethMan().addComponent(dropMenu);
		gc.gethMan().addComponent(sliderRadius);
	}

	public static void main(String[] args) {
		GameContainer.showInfo();
		GameContainer gc = new GameContainer(new Slimy());
		gc.start();
	}

	@Override
	public void onSliderSet(float value) {
		
	}

	@Override
	public void onSliderMove(float value) {

	}

	@Override
	public void onDropDownSet(int a, int value) {
		radius = (int) (sliderRadius.getValue() * 50 + 100);

		amount = value;
		circlePoints = new Point[amount];
		gap = (int) (sliderGap.getValue()/200* 10);
		
		for (int i = 0; i < circlePoints.length; i++) {
			circlePoints[i] = new Point(
					gc.getWidth() / 2 + radius * Math.cos((i * (360 / circlePoints.length) * Math.PI) / 180),
					gc.getHeight() / 2 + radius * Math.sin((i * (360 / circlePoints.length) * Math.PI) / 180));
		}
	}
}

class Point {
	final int x;
	final int y;

	Point(final double x, final double y) {
		this.x = (int) x; // we're dealing with pixels, so just truncate it
		this.y = (int) y;
	}

	@Override
	public String toString() {
		return "{" + x + ", " + y + "}";
	}
}
