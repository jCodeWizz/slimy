package dev.codewizz.players;

import java.util.UUID;

import dev.codewizz.Kingdom;
import dev.codewizz.Main;

public class PlayerInfo {

	public UUID playerID;
	public int level, levelProgression;
	public UUID kingdom;
	public boolean isKing;
	public Profession profession;
	
	public PlayerInfo(UUID playerID) {
		this.playerID = playerID;
		this.level = 0;
		this.isKing = false;
		this.levelProgression = 0;
		this.kingdom = null;
		this.profession = Profession.JOBLESS;
	}
	
	public PlayerInfo(UUID playerID, Profession p, int level, int levelProgression, boolean isKing, UUID kingdom) {
		this.playerID = playerID;
		this.level = level;
		this.isKing = isKing;
		this.profession = p;
		this.kingdom = kingdom;
		this.levelProgression = levelProgression;
	}
	
	public Kingdom getKingdom() {
		if(kingdom != null) {
			return Main.inst.kingdomManager.getProfile(kingdom);
		} else {
			return null;
		}
	}
}
