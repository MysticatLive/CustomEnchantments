package me.mysticat.enchantments.permissions;

import org.bukkit.entity.Player;

public class EnchantPerms {

    private static final String permPrefix = GeneralPermissions.permRoot + ".customenchants";

    /**
     * Permission to use the /customenchant command
     * @param player Player to get item from
     * @return If player has permission
     */
    public static boolean permissionToEnchant(Player player) {
        return GeneralPermissions.fullPerms(player)
                || player.hasPermission(permPrefix)
                || player.hasPermission(permPrefix + ".enchant");
    }
}
