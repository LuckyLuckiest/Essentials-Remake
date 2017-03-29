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

public class CommandFeed implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			Location loc = p.getLocation();
			if (cmd.getName().equalsIgnoreCase("feed") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.FEED))) {
					p.playSound(loc, Sound.ENTITY_GENERIC_EAT, 1, 1);
					p.setFoodLevel(20);
					p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Your &ahunger &7had been fully restored!"));
					return true;
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args.length == 1) {
				Player target = Bukkit.getPlayer(args[0]);
				if (p.hasPermission(Permissions.getPerm(PermType.FEED_OTHERS))) {
					if (target == null) {
						p.sendMessage(Errors.getError(ErrorType.NOT_ONLINE));
					} else {
						p.playSound(loc, Sound.ENTITY_GENERIC_EAT, 1, 1);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&6" + target.getDisplayName() + "'s &aHunger &7had been fully restored!"));
						target.setFoodLevel(20);
						target.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Your &ahunger &7had been fully restored!"));
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
