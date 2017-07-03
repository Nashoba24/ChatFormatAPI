package fr.nashoba24.chatformatapi.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.nashoba24.chatformatapi.ChatFormatAPIEvent;
import fr.nashoba24.wolvmc.WolvMCAPI;
public class WolvMC implements Listener {

	@EventHandler
	public void onChatWolvMC(ChatFormatAPIEvent e) {
		e.setVariable("WOLVMC_RACE", WolvMCAPI.getChatPrefix(e.getPlayer()));
	}
}
