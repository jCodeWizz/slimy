package dev.codewizz.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;

import dev.codewizz.Main;
import dev.codewizz.players.Profession;

public class ExpEvent implements Listener {

	@EventHandler
	public void onPickup(PlayerExpChangeEvent e) {
		if(Main.inst.manager.getProfile(e.getPlayer().getUniqueId()).profession == Profession.WIZARD) {
			e.setAmount(e.getAmount() * 10);
		}
	}
}
