package de.pmcp.hungergames.game;

import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.CMDS.Timer;
import de.pmcp.hungergames.game.Engine;
import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

public class DayTimer {
    public static int day = Engine.day;
    int minutes = 40; //Sessiondauer Angeben
    final int[] timerPoints = {60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Benachrichtigungsstände als Abstand zum Punkt Null
    int sessionLength = minutes * 60;
    public static int[] time = {-10};
    public static boolean timerPaused = true;
    BukkitScheduler scheduler = Bukkit.getScheduler();

    public void timer() {
        scheduler.runTaskTimer(main.plugin,task -> {
            if (timerPaused) return;
            if (time[0] < 0) {
                int dist = time[0] * -1;
                if (ArrayUtils.contains(timerPoints, dist))
                    if (day == 0)
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Der Tag beginnt in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                    else
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Die Spiele starten in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
            }
            else if (time[0] == 0)  {
                day += 1;
                if (day == 0)
                    Bukkit.broadcastMessage("§6§lDie Spiele sind gestartet: \n" + "§4§lViel Glück!");
                else
                    Bukkit.broadcastMessage("§6§lDer Tag ist gestartet: \n" + "§4§lViel Glück!");
                Freeze.isfreeze = false;
                Bukkit.broadcastMessage("\n§6§nDaily News: ");
                for (String deathMessageLoop : Death.deathArray) {
                    Bukkit.broadcastMessage("§4" + deathMessageLoop + "\n");
                }
            }
            else if (time[0] < sessionLength) {
                int dist = sessionLength - time[0];
                if (ArrayUtils.contains(timerPoints, dist))
                    if (day == 7)
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Die Spiele laufen noch §c" + (dist >= 60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                    else
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Der Tag endet in §c" + (dist >= 60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
            }
            else {
                if (day == 7)
                    Bukkit.broadcastMessage("§6§lDie Spiele sind geendet!");
                else
                    Bukkit.broadcastMessage("§6§lDer Tag ist geendet!");
                Freeze.isfreeze = false;
                timerPaused = true;
                time[0] = -11;
            }

            time[0] ++;
            }, 0, 20);
    }
}
