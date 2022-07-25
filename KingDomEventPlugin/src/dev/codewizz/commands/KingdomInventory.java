package dev.codewizz.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import dev.codewizz.Kingdom;
import dev.codewizz.players.PlayerInfo;

public class KingdomInventory {

	public static void open(Player p, Kingdom k) {
		Inventory inv = Bukkit.createInventory(p, 36, k.name);
		
		ItemStack fill = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
		ItemMeta fillM = fill.getItemMeta();
		fillM.setDisplayName("§r");
		fill.setItemMeta(fillM);

		for (int i = 0; i < inv.getSize(); i++) {
			inv.setItem(i, fill);
		}
		
		if(k.members.size() == 1) {
			PlayerInfo player = k.members.get(0);
			ItemStack head = getHead(Bukkit.getOfflinePlayer(player.playerID));
			ItemMeta m = head.getItemMeta();
			
			ArrayList<String> lore = new ArrayList<>();
			lore.add("");
			if(player.isKing)
				lore.add("§6►  §6King");
			lore.add("§6►  §bProfession: " + player.profession.getText());
			lore.add("§6►  §bLevel: " + player.level);
			lore.add("");
			m.setLore(lore);
			m.setDisplayName("§7§l「 §6§l" + Bukkit.getOfflinePlayer(player.playerID).getName() + " §7§l」");
			
			head.setItemMeta(m);
			inv.setItem(13, head);
		} else {
			int start = 13 - (int)((float)k.members.size()/2f);;
			if(k.members.size() % 2 == 0) {
				for(int i = start; i < k.members.size() + start; i++) {
					PlayerInfo player = k.members.get(i-start);
					ItemStack head = getHead(Bukkit.getOfflinePlayer(player.playerID));
					ItemMeta m = head.getItemMeta();
					
					ArrayList<String> lore = new ArrayList<>();
					lore.add("");
					if(player.isKing)
						lore.add("§6►  §6King");
					lore.add("§6►  §bProfession: " + player.profession.getText());
					lore.add("§6►  §bLevel: " + player.level);
					lore.add("");
					m.setLore(lore);
					m.setDisplayName("§7§l「 §6§l" + Bukkit.getOfflinePlayer(player.playerID).getName() + " §7§l」");
					
					head.setItemMeta(m);

					if(i > 12) {
						inv.setItem(i+1, head);
					} else {
						inv.setItem(i, head);
					}
				}
			} else {
				for(int i = start; i < k.members.size() + start; i++) {
					PlayerInfo player = k.members.get(i-start);
					ItemStack head = getHead(Bukkit.getOfflinePlayer(player.playerID));
					ItemMeta m = head.getItemMeta();
					
					ArrayList<String> lore = new ArrayList<>();
					lore.add("");
					if(player.isKing)
						lore.add("§6►  §6King");
					lore.add("§6►  §bProfession: " + player.profession.getText());
					lore.add("§6►  §bLevel: " + player.level);
					lore.add("");
					m.setLore(lore);
					m.setDisplayName("§7§l「 §6§l" + Bukkit.getOfflinePlayer(player.playerID).getName() + " §7§l」");
					
					head.setItemMeta(m);
					inv.setItem(i, head);
				}
			}
		}
		
		p.openInventory(inv);
	}
	
	public static ItemStack getHead(OfflinePlayer player) {
		ItemStack head = new ItemStack(Material.PLAYER_HEAD);
		SkullMeta meta = (SkullMeta) head.getItemMeta();
		meta.setOwningPlayer(player);
		head.setItemMeta(meta);
		return head;
	}
}
