package dev.codewizz.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import dev.codewizz.Main;
import dev.codewizz.players.PlayerInfo;
import dev.codewizz.players.Profession;

public class ProfessionInventory {

	private static Inventory invSelect = Bukkit.createInventory(null, 27, "Choose a profession");

	public static void loadInvs() {
		ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillM = fill.getItemMeta();
		fillM.setDisplayName("§r");
		fill.setItemMeta(fillM);

		for (int i = 0; i < invSelect.getSize(); i++) {
			invSelect.setItem(i, fill);
		}

		ItemStack minerItem = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta miner = minerItem.getItemMeta();
		miner.setDisplayName("§7§l「 §6§lMiner §7§l」");
		ArrayList<String> loreMiner = new ArrayList<>();
		loreMiner.add("");
		loreMiner.add("§6►  §bHaste effect");
		loreMiner.add("§6►  §bNight Vision effect");
		loreMiner.add("§6►  §bExtra drops from Stone & Ores");
		loreMiner.add("§6►  §bMore stone from crafting");
		loreMiner.add("§6►  §bCan mine Ancient Debris");
		loreMiner.add("");

		miner.setLore(loreMiner);

		miner.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		minerItem.setItemMeta(miner);
		invSelect.setItem(10, minerItem);

		ItemStack farmerItem = new ItemStack(Material.WHEAT);
		ItemMeta farmer = farmerItem.getItemMeta();

		farmer.setDisplayName("§7§l「 §6§lFarmer §7§l」");
		ArrayList<String> loreFarmer = new ArrayList<>();
		loreFarmer.add("");
		loreFarmer.add("§6►  §bMore drops from animals");
		loreFarmer.add("§6►  §bMore drops from crops");
		loreFarmer.add("§6►  §bCan twerk to make crops grow");
		loreFarmer.add("§6►  §bCan use Hoes");
		loreFarmer.add("§6►  §bCan use Smokers");
		loreFarmer.add("§6►  §bCan use Bonemeal");
		loreFarmer.add("");

		farmer.setLore(loreFarmer);

		farmer.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		farmerItem.setItemMeta(farmer);
		invSelect.setItem(11, farmerItem);

		ItemStack foresterItem = new ItemStack(Material.OAK_LOG);
		ItemMeta forester = foresterItem.getItemMeta();

		forester.setDisplayName("§7§l「 §6§lForester §7§l」");
		ArrayList<String> loreForester = new ArrayList<>();
		loreForester.add("");
		loreForester.add("§6►  §bExtra damage when using an Axe");
		loreForester.add("§6►  §bChop entire trees at once");
		loreForester.add("§6►  §bExtra 4 health");
		loreForester.add("§6►  §bMore planks from crafting");
		loreForester.add("§6►  §bCan use Bonemeal");
		loreForester.add("");

		forester.setLore(loreForester);

		forester.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		foresterItem.setItemMeta(forester);
		invSelect.setItem(12, foresterItem);

		ItemStack soldierItem = new ItemStack(Material.SHIELD);
		ItemMeta soldier = soldierItem.getItemMeta();

		soldier.setDisplayName("§7§l「 §6§lSoldier §7§l」");
		ArrayList<String> loreSoldier = new ArrayList<>();
		loreSoldier.add("");
		loreSoldier.add("§6►  §bExtra damage");
		loreSoldier.add("§6►  §bSpeed effect");
		loreSoldier.add("§6►  §bExtra 4 health");
		loreSoldier.add("§6►  §bCan use Netherite Armour & Weapons");
		loreSoldier.add("§6►  §bCan use Armour & Weapons with special Enchantments");
		loreSoldier.add("");

		soldier.setLore(loreSoldier);

		soldier.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		soldierItem.setItemMeta(soldier);
		invSelect.setItem(14, soldierItem);

		ItemStack wizardItem = new ItemStack(Material.ENCHANTING_TABLE);
		ItemMeta wizard = wizardItem.getItemMeta();

		wizard.setDisplayName("§7§l「 §6§lWizard §7§l」");
		ArrayList<String> loreWizard = new ArrayList<>();
		loreWizard.add("");
		loreWizard.add("§6►  §bEarn Experience 10x faster");
		loreWizard.add("§6►  §bSpeed effect");
		loreWizard.add("§6►  §bCan throw Fireballs");
		loreWizard.add("§6►  §bCan enchant custom Enchantments");
		loreWizard.add("");

		wizard.setLore(loreWizard);

		wizard.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		wizardItem.setItemMeta(wizard);
		invSelect.setItem(15, wizardItem);

		ItemStack smithItem = new ItemStack(Material.ANVIL);
		ItemMeta smith = smithItem.getItemMeta();

		smith.setDisplayName("§7§l「 §6§lBlack Smith §7§l」");
		ArrayList<String> loreSmith = new ArrayList<>();
		loreSmith.add("");
		loreSmith.add("§6►  §bEnchant tools when crafting one");
		loreSmith.add("§6►  §bStrength effect");
		loreSmith.add("§6►  §bRepair tools without costs");
		loreSmith.add("§6►  §bCan use Smithing Tables");
		loreSmith.add("§6►  §bCan use Blast Furnaces");
		loreSmith.add("");

		smith.setLore(loreSmith);

		smith.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

		smithItem.setItemMeta(smith);
		invSelect.setItem(16, smithItem);
	}

