package fr.nashoba24.chatformatapi.plugins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import fr.nashoba24.chatformatapi.ChatFormatAPIEvent;
import fr.nashoba24.chatformatapi.groups.VaultCFAPI;

public class Vault implements Listener {
	
	@EventHandler
	public void onVaultChat(ChatFormatAPIEvent e) {
		e.setVariable("RANK", VaultCFAPI.perm.getPrimaryGroup(e.getPlayer()));
		e.setVariable("PREFIX", VaultCFAPI.chat.getPlayerPrefix(e.getPlayer()));
		e.setVariable("SUFFIX", VaultCFAPI.chat.getPlayerSuffix(e.getPlayer()));
	}
	
}
