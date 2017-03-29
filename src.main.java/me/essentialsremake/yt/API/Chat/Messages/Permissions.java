package me.essentialsremake.yt.API.Chat.Messages;

public class Permissions {
	public enum PermType {
		HEAL, HEAL_OTHERS,
		FEED, FEED_OTHERS,
		FLY, FLY_OTHERS,
		GOD, GOD_OTHERS,
		FREEZE, FREEZE_OTHERS,
		HAT;
	}
	public static String getPerm(PermType type) {
		String perm = "";
		if (type == PermType.HEAL)
			perm = "essentialsremake.heal";
		else if (type == PermType.HEAL_OTHERS)
			perm = "essentialsremake.heal.others";
		else if (type == PermType.FEED)
			perm = "essentialsremake.feed";
		else if (type == PermType.FEED_OTHERS)
			perm = "essentialsremake.feed.others";
		else if (type == PermType.FLY)
			perm = "essentialsremake.fly";
		else if (type == PermType.FLY_OTHERS)
			perm = "essentialsremake.fly.others";
		else if (type == PermType.GOD)
			perm = "essentialsremake.god";
		else if (type == PermType.GOD_OTHERS)
			perm = "essentialsremake.god.others";
		else if (type == PermType.FREEZE)
			perm = "essentialsremake.freeze";
		else if (type == PermType.FREEZE_OTHERS)
			perm = "essentialsremake.freeze.others";
		else if (type == PermType.HAT)
			perm = "essentialsremake.hat";
		return perm;
	}
}
