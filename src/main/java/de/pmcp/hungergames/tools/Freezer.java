package de.pmcp.hungergames.tools;

import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.game.Engine;
import de.pmcp.hungergames.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;

public class Freezer implements Listener {
    static boolean waitSwitch = true;
    //NACHRICHTEN FÜR WARTEBILDSCHIRM
    static String[] waitTexts = {"Pizza ist ein Obstkuchen", "cargospace", "AAAAAAAAAAAAAAAAAAAA", "Lukas stinkt", "E", "Nett hier, Kriegsverbrechen", "Wir reden nicht über den Keller!"};

    @EventHandler
    public void freeze_move(PlayerMoveEvent event) {
        if (!Freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_damage(PlayerInteractAtEntityEvent event) {
        if (!Freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_interact(PlayerInteractEvent event) {
        if (!Freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_drop(PlayerDropItemEvent event) {
        if (!Freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_stats(PlayerStatisticIncrementEvent event) {
        if (!Freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }

    public static void main() {
        if (Engine.day == 1) Freeze.isfreeze = false;

        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> { //Timer für freeze
            if (!Freeze.isfreeze) return;

            for (Player player : Bukkit.getOnlinePlayers()) {
                player.sendTitle("║", (waitSwitch)? "§7§obitte warten..." : "§8§o"+waitTexts[Random.rint(0, waitTexts.length-1)], 3, 1, 12);
                if (player.isOp()) continue;
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, false));
            }
            waitSwitch = !waitSwitch;
        }, 0, 20);
    }

}
