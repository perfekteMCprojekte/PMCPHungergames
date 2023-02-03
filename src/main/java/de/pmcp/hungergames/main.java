package de.pmcp.hungergames;

//Hungergames Dateien
import de.pmcp.hungergames.CMDS.Adminmsg;
import de.pmcp.hungergames.CMDS.Timer;
import de.pmcp.hungergames.game.InfoBar;
import de.pmcp.hungergames.game.Death;
import de.pmcp.hungergames.game.DayTimer;
import de.pmcp.hungergames.CMDS.TabCompletion;
import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.tools.Freezer;

import org.bukkit.Bukkit;
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
        Bukkit.getLogger().info("§2PMCP Hungergames Plugin ist nun gestartet.");

        //Befehle Registrieren
        newCommand("adminmsg", new Adminmsg());
        newCommand("freeze", new Freeze());
        newCommand("timer", new Timer(), new TabCompletion());

        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        //pluginManager.registerEvents(new volcano(), this);
        pluginManager.registerEvents(new Freezer(), this);
        Freezer.effects();
        pluginManager.registerEvents(new Death(), this);
        InfoBar.info();
        DayTimer daytimer = new DayTimer(); daytimer.timer();
    }

    @Override
    public void onDisable() { // Plugin shutdown logic
    Bukkit.broadcastMessage("§4Hungergames Plugin wird deaktiviert.");
    }


    //Einfache Befehls Erstellung
    private void newCommand(String command, CommandExecutor cmdfile) {
        Objects.requireNonNull(getCommand(command)).setExecutor(cmdfile);
    }
    private void newCommand(String command, CommandExecutor cmdfile, TabCompleter tabfile) {
        Objects.requireNonNull(getCommand(command)).setExecutor(cmdfile);
        Objects.requireNonNull(getCommand(command)).setTabCompleter(tabfile);
    }
}
