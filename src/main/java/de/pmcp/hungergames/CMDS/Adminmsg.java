package de.pmcp.hungergames.CMDS;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Adminmsg implements CommandExecutor {
    final String hg = "§e[§6Hungergames§e] ";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,  String[] args) {
        if (args == null) {sender.sendMessage("Bist du dumm?");}
        Bukkit.broadcastMessage(hg + ChatColor.AQUA + Arrays.toString(args));
        return false;
    }
}
