package dev.codewizz.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import dev.codewizz.Main;

public class JoinLeaveEvent implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		if(!Main.inst.manager.contains(e.getPlayer().getUniqueId())) {
			Main.inst.manager.addNewProfile(e.getPlayer());
			System.out.println(Main.inst.manager.getProfile(e.getPlayer().getUniqueId()).profession);
		} else {
			System.out.println("PROFILE FOUND");
			Main.inst.manager.getProfile(e.getPlayer().getUniqueId()).level++;
		}
	}
}
