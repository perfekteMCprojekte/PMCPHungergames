package de.pmcp.hungergames;

import de.pmcp.hungergames.CMDS.Adminmsg;
import de.pmcp.hungergames.timer.BaseTimer;
import de.pmcp.hungergames.pregame.Freeze;
import de.pmcp.hungergames.pregame.isfreeze;
import de.pmcp.hungergames.timer.LaunchTimer;
import de.pmcp.hungergames.timer.TabCompletion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {
    public static main plugin; //Plugin als Variable
    @Override
    public void onEnable() {  // Plugin startup logic
        plugin = this;
        Bukkit.getLogger().info("PMCP Hungergames Plugin ist nun gestartet.");

        //Befehle Registrieren
        newCommand("isfreeze", new isfreeze());
        newCommand("adminmsg", new Adminmsg());
        newCommand("launchtimer", new LaunchTimer(), new TabCompletion());
        newCommand("basetimer", new BaseTimer(), new TabCompletion());

        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Freeze(), this);
    }

    @Override
    public void onDisable() { // Plugin shutdown logic
    Bukkit.broadcastMessage(ChatColor.DARK_RED + "Hungergames fahren runter.");
    }


    //Einfache Befehls Erstellung
    private void newCommand(String command, CommandExecutor cmdfile) { Objects.requireNonNull(getCommand(command)).setExecutor(cmdfile); }
    private void newCommand(String command, CommandExecutor cmdfile, TabCompleter tabfile) {
        Objects.requireNonNull(getCommand(command)).setExecutor(cmdfile);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(tabfile);
    }
}
