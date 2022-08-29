package dev.codewizz.players;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import dev.codewizz.Kingdom;
import dev.codewizz.Main;

public class PlayerSaveLoader {
	
	public static boolean loadFromConfig() {
		FileConfiguration c = Main.inst.getConfig();
		
		ConfigurationSection sectionKingdom = c.getConfigurationSection("kingdoms");
		
		if(sectionKingdom != null) {
			for(String uuid : sectionKingdom.getKeys(false)) {
				
				
				double x = c.getDouble("kingdoms." + uuid + ".x");
				double y = c.getDouble("kingdoms." + uuid + ".y");
				double z = c.getDouble("kingdoms." + uuid + ".z");
				World world = Bukkit.getWorld(c.getString("kingdoms." + uuid + ".world"));
				
				Location l = new Location(world, x, y, z);
				
				String name = c.getString("kingdoms." + uuid + ".name");
				
				Main.inst.kingdomManager.loadInProfile(uuid, l, name);
			}
		}
		
		ConfigurationSection section = c.getConfigurationSection("players");
		
		if(section != null) {
			for(String uuid : section.getKeys(false)) {
				int level = c.getInt("players." + uuid + ".level");
				int levelProgression = c.getInt("players." + uuid + ".levelProgression");
				int profession = c.getInt("players." + uuid + ".profession");
				boolean isKing = c.getBoolean("players." + uuid + ".king");
				String kingdom = c.getString("players." + uuid + ".kingdom");
				
				if(kingdom.equalsIgnoreCase("lonely"))
					kingdom = null;
				
				
				
				Main.inst.manager.loadInProfile(level, levelProgression, profession, uuid, isKing, kingdom);
			}
		}
		
		return true;
	}
	
	public static void saveToConfig() {
		FileConfiguration c = Main.inst.getConfig();
		
		c.set("players", null);
		c.set("kingdoms", null);
		Main.inst.saveConfig();
		
		for(Kingdom k : Main.inst.kingdomManager.kingdoms) {
			String id = k.uuid.toString();
			
			c.set("kingdoms." + id + ".x", k.l.getX());
			c.set("kingdoms." + id + ".y", k.l.getY());
			c.set("kingdoms." + id + ".z", k.l.getZ());
			c.set("kingdoms." + id + ".world", k.l.getWorld().getName());
			
			c.set("kingdoms." + id + ".name", k.name);
		}
		
		for(PlayerInfo p : Main.inst.manager.profiles) {
			String uuid = p.playerID.toString();
			
			c.set("players." + uuid + ".level", p.level);
			c.set("players." + uuid + ".levelProgression", p.levelProgression);
			c.set("players." + uuid + ".profession", p.profession.ordinal());
			c.set("players." + uuid + ".king", p.isKing);
			
			if(p.kingdom == null)
				c.set("players." + uuid + ".kingdom", "lonely");
			else
				c.set("players." + uuid + ".kingdom", p.kingdom.toString());
			
			Main.inst.saveConfig();
		}
	}
}
