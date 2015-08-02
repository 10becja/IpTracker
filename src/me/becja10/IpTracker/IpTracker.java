package me.becja10.IpTracker;

import java.util.logging.Logger;

import me.becja10.IpTracker.Events.PlayerJoin;
import me.becja10.IpTracker.Utils.IpManager;
import me.becja10.IpTracker.Utils.PlayerManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class IpTracker extends JavaPlugin
{
	private static IpTracker plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	
	public void onEnable()
	{
		plugin = this;
    
		//let the console know that the plugin has been enabled
		PluginDescriptionFile pdfFile = this.getDescription();
		log.info(pdfFile.getName() + " Version "+ pdfFile.getVersion() + " Has Been Enabled!");
		
		PlayerManager.saveDefaultPlayers();
		IpManager.saveDefaultIPs();

		//register listeners 
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

	}
	
	public void onDisable() 
	{ 
		//let the console know the plugin has stopped
		PluginDescriptionFile pdfFile = this.getDescription();
		this.log.info(pdfFile.getName() + " Has Been Disabled!");
		plugin = null; 
		
		PlayerManager.savePlayers();
		IpManager.saveIPs();
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String cml, String[] args)
	{
		
		return true;
	}
	
	
	
	public static IpTracker getInstance() 
	{
		return plugin;
	}
}


