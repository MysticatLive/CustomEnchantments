package me.mysticat.enchantments.tasks;

import me.mysticat.enchantments.logic.EnchantLogic;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

//Track arrow to nearest entity
public class HomingTask extends BukkitRunnable {

    Arrow arrow;

    public HomingTask(Arrow arrow, Plugin plugin) {
        this.arrow = arrow;
        runTaskTimer(plugin, 1L, 1L);
    }

    @Override
    public void run() {
        if (arrow.isOnGround() || arrow.isDead()) {
            cancel();
            return;
        }
        LivingEntity target = EnchantLogic.getNearestLivingEntity(arrow, 10);
        if (target != null && target != arrow.getShooter()) {
            EnchantLogic.pushEntityTowardsTargetEntity(arrow, target, 2);
        }
    }
}
