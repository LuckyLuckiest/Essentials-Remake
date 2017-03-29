package me.essentialsremake.yt.API.Chat;

import org.bukkit.ChatColor;

public class ChatUtil {
	
	public static String prefix = ChatUtil.cc("&8[&7Essentials Remake&8] ");
	public static String prefix_c = ChatUtil.cc("&8[&7Essentials Remake&8]");
	
	public static String cc(String msg) {
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
}
