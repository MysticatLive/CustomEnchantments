package me.mysticat.enchantments.enchantments;

import io.papermc.paper.enchantments.EnchantmentRarity;
import net.kyori.adventure.text.Component;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.EntityCategory;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

//Wrapper for making custom enchantments
public class EnchantmentWrapper extends Enchantment {

    private final String name;
    private final int maxLevel;

    public EnchantmentWrapper(String name, int maxLevel) {
        super(NamespacedKey.minecraft(name.toLowerCase()));
        this.name = name.toLowerCase();
        this.maxLevel = maxLevel;
    }

    @Override
    public @NotNull String getName() {
        return name;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public int getStartLevel() {
        return 0;
    }

    @Override
    public @NotNull EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(@NotNull Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(@NotNull ItemStack item) {
        return false;
    }

    @Override
    public @NotNull Component displayName(int level) {
        String displayName = name.substring(0,1).toUpperCase() + name.substring(1);
        if (level == 1) displayName += " I";
        else if (level == 2) displayName += " II";
        else if (level == 3) displayName += " III";
        else if (level == 4) displayName += " IV";
        else if (level == 5) displayName += " V";
        else displayName += " ?";
        return Component.text(displayName);
    }

    @Override
    public boolean isTradeable() {
        return false;
    }

    @Override
    public boolean isDiscoverable() {
        return false;
    }

    @Override
    public @NotNull EnchantmentRarity getRarity() {
        return null;
    }

    @Override
    public float getDamageIncrease(int level, @NotNull EntityCategory entityCategory) {
        return 0;
    }

    @Override
    public @NotNull Set<EquipmentSlot> getActiveSlots() {
        return null;
    }

    @Override
    public @NotNull String translationKey() {
        return null;
    }
}
