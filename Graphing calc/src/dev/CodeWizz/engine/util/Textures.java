package dev.CodeWizz.engine.util;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import dev.CodeWizz.engine.gfx.Image;

public class Textures {

	private static HashMap<String, Image> list = new HashMap<>();
	
	public static BufferedImage leaves;
	public static BufferedImage log;
	public static BufferedImage stump;
	public static BufferedImage nest;
	
	public void load() {
		WDebug.log("[System]: Loading textures...");
		
		WDebug.log("[System]: Loaded in " + list.size() + " textures!");
	}
	
	public static Image get(String name) {
		if(list.containsKey(name))
			return list.get(name);
		else {
			WDebug.log("[ERROR]: Texture requested for name: " + name + " but wasn't found!");
			return null;
		}
	}
	
}
