package net.infnetwork.snowball.obsidianbucket.protection;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;

@FunctionalInterface
public interface ProtectionChecker {
    boolean canBreak(Player player, Block block);
}
