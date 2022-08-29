package dev.CodeWizz.flowy;

public enum Tile {

	Sand(0xffaba441),
	Clay(0xff4b615e),
	Ground(0xff75655a);
	
	int color;
	
	Tile(int color) {
		this.color = color;
	}
	
	int getColor() {
		return this.color;
	}
}
