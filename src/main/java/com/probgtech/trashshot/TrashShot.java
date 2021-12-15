package com.probgtech.trashshot;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public class TrashShot extends JavaPlugin{
	
	public String v;

	public void onEnable() {
		
		getLogger().info("is now enabled!");
		getServer().getPluginManager().registerEvents(new TrashListener(this), this);
		getConfig().options().copyDefaults(true);
		getConfig().set("Version", getDescription().getVersion());
		saveConfig();
		new MessageHandler(this);
		registerCommandT();
		saveConfig();

		new Metrics(this, 13607);
	}

	public void onDisable() {
		this.saveConfig();
	}
	
	private void registerCommandT(){
		TrashCommands tS = new TrashCommands(this, this.getDescription().getVersion());
		this.getCommand("ts").setExecutor(tS);
    }

}

