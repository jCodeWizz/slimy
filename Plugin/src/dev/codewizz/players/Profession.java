package dev.codewizz.players;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum Profession {

	JOBLESS("Jobless", Material.DIRT, Material.DIRT, "Jobless ew"),
	SOLDIER("Soldier", Material.SHIELD, Material.IRON_SWORD, "§7§l「 §6§lSoldier §7§l」"),
	WIZARD("Wizard", Material.ENCHANTING_TABLE, Material.EXPERIENCE_BOTTLE, "§7§l「 §6§lWizard §7§l」"),
	FARMER("Farmer", Material.WHEAT, Material.IRON_HOE, "§7§l「 §6§lFarmer §7§l」"),
	FORESTER("Forester", Material.OAK_LOG, Material.IRON_AXE, "§7§l「 §6§lForester §7§l」"),
	MINER("Miner", Material.IRON_PICKAXE, Material.STONE, "§7§l「 §6§lMiner §7§l」"),
	BLACKSMITH("Black Smith", Material.ANVIL, Material.IRON_INGOT, "§7§l「 §6§lBlack Smith §7§l」");
	
	String text;
	Material m, secondIcon;
	String mName;
	
	Profession(String text, Material m, Material secondIcon, String mName) {
		this.text = text;
		this.m = m;
		this.secondIcon = secondIcon;
		this.mName = mName;
	}
	
	public String getText() {
		return text;
	}
	
	public Material getSecondIcon() {
		return secondIcon;
	}
	
	public ItemStack getIcon() {
		ItemStack icon = new ItemStack(m);
		ItemMeta meta = icon.getItemMeta();
		meta.setDisplayName(mName);
		icon.setItemMeta(meta);
		return icon;
		
	}
}
