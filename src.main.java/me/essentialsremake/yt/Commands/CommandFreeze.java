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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import me.essentialsremake.yt.Main;
import me.essentialsremake.yt.API.API;
import me.essentialsremake.yt.API.Chat.ChatUtil;
import me.essentialsremake.yt.API.Chat.Messages.Errors;
import me.essentialsremake.yt.API.Chat.Messages.Errors.ErrorType;
import me.essentialsremake.yt.API.Chat.Messages.Permissions;
import me.essentialsremake.yt.API.Chat.Messages.Permissions.PermType;

public class CommandFreeze implements CommandExecutor, Listener {

	@SuppressWarnings("unused")
	private Main core;
	public CommandFreeze(Main pl) {
		core = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			Location loc = p.getLocation();
			if (cmd.getName().equalsIgnoreCase("freeze") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.FREEZE))) {
					if (API.freezeMode.contains(p.getName())) {
						API.freezeMode.remove(p.getName());
						p.playSound(loc, Sound.ENTITY_BAT_DEATH, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are no longer in &cFreeze Mode&7!"));
						return true;
					} else {
						API.freezeMode.add(p.getName());
						p.playSound(loc, Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are now in &aFreeze Mode&7!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (p.hasPermission(Permissions.getPerm(PermType.FREEZE_OTHERS))) {
					if (API.freezeMode.contains(target.getDisplayName())) {
						API.freezeMode.remove(target.getDisplayName());
						p.playSound(loc, Sound.ENTITY_BAT_DEATH, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + " &7is no longer in &cFreeze Mode&7!"));
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are no longer in &cFreeze Mode&7!"));
						return true;
					} else {
						API.freezeMode.add(target.getDisplayName());
						p.playSound(loc, Sound.ENTITY_LIGHTNING_THUNDER, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + " &7is now in &aFreeze Mode&7!"));
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You are now in &aFreeze Mode&7!"));
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
	public void onPlayerMove(PlayerMoveEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			if (API.freezeMode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerHit(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player p = (Player) e.getDamager();
			if (API.freezeMode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			if (API.freezeMode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getPlayer() instanceof Player) {
			Player p = e.getPlayer();
			if (API.freezeMode.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
	// You know any event that could be used ? Write it in the comments down below!
}
