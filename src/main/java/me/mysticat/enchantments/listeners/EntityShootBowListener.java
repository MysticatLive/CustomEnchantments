package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.Main;
import me.mysticat.enchantments.enchantments.CustomEnchantment;
import me.mysticat.enchantments.logic.EnchantLogic;
import me.mysticat.enchantments.tasks.HomingTask;
import me.mysticat.enchantments.tasks.VortexTask;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class EntityShootBowListener implements Listener {

    @EventHandler
    public void onEntityShootBow(EntityShootBowEvent event) {
        int enchantmentLevel;
        ItemMeta shooterItemMeta = Objects.requireNonNull(event.getBow()).getItemMeta();
        Entity projectile = event.getProjectile();
        if (shooterItemMeta.hasEnchant(CustomEnchantment.POLYMORPH)) {
            enchantmentLevel = shooterItemMeta.getEnchantLevel(CustomEnchantment.POLYMORPH);
            polymorphLogic(projectile, enchantmentLevel);
        }
        if (shooterItemMeta.hasEnchant(CustomEnchantment.BECKON)) {
            EnchantLogic.addArrowToList(CustomEnchantment.BECKON, shooterItemMeta, projectile);
        }
        if (shooterItemMeta.hasEnchant(CustomEnchantment.SOARING)) {
            projectile.setGravity(false);
            projectile.setVelocity(projectile.getVelocity().multiply(shooterItemMeta.getEnchantLevel(CustomEnchantment.SOARING)));
        }
        if (shooterItemMeta.hasEnchant(CustomEnchantment.VORTEX)) {
            enchantmentLevel = shooterItemMeta.getEnchantLevel(CustomEnchantment.VORTEX);
            new VortexTask((Arrow)projectile, enchantmentLevel, Main.getPlugin());
        }
        if (shooterItemMeta.hasEnchant(CustomEnchantment.HOMING)) {
            event.getEntity();
            new HomingTask((Arrow)projectile, Main.getPlugin());
        }
    }

    /**
     * Handle which entity is chosen in the polymorph enchantment
     * @param projectile Arrow to replace
     * @param enchantmentLevel Level of polymorph enchantment
     */
    @SuppressWarnings("deprecation")
    private void polymorphLogic(Entity projectile, int enchantmentLevel) {
        if (projectile instanceof Arrow) {
            Location location = projectile.getLocation();
            World world = projectile.getWorld();
            Entity entity = null;
            if (enchantmentLevel == 1) {
                FallingBlock fallingBlock = world.spawnFallingBlock(location, Material.COBBLESTONE, (byte) 0);
                fallingBlock.setHurtEntities(true);
                entity = fallingBlock;
            }
            else if (enchantmentLevel == 2) {
                PufferFish pufferFish = (PufferFish) world.spawnEntity(location, EntityType.PUFFERFISH);
                pufferFish.setPuffState(1);
                entity = pufferFish;
            }
            else if (enchantmentLevel == 3) {
                entity = world.spawnEntity(location, EntityType.SHULKER_BULLET);
            }
            else if (enchantmentLevel == 4) {
                entity = world.spawnEntity(location, EntityType.RAVAGER);
            }
            else if (enchantmentLevel == 5) {
                entity = world.spawnEntity(location, EntityType.PRIMED_TNT);
            }
            assert entity != null;
            EnchantLogic.replaceArrowWithEntity((Arrow) projectile, entity);
        }
    }
}
