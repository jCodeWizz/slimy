package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Font;
import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.UIID;

public class Button implements IHudComponent {

	private int x, y, w, h, scale;
	protected String text;
	protected Image image, imagePressed, icon;
	protected boolean pressed = false;
	
	public Button(int x, int y, int w, int h, String text) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		
		this.text = text;
		this.scale = 1;
	}
	
	public Button(int x, int y, String text, Image image, Image pressed) {
		this.x = x;
		this.y = y;
		this.w = image.getW();
		this.h = image.getH();
		
		this.text = text;
		this.image = image;
		this.imagePressed = pressed;
		this.scale = 1;
	}
	
	public Button(int x, int y, String text, Image image, Image pressed, int scale) {
		this.x = x;
		this.y = y;
		this.w = image.getW() * scale;
		this.h = image.getH() * scale;
		
		this.text = text;
		this.image = image;
		this.imagePressed = pressed;
		this.scale = scale;
	}
	
	public Button(int x, int y, String text, Image image, Image pressed, Image icon, int scale) {
		this.x = x;
		this.y = y;
		this.w = image.getW() * scale;
		this.h = image.getH() * scale;
		
		this.text = text;
		this.image = image;
		this.imagePressed = pressed;
		this.icon = icon;
		this.scale = scale;
	}
	
	@Override
	public void tick(GameContainer gc) {

	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(icon != null) {
			if(pressed) {
				r.drawImageUI(imagePressed, x, y, scale);
				r.drawImageUI(icon, x, y + 2 * scale, scale);
			} else {
				r.drawImageUI(image, x, y, scale);
				r.drawImageUI(icon, x, y, scale);
			}
		} else if(image != null) {
			if(pressed) {
				r.drawImageUI(imagePressed, x, y, scale);
				r.drawText(text, x + w/2 - (Font.STANDARD.getWidth(text)/2)*2, y + h/2 - 8 + 2 * scale, scale, 0xff000000);
			} else {
				r.drawImageUI(image, x, y, scale);
				r.drawText(text, x + w/2 - (Font.STANDARD.getWidth(text)/2)*2, y + h/2 - 8, scale, 0xff000000);
			}
		} else {
			r.fillRectUI(x, y, w, h, 0xffffffff, Light.NONE);
			r.drawRectUI(x, y, w, h, 0xff000000, Light.NONE);
			r.drawText(text, x + w/2 - (Font.STANDARD.getWidth(text)/2)*2, y + h/2 - 8, scale, 0xff000000);
		}
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, w, h);
	}

	@Override
	public void click(GameContainer gc) {

	}

	@Override
	public UIID getID() {
		return UIID.Button;
	}

	@Override
	public void declick(GameContainer gc) {
		
	}
	
	@Override
	public void press() {
		this.pressed = true;
	}

	@Override
	public void depress() {
		this.pressed = false;
	}

	@Override
	public void valueSet(float value) {
		// TODO Auto-generated method stub
		
	}
}
