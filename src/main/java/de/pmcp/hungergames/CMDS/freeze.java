package de.pmcp.hungergames.CMDS;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class freeze implements CommandExecutor {
    public static boolean isfreeze = true;

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (isfreeze) {
            isfreeze = false;
            Bukkit.getLogger().info("Es wurde entpauseriert.");
        } else {
            isfreeze = true;
            Bukkit.getLogger().info("Es wurde pauseriert.");
        }
        return false;
    }
}
