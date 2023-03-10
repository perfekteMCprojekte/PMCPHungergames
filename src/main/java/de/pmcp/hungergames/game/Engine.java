package de.pmcp.hungergames.game;

import de.pmcp.hungergames.CMDS.freeze;
import de.pmcp.hungergames.main;
import de.pmcp.hungergames.tools.Database;
import de.pmcp.hungergames.tools.Freezer;
import de.pmcp.hungergames.tools.Item;
import de.pmcp.hungergames.tools.Random;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.TimeSkipEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Engine implements Listener {
    public static int day = 0; //0 vor beginn, startet bei 1
    //HashMap<Player, ArrayList<String>> chatHistory = new HashMap<Player, ArrayList<String>>(); //Eventuell zukünftiger Spamschutz
    //count = animals.stream().filter(animal -> "bat".equals(animal)).count();
    HashMap<Player, String> chatHistory = new HashMap<>(); //Für Speicherung der letzten Nachricht eines Spielers
    String[] blockList = {"nigger", "niger ", "arschloch", "wichser", "wikser", "verpiss", "fick", "fik", "huhre", "hure", "deez", "nuts", "nutt", "österreich", "bist scheiße", "sie arschloch"}; //Blocklist für chat
    static ItemStack[] playerGifts = { //Random Drops für Spieler
            Item.create(Material.TNT, Random.rint(3,7), "§lSprengstoff", "Trotyll löst automatisch aus, \nwenn Spieler in der nähe sind"),
            Item.create(Material.BREAD, Random.rint(12, 32), "brod", "essbar"),
            Item.create(Material.FLINT_AND_STEEL, Random.rint(1, 3), "Feuerzeug", "eine zündende Idee"),
            Item.create(Material.ARROW, Random.rint(19, 46), "Pfeil", "ein nützliches Werkzeug"),
            Item.create(Material.RED_CANDLE, 1, "§cHela Gewürzketchup", "Eine besänftigung für den Imperator"),
            Item.create(Material.AXOLOTL_BUCKET, 1, "Fischsuppe", "Trink es!"),
            Item.create(Material.FIRE_CHARGE, Random.rint(2, 6), "SSss...puff", "."),
            Item.create(Material.GUNPOWDER, Random.rint(2, 5), "§lPulverisation", "Hilft dir beim verschwinden"),
    };
    public static ArrayList<String> news = new ArrayList<>(); //Nachrichtenspeicher für Events
    public static ArrayList<int[]> explosives = new ArrayList<>(); //Liste der Koordinaten der Trotyll Blöcke zum auslösen
    public static World world = Bukkit.getWorlds().get(0); //Die Welt
    public static Location center = new Location(world, 0, 80, 0); //Location der Weltmitte

    //Generelles Serverzeug
    public static void main() {
        Database.load_data(); //Variablen initialisieren

        //Funktionen beim Serverstart
        Freezer.main();
        Info.main();
        Timer.timer();

        //Hide Nametag team erstellen wenn nicht vorhanden
        Scoreboard scoreboard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getMainScoreboard();
        if (scoreboard.getTeam("hide_nametag") == null) {
            Team team = scoreboard.registerNewTeam("hide_nametag");
            team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        }

        //Hauptschleife für alle Events, usw.
        BukkitScheduler scheduler = Bukkit.getScheduler();
        scheduler.runTaskTimer(main.plugin, task -> {
            if (freeze.isfreeze) return;

            //Berstende Kniescheiben
            if (Timer.time > 600) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isOp()) continue;
                if (Random.rint(1, 3600) == 1) {
                    player.playSound(player, Sound.ENTITY_PLAYER_HURT, 1, 1);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, Random.rint(33, 76)*20, 1, false, false));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Random.rint(14, 28)*20, 0, false, false));
                    if (player.getHealth() > 2) player.setHealth(player.getHealth()-1);
                    player.sendTitle("", "§8§oDeine Kniekappen bersten", 10, 20, 10);
                    player.setMetadata("no-kneecaps", new FixedMetadataValue(main.plugin, true));
                }
            }}

            //Vulkanausbrüche
            if (Timer.time > 120 && Random.rint(1, 1200) == 1) Volcano.erupt(Random.rint(65, 346), Random.rint(1,5));

            //Goodies
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isOp()) continue;
                if (Random.rint(1, 2135) == 1) { //Lost für Spieler aus
                    ItemStack item = playerGifts[Random.rint(0, playerGifts.length)]; //Lost Drop aus
                    player.getInventory().addItem(item);
                    player.sendMessage("§e[§6Hungergames§e] §g Due erhälst " + item.getItemMeta().getDisplayName() + " von einem unbekannten Sponsor");
                    news.add("§e" + player.getName() + " erhielt " + item.getItemMeta().getDisplayName() + " von einem unbekannten Sponsor");
                }
            }

            //Sprengstoff
            for (int[] coords : explosives) {
                Block block = world.getBlockAt(coords[0], coords[1], coords[2]);
                if (block.getType() != Material.TNT || !block.hasMetadata("owner")) { //Überprüfen, ob sprengfalle
                    explosives.remove(coords); //Geister entfernen
                    continue;
                }
                //Spieler von Sprengfallen detektieren
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isOp() || player == block.getMetadata("owner").get(0).value()) continue;
                    if (player.getLocation().distance(block.getLocation()) < 4) {
                        block.setType(Material.AIR);
                        world.spawnParticle(Particle.EXPLOSION_LARGE, block.getLocation(), 14, 1,1,1);
                        world.createExplosion(block.getLocation(), 5);
                        explosives.remove(coords);
                        break;
                    } } }

        }, 0, 20);
    }


    //Beim Beitritt eines Spielers
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        world.playSound(center, Sound.BLOCK_BEACON_ACTIVATE, 1000, 2);
        if (player.isOp()) Info.bar.addPlayer(player); //Infobar für ops anzeigen
        if (!player.getScoreboard().getTeams().contains("hide_nametag")) //Spieler ins hide_nametags team hinzufügen
            player.getScoreboard().getTeam("hide_nametag").addEntry(player.getName());
    }

    //Schlafverhindeung
    @EventHandler
    public void provideSleep(TimeSkipEvent event) {
        if (event.getSkipReason().equals(TimeSkipEvent.SkipReason.NIGHT_SKIP)) event.setCancelled(true);
    }

    //Schützt vor 3fach wiederholten eingaben und notdürftig vor beleidigungen
    @EventHandler
    public void chatProtectTM(AsyncPlayerChatEvent event) {
        //Beleidigungsschutz
        String msg = event.getMessage().toLowerCase();
        Player player = event.getPlayer();
        if (ArrayUtils.contains(blockList, msg)) {
            player.sendMessage("§e[§6Hungergames§e] §7Gemäßige deine Ausdrucksweise!");
            Bukkit.broadcastMessage("§e[§6Hungergames§e] §7"+ player.getName() +"'s Nachricht wurde vorsichtshalber blockiert!");
            event.setCancelled(true);
        }
        if (player.isOp()) return;

        //Spamschutz
        if (chatHistory.containsValue(msg)) {
            chatHistory.replace(player, msg+"?MST?");
        } else if (chatHistory.containsValue(msg + "?MST?")) {
            player.sendMessage("§e[§6Hungergames§e] §7Unterlasse bitte den Spam und bedenke das umgehen des Spam-Schutzes folgen hat!");
            event.setCancelled(true);
        } else {
            chatHistory.remove(player);
            chatHistory.put(event.getPlayer(), msg);
        }
    }

    //Sprengstoff platziert
    @EventHandler
    public void place_explosive(BlockPlaceEvent event) {
        if (event.getItemInHand().getType() != Material.TNT) return; //auf sprengstoff überprüfen
        else if (!event.getItemInHand().getItemMeta().getDisplayName().equals("§lSprengstoff")) return;
        Block block = event.getBlockPlaced();

        int[] coords = {block.getX(), block.getY(), block.getZ()}; //sprengstoff registrieren und events ausführen
        explosives.add(coords);
        event.getBlockPlaced().setMetadata("owner", new FixedMetadataValue(main.plugin, event.getPlayer()));
        event.getPlayer().sendMessage("§7 Du hast eine Sprengfalle Platziert");
        world.playSound(block.getLocation(), Sound.BLOCK_NOTE_BLOCK_BIT, 1F, 0.7F);
    }

    //Spezial-Items nutzen
    @EventHandler
    public void use_kneecaps(PlayerInteractEvent event) {
        if (!event.hasItem() || !event.getItem().hasItemMeta()) return;
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        //Kniekappen Ersetzung
        if (item.getItemMeta().getDisplayName().equals("§cKniescheibe")) {
            if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            else if (!player.hasMetadata("no-kneecaps") && !player.isOp()) {
                player.sendMessage("§8Du kannst keine Intakten Kniekappen ersetzen");
                return;
            }

            player.removePotionEffect(PotionEffectType.SLOW);
            player.removePotionEffect(PotionEffectType.JUMP);
            player.sendMessage("§cDu hast erfolgreich deine Kniekappen ersetzt");

            player.removeMetadata("no-kneecaps", main.plugin);
            item.setAmount(item.getAmount() - 1);
            event.setCancelled(true);
        }
        //Ketchup Nutzung
        else if (item.getItemMeta().getDisplayName().equals("§cHela Gewürzketchup")) {
            if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, Random.rint(32, 68)*20, 0, false, false));
            player.addPotionEffect(new PotionEffect(PotionEffectType.HEALTH_BOOST, Random.rint(45, 93)*20, 0, false, false));
            player.sendMessage("§7Du verspürst die überragende Wirkung von <insert name>");
            item.setAmount(item.getAmount() - 1);
            event.setCancelled(true);
        }
        //Pulverisation
        else if (item.getItemMeta().getDisplayName().equals("§lPulverisation")) {
            if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
            Engine.world.spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 5, 1, 1, 1);
            Engine.world.spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 70, 3, 1, 3);
            item.setAmount(item.getAmount() - 1);
            event.setCancelled(true);
        }

    }

    //Knie-Op extra
    //@EventHandler
    //public void knee_surgery(PlayerInteractAtEntityEvent event) {
    //    if (event.getRightClicked().getType() != org.bukkit.entity.EntityType.PLAYER) return;
    //    Player player = event.getPlayer();
    //    Player victim = (Player) event.getRightClicked();
    //    ItemStack item = event.getPlayer().getItemInUse();
    //    if (item != null && item.getType() == Material.SHEARS) return;
    //    Bukkit.broadcastMessage("e");
    //    event.setCancelled(true);
    //}
}
