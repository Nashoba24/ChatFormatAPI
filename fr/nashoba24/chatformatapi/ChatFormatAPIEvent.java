package fr.nashoba24.chatformatapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ChatFormatAPIEvent extends Event {
	
	private static final HandlerList handlers = new HandlerList();
    private Player p;
    private String msg;
    private String fmsg;
 
    public ChatFormatAPIEvent(Player p, String format, String m) {
        this.p = p;
        this.msg = m;
        this.fmsg = format;
        setVariable("MESSAGE", "%2$s");
        setVariable("PLAYER_NAME", p.getName());
        setVariable("DISPLAY_NAME", "%s");
        setVariable("EXP", String.valueOf(p.getExp()));
        setVariable("LEVEL", String.valueOf(p.getLevel()));
        setVariable("FOOD_LEVEL", String.valueOf(p.getFoodLevel()));
        setVariable("HEALTH", String.valueOf(p.getHealth()));
        setVariable("COORDINATE_X", String.valueOf(p.getLocation().getX()));
        setVariable("COORDINATE_Y", String.valueOf(p.getLocation().getY()));
        setVariable("COORDINATE_Z", String.valueOf(p.getLocation().getZ()));
        setVariable("WORLD", String.valueOf(p.getWorld().getName()));
    }
 
    public Player getPlayer() {
        return this.p;
    }
    
    public String getMessage() {
        return this.msg;
    }

	public HandlerList getHandlers() {
		return handlers;
	}
	
    public static HandlerList getHandlerList() {
        return handlers;
    }
 
    public void setVariable(String key, String value) {
    	fmsg = fmsg.replace("{" + key.toUpperCase() + "}", value);
    	fmsg = fmsg.replace("{" + key.toLowerCase() + "}", value);
    }
    
    public String getFinalFormat() {
    	return fmsg;
    }
}
