package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import me.mysticat.enchantments.logic.EnchantLogic;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDamageByEntityListener implements Listener {

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player damager = (Player) event.getDamager();
        LivingEntity victim = (LivingEntity) event.getEntity();
        ItemMeta damagerItemMeta = damager.getInventory().getItemInMainHand().getItemMeta();
        if (damagerItemMeta.hasEnchant(CustomEnchantment.LEECH)) {
            double enchantmentLevel = damagerItemMeta.getEnchantLevel(CustomEnchantment.LEECH);
            double amountToHeal = (event.getDamage() / 40) + (enchantmentLevel / 2);
            damager.setHealth(damager.getHealth() + amountToHeal);
        }
        else if (damagerItemMeta.hasEnchant(CustomEnchantment.TOXIC)) {
            victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, EnchantLogic.getEnchantmentLevelByName(damager, "toxic")));
        }
        else if (damagerItemMeta.hasEnchant(CustomEnchantment.FREEZE)) {
            victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, EnchantLogic.getEnchantmentLevelByName(damager, "freeze")));
        }
    }
}
