package de.pmcp.hungergames;

import de.pmcp.hungergames.pregame.Freeze;
import de.pmcp.hungergames.pregame.isfreeze;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("PMCP Hungergames ist nun gestartet.");
        //cmd register
        this.getCommand("isfreeze").setExecutor(new isfreeze());
        //listener register
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new Freeze(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
