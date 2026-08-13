package net.infnetwork.snowball.obsidianbucket.service;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public final class LavaBucketTransformer {
    public void transform(Player player, Block block, boolean fillEmptyBucket) {
        block.setType(Material.LAVA, false);
        if (fillEmptyBucket) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.LAVA_BUCKET));
        } else if (player.getGameMode() != GameMode.CREATIVE) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.BUCKET));
        }
    }
}
