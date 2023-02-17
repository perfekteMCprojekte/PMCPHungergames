package de.pmcp.hungergames.game;

import de.pmcp.hungergames.tools.Item;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

public class Death implements Listener {
    public static int deathCount;

    //Sucht einen zufälligen String aus + leerzeichen davor
    public String gre(String... list) { return " " + list[Random.rint(0, list.length-1)]; }

    //Generiert Nachricht aus
    public void create_death_message(String victim, String killer, String cause) {
        String message = victim + gre("starb auf unbekannte Art", "kam ums Leben", "erlitt einen tragischen Tod", "verunglückte tödlich");
        Bukkit.broadcastMessage("Mörder: " + killer); Bukkit.broadcastMessage("Opfer: " + victim); Bukkit.broadcastMessage("Grund: " + cause); //Debug
        if (!killer.equals("")) {
            if (cause.contains("fell out of the world")) message = gre("hehe, da hat jemand /kill für "+victim+" genutzt", victim+"wurde zufällig tod aufgefunden");
            else if (cause.contains("burned")) message = victim+" war Feuer und Flamme ";
        }
        else {
            if (cause.contains("was slain by")) message = gre(victim+" hatte eine unangenehme Begegnung mit "+killer, killer+" braachte "+victim+" um");
            else if (cause.contains("shot by")) message = gre(killer+" führte an "+victim+"den Murrika Move durch");

        }
        message = "§c" + message;
        Engine.news.add(message);
    }


    @EventHandler
    public void death(@NotNull PlayerDeathEvent event) { //Bei Spielertod
        Player player = event.getEntity();
        if(player.isOp()) return; //BITTE ENTKOMMENTIEREN

        //Inhalte der Todesnachricht
        String victim, cause, killer;
        victim = player.getName();
        killer = (player.getKiller() == null) ? "" : player.getKiller().getName();
        cause = event.getDeathMessage().replace(victim, "").replace(killer, "").strip();
        //Todesnachricht kreieren und speichern
        create_death_message(victim, killer, cause);

        deathCount++;

        //Bei Tod
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 100F, 0.5F);
        player.getWorld().dropItemNaturally(player.getLocation(), Item.create(Material.EGG, 2, "Kniescheibe", "Eine wertvolle Ressource\nVon:" + player.getDisplayName()));
        Volcano.erupt(Random.rint(40, 85), Random.rint(1,2));

        event.setDeathMessage(null);
        //Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(player.getUniqueId()), "§4Du bist Tod!\n§g Danke fürs Spielen.", null, null);
        //player.kickPlayer("§4Du bist gestorben!\n§gDu hast 'gut' gekämpft.\nDanke fürs Spielen");
    }
}
