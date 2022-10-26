package de.pmcp.hungergames.pregame;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class timerSekunden implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Integer sekunde = Integer.valueOf(args[0]);
            de.pmcp.hungergames.pregame.StartTimer.secondsLeft = sekunde;
                commandSender.sendMessage("§a[PMCP] §bTimer auf " + sekunde + " Sekunden geändert.");
                commandSender.sendMessage(String.valueOf(sekunde));
        return false;
    }
}
