package fr.nashoba24.chatformatapi.groups;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class GroupManagerCFAPI {

	public static String getGroup(Player p) {
		final AnjoPermissionsHandler handler = ((GroupManager) Bukkit.getServer().getPluginManager().getPlugin("GroupManager")).getWorldsHolder().getWorldPermissions(p);
		if (handler == null)
		{
			return "default";
		}
		if(handler.getGroup(p.getName())==null) {
			return "default";
		}
		return handler.getGroup(p.getName());
	}
	
}
