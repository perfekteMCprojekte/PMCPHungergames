package de.pmcp.hungergames.game;

import de.pmcp.hungergames.tools.Database;
import de.pmcp.hungergames.tools.Item;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Death implements Listener {
    public static int deathCount;
    public static ArrayList<String> deathMessages = new ArrayList<String>(); //Todesnachrichtenspeicher

    public void create_death_message(String victim, String killer, String cause) {
        String message = "ERROR";
        Bukkit.broadcastMessage("->"+cause+"<-");
        if (!killer.equals("")) {
            if (cause.contains("burned")) message = victim + " war Feuer und Flamme ";
            else message = victim + " erlitt einen tragischen Unfall ";
        }
        else {
            if (cause.contains("was slain by")) message = victim + " hatte eine unangenehme Begegnung mit " + killer;
        }
        deathMessages.add(message);
    }


    @EventHandler
    public void death(@NotNull PlayerDeathEvent event) { //Bei Spielertod
        Player player = event.getEntity();
        //if(player.isOp()) return; //BITTE ENTKOMMENTIEREN
        //Inhalte der Todesnachricht
        String victim, cause, killer;
        victim = player.toString();
        killer = (player.getKiller() == null) ? "" : player.getKiller().toString();
        cause = event.getDeathMessage().replace(victim, "").replace(killer, "");
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
