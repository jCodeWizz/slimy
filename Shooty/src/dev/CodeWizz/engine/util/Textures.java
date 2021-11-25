package dev.CodeWizz.engine.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import dev.CodeWizz.engine.gfx.Image;
import dev.CodeWizz.engine.gfx.ImageTile;

public class Textures {

	private static HashMap<String, Image> list = new HashMap<>();
	private static HashMap<String, ImageTile> listT = new HashMap<>();
	
	public static BufferedImage leaves;
	public static BufferedImage log;
	public static BufferedImage stump;
	public static BufferedImage nest;
	
	public void load() {
		WDebug.log("[System]: Loading textures...");
		
		listT.put("US", new ImageTile("/assets/textures/guns/icons/US.png", 32, 15));
		listT.put("USSR", new ImageTile("/assets/textures/guns/icons/USSR.png", 33, 17));
		listT.put("G", new ImageTile("/assets/textures/guns/icons/G.png", 33, 17));
		listT.put("UK", new ImageTile("/assets/textures/guns/icons/UK.png", 33, 17));
		listT.put("icons", new ImageTile("/assets/textures/ui/icons.png", 24, 24));
		
		list.put("gunholder", new Image("/assets/textures/ui/gunholder.png"));
		list.put("hands", new Image("/assets/textures/guns/icons/hands.png"));
		list.put("slot", new Image("/assets/textures/ui/slot.png"));
		list.put("slotselected", new Image("/assets/textures/ui/slotselected.png"));
		list.put("info", new Image("/assets/textures/ui/info.png"));
		
		WDebug.log("[System]: Loaded in " + list.size() + " textures!");
		WDebug.log("[System]: Loaded in " + listT.size() + " texture tiles!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: Texture requested for name: " + name + " but wasn't found!");
			return null;
		}
	}
	
	public static Image get(String name, int x, int y) {
		if(listT.containsKey(name))
			return listT.get(name).getTileImage(x, y);
		else {
			WDebug.log("[ERROR]: Texture tile requested for name: " + name + " on coords {" + x + " ; " + y + "} but wasn't found!");
			return null;
		}
	}
	
}
