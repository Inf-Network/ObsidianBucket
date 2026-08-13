package net.infnetwork.snowball.obsidianbucket.service;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public final class ConversionEffectService {
    private static final long EFFECT_DURATION_TICKS = 60L * 60L * 20L;
    private static final long EFFECT_INTERVAL_TICKS = 20L;

    private final JavaPlugin plugin;

    public ConversionEffectService(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void play(Location sourceLocation) {
        Location effectLocation = sourceLocation.clone().add(0.5, 0.5, 0.5);
        World world = effectLocation.getWorld();
        if (world == null) {
            return;
        }

        world.playSound(effectLocation, Sound.ITEM_BUCKET_FILL_LAVA, 1.0f, 1.0f);
        final BukkitTask[] task = new BukkitTask[1];
        task[0] = plugin.getServer().getScheduler().runTaskTimer(plugin, new Runnable() {
            private long elapsedTicks;

            @Override
            public void run() {
                if (!world.isChunkLoaded(effectLocation.getBlockX() >> 4, effectLocation.getBlockZ() >> 4)
                        || elapsedTicks >= EFFECT_DURATION_TICKS) {
                    task[0].cancel();
                    return;
                }
                world.spawnParticle(Particle.LAVA, effectLocation, 4, 0.25, 0.25, 0.25, 0.0);
                elapsedTicks += EFFECT_INTERVAL_TICKS;
            }
        }, 0L, EFFECT_INTERVAL_TICKS);
    }
}
