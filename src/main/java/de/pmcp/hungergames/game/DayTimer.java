package de.pmcp.hungergames.game;

import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.CMDS.Timer;
import de.pmcp.hungergames.game.Engine;
import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class DayTimer {
    int minutes = 40; //Sessiondauer Angeben
    final int[] timerPoints = {60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Benachrichtigungsstände als Abstand zum Punkt Null
    int sessionLength = minutes * 60;
    public static int[] time = {-10};
    public static boolean timerPaused = true;
    BukkitScheduler scheduler = Bukkit.getScheduler();

    public void timer() {
        int day = Engine.day;
        scheduler.runTaskTimer(main.plugin,task -> {
            if (timerPaused) return;
            //Start des Countdowns
            if (time[0] < 0) {
                int dist = time[0] * -1; //Abstand zum start des Tages
                if (ArrayUtils.contains(timerPoints, dist))
                    if (day == 0)
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Der Tag beginnt in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                    else
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Die Spiele starten in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                if (dist == 5 && day > 1) Info.DailyNews();
            }
            //Start des Tages
            else if (time[0] == 0)  {
                Engine.day ++;
                Freeze.isfreeze = false;
                for (Player player : Bukkit.getOnlinePlayers()) player.playSound(player, Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 10F, 0.8F);

                if (day == 1) {
                    Bukkit.broadcastMessage("§6§lDie Spiele sind gestartet: \n" + "§4§lViel Glück!");
                }
                else {
                    Bukkit.broadcastMessage("§6§lDer Tag ist gestartet: \n" + "§4§lViel Glück!");
                }
            }
            //Während dem Tag
            else if (time[0] < sessionLength) {
                int dist = sessionLength - time[0]; //Abstand zum Ende des Tages
                if (ArrayUtils.contains(timerPoints, dist))
                    if (day == 7)
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Die Spiele laufen noch §c" + (dist >= 60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                    else
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Der Tag endet in §c" + (dist >= 60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
            }
            //Ende des Tages
            else {
                if (day == 7)
                    Bukkit.broadcastMessage("§6§lDie Spiele sind geendet!");
                else
                    Bukkit.broadcastMessage("§6§lDer Tag ist geendet!");
                Info.DailyNews();
                Freeze.isfreeze = true;
                timerPaused = true;
                time[0] = -11;
            }

            time[0] ++;
            }, 0, 20);
    }
}
