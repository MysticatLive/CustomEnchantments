package me.mysticat.enchantments.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class CustomEnchantment {

    //List of all enchantments
    public static final Enchantment TELEKINESIS = new EnchantmentWrapper("Telekinesis", 1);
    public static final Enchantment HOMING = new EnchantmentWrapper("Homing", 1);
    public static final Enchantment POLYMORPH = new EnchantmentWrapper("Polymorph", 5);
    public static final Enchantment HELICOPTER  = new EnchantmentWrapper("Helicopter", 5);
    public static final Enchantment BECKON = new EnchantmentWrapper("Beckon", 3);
    public static final Enchantment TOXIC = new EnchantmentWrapper("Toxic", 3);
    public static final Enchantment LEECH = new EnchantmentWrapper("Leech", 3);
    public static final Enchantment FREEZE = new EnchantmentWrapper("Freeze", 3);
    public static final Enchantment SOARING = new EnchantmentWrapper("Soaring", 5);
    public static final Enchantment VORTEX = new EnchantmentWrapper("Vortex", 1000);
    public static final Enchantment FELLER = new EnchantmentWrapper("Feller", 5);
    public static final Enchantment CLUSTER = new EnchantmentWrapper("Cluster", 3);

    //Enchantment variables
    public static final List<Enchantment> customEnchantmentList = new ArrayList<>();
    public static final Map<Entity, Map<Enchantment, Integer>> enchantedArrowsList = new HashMap<>();

    /**
     * register new Enchantment if unregistered
     * @param enchantment Enchantment to register
     */
    public static void register(Enchantment enchantment) {
        if (!Arrays.stream(Enchantment.values()).collect(Collectors.toList()).contains(enchantment)) {
            registerEnchantment(enchantment);
        }
    }

    /**
     * register new Enchantment into Minecraft
     * @param enchantment Enchantment to register
     */
    private static void registerEnchantment(Enchantment enchantment) {
        try {
            Field field = Enchantment.class.getDeclaredField("acceptingNew");
            field.setAccessible(true);
            field.set(null, true);
            Enchantment.registerEnchantment(enchantment);
            customEnchantmentList.add(enchantment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
