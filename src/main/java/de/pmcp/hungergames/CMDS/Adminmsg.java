package de.pmcp.hungergames.CMDS;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class Adminmsg implements CommandExecutor {
    final String hg = "§e[§6Hungergames§e] ";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Bukkit.broadcastMessage(hg + args);
        return false;
    }
}
