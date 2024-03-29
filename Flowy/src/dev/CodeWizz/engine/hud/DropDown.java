package dev.CodeWizz.engine.hud;

import java.awt.Rectangle;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;
import dev.CodeWizz.engine.util.UIID;

public class DropDown implements IHudComponent {
	
	private int x, y, w, fw, selectedOption;
	private int[] options;
	private boolean open, justOpened;
	private IDropDownListener listener;
	
	public DropDown(int x, int y, int w, int ao, IDropDownListener listener, int... values) {
		this.x = x;
		this.y = y;
		this.w = w;
		options = new int[ao];
		this.listener = listener;
		
		for(int i = 0; i < ao; i++) {
			options[i] = values[i];
		}
		
		fw = 16 + options.length*16;
	}
	
	
	@Override
	public UIID getID() {
		return UIID.DropDown;
	}

	@Override
	public void tick(GameContainer gc) {
		
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		if(open) {
			r.fillRectUI(x, y, w, 16, 0xff868f8d, Light.NONE);
			r.drawRectUI(x, y, w, 16, 0xff000000, Light.NONE);
			r.drawText(options[selectedOption] + "", x, y, 2, 0xff000000);
			for(int i = 0; i < options.length; i++) {
				r.fillRectUI(x, y + i*16 + 16, w, 16, 0xffffffff, Light.NONE);
				r.drawText(options[i] + "", x, y + i*16 + 16, 2, 0xff000000);
				r.drawRectUI(x, y + i*16 + 16, w, 16, 0xff000000, Light.NONE);
			}
		} else {
			r.fillRectUI(x, y, w, 16, 0xffffffff, Light.NONE);
			r.drawRectUI(x, y, w, 16, 0xff000000, Light.NONE);
			r.drawText(options[selectedOption] + "", x, y, 2, 0xff000000);
		}
	}

	@Override
	public Rectangle getBounds() {
		if(open) {
			return new Rectangle(x, y, w, fw);
		} else {
			return new Rectangle(x, y, w, 16);
		}
	}

	@Override
	public void click(GameContainer gc) {
		if(!open) {
			open = true;
			justOpened = true;
		} else {
			for(int i = 0; i < options.length; i++) {
				if(new Rectangle(gc.getInput().getMouseX() - gc.camera.getX(), gc.getInput().getMouseY() - gc.camera.getY(), 1, 1).intersects(new Rectangle(x, y + i*16 + 16, w, 10))) {
					selectedOption = i;
					listener.onDropDownSet(selectedOption, options[selectedOption]);
					continue;
				}
			}
		}
	}

	@Override
	public void declick(GameContainer gc) {
		if(open && !justOpened)
			open = false;
		else if(justOpened) {
			justOpened = false;
		}
		
	}


	@Override
	public void press() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void depress() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void valueSet(float value) {
		// TODO Auto-generated method stub
		
	}
}
