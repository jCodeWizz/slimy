package dev.codewizz.players;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.entity.Player;

import dev.codewizz.Main;

public class PlayerManager {

	public List<PlayerInfo> profiles;

	public PlayerManager() {
		profiles = new CopyOnWriteArrayList<>();
	}
	
	public PlayerInfo getProfile(UUID name) {
		for(PlayerInfo p : profiles) {
			if(p.playerID.equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public boolean contains(UUID name) {
		for(PlayerInfo p : profiles) {
			if(p.playerID.equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewProfile(Player p) {
		profiles.add(new PlayerInfo(p.getUniqueId()));
	}
	
	public void loadInProfile(int level, int levelProgression, int prof, String id, boolean isKing, String kingdom) {
		PlayerInfo f = new PlayerInfo(UUID.fromString(id), Profession.values()[prof], level, levelProgression, isKing, UUID.fromString(kingdom));
		profiles.add(f);
		Main.inst.kingdomManager.getProfile(f.kingdom).members.add(f);
	}
}
