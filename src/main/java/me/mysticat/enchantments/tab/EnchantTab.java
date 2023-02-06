package me.mysticat.enchantments.tab;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import me.mysticat.enchantments.logic.EnchantLogic;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnchantTab implements TabCompleter, Listener {


    @SuppressWarnings("deprecation")
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> tab = new ArrayList<>();
        if (command.getName().equalsIgnoreCase("customenchant")) {
            if (args.length == 1) {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    tab.add(online.getName());
                }
            }
            else if (args.length == 2) {
                for (Enchantment enchantment : CustomEnchantment.customEnchantmentList) {
                    String name = enchantment.getName();
                    String displayName = name.substring(0,1).toUpperCase() + name.substring(1);
                    tab.add(displayName);
                }
            }
            else if (args.length == 3) {
                Enchantment enchantment = EnchantLogic.getEnchantmentByName(args[1]);
                if (enchantment != null) {
                    int maxLevel = enchantment.getMaxLevel();
                    for (int i = 1; i < maxLevel + 1; i++) {
                        tab.add("" + i);
                    }
                }
            }
        }
        Collections.sort(tab);
        return tab;
    }
}
