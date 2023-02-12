package de.pmcp.hungergames.game;

import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

public class DayTimer {
    public static int sessionLength = 40 * 60; //Sessiondauer Angeben (min * sek)
    final static int[] timerPoints = {60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Benachrichtigungsstände als Abstand zum Punkt Null
    public static int time = -10; //VORBEREITUNGSZEIT BITTE ÄNDERN
    public static boolean timerPaused = true;

    public static void timer() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        int day = Engine.day;
        scheduler.runTaskTimer(main.plugin,task -> { //Timer für Spielzeit
            //Tageszeit berechnen und setzen (sonnenaufgang bis mitternacht)
            int dayTime = (time * (20000 / sessionLength))-1100;
            Bukkit.getWorlds().get(0).setTime(dayTime);

            if (timerPaused) return;
            //Start des Countdowns
            if (time < 0) {
                int dist = time * -1; //Abstand zum start des Tages
                if (ArrayUtils.contains(timerPoints, dist))
                    if (day == 0)
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Der Tag beginnt in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                    else
                        Bukkit.broadcastMessage("§e[§6Hungergames§e] §4Die Spiele starten in §c" + (dist <= -60 ? dist / 60 + " §4Minuten" : dist + " §4Sekunden"));
                if (dist == 5 && day > 1) Info.DailyNews();
            }
            //Start des Tages
            else if (time == 0) {
                Engine.day++;
                Freeze.isfreeze = false;
                for (Player player : Bukkit.getOnlinePlayers())
                    player.playSound(player, Sound.ENTITY_GOAT_SCREAMING_AMBIENT, 10F, 0.8F);

                if (day == 1) {
                    Bukkit.broadcastMessage("§6§lDie Spiele sind gestartet: \n" + "§4§lViel Glück!");
                } else {
                    Bukkit.broadcastMessage("§6§lDer Tag ist gestartet: \n" + "§4§lViel Glück!");
                    //hier war mal das
                }
            }
                //Während dem Tag
            else if (time < sessionLength) {
                int dist = sessionLength - time; //Abstand zum Ende des Tages
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
                time = -601;
            }

            time ++;
            }, 0, 20);
    }
}
