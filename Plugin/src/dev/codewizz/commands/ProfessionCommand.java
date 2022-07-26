package dev.codewizz.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import dev.codewizz.Main;
import dev.codewizz.players.PlayerInfo;
import dev.codewizz.players.Profession;

public class ProfessionCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerInfo playerProfile = Main.inst.manager.getProfile(player.getUniqueId());
            
            if(args.length == 0) {
            	if(playerProfile.profession == Profession.JOBLESS) {
                	ProfessionInventory.openSelector(player);
            	} else {
                	ProfessionInventory.openInfo(player);
            	}
            } else if(args.length == 1) {
            	if(args[0].equalsIgnoreCase("repair")) {
            		if(playerProfile.profession == Profession.BLACKSMITH) {
            			ItemStack i = player.getInventory().getItemInMainHand();
            			if(i != null) {
            				ItemMeta m = i.getItemMeta();
            				if(m instanceof Damageable) {
            					((Damageable) m).setDamage(0);
            					i.setItemMeta(m);
                				player.getInventory().setItemInMainHand(i);
                				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §bRepaired the item in your hand");
            				} else {
                				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §cYou're not holding a tool");

            				}
            			}
            		}
            	}
            } else {
            	if(args[0].equalsIgnoreCase("set")) {
            		String playerName = args[1];
            		Player target = Bukkit.getPlayer(playerName);
            		if(target != null) {
            			PlayerInfo targetProfile = Main.inst.manager.getProfile(target.getUniqueId());
            			
            			if(args[2].equalsIgnoreCase("level")) {
            				targetProfile.level = Integer.parseInt(args[3]);
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s " + args[2] + " is now set to: §6" + targetProfile.level);
            			} else if(args[2].equalsIgnoreCase("progress")) {
            				targetProfile.levelProgression = Integer.parseInt(args[3]);
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s " + args[2] + " is now set to: §6" + targetProfile.levelProgression);
            			} else if(args[2].equalsIgnoreCase("prof")) {
            				targetProfile.profession = Profession.valueOf(args[3]);
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s profession is now set to: §6" + targetProfile.profession.getText());
            			} else if(args[2].equalsIgnoreCase("king")) {
            				targetProfile.isKing = Boolean.parseBoolean(args[3]);
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + " is a king, is now set to: §6" + targetProfile.isKing);
            			} else {
            				player.sendMessage(ChatColor.RED + "Could not find var");
            			}
            		} else {
            			player.sendMessage(ChatColor.RED + "Could not find player!");
            		}
            	} else if(args[0].equalsIgnoreCase("get")) {
            		String playerName = args[1];
            		Player target = Bukkit.getPlayer(playerName);
            		if(target != null) {
            			PlayerInfo targetProfile = Main.inst.manager.getProfile(target.getUniqueId());
            			
            			if(args[2].equalsIgnoreCase("level")) {
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s " + args[2] + " is: §6" + targetProfile.level);
            			} else if(args[2].equalsIgnoreCase("progress")) {
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s " + args[2] + " is: §6" + targetProfile.levelProgression);
            			} else if(args[2].equalsIgnoreCase("prof")) {
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + "'s profession is: §6" + targetProfile.profession.getText());
            			} else if(args[2].equalsIgnoreCase("king")) {
            				player.sendMessage("§7§l[§6§lSERVER§7§l]§7: §b" + playerName + " is a king: §6" + targetProfile.isKing);
            			} else {
            				player.sendMessage(ChatColor.RED + "Could not find var");
            			}
            		} else {
            			player.sendMessage(ChatColor.RED + "Could not find player!");
            		}
            	}
            }
		} else {
			sender.sendMessage(ChatColor.RED + "Not able to execute command on console.");
		}
		return true;
	}
}
