package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.*;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

//Effekte für einen Vulkan
public class Volcano implements Listener {
    static World world = Bukkit.getWorlds().get(0);
    static Location loc = new Location(world, -980.00, 70.00, 384.00); //HIER BITTE VULKAN COORDS EINTRAGEN!
    static int timeErupting = 0;
    static boolean isErupting = false;

    static Particle.DustTransition smoke = new Particle.DustTransition(Color.fromRGB(34, 30, 42), Color.fromRGB(83, 72, 95), 100F);

    //Vulkanbombem
    public static void spit() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        Material[] vulcanoDrops = {Material.MAGMA_BLOCK, Material.ANDESITE}; //Materialien für Vulkanbomben
        Particle.DustTransition transition = new Particle.DustTransition(Color.fromRGB(255, 200, 100), Color.fromRGB(128, 0, 0), 4F);

        //Die Vulkanbombe erstellen
        FallingBlock bombe = world.spawnFallingBlock(loc, vulcanoDrops[Random.rint(0, vulcanoDrops.length-1)], (byte) 0);
        bombe.setVelocity(new Vector(Random.rouble(-1, 1), Random.rouble(0.2, 4.0), Random.rouble(-1, 1)));
        bombe.setDropItem(false);

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
            world.playSound(bombloc, Sound.BLOCK_LAVA_EXTINGUISH, 6F, 2F);
            for (Player player : Bukkit.getOnlinePlayers())
                if (player.getNearbyEntities(96, 96, 96).contains(bombe)) player.playSound(bombloc, Sound.BLOCK_LAVA_EXTINGUISH, 6F, 2F);
        }, 0, 2); //Timings
    }

    //Steuert den Ausbruch des Vulkans
    public static void erupt(int duration, int strenth) {
        final int strength = strenth;
        if (isErupting) return; //Vulkan soll nicht doppelt ausbrechen
        isErupting = true;
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> { //Vulkantimer
            //Abrechen des Ausbruchs
            if (timeErupting > duration/**4*/) {
                timeErupting = 0;
                isErupting = false;
                task.cancel();
            }
            //Während des Ausbruchs
            if (Random.rint(strength, 6) == 6) spit();
            if (Random.rint(strength, 8) == 6) world.spawnParticle(Particle.REDSTONE, loc, 1, 2, 2, 2, 0, smoke, true);
            world.spawnParticle(Particle.SMOKE_LARGE, loc, 1, 2, 2, 2, 0, null, true);
            if (Random.rint(strength, 16) == 6) world.playSound(loc, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 10F, 0.6F);

            timeErupting++;
        }, 100, 5); //Timings
    }
}
