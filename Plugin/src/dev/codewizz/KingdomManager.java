package dev.codewizz;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Location;

public class KingdomManager {
	public List<Kingdom> kingdoms;

	public KingdomManager() {
		kingdoms = new CopyOnWriteArrayList<>();
	}
	
	public Kingdom getProfile(UUID id) {
		for(Kingdom k : kingdoms) {
			if(k.uuid.equals(id)) {
				return k;
			}
		}
		return null;
	}
	
	public boolean contains(UUID id) {
		for(Kingdom k : kingdoms) {
			if(k.uuid.equals(id)) {
				return true;
			}
		}
		return false;
	}
	
	public void addNewKingdom(Kingdom k) {
		kingdoms.add(k);
	}
	
	public void loadInProfile(String id, Location l, String name) {
		kingdoms.add(new Kingdom(UUID.fromString(id), l, name));
	}
}
