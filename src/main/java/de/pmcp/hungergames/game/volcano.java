package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.random;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

public class volcano implements Listener {

    @EventHandler
    public void spit(PlayerToggleSneakEvent event) {
        Material[] vulcanoDrops = {Material.MAGMA_BLOCK, Material.ANDESITE};

        Particle.DustTransition transition = new Particle.DustTransition(Color.fromRGB(255, 200, 100), Color.fromRGB(128, 0, 0), 4F);

        Location loc = event.getPlayer().getLocation();
        FallingBlock block = loc.getWorld().spawnFallingBlock(
                new Location(event.getPlayer().getWorld(),-979.52, 68.00, 384.34), vulcanoDrops[random.rint(0, vulcanoDrops.length-1)], (byte) 0);
        block.setVelocity(new Vector(random.rouble(-1, 1), random.rouble(0.2, 4.0), random.rouble(-1, 1)));
        block.setDropItem(false);
        
        //block.getWorld().spawnParticle(Particle.REDSTONE, block.getLocation(), 10, 0.1, 1.0,1.0,0, transition, true);

    }
    @EventHandler
    public void effects (EntityChangeBlockEvent event) {
        if (event.getEntityType() != EntityType.FALLING_BLOCK) return;
        if (event.getTo() != Material.MAGMA_BLOCK && event.getTo() != Material.ANDESITE) return;
        event.getBlock().getWorld().playSound(event.getBlock().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 10F, 0.5F);
        event.getBlock().getWorld().spawnParticle(Particle.EXPLOSION_HUGE, event.getBlock().getLocation(), 10, 1, 3, 3, 3);
        Bukkit.broadcastMessage("LANDED!");
    }
}
