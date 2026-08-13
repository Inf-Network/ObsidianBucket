package net.infnetwork.snowball.obsidianbucket;

import net.infnetwork.snowball.obsidianbucket.config.PluginConfig;
import net.infnetwork.snowball.obsidianbucket.protection.BlockBreakProtectionChecker;
import net.infnetwork.snowball.obsidianbucket.protection.ProtectionChecker;
import net.infnetwork.snowball.obsidianbucket.service.LavaBucketTransformer;
import net.infnetwork.snowball.obsidianbucket.service.ObsidianBucketListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class ObsidianBucketPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        saveDefaultConfig();
        PluginConfig config = PluginConfig.load(this);
        ProtectionChecker protectionChecker = new BlockBreakProtectionChecker(getServer());
        getServer().getPluginManager().registerEvents(
                new ObsidianBucketListener(this, config, protectionChecker, new LavaBucketTransformer()), this);
        getLogger().info("ObsidianBucket enabled.");
    }
}
