package de.pmcp.hungergames.game;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Startgame implements CommandExecutor {
    public static boolean game;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (game == true) {
            //timer hier
        }
        else {
            commandSender.sendMessage("starte das Game erstmal");
        }
        return false;
    }
}
