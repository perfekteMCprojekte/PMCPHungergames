package de.pmcp.hungergames.pregame;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import static de.pmcp.hungergames.pregame.isfreeze.isfreeze;


public class Freeze implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onmove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (de.pmcp.hungergames.pregame.isfreeze.isfreeze == true) {
            e.setCancelled(true);
            player.sendMessage("Du darfst dich nicht bewegen!");
        }
        else {e.setCancelled(false);}
    }
}