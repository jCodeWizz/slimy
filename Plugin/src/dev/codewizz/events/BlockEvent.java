package dev.codewizz.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import dev.codewizz.Main;
import dev.codewizz.Util;
import dev.codewizz.players.PlayerInfo;
import dev.codewizz.players.Profession;

public class BlockEvent implements Listener {

	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if(e.getBlock().getType() == Material.ANCIENT_DEBRIS) {
			if(Main.inst.manager.getProfile(e.getPlayer().getUniqueId()).level < 2 || Main.inst.manager.getProfile(e.getPlayer().getUniqueId()).profession != Profession.MINER) {
				e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly a Miner of §c§lLVL[2] §ccan mine this");
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.SMITHING_TABLE) {
				PlayerInfo f = Main.inst.manager.getProfile(e.getPlayer().getUniqueId());
				if(f.level < 3 || f.profession != Profession.BLACKSMITH) {
					e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly a Black Smith of §c§lLVL[3] §ccan use this");
					e.setCancelled(true);
				}
			}
			if(e.getClickedBlock().getType() == Material.BLAST_FURNACE) {
				PlayerInfo f = Main.inst.manager.getProfile(e.getPlayer().getUniqueId());
				if(f.profession != Profession.BLACKSMITH) {
					e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly a Black Smith of §c§lLVL[3] §ccan use this");
					e.setCancelled(true);
				}
			}
			if(e.getClickedBlock().getType() == Material.SMOKER) {
				PlayerInfo f = Main.inst.manager.getProfile(e.getPlayer().getUniqueId());
				if(f.profession != Profession.BLACKSMITH) {
					e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly a Farmer can use this");
					e.setCancelled(true);
				}
			}
			if(e.getItem() != null) {
				if(e.getItem().getType() == Material.BONE_MEAL) {
					PlayerInfo f = Main.inst.manager.getProfile(e.getPlayer().getUniqueId());
					if(f.profession != Profession.FORESTER && f.profession != Profession.FARMER) {
						e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly Farmers and Foresters can use this");
						e.setCancelled(true);
					}
				} else if(Util.HOES.contains(e.getItem().getType())) {
					PlayerInfo f = Main.inst.manager.getProfile(e.getPlayer().getUniqueId());
					if(f.profession != Profession.FARMER) {
						e.getPlayer().sendMessage("§7§l[§6§lSERVER§7§l]§7: §cOnly a Farmer can use this");
						e.setCancelled(true);
					}
				}
			}
		}
	}
}
