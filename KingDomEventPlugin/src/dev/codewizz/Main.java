package dev.codewizz;

import org.bukkit.plugin.java.JavaPlugin;

import dev.codewizz.commands.KingdomCommand;
import dev.codewizz.commands.ProfessionCommand;
import dev.codewizz.commands.ProfessionInventory;
import dev.codewizz.events.BlockEvent;
import dev.codewizz.events.ExpEvent;
import dev.codewizz.events.InvClickEvent;
import dev.codewizz.events.JoinLeaveEvent;
import dev.codewizz.players.PlayerManager;
import dev.codewizz.players.PlayerSaveLoader;

public class Main extends JavaPlugin {

	public static Main inst;
	public PlayerManager manager;
	public KingdomManager kingdomManager;
	
	@Override
	public void onEnable() {
		inst = this;
		Util.setup();
		manager = new PlayerManager();
		kingdomManager = new KingdomManager();
		
		PlayerSaveLoader.loadFromConfig();

		registerEvents();
		registerCommands();
		
		ProfessionInventory.loadInvs();
		System.out.println("ENABLED MAIN PLUGIN");
	}
	
	@Override
	public void onDisable() {
		PlayerSaveLoader.saveToConfig();
		System.out.println("DISABLED MAIN PLUGIN");
	}
	
	private void registerCommands() {
		this.getCommand("prof").setExecutor(new ProfessionCommand());
		this.getCommand("kingdom").setExecutor(new KingdomCommand());
	}
	
	private void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new JoinLeaveEvent(), this);
		this.getServer().getPluginManager().registerEvents(new InvClickEvent(), this);
		this.getServer().getPluginManager().registerEvents(new ExpEvent(), this);
		this.getServer().getPluginManager().registerEvents(new BlockEvent(), this);
	}
}
