package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

//Effekte für einen Vulkan
public class Volcano implements Listener {
    @EventHandler
    public void spit(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) return; //trigger
        BukkitScheduler scheduler = Bukkit.getScheduler();
        Material[] vulcanoDrops = {Material.MAGMA_BLOCK, Material.ANDESITE}; //Materialien für Vulkanbomben
        Particle.DustTransition transition = new Particle.DustTransition(Color.fromRGB(255, 200, 100), Color.fromRGB(128, 0, 0), 4F);

        //Die Vulkanbombe erstellen
        FallingBlock bombe = event.getPlayer().getLocation().getWorld().spawnFallingBlock(
                new Location(event.getPlayer().getWorld(),-979.52, 68.00, 384.34), vulcanoDrops[Random.rint(0, vulcanoDrops.length-1)], (byte) 0);
        bombe.setVelocity(new Vector(Random.rouble(-1, 1), Random.rouble(0.2, 4.0), Random.rouble(-1, 1)));
        bombe.setDropItem(false);

        final int[] bombenalter = {-1};

        scheduler.runTaskTimer(main.plugin, task -> { //Bombentimer
            //Nach der Landung
            if (bombenalter[0] == bombe.getTicksLived()) {
                bombe.getWorld().playSound(bombe.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 20F, 0.5F);
                bombe.getWorld().spawnParticle(Particle.EXPLOSION_HUGE, bombe.getLocation(), 10, 3, 3, 3, 0, null, true);
                task.cancel();
            } bombenalter[0] = bombe.getTicksLived();

            //Während des Fluges
            bombe.getWorld().spawnParticle(Particle.REDSTONE, bombe.getLocation(), Random.rint(3, 8), 0.5, 0.5,0.5,0, transition, true);
            bombe.getWorld().playSound(bombe.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 6F, 2F);
        }, 0, 2); //Timings
    }
}
