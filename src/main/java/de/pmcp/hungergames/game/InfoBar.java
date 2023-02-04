package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InfoBar {
    public static void info() {
        new BukkitRunnable() {
            @Override public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Tag: §6§l" + "...   " + "   ..." + "§r§4 Leichen gefunden"));
                }
            }
        }.runTaskTimer(main.plugin, 0, 10);
    }
}
