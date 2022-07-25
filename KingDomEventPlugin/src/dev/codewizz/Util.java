package dev.codewizz;

import java.util.ArrayList;

import org.bukkit.Material;

public class Util {

	public static ArrayList<Material> HOES = new ArrayList<>();
	
	public static void setup() {
		HOES.add(Material.WOODEN_HOE);
		HOES.add(Material.STONE_HOE);
		HOES.add(Material.GOLDEN_HOE);
		HOES.add(Material.IRON_HOE);
		HOES.add(Material.DIAMOND_HOE);
		HOES.add(Material.NETHERITE_HOE);
	}
}
