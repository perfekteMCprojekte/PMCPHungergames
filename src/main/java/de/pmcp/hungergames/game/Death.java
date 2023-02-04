package de.pmcp.hungergames.game;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Death implements Listener {

    public static int deathCount;
    public static ArrayList<String> deathArray = new ArrayList<String>();

    @EventHandler
    public static void death(@NotNull PlayerDeathEvent event) {
        //Bei Spielertod
        Player player = event.getEntity();
        if(!player.isOp()) {
            event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1000F, 0.1F);
            deathCount++;
            //Nimmt die DeathMessage für Daily News auf
            deathArray.add(event.getDeathMessage());
            Bukkit.getLogger().info(deathArray.toString());
        }
        event.setDeathMessage(null);
    }
}
