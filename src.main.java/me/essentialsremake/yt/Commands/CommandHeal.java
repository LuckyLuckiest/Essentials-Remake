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

public class CommandHeal implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			Location loc = p.getLocation();
			if (cmd.getName().equalsIgnoreCase("heal") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.HEAL))) {
					p.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
					p.setHealth(20.0);
					p.setFireTicks(0);
					p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Your &ahealth &7had been fully regenerated!"));
					return true;
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (p.hasPermission(Permissions.getPerm(PermType.HEAL_OTHERS))) {
					if (target == null) {
						p.sendMessage(Errors.getError(ErrorType.NOT_ONLINE));
					} else {
						p.playSound(loc, Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + "'s &aHealth &7had been fully regenrated!"));
						target.setHealth(20.0);
						target.setFireTicks(0);
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Your &ahealth &7had been fully regenerated!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
		} else
			cs.sendMessage(Errors.getError(ErrorType.NOT_PLAYER));
		return false;
	}
}
