package de.pmcp.hungergames.game;

import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.Database;
import de.pmcp.hungergames.tools.Freezer;
import de.pmcp.hungergames.tools.Random;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class Engine implements Listener {
    public static int day = 0; // 0 vor beginn, startet bei 1
    HashMap<Player, String> chatHistory = new HashMap<Player, String>(); //Für Speicherung der letzten Nachricht eines Spielers

    public static void main() {
        //Funktionen beim Serverstart
        Freezer.main();
        Info.main();
        DayTimer.timer();

        Database.load_data(); //Variablen initialisieren

        //Hide Nametag team erstellen wenn nicht vorhanden
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        if (scoreboard.getTeam("hide_nametag") != null) return;
        Team team = scoreboard.registerNewTeam("hide_nametag");
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);

        //Hauptschleife für alle Events, usw.
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> {
            //Berstende Kniescheiben
            if (DayTimer.time < 600) return;
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (Random.rint(1, 2000) == 1) {
                    player.playSound(player, Sound.ENTITY_PLAYER_HURT, 1, 1);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Random.rint(33, 76), 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, Random.rint(14, 28), 255, false, false));
                    player.sendTitle("", "§8§oDeine Kniekappen bersten", 10, 20, 10);
                }
            }

            //Vulkanausbrüche
            if (Random.rint(1, 1200) == 1) Volcano.erupt(Random.rint(65, 346), Random.rint(1,5));

            //...

        }, 0, 20);
    }

    //Beim Beitritt eines Spielers
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.getScoreboard().getTeams().contains("hide_nametag")) //Spieler ins hide_nametags team hinzufügen
            player.getScoreboard().getTeam("hide_nametag").addEntry(player.getName());
    }

    @EventHandler
    public void chatProtectTM(AsyncPlayerChatEvent event) {
        chatHistory.remove(event.getPlayer());
        chatHistory.put(event.getPlayer(), event.getMessage());
    }
}
