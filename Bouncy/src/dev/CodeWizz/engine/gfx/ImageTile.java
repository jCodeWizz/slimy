package dev.CodeWizz.engine.gfx;

public class ImageTile extends Image {

	private int tileW;
	private int tileH;
	
	public ImageTile(String path, int w, int h) {
		super(path);
		
		this.tileW = w;
		this.tileH = h;

	}
	
	public Image getTileImage(int tileX, int tileY) {
		
		int[] p = new int[tileW * tileH];
		
		for(int y = 0; y < tileH; y++) {
			for(int x = 0; x < tileW; x++) {
				p[x + y * tileW] = this.getP()[(x + tileX * tileW) + (y + tileY * tileH) *  this.getW()];
			}
		}
		
		return new Image(p, tileW, tileH);
	}

	public int getTileW() {
		return tileW;
	}

	public void setTileW(int tileW) {
		this.tileW = tileW;
	}

	public int getTileH() {
		return tileH;
	}

	public void setTileH(int tileH) {
		this.tileH = tileH;
	}

}
