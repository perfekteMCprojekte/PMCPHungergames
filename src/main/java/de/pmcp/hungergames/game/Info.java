package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class Info implements Listener {

    public static void info() {
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> { //Timer für timer
            //Füllt die Actionbar mit "Tag" und "Todesanzahl" aus
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Death.deathCount != 1) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Tag: §r§6" + Engine.day + " §r§4|| " + "§r§6" + Death.deathCount + " §r§4Leichen gefunden"));
                } else {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§4Tag: §6§l" + Engine.day + " §r§4|| " + "§r§6" + Death.deathCount + " §r§4Leiche gefunden"));
                }
            }
        }, 0, 20); //Timings für Info
    }
    //Für Tagesnachrichten
    public static void DailyNews() {
            Bukkit.broadcastMessage("\n§6§nDaily News: ");
            for (String deathMessageLoop : Death.deathArray) {
                Bukkit.broadcastMessage("§4" + deathMessageLoop + "\n");
        }
    }
}
