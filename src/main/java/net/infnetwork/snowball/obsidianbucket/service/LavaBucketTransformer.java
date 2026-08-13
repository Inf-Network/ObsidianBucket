package net.infnetwork.snowball.obsidianbucket.service;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class LavaBucketTransformer {
    public void transform(Player player, Block block, boolean fillEmptyBucket) {
        if (fillEmptyBucket) {
            block.setType(Material.AIR, false);
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
