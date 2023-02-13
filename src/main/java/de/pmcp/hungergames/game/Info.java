package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitScheduler;

public class Info implements Listener {

    public static void main() {
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
    public static void nachrichten() {
        Bukkit.broadcastMessage("§8-------");
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player, Sound.BLOCK_BELL_RESONATE, 10F, 0.8F);
            player.playSound(player, Sound.BLOCK_BELL_USE, 10F, 1F);
        }
        Bukkit.broadcastMessage( (DayTimer.time < 0) ? "§6Letztes mal bei Hungergames: " : "§6Hier ist das erste Deutsche Fernsehen \nmit der Tagesschau:");

        //Nachrichtenausgabe
        final int[] i = {0};
        int x = Death.deathMessages.size();
        Bukkit.getScheduler().runTaskTimer(main.plugin,task -> {
            Bukkit.broadcastMessage("§4| " + Death.deathMessages.get(i[0]));
            if (i[0] < x) i[0]++; else task.cancel();
        }, 50, 20);
        Bukkit.broadcastMessage("§8-------");
    }
}
