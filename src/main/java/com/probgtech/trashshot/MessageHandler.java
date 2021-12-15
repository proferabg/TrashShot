package com.probgtech.trashshot;

import org.bukkit.ChatColor;

public class MessageHandler {
		
	static TrashShot plugin;
	public MessageHandler(TrashShot p){
		plugin = p;
	}

	public static String getPrefix() {
		String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Messages.Prefix"));
		return prefix;
	}

}
