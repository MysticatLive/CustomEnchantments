package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerFallListener implements Listener {

    @EventHandler
    public void onPlayerFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(CustomEnchantment.HELICOPTER)) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
