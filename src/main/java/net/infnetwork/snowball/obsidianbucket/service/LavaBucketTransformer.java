package net.infnetwork.snowball.obsidianbucket.service;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class LavaBucketTransformer {
    public void transform(Player player, Block block, boolean fillEmptyBucket) {
        if (fillEmptyBucket) {
            BlockData obsidianData = block.getBlockData();
            block.setType(Material.AIR, false);
            block.getWorld().spawnParticle(
                    Particle.BLOCK,
                    block.getLocation().add(0.5, 0.5, 0.5),
                    3,
                    0.12, 0.12, 0.12,
                    0.0,
                    obsidianData
            );
            block.getWorld().spawnParticle(
                    Particle.FLAME,
                    block.getLocation().add(0.5, 0.5, 0.5),
                    6,
                    0.18, 0.18, 0.18,
                    0.0
            );
            block.getWorld().playSound(block.getLocation(), Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 1.0f);
            ItemStack held = player.getInventory().getItemInMainHand();
            if (held.getAmount() == 1) {
                player.getInventory().setItemInMainHand(new ItemStack(Material.LAVA_BUCKET));
            } else {
                held.setAmount(held.getAmount() - 1);
                player.getInventory().addItem(new ItemStack(Material.LAVA_BUCKET));
            }
        } else {
            block.setType(Material.LAVA, false);
            if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.BUCKET));
            }
        }
    }
}
