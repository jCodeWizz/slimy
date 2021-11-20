package dev.CodeWizz.shooty;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.util.Vector;

public class Path {

	public List<Vector> points = new CopyOnWriteArrayList<>();
	public boolean selected = true;
		
	public void addPoint(Vector v) {
		points.add(v);
	}
	
	public void render(GameContainer gc, Renderer r) {
		if(selected) {
			for(Vector v : points) {
				if(points.size() > 2) {
					if(points.indexOf(v) < points.size() - 1) {
						r.drawLine(0xffff0000, (int)v.x, (int)v.y, (int)points.get(points.indexOf(v)+1).x, (int)points.get(points.indexOf(v)+1).y);
					}
				}
			}
		} else {
			for(Vector v : points) {
				if(points.size() > 2) {
					if(points.indexOf(v) == points.size() - 1) {
						r.drawLine(0xffff0000, (int)v.x, (int)v.y, (int)points.get(0).x, (int)points.get(0).y);
					} else {
						r.drawLine(0xffff0000, (int)v.x, (int)v.y, (int)points.get(points.indexOf(v)+1).x, (int)points.get(points.indexOf(v)+1).y);
					}
				}
			}
		}
	}
	
	public List<Vector> getPoints() {
		return points;
	}
	
	
	
}
