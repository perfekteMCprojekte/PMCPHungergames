package de.pmcp.hungergames;

//Hungergames Dateien
import de.pmcp.hungergames.CMDS.Adminmsg;
import de.pmcp.hungergames.CMDS.Timer;
import de.pmcp.hungergames.game.*;
import de.pmcp.hungergames.CMDS.TabCompletion;
import de.pmcp.hungergames.CMDS.Freeze;
import de.pmcp.hungergames.tools.Freezer;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {
    public static main plugin; //Plugin als Variable
    @Override
    public void onEnable() {  // Plugin startup logic
        plugin = this;
        Bukkit.getLogger().info("§2PMCP Hungergames Plugin ist nun gestartet.");

        //Befehle registrieren
        newCommand("adminmsg", new Adminmsg());
        newCommand("freeze", new Freeze());
        newCommand("timer", new Timer(), new TabCompletion());

        //Event Listener registrieren
        newEvent(new Volcano());
        newEvent(new Freezer());
        newEvent(new Death());
        newEvent(new Engine());

        Engine.main(); //Alles was beim Serverstart ausgeführt wird
    }

    @Override
    public void onDisable() { // Plugin shutdown logic
    Bukkit.broadcastMessage("§4Hungergames Plugin wird deaktiviert.");
    }

    //Eventregisitrierung
    private void newEvent(Listener eventfile) {
        Bukkit.getPluginManager().registerEvents(eventfile, this);
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
