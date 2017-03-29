package me.essentialsremake.yt.Commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import me.essentialsremake.yt.API.Chat.ChatUtil;
import me.essentialsremake.yt.API.Chat.Messages.Errors;
import me.essentialsremake.yt.API.Chat.Messages.Errors.ErrorType;
import me.essentialsremake.yt.API.Chat.Messages.Permissions;
import me.essentialsremake.yt.API.Chat.Messages.Permissions.PermType;

public class CommandHat implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (cs instanceof Player) {
			Player p = (Player) cs;
			PlayerInventory inv = p.getInventory();
			Location loc = p.getLocation();
			ItemStack helmetItem = inv.getHelmet();
			ItemStack handItem = p.getItemInHand();
			if (cmd.getName().equalsIgnoreCase("hat") && args.length == 0) {
				if (p.hasPermission(Permissions.getPerm(PermType.HAT))) {
					if (handItem != null && handItem.getType() != Material.AIR) {
						p.playSound(loc, Sound.ITEM_ARMOR_EQUIP_LEATHER, 1, 1);
						
						String item = handItem.getType().toString().toLowerCase().replace("_", " ");
						
						p.setItemInHand(helmetItem);
						inv.setHelmet(handItem);
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You have &asuccessfully &7wore &b" + item + " &7Hat!"));
						return true;
					} else {
						p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7There is no selected &8hat &7to be worn, please select one!"));
						return true;
					}
				} else
					p.sendMessage(Errors.getError(ErrorType.NO_PERMISSION));
			}
			if (args[0].equalsIgnoreCase("remove") && args.length == 1) {
				if (inv.firstEmpty() != -1) {
					inv.setHeldItemSlot(inv.firstEmpty());
					p.setItemInHand(helmetItem);
					inv.setHelmet(null);
					p.playSound(loc, Sound.ENTITY_ITEMFRAME_ROTATE_ITEM, 1, 1);
					p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7You &cretrieved &7your hat!"));
					return true;
				} else {
					p.sendMessage(ChatUtil.prefix + ChatUtil.cc("&7Please &cclear &7a slot to return your &8hat&7!"));
					return true;
				}
			}
		} else
			cs.sendMessage(Errors.getError(ErrorType.NOT_PLAYER));
		return false;
	}
}
