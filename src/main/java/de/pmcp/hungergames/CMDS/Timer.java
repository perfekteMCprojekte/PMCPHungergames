package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.DayTimer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Timer implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String string, @NotNull String[] args) {
        //Spieler, der befehl als objekt, eingegebener befehl, argumente als liste
        if (args.length == 0) return false;

        //Verarbeitung der Aktion für den Timer
        switch (args[0]) {
            case "pause":
                if (DayTimer.timerPaused) { sender.sendMessage("Die Spiele sind bereits pausiert"); return true; }
                DayTimer.timerPaused = true;
                Bukkit.broadcastMessage("§e[§6Hungergames§e] Die Spiele wurden pausiert!");
                break;
            case "resume":
                if (!DayTimer.timerPaused) { sender.sendMessage("Die Spiele laufen gerade"); return true;}
                DayTimer.timerPaused = false;
                Bukkit.broadcastMessage("§e[§6Hungergames§e] Die Spiele gehen weiter!");
                break;
            case "set":
                if (args.length < 2) { sender.sendMessage("Gebe eine Sekundenzahl an auf die du den Timer setzen willst!"); return false; }
                try {
                    DayTimer.time[0] = Integer.parseInt(args[1]);
                    Bukkit.broadcastMessage("§e[§6Hungergames§e] Verbleibende Spielzeit auf §b" + DayTimer.time[0] + " §2Sekunden gesetzt!");
                } catch (NumberFormatException e) {
                    sender.sendMessage("Gebe eine §lZahl§l an");
                    return false;
                }
                break;
            case "status":
                sender.sendMessage("§6 Die Spiele sind gerade" + (DayTimer.timerPaused ? " §lpausiert§l " : " ") + " bei §b" + DayTimer.time[0] + "§6 Sekunden");
                break;
            default:
                sender.sendMessage("Ungültige Aktion: §o" + args[0]);
                return false;
        }
        //Ende von OnCommand
        return true;
    }
}
