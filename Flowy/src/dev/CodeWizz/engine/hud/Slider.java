package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.UIID;
import dev.CodeWizz.engine.util.WMath;

public class Slider implements IHudComponent {

	private int x, y, w;
	private float value;
	private float oldValue;
	private boolean dragging;
	
	public Slider(int x, int y, int w) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.value = w/2+1;
		
		valueSet((value)/w);
	}
	
	
	@Override
	public UIID getID() {
		return UIID.Slider;
	}

	@Override
	public void tick(GameContainer gc) {
		if(dragging) {
			oldValue = value;
			value = gc.getInput().getMouseX() - gc.camera.getX()-x;
			value = WMath.clamb(value, w, 0);
			
			if(oldValue != value) {
				valueSet((value)/w);
			}
		}
	}
	
	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillRectUI(x, y, w, 2, 0xff222034, Light.NONE);
		r.fillRectUI(x, y+12, w, 2, 0xff222034, Light.NONE);
		r.fillRectUI(x-2, y, 2, 14, 0xff222034, Light.NONE);
		r.fillRectUI(x+w, y, 2, 14, 0xff222034, Light.NONE);
		
		r.fillRectUI(x, y+2, w, 10, 0xff214A61, Light.NONE);
		r.fillRectUI(x, y+2, (int)value, 8, 0xff2B6584, Light.NONE);

		r.fillRectUI(x + (int)value-2, y-1, 4, 16, 0xff222034, Light.NONE);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, 14);
	}

	@Override
	public void click(GameContainer gc) {
		dragging = true;
	}
	
	@Override
	public void declick(GameContainer gc) {
		dragging = false;
	}


	public float getValue() {
		return value;
	}


	public void setValue(float value) {
		this.value = value;
	}


	@Override
	public void press() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void depress() {
		dragging = false;
	}


	@Override
	public void valueSet(float value) {
		
	}
}
