package de.pmcp.hungergames.timer;

import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;


public class BaseTimer implements CommandExecutor {
    public boolean starttimer = false;
    public boolean basetimer = true;
    public static int secondsLeft = 600; //Hier Countdown Dauer eintragen
    final int[] timerPoints = {600, 300, 60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Hier beliebige Werte eintragen

    /**Startlogik bei Ablauf des Timers*/
    private void ende() {
        Bukkit.broadcastMessage("§a[PMCP] §bDie Hungergames sind beendet:");
        Bukkit.broadcastMessage("§4§lENDE!");
        de.pmcp.hungergames.pregame.isfreeze.isfreeze = true;
        basetimer = false;
    }

    /**Jede Sekunde ausgeführt*/
    private void tick() {
        if (ArrayUtils.contains(timerPoints, secondsLeft)) {
            Bukkit.broadcastMessage("§a[PMCP] §bDie Hungergames enden in §c" + (secondsLeft >= 60 ? secondsLeft/60 + " §bMinuten" : secondsLeft + " §bSekunden"));
        }
        else if (secondsLeft == 0) ende();
        secondsLeft--;
    }

    /**Der Countdown ausgelöst durch /basetimer */
    @Override
    public boolean onCommand( CommandSender sender, Command command, String string, String[] strings) {
        sender.sendMessage("Countdown gestartet");
        BukkitScheduler scheduler = Bukkit.getScheduler();

        //Sekundentimer
        scheduler.runTaskTimer(main.plugin, task -> {
            tick();
            if (secondsLeft < 0) {
                task.cancel();
                secondsLeft = 600;
            } }, 0L, 20L);

        return false;
    }
}
