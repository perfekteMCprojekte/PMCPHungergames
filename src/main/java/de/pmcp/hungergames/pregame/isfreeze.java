package de.pmcp.hungergames.pregame;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class isfreeze implements CommandExecutor {

    public static boolean isfreeze = false;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (isfreeze == true) {
            isfreeze = false;
            sender.sendMessage("Freeze ist nun aus!");
        } else {
            isfreeze = true;
            sender.sendMessage("Freeze ist nun an!");
        }
        return false;
    }
}
