package com.probgtech.trashshot;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TrashCommands implements CommandExecutor {
	
	private TrashShot plugin;
	private String version;
	public TrashCommands(TrashShot p, String v) {
		plugin = p;
		version = v;
		
	}

	@Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args){
    	if (command.getName().equalsIgnoreCase("ts")){
        	handleCommandHub(sender, args);
        	return true;
        }
    	return false;
    }

    private void handleCommandHub(CommandSender sender, String[] args) {
    	if (args.length == 0){
    		sender.sendMessage(MessageHandler.getPrefix() + "Invalid arguments try " + ChatColor.DARK_GREEN + " /ts help");
    	}
    	else if (args.length == 1){
    		if (args[0].equalsIgnoreCase("help")) {	
    			sender.sendMessage(ChatColor.GRAY + " ------ " + ChatColor.DARK_GREEN + "TrashShot" + ChatColor.GRAY + " ------ ");
    			sender.sendMessage(ChatColor.DARK_GREEN + "/ts help" + ChatColor.GRAY + " - Shows all available commands!");
    			if(sender.hasPermission("trashshot.reload")){
    				sender.sendMessage(ChatColor.DARK_GREEN + "/ts reload" + ChatColor.GRAY + " - Reloads the plugin!");
    			}
    			sender.sendMessage(ChatColor.DARK_GREEN + "/ts about" + ChatColor.GRAY + " - Shows proferabg's bragging rights!");
    		}
    		else if (args[0].equalsIgnoreCase("about")) {
    			sender.sendMessage(ChatColor.GRAY + " ------ " + ChatColor.DARK_GREEN + "TrashShot" + ChatColor.GRAY + " ------ ");
    			sender.sendMessage(ChatColor.DARK_GREEN + "TrashShot v" + version + ChatColor.GRAY + " by proferabg");
        		sender.sendMessage(ChatColor.GRAY + "Made for the server:");
        		sender.sendMessage(ChatColor.DARK_GREEN + "    Trash" + ChatColor.GRAY + "Craft");
    		}
    		else if (args[0].equalsIgnoreCase("reload")){
    			if(sender instanceof Player) {
            		Player play = (Player)sender;
    			    if(play.hasPermission("trashshot.reload")){
    			    	plugin.reloadConfig();
    			    	sender.sendMessage(MessageHandler.getPrefix() + "Config Reloaded!");
    			    }
    			    else{
    			    	sender.sendMessage(MessageHandler.getPrefix() + ChatColor.RED + "You do not have permission!");
    			    }
    			}
    			else {
			    	plugin.reloadConfig();
			    	sender.sendMessage(MessageHandler.getPrefix() + "Config Reloaded!");
    			}
    		}
    		else {
    			sender.sendMessage(MessageHandler.getPrefix() + "Check your arguments or do " + ChatColor.DARK_GREEN + "/ts help");
    		}
    	}
	}

}
