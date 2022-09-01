package dev.CodeWizz.flowy;

import dev.CodeWizz.engine.GameContainer;
import dev.CodeWizz.engine.Renderer;
import dev.CodeWizz.engine.gfx.light.Light;

public class Cell {

	public static final int WIDTH = 2, HEIGHT = 2, SCALE = 27*10; 
	
	public static Cell[][] cells = new Cell[SCALE][SCALE];
	
	public int x, y, indexX, indexY;
	public Tile tile = Tile.Ground;
	public float height = 0f;
	
	public Cell(int iX, int jY) {
		this.x = iX*WIDTH;
		this.y = jY*HEIGHT;
		
		this.indexX = iX;
		this.indexY = jY;
		
	}
	
	public void tick(GameContainer gc) {
		
	}
	
	public void update(GameContainer gc, Cell cell) {
		
	}
	
	public void render(GameContainer gc, Renderer r) {
		r.fillRect(x, y, WIDTH, HEIGHT, tile.getColor(), Light.NONE);
		if(tile != Tile.Ground) {
			r.fillRect(x, y, WIDTH, HEIGHT, Renderer.changeAlpha(0xff535454, 150-(int)(150*(height))), Light.NONE);

		}
	}
	
	public static Cell getCell(int mx, int my) {
		int dX = mx / Cell.WIDTH;
		int dY = my / Cell.HEIGHT;
		
		if(dX >= 0 && dY >= 0 && dX < Cell.SCALE && dY < Cell.SCALE) {
			return cells[dX][dY];
		}
		
		return null;
	}
	
	public void fill(Tile tile, Tile clicked) {
		
		if(this.tile != tile) {
			this.tile = tile;
			Cell[] data = getNeighbours();
			
			for(int i = 0; i < data.length; i++) {
				if(data[i] != null) {
					if(data[i].tile == clicked) {
						data[i].fill(tile, clicked);
					}
				}
			}
		}
	}
	
	public Cell[] getNeighbours() {
		Cell[] data = new Cell[4];
		
		data[0] = getCellIndex(indexX, indexY+1);
		data[1] = getCellIndex(indexX-1, indexY);
		data[2] = getCellIndex(indexX+1, indexY);
		data[3] = getCellIndex(indexX, indexY-1);
		
		return data;
	}
	
	public static Cell getCellIndex(int mx, int my) {
		int dX = mx;
		int dY = my;
		
		if(dX >= 0 && dY >= 0 && dX < Cell.SCALE && dY < Cell.SCALE) {
			return cells[dX][dY];
		}
		
		return null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getIndexX() {
		return indexX;
	}

	public void setIndexX(int indexX) {
		this.indexX = indexX;
	}

	public int getIndexY() {
		return indexY;
	}

	public void setIndexY(int indexY) {
		this.indexY = indexY;
	}
}
