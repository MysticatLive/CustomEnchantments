package me.mysticat.enchantments.tasks;

import me.mysticat.enchantments.logic.EnchantLogic;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

//Apply constant suction to an arrow
public class VortexTask extends BukkitRunnable {

    private static final int SUCTION_RANGE = 10;
    Arrow arrow;
    int enchantmentLevel;

    public VortexTask(Arrow arrow, int enchantmentLevel, Plugin plugin) {
        this.arrow = arrow;
        this.enchantmentLevel = enchantmentLevel;
        runTaskTimer(plugin, 1L, 1L);
    }

    @Override
    public void run() {
        if (arrow.isOnGround() || arrow.isDead()) {
            cancel();
            return;
        }
        for (Entity entity : arrow.getNearbyEntities(SUCTION_RANGE, SUCTION_RANGE, SUCTION_RANGE)) {
            if (entity instanceof LivingEntity && entity != arrow.getShooter()) {
                EnchantLogic.pushEntityTowardsTargetEntity(entity, arrow, (0.5 * enchantmentLevel));
            }
        }
    }
}
