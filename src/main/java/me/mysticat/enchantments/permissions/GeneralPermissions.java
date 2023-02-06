package me.mysticat.enchantments.permissions;

import org.bukkit.entity.Player;

public class GeneralPermissions {

    public static String permRoot = "customenchants";

    /**
     * Permission to use any command
     * @param player Player to get item from
     * @return If player has permission to use all commands
     */
    public static boolean fullPerms(Player player) { return player.hasPermission(permRoot + ".*"); }
}
