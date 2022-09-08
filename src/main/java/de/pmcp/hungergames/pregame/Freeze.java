package de.pmcp.hungergames.pregame;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import static de.pmcp.hungergames.pregame.isfreeze.isfreeze;

public class Freeze implements Listener {
    public void onmove(PlayerMoveEvent e) {

        if (de.pmcp.hungergames.pregame.isfreeze.isfreeze == true) {
            e.setCancelled(true);

        }
    }
}
