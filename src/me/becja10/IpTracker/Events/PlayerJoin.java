package me.becja10.IpTracker.Events;

import java.util.List;

import me.becja10.IpTracker.Utils.IpManager;
import me.becja10.IpTracker.Utils.MessageType;
import me.becja10.IpTracker.Utils.PlayerManager;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerJoin implements Listener 
{

	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPreJoin(AsyncPlayerPreLoginEvent e)
	{
		
		FileConfiguration getIps = IpManager.getIPs();
		FileConfiguration getPlayers = PlayerManager.getPlayers();
		
		String playerName = e.getName();
		String playerId = e.getUniqueId().toString();
		
		String ipAddress = e.getAddress().getHostAddress();
		
		List<String> ips = getIps.getStringList(playerId + ".ips");
		List<String> names = getPlayers.getStringList(ipAddress + ".players");
		
		if(!ips.contains(ipAddress))
		{
			ips.add(ipAddress);
		}
		
		if(!names.contains(playerName))
		{
			names.add(playerName);
		}
		
		getIps.set(playerId +".name", playerName);
		getIps.set(playerId +".ips", ips);
		
		getPlayers.set(ipAddress + ".names", names);
		
		PlayerManager.savePlayers();
		IpManager.saveIPs();
		
		System.out.println(MessageType.PREFIX.getMsg() + ChatColor.AQUA + " " + playerName + " attempted login with IP Address: " + ipAddress);
		
	}
}
