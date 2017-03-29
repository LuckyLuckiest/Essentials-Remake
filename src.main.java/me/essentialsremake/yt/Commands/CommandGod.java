package me.essentialsremake.yt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import me.essentialsremake.yt.Main;
import me.essentialsremake.yt.API.API;
import me.essentialsremake.yt.API.Chat.ChatUtil;
import me.essentialsremake.yt.API.Chat.Messages.Errors;
import me.essentialsremake.yt.API.Chat.Messages.Errors.ErrorType;
import me.essentialsremake.yt.API.Chat.Messages.Permissions;
import me.essentialsremake.yt.API.Chat.Messages.Permissions.PermType;

public class CommandGod implements CommandExecutor, Listener {
	
	@SuppressWarnings("unused")
	private Main core;
	public CommandGod(Main pl) {
		core = pl;
	}

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			Location loc = p.getLocation();
			if (cmd.getName().equalsIgnoreCase("god") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.GOD))) {
					if (API.godMode.contains(p.getName())) {
						API.godMode.remove(p.getName());
						if (p.getAllowFlight() == true) {
							p.setAllowFlight(false);
						}
						p.playSound(loc, Sound.ENTITY_BAT_DEATH, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are no longer in &cGod Mode&7!"));
						return true;
					} else {
						API.godMode.add(p.getName());
						if (p.getAllowFlight() == false) {
							p.setAllowFlight(true);
						}
						p.playSound(loc, Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are now in &aGod Mode&7!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (p.hasPermission(Permissions.getPerm(PermType.GOD_OTHERS))) {
					if (API.godMode.contains(target.getDisplayName())) {
						API.godMode.remove(target.getDisplayName());
						if (p.getAllowFlight() == true) {
							p.setAllowFlight(false);
						}
						p.playSound(loc, Sound.ENTITY_BAT_DEATH, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + " &7is no longer in &cGod Mode&7!"));
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are no longer in &cGod Mode&7!"));
						return true;
					} else {
						API.godMode.add(target.getDisplayName());
						if (p.getAllowFlight() == false) {
							p.setAllowFlight(true);
						}
						p.playSound(loc, Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + " &7is now in &aGod Mode&7!"));
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are now in &aGod Mode&7!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
		} else
			cs.sendMessage(Errors.getError(ErrorType.NOT_PLAYER));
		return false;
	}
	
	@EventHandler
	public void onPlayerDamage(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if (API.godMode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
