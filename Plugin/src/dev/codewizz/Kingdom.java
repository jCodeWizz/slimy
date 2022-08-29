package dev.codewizz;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import org.bukkit.Location;

import dev.codewizz.players.PlayerInfo;

public class Kingdom {

	public List<PlayerInfo> members = new CopyOnWriteArrayList<>();
	public Location l;
	public String name;
	public UUID uuid;
	
	public Kingdom(Location l, String name) {
		this.l = l;
		this.name = name;
		this.uuid = UUID.randomUUID();
	}
	
	public Kingdom(UUID id, Location l, String name) {
		this.l = l;
		this.name = name;
		this.uuid = id;
	}
}
