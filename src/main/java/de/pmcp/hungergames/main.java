package de.pmcp.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {
boolean freeze = false;
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getLogger().info("PMCP Hungergames ist nun gestartet.");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
