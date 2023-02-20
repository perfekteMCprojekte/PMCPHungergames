package de.pmcp.hungergames.tools;

import de.pmcp.hungergames.CMDS.freeze;
import de.pmcp.hungergames.game.Engine;
import de.pmcp.hungergames.game.Timer;
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
        if (!freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_damage(PlayerInteractAtEntityEvent event) {
        if (!freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_interact(PlayerInteractEvent event) {
        if (!freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_drop(PlayerDropItemEvent event) {
        if (!freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }
    @EventHandler
    public void freeze_stats(PlayerStatisticIncrementEvent event) {
        if (!freeze.isfreeze) return;
        if (event.getPlayer().isOp()) return;
        event.setCancelled(true);
    }

    public static void main() {
        //Timer für freeze
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> {
            if (!freeze.isfreeze) return;

            for (Player player : Bukkit.getOnlinePlayers()) {
                //Infos/Zeug für Titel auswählen und Anzeigen
                String title = "";
                if (waitSwitch) {
                    if (Timer.time > Timer.sessionLength+10) title = "§7§otag beendet...";
                    else title = "§7§obitte warten...";
                }
                else {
                    if (Timer.time > -15 && Timer.time < 0) title = "§8§otag startet...";
                    else if (Timer.time > Timer.sessionLength+10) title = "§7§oDu gehst!";
                    else if (Timer.time > Timer.sessionLength) title = "§7§oNachrichten laden...";
                    else title = "§8§o"+waitTexts[Random.rint(0, waitTexts.length-1)];
                }
                player.sendTitle("║", title, 3, 1, 12);// );
                //Blindheit für nicht ops
                if (player.isOp()) continue;
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 0, false, false));
            }
            waitSwitch = !waitSwitch;
        }, 0, 20);
    }

}
