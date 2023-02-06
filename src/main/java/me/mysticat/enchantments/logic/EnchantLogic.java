package me.mysticat.enchantments.logic;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Location;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EnchantLogic {

    /**
     * Apply enchantment to player's held item
     * @param player Player to get item from
     * @param enchantment Enchantment to apply
     * @param level Level of enchantment
     */
    public static void enchantHeldItem(Player player, Enchantment enchantment, int level) {
        ItemStack item = player.getInventory().getItemInMainHand();
        item.addUnsafeEnchantment(enchantment, level);
        ItemMeta meta = item.getItemMeta();
        List<Component> newLore = new ArrayList<>();
        Component displayNameComponent = enchantment.displayName(level);
        newLore.add(displayNameComponent
                .color(NamedTextColor.GRAY)
                .decoration(TextDecoration.ITALIC, false)
        );
        if (meta.hasLore()) {
            newLore.addAll(Objects.requireNonNull(meta.lore()));
        }
        meta.lore(newLore);
        item.setItemMeta(meta);
    }

    /**
     * Get a custom enchantment by its name
     * @param name Enchantment to find
     * @return Enchantment
     */
    @SuppressWarnings({"deprication", "deprecation"})
    public static Enchantment getEnchantmentByName(String name) {
        for (Enchantment enchantment : CustomEnchantment.customEnchantmentList) {
            if (enchantment.getName().equalsIgnoreCase(name)) {
                return enchantment;
            }
        }
        return null;
    }

    /**
     * Get custom enchantment's level by its name
     * @param player Player to get item from
     * @param name Enchantment to find
     * @return Enchantment Level
     */
    public static int getEnchantmentLevelByName(Player player, String name) {
        return player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(Objects.requireNonNull(getEnchantmentByName(name)));
    }

    /**
     * Apply enchantment to player's held item
     * @param player Player to get item from
     * @param name Enchantment name
     * @param level Level of enchantment
     * @return If Enchantment applied successfully
     */
    public static boolean applyEnchantment(Player player, String name, int level) {
        Enchantment enchantment = getEnchantmentByName(name);
        if (enchantment != null && level > 0 && level <= enchantment.getMaxLevel()) {
            enchantHeldItem(player, enchantment, level);
            return true;
        }
        return false;
    }

    /**
     * Add arrow to tracking list
     * @param enchantment Enchantment used
     * @param itemMeta ItemMeta to check
     * @param projectile Arrow to add to list
     */
    public static void addArrowToList(Enchantment enchantment, ItemMeta itemMeta, Entity projectile) {
        HashMap<Enchantment, Integer> innerMap = new HashMap<>();
        innerMap.put(enchantment, itemMeta.getEnchantLevel(enchantment));
        CustomEnchantment.enchantedArrowsList.put(projectile, innerMap);
    }

    /**
     * Apply force on one entity in the direction of another entity
     * @param victimEntity Entity to push
     * @param targetEntity Entity to push towards
     * @param strength Strength of push
     */
    public static void pushEntityTowardsTargetEntity(Entity victimEntity, Entity targetEntity, double strength) {
        Location victimLocation = victimEntity.getLocation();
        Vector victimVector = victimLocation.toVector();
        Vector vectorFromVictimToTarget = targetEntity.getLocation().toVector().subtract(victimVector);

        vectorFromVictimToTarget.normalize();
        vectorFromVictimToTarget.multiply(strength);
        victimEntity.setVelocity(vectorFromVictimToTarget);
    }

    /**
     * Get the nearest living entity
     * @param sourceEntity Entity to center location on
     * @param range Distance to check from source entity
     * @return LivingEntity
     */
    public static LivingEntity getNearestLivingEntity(Entity sourceEntity, double range) {
        List<Entity> nearbyEntities = sourceEntity.getNearbyEntities(range, range, range);
        double lowestDistanceSoFar = Double.MAX_VALUE;
        LivingEntity closestEntity = null;
        for (Entity entity : nearbyEntities) {
            double distance = entity.getLocation().distance(sourceEntity.getLocation());
            if (distance < lowestDistanceSoFar && entity instanceof LivingEntity) {
                lowestDistanceSoFar = distance;
                closestEntity = (LivingEntity)entity;
            }
        }
        return closestEntity;
    }

    /**
     * Replace an arrow with a different entity but retain its momentum
     * @param arrow Arrow to replace
     * @param entity Entity to replace with
     */
    public static void replaceArrowWithEntity(Arrow arrow, Entity entity) {
        Vector arrowVector = arrow.getVelocity();
        entity.setVelocity(arrowVector);
        arrow.remove();
    }

}