	public static void openSelector(Player p) {
		p.openInventory(invSelect);
	}

	public static void openInfo(Player player) {
		Inventory invInfo = Bukkit.createInventory(null, 27, "Profession Information");
		PlayerInfo f = Main.inst.manager.getProfile(player.getUniqueId());
		Profession p = f.profession;

		ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillM = fill.getItemMeta();
		fillM.setDisplayName("§r");
		fill.setItemMeta(fillM);

		for (int i = 0; i < invSelect.getSize(); i++) {
			invInfo.setItem(i, fill);
		}

		ItemStack level = getHead(player);
		ItemMeta levelMeta = level.getItemMeta();
		levelMeta.setDisplayName("§7§l「 §6§lLevelProgress §7§l」");
		ArrayList<String> lore = new ArrayList<>();
		lore.add("");
		lore.add("§bLevel: §6§l" + f.level);
		lore.add("§bProgress: §6§l" + f.levelProgression + "/100");
		lore.add("");
		levelMeta.setLore(lore);
		levelMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		level.setItemMeta(levelMeta);

		ItemStack unlock = getUnlock(f);

		invInfo.setItem(11, level);
		invInfo.setItem(13, p.getIcon());
		invInfo.setItem(15, unlock);

		player.openInventory(invInfo);
	}

