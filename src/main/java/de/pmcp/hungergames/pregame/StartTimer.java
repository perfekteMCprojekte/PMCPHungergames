package de.pmcp.hungergames.pregame;

import de.pmcp.hungergames.main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;


public class StartTimer implements CommandExecutor {
    private int sec;
    public boolean starttimer = true;
    public boolean basetimer = false;
    @Override
    public boolean onCommand(CommandSender sender, Command c, String s, String[] strings) {
        sender.sendMessage("Start eingeleitet");
       // sec = 600;
        sec = 1; //db
        //starttimer
        do {
            BukkitRunnable runnable = new BukkitRunnable() {
                @Override
                public void run() {
                    //Timer
                    switch (sec) {
                        case 600:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 10" + ChatColor.AQUA + " Minuten");
                            break;
                        case 300:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 5" + ChatColor.AQUA + " Minuten");
                            break;
                        case 60:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 60" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 30:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 30" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 10:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 10" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 9:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 9" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 8:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 8" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 7:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 7" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 6:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 6" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 5:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 5" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 4:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 4" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 3:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 3" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 2:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 2" + ChatColor.AQUA + " Sekunden");
                            break;
                        case 1:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP]" + ChatColor.AQUA + " Die Hungergames starten in" + ChatColor.RED + " 1" + ChatColor.AQUA + " Sekunde");
                            break;
                    }
                    //Ende vom Timer
                    if (sec == 0) {
                        Bukkit.broadcastMessage(ChatColor.DARK_RED + "Viel Glück!");
                        de.pmcp.hungergames.pregame.isfreeze.isfreeze = false;
                        cancel();
                        starttimer = false;
                        basetimer = true;
                    }
                    sec--;

                }
          };
            runnable.runTaskTimer(main.getPlugin(), 0, 20);
        } while (starttimer == true);
        do {
            BukkitRunnable baseRunnable = new BukkitRunnable() {
                int baseSec = 1800;

                @Override
                public void run() {
                    switch (baseSec) {
                        case 1799:
                        /*db*/    Bukkit.broadcastMessage("AAAAA");
                        break;
                        case 1200:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "20 " + ChatColor.AQUA + "Minuten");
                            break;
                        case 900:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "15 " + ChatColor.AQUA + "Minuten");
                            break;
                        case 600:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10 " + ChatColor.AQUA + "Minuten");
                            break;
                        case 300:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "5 " + ChatColor.AQUA + "Minuten");
                            break;
                        case 60:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "1 " + ChatColor.AQUA + "Minute");
                            break;
                        case 30:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "30 " + ChatColor.AQUA + "Sekunden");
                            break;
                        case 10:
                            Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Die Hungergames enden in " + ChatColor.RED + "10 " + ChatColor.AQUA + "Sekunden");
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
                    if (baseSec == 0) {
                        Bukkit.broadcastMessage(ChatColor.GREEN + "[PMCP] " + ChatColor.AQUA + "Ihr könnt den Gommemode deaktivieren!");
                        de.pmcp.hungergames.pregame.isfreeze.isfreeze = true;
                        cancel();
                    }
                    baseSec--;
                } //end void
            }; //endrunnalbe
            baseRunnable.runTaskTimer(main.getPlugin(), 0, 20);
        } while (basetimer == true);

        return false;
    }
}
