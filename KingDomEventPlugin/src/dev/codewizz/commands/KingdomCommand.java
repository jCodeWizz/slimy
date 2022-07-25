package dev.codewizz.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.codewizz.Kingdom;
import dev.codewizz.Main;
import dev.codewizz.players.PlayerInfo;

public class KingdomCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerInfo playerProfile = Main.inst.manager.getProfile(player.getUniqueId());
		
            if(args.length > 0) {
            	if(args.length > 1) {
            		if(args[0].equalsIgnoreCase("create")) {
            			String kingdomName = args[1];
            			Kingdom k = new Kingdom(player.getLocation(), kingdomName);
            			k.members.add(playerProfile);
            			Main.inst.kingdomManager.addNewKingdom(k);
            			playerProfile.kingdom = k.uuid;
            			
            			player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §bSuccesfully created the Kingdom: " + k.name);
            		} else if(args[0].equalsIgnoreCase("add")) {
            			if(playerProfile.getKingdom() != null) {
            				Player target = Bukkit.getPlayer(args[1]);
                			if(target != null) {
                				PlayerInfo f = Main.inst.manager.getProfile(target.getUniqueId());
                    			f.kingdom = playerProfile.getKingdom().uuid;
                    			f.getKingdom().members.add(f);
                    			
                    			
                    			target.sendMessage("§7§l[§6§lSERVER§7§l]§7: §bAdded you to §6" + f.getKingdom().name);
                    			player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §bAdded §6" + target.getName() + "§b to your Kingdom");
                			} else {
                    			player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §cCould not find player: " + args[1]);
                			}
            			} else {
                			player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §cYou're not in a Kingdom");
            			}
            		}
            	} 
            	if(args[0].equalsIgnoreCase("set") && playerProfile.getKingdom() != null) {
            		Kingdom k = playerProfile.getKingdom();
            		k.l = player.getLocation();
            		player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §bSet your kingdom's location to your current location");
            	}
            } else {
            	if(playerProfile.getKingdom() != null) {
            		KingdomInventory.open(player, playerProfile.getKingdom());
            	}
            }
		} else {
			sender.sendMessage(ChatColor.RED + "Only players can use this command");
		}
		
		
		
		
		return true;
	}
}
