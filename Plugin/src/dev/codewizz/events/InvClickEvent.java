package dev.codewizz.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import dev.codewizz.Main;
import dev.codewizz.players.PlayerInfo;
import dev.codewizz.players.Profession;

public class InvClickEvent implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if(e.getView().getTitle().equalsIgnoreCase("Choose a profession")) {
			PlayerInfo f = Main.inst.manager.getProfile(e.getWhoClicked().getUniqueId());
			boolean succes = false;
			if(e.getView().getTopInventory().contains(e.getCurrentItem())) {
				if(e.getCurrentItem().getType() == Material.IRON_PICKAXE) {
					f.profession = Profession.MINER;
					succes = true;
				} else if(e.getCurrentItem().getType() == Material.WHEAT) {
					f.profession = Profession.FARMER;
					succes = true;
				} else if(e.getCurrentItem().getType() == Material.OAK_LOG) {
					f.profession = Profession.FORESTER;
					succes = true;
				} else if(e.getCurrentItem().getType() == Material.SHIELD) {
					f.profession = Profession.SOLDIER;
					succes = true;
				} else if(e.getCurrentItem().getType() == Material.ENCHANTING_TABLE) {
					f.profession = Profession.WIZARD;
					succes = true;
				} else if(e.getCurrentItem().getType() == Material.ANVIL) {
					f.profession = Profession.BLACKSMITH;
					succes = true;
				}
			}
			
			if(succes) {
				e.getWhoClicked().sendMessage("§7§l[§6§lSERVER§7§l]§7: §bCongratulations! You are now a §6" + f.profession.getText());
				e.getView().close();
			}
			
			e.setCancelled(true);
		} else if(e.getView().getTitle().equalsIgnoreCase("Profession Information")) {
			e.setCancelled(true);
		}
	}
}
