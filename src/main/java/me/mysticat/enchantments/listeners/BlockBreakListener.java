package me.mysticat.enchantments.listeners;

import me.mysticat.enchantments.Main;
import me.mysticat.enchantments.enchantments.CustomEnchantment;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BlockBreakListener implements Listener {

    private static int blocksBroken = 0;
    private static final int MAX_BLOCKS_BREAKABLE = 100;
    private static final List<Location> blocksAlreadyExplored = new ArrayList<>();

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        ItemStack playerItem = player.getInventory().getItemInMainHand();
        if (playerItem.getItemMeta().hasEnchant(CustomEnchantment.TELEKINESIS)) {
            if (player.getInventory().firstEmpty() == -1
                || block.getState() instanceof Container) {
                return;
            }
            event.setDropItems(false);
            Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
            for (ItemStack item : drops) {
                player.getInventory().addItem(item);
            }
        }
        else if (playerItem.getItemMeta().hasEnchant(CustomEnchantment.FELLER)) {
            int enchantmentLevel = playerItem.getEnchantmentLevel(CustomEnchantment.FELLER);
            blocksBroken = 0;
            breakBlocksSequentially(Main.logMaterials, block, enchantmentLevel);
            blocksAlreadyExplored.clear();
        }
        else if (playerItem.getItemMeta().hasEnchant(CustomEnchantment.CLUSTER)) {
            int enchantmentLevel = playerItem.getEnchantmentLevel(CustomEnchantment.CLUSTER);
            blocksBroken = 0;
            breakBlocksSequentially(Main.oreMaterials, block, enchantmentLevel);
            blocksAlreadyExplored.clear();
        }
    }

    private static void breakBlocksSequentially(List<Material> materialList, Block currentBlock, int enchantmentLevel) {
        Material blockType = currentBlock.getType();
        Location currentBlockLocation = currentBlock.getLocation();
        if (!materialList.contains(blockType) || blocksAlreadyExplored.contains(currentBlockLocation) || !(blocksBroken < (MAX_BLOCKS_BREAKABLE * enchantmentLevel))) {
            return;
        }
        Bukkit.getScheduler().runTaskLater(Main.getPlugin(), () -> {
            currentBlock.breakNaturally();
            blocksAlreadyExplored.add(currentBlockLocation);
            blocksBroken++;
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.UP, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.NORTH, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.EAST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.SOUTH, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.WEST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.NORTH_EAST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.SOUTH_EAST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.NORTH_WEST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.SOUTH_WEST, 1), enchantmentLevel);
            breakBlocksSequentially(materialList, currentBlock.getRelative(BlockFace.DOWN, 1), enchantmentLevel);
        }, 1L);
    }

}
