package fr.nashoba24.chatformatapi.groups;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultCFAPI {
	
	public static Permission perm;
	public static Chat chat;

	public static String getGroup(Player p) {
		if(perm.getPrimaryGroup(p)==null) {
			return "default";
		}
		return perm.getPrimaryGroup(p);
	}
	
	public static void init() {
		RegisteredServiceProvider<Permission> permProvider = Bukkit.getServer().getServicesManager().getRegistration(Permission.class);
        if (permProvider != null) {
            perm = permProvider.getProvider();
        }
        RegisteredServiceProvider<Chat> chatProvider = Bukkit.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }
	}
	
}
