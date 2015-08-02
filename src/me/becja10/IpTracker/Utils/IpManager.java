package me.becja10.IpTracker.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import me.becja10.IpTracker.IpTracker;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class IpManager 
{
	private static FileConfiguration config = null;
	private static File ips = null;

	/*
	 * Get information about the players stored in players.yml
	 */
	public static FileConfiguration getIPs() {
		if (config == null)
			reloadIPs();
		return config;
	}
	
	/*
	 * Reloads the player.yml file
	 */
	public static void reloadIPs() {
		if (ips == null)
			ips = new File(IpTracker.getInstance().getDataFolder(), "ips.yml");
		config = YamlConfiguration.loadConfiguration(ips);

		InputStream defConfigStream = IpTracker.getInstance().getResource("ips.yml");
		if (defConfigStream != null) {
			@SuppressWarnings("deprecation")
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			config.setDefaults(defConfig);
		}
	}
	
	/*
	 * saves information to player.yml
	 */
	public static void saveIPs() {
		if ((config == null) || (ips == null))
			return;
		try {
			getIPs().save(ips);
		} catch (IOException ex) {
			System.out.println(MessageType.PREFIX.getMsg() + "Could not save config to " + ips);
		}
	}

	/*
	 * Creates the default, empty player.yml file
	 */
	public static void saveDefaultIPs() {
		if (ips == null)
			ips = new File(IpTracker.getInstance().getDataFolder(), "ips.yml");
		if (!ips.exists())
			IpTracker.getInstance().saveResource("ips.yml", false);
	}
}
