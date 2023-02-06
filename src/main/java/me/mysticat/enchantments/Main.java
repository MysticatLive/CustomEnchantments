package me.mysticat.enchantments;

import me.mysticat.enchantments.commands.EnchantCommand;
import me.mysticat.enchantments.enchantments.CustomEnchantment;
import me.mysticat.enchantments.listeners.*;
import me.mysticat.enchantments.tab.EnchantTab;
import org.bukkit.Material;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Main extends JavaPlugin {

    public static Main plugin;
    public static List<Material> logMaterials = Arrays.asList(Material.OAK_LOG, Material.SPRUCE_LOG, Material.DARK_OAK_LOG, Material.BIRCH_LOG, Material.ACACIA_LOG, Material.JUNGLE_LOG);
    public static List<Material> oreMaterials = Arrays.asList(Material.COAL_ORE, Material.IRON_ORE, Material.DIAMOND_ORE, Material.REDSTONE_ORE, Material.EMERALD_ORE, Material.COPPER_ORE, Material.GOLD_ORE, Material.LAPIS_ORE, Material.NETHER_GOLD_ORE, Material.NETHER_QUARTZ_ORE, Material.DEEPSLATE_COPPER_ORE, Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_COAL_ORE, Material.DEEPSLATE_DIAMOND_ORE, Material.DEEPSLATE_EMERALD_ORE, Material.DEEPSLATE_LAPIS_ORE, Material.DEEPSLATE_GOLD_ORE);
    public static List<Material> swordMaterials = Arrays.asList(Material.WOODEN_SWORD, Material.STONE_SWORD, Material.GOLDEN_SWORD, Material.IRON_SWORD, Material.DIAMOND_SWORD, Material.NETHERITE_SWORD);
    public static List<Material> pickAxeMaterials = Arrays.asList(Material.WOODEN_PICKAXE, Material.STONE_PICKAXE, Material.GOLDEN_PICKAXE, Material.IRON_PICKAXE, Material.DIAMOND_PICKAXE, Material.NETHERITE_PICKAXE);
    public static List<Material> axeMaterials = Arrays.asList(Material.WOODEN_AXE, Material.STONE_AXE, Material.GOLDEN_AXE, Material.IRON_AXE, Material.DIAMOND_AXE, Material.NETHERITE_AXE);
    public static List<Material> bowMaterials = Collections.singletonList(Material.BOW);

    @Override
    public void onEnable() {
        init();
        plugin = this;
    }

    @Override
    public void onDisable() {
    }

    private void init() {
        initListeners();
        initCommands();
        initEnchants();
        getLogger().info("Custom Enchants Loaded!");
    }

    private void initListeners() {
        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new BlockBreakListener(), this);
        pluginManager.registerEvents(new PlayerInteractListener(), this);
        pluginManager.registerEvents(new EntityDamageByEntityListener(), this);
        pluginManager.registerEvents(new PlayerFallListener(), this);
        pluginManager.registerEvents(new ProjectileHitListener(), this);
        pluginManager.registerEvents(new EntityShootBowListener(), this);
    }

    private void initCommands() {
        Objects.requireNonNull(getCommand("customenchant")).setExecutor(new EnchantCommand());
        Objects.requireNonNull(getCommand("customenchant")).setTabCompleter(new EnchantTab());
    }

    private void initEnchants() {
        CustomEnchantment.register(CustomEnchantment.TELEKINESIS);
        CustomEnchantment.register(CustomEnchantment.HELICOPTER);
        CustomEnchantment.register(CustomEnchantment.LEECH);
        CustomEnchantment.register(CustomEnchantment.TOXIC);
        CustomEnchantment.register(CustomEnchantment.FREEZE);
        CustomEnchantment.register(CustomEnchantment.BECKON);
        CustomEnchantment.register(CustomEnchantment.SOARING);
        CustomEnchantment.register(CustomEnchantment.HOMING);
        CustomEnchantment.register(CustomEnchantment.FELLER);
        CustomEnchantment.register(CustomEnchantment.CLUSTER);
        CustomEnchantment.register(CustomEnchantment.VORTEX);
        CustomEnchantment.register(CustomEnchantment.POLYMORPH);
    }

    public static Main getPlugin() {
        return plugin;
    }
}
