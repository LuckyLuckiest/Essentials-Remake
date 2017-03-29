package me.essentialsremake.yt.API.Chat.Messages;

import me.essentialsremake.yt.API.Chat.ChatUtil;

public class Errors {
	public enum ErrorType {
		NOT_PLAYER, NOT_ONLINE,NO_PERMISSION;
	}
	public static String getError(ErrorType type) {
		String error = "";
		if (type == ErrorType.NOT_PLAYER)
			error = ChatUtil.prefix + ChatUtil.cc("&cYou need to be player to use this!");
		else if (type == ErrorType.NOT_ONLINE)
			error = ChatUtil.prefix + ChatUtil.cc("&7This player is not &conline&7!");
		else if (type == ErrorType.NO_PERMISSION)
			error = ChatUtil.prefix + ChatUtil.cc("&cYou don't have permission to use this!");
		return error;
	}
}
