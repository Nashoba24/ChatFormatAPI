package fr.nashoba24.chatformatapi;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ChatFormatAPICommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length!=1) {
			sender.sendMessage(ChatColor.GREEN + "/cfapi help");
			sender.sendMessage(ChatColor.GREEN + "/cfapi reload");
		}
		else {
			if(args[0].equalsIgnoreCase("reload")) {
				ChatFormatAPI.getInstance().reloadConfig();
				sender.sendMessage(ChatColor.AQUA + "[ChatFormatAPI] Config reloaded!");
			}
			else {
				sender.sendMessage(ChatColor.GREEN + "/cfapi help");
				sender.sendMessage(ChatColor.GREEN + "/cfapi reload");
			}
		}
		return true;
	}
}
