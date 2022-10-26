package de.pmcp.hungergames.pregame;

import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;


public class StartTimer implements CommandExecutor {
    public boolean starttimer = true;
    public boolean basetimer = false;
    public int secondsLeft = 600; //Hier Countdown dauer Eintragen
    final int[] timerPoints = {600, 300, 60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Hier beliebige Werte eintragen

    /**Startlogik bei Ablauf des Timers*/
    private void start() {
        Bukkit.broadcastMessage("§a[PMCP] §bDie Hungergames sind gestartet:");
        Bukkit.broadcastMessage("§4§lViel Glück!");
        de.pmcp.hungergames.pregame.isfreeze.isfreeze = false;
        starttimer = false;
        basetimer = true;
    }

    /**Jede Sekunde ausgeführt*/
    private void tick() {
        if (ArrayUtils.contains(timerPoints, secondsLeft)) {
            Bukkit.broadcastMessage("§a[PMCP] §bDie Hungergames starten in §c" + (secondsLeft >= 60 ? secondsLeft/60 : secondsLeft) + " §bMinuten");
        }
        else if (secondsLeft == 0) start();
        secondsLeft--;
    }

    /**Der Countdown ausgelöst durch /starttimer */
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String string, @NotNull String[] strings) {
        sender.sendMessage("Countdown gestartet");
        BukkitScheduler scheduler = Bukkit.getScheduler();

        //Ein Sekunden wiederhohlender timer
        scheduler.runTaskTimer(main.plugin, task -> {
            tick();
            if (secondsLeft < 0) {
                task.cancel();
                secondsLeft = 600;
            } }, 0L, 20L);

        return false;
    }
}
