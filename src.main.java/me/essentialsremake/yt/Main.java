package me.essentialsremake.yt;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.essentialsremake.yt.API.Chat.ChatUtil;
import me.essentialsremake.yt.Commands.CommandFeed;
import me.essentialsremake.yt.Commands.CommandFly;
import me.essentialsremake.yt.Commands.CommandFreeze;
import me.essentialsremake.yt.Commands.CommandGod;
import me.essentialsremake.yt.Commands.CommandHat;
import me.essentialsremake.yt.Commands.CommandHeal;

public class Main extends JavaPlugin {
	
	ConsoleCommandSender ccs = Bukkit.getConsoleSender();
	PluginDescriptionFile pdf = getDescription(); 
	
	public void onEnable() {
		registerCommands();
		registerEvents();
		ccs.sendMessage(ChatUtil.prefix_c);
		ccs.sendMessage(ChatUtil.cc("Enabling " + pdf.getName()));
		ccs.sendMessage(ChatUtil.cc("Version: " + pdf.getVersion()));
		ccs.sendMessage(ChatUtil.cc("Author: " + pdf.getAuthors()));
	}
	public void onDisable() {
		ccs.sendMessage(ChatUtil.prefix_c);
		ccs.sendMessage(ChatUtil.cc("Disabling " + pdf.getName()));
		ccs.sendMessage(ChatUtil.cc("Version: " + pdf.getVersion()));
		ccs.sendMessage(ChatUtil.cc("Author: " + pdf.getAuthors()));
	}
	private void registerCommands() {
		getCommand("heal").setExecutor(new CommandHeal());
		getCommand("feed").setExecutor(new CommandFeed());
		getCommand("fly").setExecutor(new CommandFly());
		getCommand("god").setExecutor(new CommandGod(this));
		getCommand("freeze").setExecutor(new CommandFreeze(this));
		getCommand("hat").setExecutor(new CommandHat());
	}
	private void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new CommandGod(this), this);
		pm.registerEvents(new CommandFreeze(this), this);
	}
}
