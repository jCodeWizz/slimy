package dev.CodeWizz.shooty.weapons;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.Animation;
import dev.CodeWizz.engine.object.GameObject;
import dev.CodeWizz.engine.object.ID;
import dev.CodeWizz.engine.util.Textures;
import dev.CodeWizz.engine.util.WMath;

public class Explosion extends GameObject {

	private int counter = 0;
	private int r;
	
	private Animation anim;
	
	public Explosion(float x, float y, int r) {
		super(x, y);

		this.r = r;
		anim = new Animation(3, Textures.explosion, 1);
	}
	
	@Override
	public void tick(GameContainer gc) {
		if(counter == 15)
			explode(gc);

		anim.tick();
		

		if(anim.hasCycled())
			gc.handler.removeObject(this);
		
		counter++;
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
		//r.fillCircle(0x64ff0000, position, 70);
		r.drawImage(anim.getFrame(), (int)position.x -64, (int)position.y-64, 2);
	}
}
