package de.pmcp.hungergames.timer;

import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;


public class LaunchTimer implements CommandExecutor {
    BukkitScheduler scheduler = Bukkit.getScheduler();
    public static boolean timerActive, timerPaused = false;
    public static int secondsLeft = 600; //Hier Countdown Dauer eintragen
    final int[] timerPoints = {600, 300, 60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Hier beliebige Werte eintragen
    final String hg = "§e[§6Hungergames§e] ";

    /**Jede Sekunde ausgeführt*/
    private void tick() {
        if (ArrayUtils.contains(timerPoints, secondsLeft)) {
            Bukkit.broadcastMessage(hg + "§bDie Spiele starten in §c" + (secondsLeft >= 60 ? secondsLeft/60 + " §bMinuten" : secondsLeft + " §bSekunden"));
        }
        else if (secondsLeft == 0) new BaseTimer().start_games(); //Spielstart

        secondsLeft--;
    }

    /**Der Timer für den Countdown (1Hz)*/
    private void timer() {
        scheduler.runTaskTimer(main.plugin,task -> {
            if (!timerPaused) tick(); //Sekündlicher Code
            if (secondsLeft < 0 || !timerActive) { //Timerabbruch mir reset
                task.cancel();
                timerActive = timerPaused = false;
                secondsLeft = 600;
            }
        }, 0L, 20L);
    }

    /**Der Countdown ausgelöst durch /starttimer */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String alias, String[] args) {
        //Spieler, der befehl als objekt, eingegebener befehl, argumente als liste
        if (args.length == 0) return false;

        //Verarbeitung der Aktion für den Timer
        switch (args[0]) {
            case "start":
                if (timerActive) { sender.sendMessage("Der Countdown läuft bereits. Nutze /launchtimer status um mehr zu erfahren"); return true; }
                else if (BaseTimer.timerActive) { sender.sendMessage("Die Spiele laufen bereits!"); return true;}
                timerPaused = false;
                timerActive = true;
                timer();
                break;
            case "cancel":  //Abbruch
                if (!timerActive) { sender.sendMessage("Der Countdown läuft gerade nicht"); return true; }
                timerActive = false;
                Bukkit.broadcastMessage(hg + "Start abgebrochen!");
                break;
            case "pause":
                if (timerPaused) { sender.sendMessage("Der Countdown ist bereits pausiert"); return true; }
                timerPaused = true;
                Bukkit.broadcastMessage(hg + "Countdown pausiert!");
                break;
            case "resume":
                if (!timerPaused) { sender.sendMessage("Der Countdown läuft gerade"); return true; }
                timerPaused = false;
                Bukkit.broadcastMessage(hg + "Countdown fortgesetzt!");
                break;
            case "set":
                if (args.length < 2) { sender.sendMessage("Gebe eine Sekundenzahl an auf die du den Timer setzen willst!"); return false; }
                try {
                    secondsLeft = Integer.parseInt(args[1]);
                    Bukkit.broadcastMessage(hg + "Countdown auf §b" + secondsLeft + " §2Sekunden gesetzt!");
                } catch (NumberFormatException e) {
                    sender.sendMessage("Gebe eine §lZahl§l an");
                    return false;
                }
                break;
            case "status":
                if (timerActive) sender.sendMessage("§6 Der Countdown ist gerade" + (timerPaused ? " §lpausiert§l " : " ") + " bei §b" + secondsLeft + "§6 Sekunden");
                else sender.sendMessage("§6Der Countdown läuft gerade nicht");
                break;
            default:
                sender.sendMessage("Ungültige Aktion: §o" + args[0]);
                return false;
        }
        //Ende von OnCommand
        return true;
    }
}
