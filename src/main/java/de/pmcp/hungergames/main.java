package de.pmcp.hungergames;

import de.pmcp.hungergames.timer.BaseTimer;
import de.pmcp.hungergames.pregame.Freeze;
import de.pmcp.hungergames.timer.LaunchTimer;
import de.pmcp.hungergames.pregame.isfreeze;
import org.bukkit.Bukkit;
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
        Objects.requireNonNull(this.getCommand("isfreeze")).setExecutor(new isfreeze());
        Objects.requireNonNull(this.getCommand("launchtimer")).setExecutor(new LaunchTimer());
        //Objects.requireNonNull(this.getCommand("basetimer")).setExecutor(new BaseTimer());

        //Wird ersetzt
        Objects.requireNonNull(this.getCommand("PostTimer")).setExecutor(new BaseTimer());

        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Freeze(), this);
    }

    @Override
    public void onDisable() { // Plugin shutdown logic

    }
}
