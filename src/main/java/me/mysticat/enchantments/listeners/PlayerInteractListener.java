package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.enchantments.CustomEnchantment;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;

public class PlayerInteractListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemMeta playerItemMeta = player.getInventory().getItemInMainHand().getItemMeta();
        if (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (
                    (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK)
                    && playerItemMeta != null
                    && playerItemMeta.hasEnchant(CustomEnchantment.HELICOPTER)
            ) {
                helicopterPlayer(player, 10 * playerItemMeta.getEnchantLevel(CustomEnchantment.HELICOPTER));
            }
        }
    }

    private void helicopterPlayer (Player player, float amount) {
        Location location = player.getLocation();
        location.setYaw(location.getYaw() + amount);
        location.setY(location.getY() + 0.4);
        player.teleport(location);
    }
}
