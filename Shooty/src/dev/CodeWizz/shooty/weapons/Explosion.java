package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.WMath;

public class Explosion extends GameObject {

	private int counter = 0;
	private int r;
	
	public Explosion(float x, float y, int r) {
		super(x, y);

		this.r = r;
		
	}
	
	@Override
	public void tick(GameContainer gc) {
		if(counter == 0)
			explode(gc);

		counter++;
		if(counter >= 30)
			gc.handler.removeObject(this);
	}
	
	public void explode(GameContainer gc) {
		for(GameObject object : gc.handler.object) {
			if(object.getId() == ID.Zombie) {
				if(WMath.distance(position, object.getPosition()) < r) {
					object.damage(gc, (r - WMath.distance(position, object.getPosition())) / 5);
				}
			}
		}
	}

	@Override
	public void render(GameContainer gc, Renderer r) {
		r.fillCircle(0x64ff0000, position, 70);
	}
}
