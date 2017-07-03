package fr.nashoba24.chatformatapi.groups;

import org.bukkit.entity.Player;

import ru.tehkode.permissions.bukkit.PermissionsEx;

public class PermissionsExCFAPI {

	@SuppressWarnings("deprecation")
	public static String getGroup(Player p) {
		if(PermissionsEx.getUser(p.getName()).getGroups().length==0) {
			return "default";
		}
		return PermissionsEx.getUser(p.getName()).getGroups()[0].getIdentifier();
	}
	
}
