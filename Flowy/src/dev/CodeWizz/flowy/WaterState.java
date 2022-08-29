package dev.CodeWizz.flowy;

import dev.CodeWizz.engine.util.Vector;

public class WaterState {

	public float water = 0.0f;
	public float distance = 0f;
	public float height = 0f;
	public Vector speed;
	public Cell cell;
	
	public WaterState(Cell cell) {
		this.cell = cell;
		
		speed = new Vector();
	}
	
	public void move() {
		Cell[] data = cell.getNeighbours();
		
		
		
		
		
	}
	
	public float tryMove(Cell a) {
		
	}
}
