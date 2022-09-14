package de.pmcp.hungergames.postgame;

import de.pmcp.hungergames.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;

public class PostTimer implements CommandExecutor {
    private int sec;
    private double time;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        sec = 600;
        BukkitRunnable runnable = new BukkitRunnable() {
            @Override
            public void run() {
            //Timer
                switch (sec) {
                    case 600:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10 " + ChatColor.AQUA + "Minuten");
                        break;
                    case 300:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "5 " + ChatColor.AQUA + "Minuten");
                        break;
                    case 60:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "60 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 30:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "30 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 10:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 9:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "9 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 8:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "8 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 7:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "7 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 6:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "6 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 5:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "5 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 4:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "4 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 3:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "3 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 2:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "2 " + ChatColor.AQUA + "Sekunden");
                        break;
                    case 1:
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "1 " + ChatColor.AQUA + "Sekunde");
                        break;
                }
                //Ende vom Timer
                if (sec == 0) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Ihr könnt den Gommemode deaktivieren");
                    de.pmcp.hungergames.pregame.isfreeze.isfreeze = true;
                    cancel();
                    return;
                }
                sec--;
            }
        };
        runnable.runTaskTimer(main.getPlugin(), 0, 20);
        return false;
    }
}
