package de.pmcp.hungergames.pregame;


import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class Freeze implements Listener {
    public void onmove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (de.pmcp.hungergames.pregame.isfreeze.isfreeze == true) {
            e.setCancelled(true);
            player.sendMessage("Du darfst dich nicht bewegen!");
        }
        else {e.setCancelled(false);}
    }
}
