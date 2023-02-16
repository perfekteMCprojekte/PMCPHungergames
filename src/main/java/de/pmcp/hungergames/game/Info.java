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
            for (Player player : Bukkit.getOnlinePlayers())
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(
                        "§4Tag: §r§6" + Engine.day + "§r§c  ||  §r§6" + Death.deathCount + " §r§4" + ((Death.deathCount == 1)?  "Leiche gefunden" : "Leichen gefunden")));

        }, 0, 20); //Timings für Info
    }
    //Für Tagesnachrichten
    public static void nachrichten() {
        Bukkit.broadcastMessage("§8---------");
        Engine.world.playSound(Engine.center, Sound.BLOCK_BELL_USE, 1000F, 1F);
        Engine.world.playSound(Engine.center, Sound.BLOCK_BELL_RESONATE, 1000F, 0.8F);
        Bukkit.broadcastMessage( (DayTimer.time < 0) ? "§6§o§lLetztes mal bei Hungergames: " : "§6§o§lHier ist das erste Deutsche Fernsehen \nmit der Tagesschau:");

        //Nachrichtenausgabe
        if (Engine.news.isEmpty()) return;
        final int[] i = {0};
        Bukkit.getScheduler().runTaskTimer(main.plugin,task -> {
            Bukkit.broadcastMessage("§6| " + Engine.news.get(i[0]));
            Engine.world.playSound(Engine.center, Sound.BLOCK_NOTE_BLOCK_COW_BELL, 1000, 1);
            i[0]++;
            if (i[0] == Engine.news.size()) {
                task.cancel();
                Bukkit.broadcastMessage("§8---------");
            }
        }, 50, 20);
    }
}
