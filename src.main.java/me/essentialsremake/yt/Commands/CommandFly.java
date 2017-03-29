package me.essentialsremake.yt.Commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.essentialsremake.yt.API.Chat.ChatUtil;
import me.essentialsremake.yt.API.Chat.Messages.Errors;
import me.essentialsremake.yt.API.Chat.Messages.Permissions;
import me.essentialsremake.yt.API.Chat.Messages.Errors.ErrorType;
import me.essentialsremake.yt.API.Chat.Messages.Permissions.PermType;

public class CommandFly implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			Location loc = p.getLocation();
			if (cmd.getName().equalsIgnoreCase("fly") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.FLY))) {
					if (p.getAllowFlight() == false) {
						p.setAllowFlight(true);
						p.playSound(loc, Sound.ITEM_ELYTRA_FLYING, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Fly is now &aEnabled&7!"));
						return true;
					} else {
						p.setAllowFlight(false);
						p.playSound(loc, Sound.BLOCK_ANVIL_FALL, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Fly is now &cDisabled&7!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (p.hasPermission(Permissions.getPerm(PermType.FLY_OTHERS))) {
					if (target == null) {
						p.sendMessage(Errors.getError(ErrorType.NOT_ONLINE));
					} else {
						if (target.getAllowFlight() == false) {
							p.playSound(loc, Sound.ITEM_ELYTRA_FLYING, 1, 1);
							p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + "'s &7Fly is now &aEnabled&7!"));
							target.setAllowFlight(true);
							target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Fly is now &aEnabled&7!"));
							return true;
						} else {
							target.setAllowFlight(false);
							p.playSound(loc, Sound.BLOCK_ANVIL_FALL, 1, 1);
							p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + "'s &7Fly is now &cDisabled&7!"));
							target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Fly is now &cDisabled&7!"));
							return true;
						}
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
		} else
			cs.sendMessage(Errors.getError(ErrorType.NOT_PLAYER));
		return false;
	}
}
