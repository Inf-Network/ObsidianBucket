package net.infnetwork.snowball.obsidianbucket.service;

import net.infnetwork.snowball.obsidianbucket.config.PluginConfig;
import net.infnetwork.snowball.obsidianbucket.protection.ProtectionChecker;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public final class ObsidianBucketListener implements Listener {
    private static final String USE_PERMISSION = "obsidianbucket.use";
    private final PluginConfig config;
    private final ProtectionChecker protectionChecker;
    private final LavaBucketTransformer transformer;

    public ObsidianBucketListener(PluginConfig config, ProtectionChecker protectionChecker,
                                  LavaBucketTransformer transformer) {
        this.config = config;
        this.protectionChecker = protectionChecker;
        this.transformer = transformer;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getHand() != EquipmentSlot.HAND
                || event.getClickedBlock() == null
                || event.getClickedBlock().getType() != Material.OBSIDIAN) {
            return;
        }
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item == null || event.isCancelled()
                || (item.getType() != Material.BUCKET && item.getType() != Material.LAVA_BUCKET)) {
            return;
        }
        if (config.requirePermission() && !player.hasPermission(USE_PERMISSION)) {
            player.sendMessage(config.noPermissionMessage());
            denyVanillaAction(event);
            return;
        }
        Block block = event.getClickedBlock();
        if (!protectionChecker.canBreak(player, block)) {
            player.sendMessage(config.protectedMessage());
            denyVanillaAction(event);
            return;
        }
        denyVanillaAction(event);
        transformer.transform(player, block, item.getType() == Material.BUCKET);
    }

    private void denyVanillaAction(PlayerInteractEvent event) {
        event.setUseInteractedBlock(PlayerInteractEvent.Result.DENY);
        event.setUseItemInHand(PlayerInteractEvent.Result.DENY);
    }
}
