package de.pmcp.hungergames.timer;

import de.pmcp.hungergames.CMDS.freeze;
import de.pmcp.hungergames.main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;


public class BaseTimer implements CommandExecutor {
    BukkitScheduler scheduler = Bukkit.getScheduler();
    public static boolean timerActive, timerPaused = false;
    public static int secondsLeft = 600; //Hier Countdown Dauer eintragen
    final int[] timerPoints = {600, 300, 60, 30, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1}; //Hier beliebige Werte eintragen
    final String hg = "§e[§6Hungergames§e] ";

    /**Der Spielstart*/
     public void start_games() {
        timerPaused = false;
        timerActive = true;
        freeze.isfreeze = false;
        Bukkit.broadcastMessage(" ");
        Bukkit.broadcastMessage(hg + "§bDie Spiele sind gestartet:");
        Bukkit.broadcastMessage(hg + "§4§lViel Glück!");
        timer();
    }

    private void end_games() {
        Bukkit.broadcastMessage(hg + "§bDie Spiele sind beendet!");
    }

    /**Jede Sekunde ausgeführt*/
    private void tick() {
        if (ArrayUtils.contains(timerPoints, secondsLeft)) {
            Bukkit.broadcastMessage(hg + "§bDie Spiele laufen noch §c" + (secondsLeft >= 60 ? secondsLeft/60 + " §bMinuten" : secondsLeft + " §bSekunden"));
        }
        else if (secondsLeft == 0) end_games();
        secondsLeft--;
    }

    /**Der Timer für den Countdown (1Hz)*/
    private void timer() {
        scheduler.runTaskTimer(main.plugin,task -> {
            if (!timerPaused) tick(); //Sekündlicher Code
            if (secondsLeft < 0 || !timerActive) { //Timerabbruch mir reset
                task.cancel();
                timerActive = timerPaused = false;
                secondsLeft = 8400;
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
                if (timerActive) { sender.sendMessage("Die Spiele laufen noch. Nutze /basetimer status um mehr zu erfahren"); return true; }
                else if (LaunchTimer.timerActive) { sender.sendMessage("Der Start-Countdown läuft gerade!"); return true;}
                start_games();
                break;
            case "cancel":  //Abbruch
                if (!timerActive) { sender.sendMessage("Die Spiele laufen gerade nicht"); return true; }
                timerActive = false;
                Bukkit.broadcastMessage(hg + "Die Spiele wurden abgebrochen!");
                break;
            case "pause":
                if (timerPaused) { sender.sendMessage("Die Spiele sind bereits pausiert"); return true; }
                timerPaused = true;
                Bukkit.broadcastMessage(hg + "Die Spiele wurden pausiert!");
                break;
            case "resume":
                if (!timerPaused) { sender.sendMessage("Die Spiele laufen gerade"); return true; }
                timerPaused = false;
                Bukkit.broadcastMessage(hg + "Die Spiele gehen weiter!");
                break;
            case "set":
                if (args.length < 2) { sender.sendMessage("Gebe eine Sekundenzahl an auf die du den Timer setzen willst!"); return false; }
                try {
                    secondsLeft = Integer.parseInt(args[1]);
                    Bukkit.broadcastMessage(hg + "Verbleibende Spielzeit auf §b" + secondsLeft + " §2Sekunden gesetzt!");
                } catch (NumberFormatException e) {
                    sender.sendMessage("Gebe eine §lZahl§l an");
                    return false;
                }
                break;
            case "status":
                if (timerActive) sender.sendMessage("§6 Die Spiele sind gerade" + (timerPaused ? " §lpausiert§l " : " ") + " bei §b" + secondsLeft + "§6 Sekunden");
                else sender.sendMessage("§6Die Spiele laufen gerade nicht");
                break;
            default:
                sender.sendMessage("Ungültige Aktion: §o" + args[0]);
                return false;
        }
        //Ende von OnCommand
        return true;
    }
}
