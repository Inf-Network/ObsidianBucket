package net.infnetwork.snowball.obsidianbucket.protection;

import org.bukkit.Server;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;

/** Uses the server's normal protection listeners, including WG/Residence/SS2. */
public final class BlockBreakProtectionChecker implements ProtectionChecker {
    private final Server server;

    public BlockBreakProtectionChecker(Server server) {
        this.server = server;
    }

    @Override
    public boolean canBreak(Player player, Block block) {
        BlockBreakEvent probe = new BlockBreakEvent(block, player);
        server.getPluginManager().callEvent(probe);
        return !probe.isCancelled();
    }
}
