package fr.nashoba24.chatformatapi.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.nashoba24.chatformatapi.ChatFormatAPIEvent;
import fr.nashoba24.prefixlogo.PrefixLogoCommand;

public class PrefixLogo implements Listener {

	@EventHandler
	public void onChatPrefixLogo(ChatFormatAPIEvent e) {
		String logo = PrefixLogoCommand.getPlayerLogo(e.getPlayer().getName());  
		if(logo==null) {
			e.setVariable("PREFIX_LOGO", "");
		}
		else {
			e.setVariable("PREFIX_LOGO", logo + " " + ChatColor.RESET);
		}
	}
}
