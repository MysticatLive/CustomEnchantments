package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.Map;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getHitEntity() instanceof LivingEntity)) {
            return;
        }
        Projectile projectile = event.getEntity();
        if (CustomEnchantment.enchantedArrowsList.containsKey(projectile)) {
            Map<Enchantment, Integer> innerMap = CustomEnchantment.enchantedArrowsList.get(projectile);
            if (innerMap.containsKey(CustomEnchantment.BECKON)) {
                int level = innerMap.get(CustomEnchantment.BECKON);
                LivingEntity entity = (LivingEntity) event.getHitEntity();
                entity.setVelocity(projectile.getVelocity().multiply(-level));
            }
            CustomEnchantment.enchantedArrowsList.remove(projectile);
        }

    }
}
