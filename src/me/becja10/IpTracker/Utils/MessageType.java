package me.becja10.IpTracker.Utils;

import org.bukkit.ChatColor;

public enum MessageType 
{
	PREFIX("&8[&cIpTracker&8] ");
	
	String msg;
	
	private MessageType(String s) 
	{
		this.msg = s;
	}

	public String getMsg() 
	{
		return ChatColor.translateAlternateColorCodes('&', this.msg);
	}
}
