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
		
		list.put("button", new Image("/assets/textures/ui/button.png"));
		list.put("button-pressed", new Image("/assets/textures/ui/button-pressed.png"));
		list.put("icon", new Image("/assets/textures/ui/icon.png"));
		list.put("icon-pressed", new Image("/assets/textures/ui/icon-pressed.png"));
		list.put("bucket-icon", new Image("/assets/textures/ui/bucket-icon.png"));
		list.put("point-icon", new Image("/assets/textures/ui/point-icon.png"));
		list.put("line-icon", new Image("/assets/textures/ui/line-icon.png"));

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
