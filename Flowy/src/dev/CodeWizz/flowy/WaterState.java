package dev.CodeWizz.flowy;

import java.util.Random;

import dev.CodeWizz.engine.util.Vector;

public class WaterState {

	public static Random r = new Random();
	
	public float water = 0f;
	public float distance = 0f;
	public float height = 0f;
	public float toughness = 0f;
	public boolean source = false;
	
	public Vector speed;
	public Cell cell;
	
	public WaterState(Cell cell) {
		this.cell = cell;
		
		speed = new Vector();
	}
	
	public void setAsSource() {
		source = true;
		water = 1f;
	}
		
	public void update() {
		Cell[] data = cell.getNeighbours();
		
		Cell left = data[2];
		Cell right = data[1];
		
		if(r.nextBoolean()) {
			data[1] = left;
			data[2] = right;
		}
		
		
		
		int index = 0;
		float flow = 0f;
		
		for(int i = 0; i < data.length-1; i++) {
			if(data[i] != null) {
				if(data[i].tile != Tile.Ground && data[i].water.height <= 0) {
					float b = water - data[i].water.water;
					if(b > flow) {
						flow = b;
						index = i;
					}
				}
			}
		}
		
		
		
		
		
		
		
		
		if(data[index] != null) {
			if(data[index].tile != Tile.Ground && data[index].water.height <= 0) {
				if(water - data[index].water.water > 0) {
					data[index].water.water += water;
					water = 0f;
				}
			}
		}

		/*
		for(int i = 0;i < data.length; i++) {
			float flow = 0f;

			if(data[i] != null) {
				if(data[i].tile != Tile.Ground && data[i].water.height <= 0) {
					flow = water - data[i].water.water - 0.1f;
				} else {
					flow = -1f;
				}
				
				
			} else {
				flow = -1f;
			}
			
			if(cFlow < flow) {
				cFlow = flow;
				priorityIndex = i;
			}
		}
		*/
		
	}
	
	public float tryMove(Cell a) {
		return 0f;
	}
}
