package me.mysticat.enchantments.commands;

import me.mysticat.enchantments.logic.EnchantLogic;
import me.mysticat.enchantments.permissions.EnchantPerms;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

//Command to apply custom enchantments to player's held item
public class EnchantCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("onlyPlayers");
        }
        if (args.length > 2) {
            Player player = Bukkit.getPlayer(args[0]);
            int level = Integer.parseInt(args[2]);
            if (player == null || !EnchantPerms.permissionToEnchant(player)) {
                return false;
            }
            return EnchantLogic.applyEnchantment(player, args[1], level);
        }
        return false;
    }
}