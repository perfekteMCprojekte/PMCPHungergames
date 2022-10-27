package de.pmcp.hungergames;

import de.pmcp.hungergames.timer.BaseTimer;
import de.pmcp.hungergames.pregame.Freeze;
import de.pmcp.hungergames.timer.StartTimer;
import de.pmcp.hungergames.pregame.isfreeze;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class main extends JavaPlugin {
    public static main plugin;
    @Override
    public void onEnable() {
        plugin = this;
        // Plugin startup logic
        Bukkit.getLogger().info("PMCP Hungergames ist nun gestartet.");
        //cmd register
        Objects.requireNonNull(this.getCommand("isfreeze")).setExecutor(new isfreeze());
        Objects.requireNonNull(this.getCommand("starttimer")).setExecutor(new StartTimer());
        Objects.requireNonNull(this.getCommand("PostTimer")).setExecutor(new BaseTimer());

        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Freeze(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
