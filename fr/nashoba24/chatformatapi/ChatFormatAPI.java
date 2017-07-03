package fr.nashoba24.chatformatapi;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

import fr.nashoba24.chatformatapi.groups.GroupManagerCFAPI;
import fr.nashoba24.chatformatapi.groups.PermissionsExCFAPI;
import fr.nashoba24.chatformatapi.groups.VaultCFAPI;
import fr.nashoba24.chatformatapi.plugins.PrefixLogo;
import fr.nashoba24.chatformatapi.plugins.Vault;
import fr.nashoba24.chatformatapi.plugins.WolvMC;

public class ChatFormatAPI extends JavaPlugin implements Listener {
	
	public String defaultFormat = "{PLAYER_NAME}> {MESSAGE}";
	public HashMap<String, String> formats = new HashMap<String, String>();
	private static ChatFormatAPI instance;

	  @Override
	  public void onDisable()
	  {
		  Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&bChatFormatAPI Disabled!"));
	  }
	  
	  @Override
	  public void onEnable()
	  {
		  instance = this;
		  File file = new File(this.getDataFolder() + "/");
		  if(!file.exists()) {
			  file.mkdir();
		  }
		  getCommand("chatformatapi").setExecutor(new ChatFormatAPICommand());
		  Bukkit.getPluginManager().registerEvents(this, this);
		  reloadConfig();
		  registerAll();
		  Bukkit.getLogger().info(ChatColor.translateAlternateColorCodes('&', "&aChatFormatAPI Enabled!"));
	  }
	  
	  @EventHandler
	  public void onChat(AsyncPlayerChatEvent e) {
	      ChatFormatAPIEvent event = new ChatFormatAPIEvent(e.getPlayer(), getFormat(e.getPlayer()), e.getMessage());
	      Bukkit.getServer().getPluginManager().callEvent(event);
	      e.setFormat(ChatColor.translateAlternateColorCodes('&', event.getFinalFormat()));
	  }
	  
	  public void reloadConfig() {
		  formats.clear();
		  defaultFormat = "{PLAYER_NAME}> {MESSAGE}";
		  ChatFormatAPI.getPlugin(ChatFormatAPI.class).saveDefaultConfig();
		  File configFile = new File(ChatFormatAPI.getPlugin(ChatFormatAPI.class).getDataFolder() + "/config.yml");
		  FileConfiguration conf = YamlConfiguration.loadConfiguration(configFile);
		  if(conf.isSet("default-format")) {
			  defaultFormat = conf.getString("default-format");
		  }
		  if(conf.isSet("worlds")) {
			  Set<String> keys = conf.getConfigurationSection("worlds").getKeys(false);
			  for(String key : keys) {
				  if(conf.getConfigurationSection("worlds").isConfigurationSection(key)) {
					  Set<String> groups = conf.getConfigurationSection("worlds." + key).getKeys(false);
					  for(String gr : groups) {
						  formats.put(key.toLowerCase() + "|" + gr.toLowerCase(), conf.getConfigurationSection("worlds." + key).getString(gr).toLowerCase());
					  }
				  } 
				  else if(conf.getConfigurationSection("worlds").isString(key)) {
					  formats.put(key.toLowerCase() + "|default", conf.getConfigurationSection("worlds").getString(key).toLowerCase());
				  }
			  }
		  }
	  }
	  
	  public String getFormat(Player p) {
		  String group = "default";
		  String world = p.getWorld().getName().toLowerCase();
		  if(getServer().getPluginManager().getPlugin("Vault")!=null) {
			  group = VaultCFAPI.getGroup(p).toLowerCase();
		  }
		  else if(getServer().getPluginManager().getPlugin("GroupManager")!=null) {
			  group = GroupManagerCFAPI.getGroup(p).toLowerCase();
		  }
		  else if(getServer().getPluginManager().getPlugin("PermissionsEx")!=null) {
			  group = PermissionsExCFAPI.getGroup(p).toLowerCase();
		  }
		  if(formats.containsKey(world + "|" + group)) {
			  return formats.get(world + "|" + group);
		  }
		  else if(formats.containsKey("default|" + group)) {
			  return formats.get("default|" + group);
		  }
		  else if(formats.containsKey(world + "|default")) {
			  return formats.get(world + "|default");
		  }
		  else if(formats.containsKey("default|default")) {
			  return formats.get("default|default");
		  }
		  else {
			  return defaultFormat;
		  }
	  }
	  
	  public static ChatFormatAPI getInstance() {
		  return instance;
	  }
	  
	  public void registerAll() {
		  if(getServer().getPluginManager().getPlugin("Vault")!=null) {
			  VaultCFAPI.init();
			  Bukkit.getPluginManager().registerEvents(new Vault(), this);
		  }
		  if(getServer().getPluginManager().getPlugin("WolvMC")!=null) {
			  Bukkit.getPluginManager().registerEvents(new WolvMC(), this);
		  }
		  if(getServer().getPluginManager().getPlugin("PrefixLogo")!=null) {
			  Bukkit.getPluginManager().registerEvents(new PrefixLogo(), this);
		  }
	  }
}
