package de.pmcp.hungergames.postgame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PostTimer implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10" + ChatColor.AQUA + " Minuten!");
        try {
            wait(300000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "5" + ChatColor.AQUA + " Minuten!");
        try {
            wait(120000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "3" + ChatColor.AQUA + " Minuten!");
        try {
            wait(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "2" + ChatColor.AQUA + " Minuten!");
        try {
            wait(60000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "1" + ChatColor.AQUA + " Minute!");
        try {
            wait(30000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "30" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "5" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "4" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "3" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "2" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "1" + ChatColor.AQUA + " Sekunden!");
        try {
            wait(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Ihr könnt den Gommemode deaktivieren");
        return false;
    }
}