	public static ItemStack getUnlock(PlayerInfo p) {
		Profession f = p.profession;
		ItemStack i = new ItemStack(f.getSecondIcon());
		ItemMeta m = i.getItemMeta();
		m.setDisplayName("§r");

		if (f == Profession.BLACKSMITH) {
			ArrayList<String> lore = new ArrayList<>();
			if (p.level >= 3) {
				lore.add("§6►  §aCan use Smithing Tables §a§lLVL[3]");
			} else {
				lore.add("§6►  §cCan use Smithing Tables §c§lLVL[3]");
			}
			lore.add("§6►  §aEnchant tools when crafting one");
			lore.add("§6►  §aStrength effect");
			lore.add("§6►  §aRepair tools without costs");
			lore.add("§6►  §aCan use Blast Furnaces");
			lore.add("");
			m.setLore(lore);

		} else if (f == Profession.FARMER) {
			ArrayList<String> loreFarmer = new ArrayList<>();
			if (p.level >= 2) {
				loreFarmer.add("§6►  §aCan twerk to make crops grow §a§lLVL[2]");
			} else {
				loreFarmer.add("§6►  §cCan twerk to make crops grow §c§lLVL[2]");
			}
			loreFarmer.add("§6►  §aMore drops from animals");
			loreFarmer.add("§6►  §aMore drops from crops");
			loreFarmer.add("§6►  §aCan use Bonemeal");
			loreFarmer.add("§6►  §aCan use Hoes");
			loreFarmer.add("§6►  §aCan use Smokers");
			loreFarmer.add("");
			m.setLore(loreFarmer);
		} else if (f == Profession.SOLDIER) {
			ArrayList<String> loreSoldier = new ArrayList<>();

			if (p.level >= 2) {
				loreSoldier.add("§6►  §aCan use Netherite Armour & Weapons §a§lLVL[2]");
			} else {
				loreSoldier.add("§6►  §cCan use Netherite Armour & Weapons §c§lLVL[2]");
			}

			if (p.level >= 3) {
				loreSoldier.add("§6►  §aExtra 4 health");
			} else {
				loreSoldier.add("§6►  §cExtra 4 health §c§lLVL[2]");
			}

			loreSoldier.add("§6►  §aExtra damage");
			loreSoldier.add("§6►  §aSpeed effect");
			loreSoldier.add("§6►  §aCan use Armour & Weapons with special Enchantments");
			loreSoldier.add("");

			m.setLore(loreSoldier);
		} else if (f == Profession.WIZARD) {

			ArrayList<String> loreWizard = new ArrayList<>();
			if (p.level >= 2) {
				loreWizard.add("§6►  §aCan throw Fireballs §a§lLVL[2]");
			} else {
				loreWizard.add("§6►  §cCan throw Fireballs §c§lLVL[2]");
			}
			loreWizard.add("§6►  §aEarn Experience 10x faster");
			loreWizard.add("§6►  §aSpeed effect");
			loreWizard.add("§6►  §aCan throw Fireballs");
			loreWizard.add("§6►  §aCan enchant custom Enchantments");
			loreWizard.add("");

			m.setLore(loreWizard);

		} else if (f == Profession.FORESTER) {
			ArrayList<String> loreForester = new ArrayList<>();
			if (p.level >= 3) {
				loreForester.add("§6►  §aExtra 4 health §a§lLVL[3]");
			} else {
				loreForester.add("§6►  §cExtra 4 health §c§lLVL[3]");
			}
			if (p.level >= 2) {
				loreForester.add("§6►  §aExtra damage when using an Axe §a§lLVL[2]");
			} else {
				loreForester.add("§6►  §cExtra damage when using an Axe §c§lLVL[2]");
			}
			if (p.level >= 1) {
				loreForester.add("§6►  §aChop entire trees at once §a§lLVL[1]");
			} else {
				loreForester.add("§6►  §cChop entire trees at once §c§lLVL[1]");
			}
			loreForester.add("§6►  §aMore planks from crafting");
			loreForester.add("§6►  §aCan use Bonemeal");
			loreForester.add("");

			m.setLore(loreForester);

		} else if (f == Profession.MINER) {
			ArrayList<String> loreMiner = new ArrayList<>();
			if(p.level >= 3) {
				loreMiner.add("§6►  §aNight Vision effect §a§lLVL[3]");
			} else {
				loreMiner.add("§6►  §cNight Vision effect §c§lLVL[3]");
			}
			if(p.level >= 2) {
				loreMiner.add("§6►  §aCan mine Ancient Debris §a§lLVL[2]");
			} else {
				loreMiner.add("§6►  §cCan mine Ancient Debris §c§lLVL[2]");
			}
			loreMiner.add("§6►  §aHaste effect");
			loreMiner.add("§6►  §aExtra drops from Stone & Ores");
			loreMiner.add("§6►  §aMore stone from crafting");
			loreMiner.add("");

			m.setLore(loreMiner);
		}

		m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack getHead(Player player) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwningPlayer(player);
		head.setItemMeta(meta);
		return head;
	}
}
