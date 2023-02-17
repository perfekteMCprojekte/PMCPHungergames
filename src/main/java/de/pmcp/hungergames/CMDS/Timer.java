package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.DayTimer;
import de.pmcp.hungergames.game.Engine;
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
                if (DayTimer.timerPaused) { sender.sendMessage("§e[§6Hungergames§e]§7 Die Spiele sind bereits pausiert"); return true; }
                DayTimer.timerPaused = true;
                sender.sendMessage("§e[§6Hungergames§e]§7 Die Spiele wurden pausiert!");
                break;
            case "run":
                if (!DayTimer.timerPaused) { sender.sendMessage("§e[§6Hungergames§e]§7 Die Spiele laufen gerade"); return true; }
                DayTimer.timerPaused = false;
                sender.sendMessage("§e[§6Hungergames§e]§7 Die Spiele gehen weiter!");
                break;
            case "set":
                if (args.length < 2) { sender.sendMessage("§e[§6Hungergames§e]§8 Gebe eine Sekundenzahl für den Timer an (negativ vor Start)!"); return false; }
                try {
                    int newTime = Integer.parseInt(args[1]); //Zahl auslesen
                    if (DayTimer.time >= 0 && newTime < 0) Engine.day --; //Tag korrigieren bei entsprechender Zeitänderung
                    else if (DayTimer.time < 0 && newTime >= 0) Engine.day++;
                    DayTimer.time = newTime; //Aktualisieren

                    if (DayTimer.time < 0) Bukkit.broadcastMessage("§e[§6Hungergames§e]§4 Der Tag startet in §c" + DayTimer.time*-1 + " §4Sekunden!");
                    else Bukkit.broadcastMessage("§e[§6Hungergames§e]§4 Der Tag geht noch §c" + (DayTimer.sessionLength - DayTimer.time) + " §4Sekunden!");
                } catch (NumberFormatException e) { //Bei Zahlfehler
                    sender.sendMessage("§e[§6Hungergames§e]§7 Gebe eine §lZahl§l an");
                    return false;
                }
                break;
            case "setday":
                if (args.length < 2) { sender.sendMessage("§e[§6Hungergames§e]§8 Gebe eine Tageszahl an (0-7)"); return false; }
                try {
                    int newDay = Integer.parseInt(args[1]); //Zahl auslesen
                    if (newDay < 0 || newDay > 7) { sender.sendMessage("§e[§6Hungergames§e]§8 Tag muss zwischen 0 und 7 liegen)"); return false; }
                    Engine.day = newDay; //Aktualisieren
                    Bukkit.broadcastMessage("§e[§6Hungergames§e]§4 Die Spiele wurden auf Tag §c" + Engine.day + " §4Gesetzt!");
                } catch (NumberFormatException e) { //Bei Zahlfehler
                    sender.sendMessage("§e[§6Hungergames§e]§7 Gebe eine §lZahl§l an");
                    return false;
                }
                break;
            case "status":
                sender.sendMessage("§e[§6Hungergames§e]§7 Die Spiele sind gerade" + (DayTimer.timerPaused ? " §8pausiert§7" : "") + " bei §8" + DayTimer.time + "§7 Sekunden");
                break;
            default:
                sender.sendMessage("§e[§6Hungergames§e]§7 Ungültige Aktion: §o" + args[0]);
                return false;
        }
        //Ende von OnCommand
        return true;
    }
}
