package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

//Effekte für einen Vulkan
public class Volcano implements Listener {
    static World world = Engine.world;
    static Location loc = new Location(world, -980.00, 70.00, 384.00); //HIER BITTE VULKAN COORDS EINTRAGEN!
    static Material[] vulcanoDrops = {Material.MAGMA_BLOCK, Material.MAGMA_BLOCK, Material.OBSIDIAN, Material.ANDESITE, Material.TUFF, Material.TUFF}; //Materialien für Vulkanbomben
    static int timeErupting = 0;
    static boolean isErupting = false;

    //Vulkanbombem
    public static void spit() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        Particle.DustTransition transition = new Particle.DustTransition(Color.fromRGB(255, 200, 100), Color.fromRGB(128, 0, 0), 4F);

        //Die Vulkanbombe erstellen
        FallingBlock bombe = world.spawnFallingBlock(loc, vulcanoDrops[Random.rint(0, vulcanoDrops.length-1)], (byte) 0);
        bombe.setVelocity(new Vector(Random.rouble(-1, 1), Random.rouble(0.2, 4.0), Random.rouble(-1, 1)));
        bombe.setDropItem(false);
        world.playSound(bombe.getLocation(), Sound.ENTITY_WITHER_HURT, 6F, 0.5F);

        final int[] bombenalter = {-1};

        scheduler.runTaskTimer(main.plugin, task -> { //Bombentimer
            Location bombloc = bombe.getLocation();
            //Nach der Landung
            if (bombenalter[0] == bombe.getTicksLived()) { //Überprüft, ob sich die Bombe nicht mehr bewegt
                world.playSound(bombloc, Sound.ENTITY_GENERIC_EXPLODE, 15F, 0.5F);
                world.spawnParticle(Particle.EXPLOSION_HUGE, bombloc, 10, 3, 3, 3, 0, null, true);
                world.createExplosion(bombloc, (float) Random.rouble(2,5));
                world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 5F, 3.0F);
                task.cancel();
            } bombenalter[0] = bombe.getTicksLived();

            //Während des Fluges
            world.spawnParticle(Particle.REDSTONE, bombloc, Random.rint(3, 8), 0.5, 0.5,0.5,0, transition, true);
        }, 0, 2); //Timings
    }

    //Steuert den Ausbruch des Vulkans
    public static void erupt(int duration, int strenth) {
        final int strength = strenth;
        if (isErupting) return; //Vulkan soll nicht doppelt ausbrechen
        isErupting = true;

        //Vulkantimer (Vulkanausbruch)
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> {
            //Abrechen des Ausbruchs
            if (timeErupting > duration) {
                timeErupting = 0;
                isErupting = false;
                task.cancel();
            }
            //Während des Ausbruchs
            if (!DayTimer.timerPaused && Random.rint(strength, 6) == 6) spit();
            if (Random.rint(strength, 8) == 6) world.spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, loc, 6, 4, 8, 4, 0.05, null, true);
            int color = Random.rint(40, 120);
            world.spawnParticle(Particle.REDSTONE, loc, 10, 10, 7, 10, 0.1,
                    new Particle.DustOptions(Color.fromRGB(color,color,color), 30.0F), true);
            if (Random.rint(strength, 10) == 6) world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 30F, 0.6F);
            if (Random.rint(0, 50) == 0) world.playSound(loc, Sound.ENTITY_WITHER_AMBIENT, 0.7F, 0.5F);

            timeErupting++;
        }, 100, 5); //Timings

        //Soundschleife (soll noch überabeitet werden)
        scheduler.runTaskTimer(main.plugin, task -> {
            if (!isErupting) task.cancel();
            for (Player player : Bukkit.getOnlinePlayers())
                for (Entity entity : player.getNearbyEntities(80, 80, 80))
                    if (entity.getType() == EntityType.FALLING_BLOCK) {
                        player.playSound(entity.getLocation(), Sound.BLOCK_LAVA_EXTINGUISH, 5F, 2F);
                        break;
                    }
        }, 100, 3);
    }
}
