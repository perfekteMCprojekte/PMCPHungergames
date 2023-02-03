package de.pmcp.hungergames;

//Hungergames Dateien
import de.pmcp.hungergames.CMDS.Adminmsg;
import de.pmcp.hungergames.game.InfoBar;
import de.pmcp.hungergames.game.death;
import de.pmcp.hungergames.timer.BaseTimer;
import de.pmcp.hungergames.timer.LaunchTimer;
import de.pmcp.hungergames.timer.TabCompletion;
import de.pmcp.hungergames.game.volcano;
import de.pmcp.hungergames.CMDS.freeze;
import de.pmcp.hungergames.tools.freezer;

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
        newCommand("launchtimer", new LaunchTimer(), new TabCompletion());
        newCommand("basetimer", new BaseTimer(), new TabCompletion());
        newCommand("freeze", new freeze());

        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        //pluginManager.registerEvents(new volcano(), this);
        pluginManager.registerEvents(new freezer(), this);
        freezer.effects();
        pluginManager.registerEvents(new death(), this);
        InfoBar.info();
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
