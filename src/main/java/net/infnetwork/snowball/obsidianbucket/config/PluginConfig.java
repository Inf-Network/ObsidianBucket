package net.infnetwork.snowball.obsidianbucket.config;

import net.infnetwork.snowball.obsidianbucket.ObsidianBucketPlugin;
import org.bukkit.ChatColor;

public record PluginConfig(boolean requirePermission, String noPermissionMessage, String protectedMessage) {
    public static PluginConfig load(ObsidianBucketPlugin plugin) {
        return new PluginConfig(
                plugin.getConfig().getBoolean("require-permission", false),
                color(plugin.getConfig().getString("messages.no-permission", "&c你没有权限使用这个功能。")),
                color(plugin.getConfig().getString("messages.protected", "&c这里受到保护，不能桶装黑曜石。"))
        );
    }

    private static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
