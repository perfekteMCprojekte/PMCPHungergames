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
    String lastDeath = "";

    //Sucht einen zufälligen String aus + leerzeichen davor
    public String gre(String... list) { if (list.length==1) return list[0]; else return " " + list[Random.rint(0, list.length-1)]; }

    //Generiert Todesnachrichten
    public void create_death_message(String victim, String killer, String cause) {
        String message = victim + gre("starb auf unbekannte Art", "kam ums Leben", "erlitt einen tragischen Tod", "verunglückte tödlich"); //defaults
        //Bukkit.broadcastMessage("Mörder: " + killer); Bukkit.broadcastMessage("Opfer: " + victim); Bukkit.broadcastMessage("Grund: " + cause); //Debug

        if (killer.equals("ERROR")) { //Ohne Mörder
            if (cause.contains("fell out of the world")) message = gre("hehe, da hat jemand /kill für "+victim+" genutzt", victim+"wurde zufällig tod aufgefunden", victim+" starb auf absolut unerklärliche weise §lLUKAS?");
            else if (cause.contains("was pricked to death")) message = gre(victim+"+kaktus+L", victim+" warf sich in einen Kaktus"); //Kaktus
            else if (cause.contains("drowned")) message = gre(victim+" ging unter wie ein fetter Fels", "Jeder Fisch ist fähiger als "+victim, victim+" hat vergessen das sie/er Luft braucht"); //Ertrinken
            else if (cause.contains("blew up") || cause.contains("blown up by")) message = gre(victim+" flog in die Luft", "Eine Explosion erfasste "+victim, victim+" erfuhr eine Druckwelle"); //Pfeil
            else if (cause.contains("hit the ground")) message = gre(victim+" stolperte", victim+" küsste den Boden"); //Fallen
            else if (cause.contains("fell")) message = gre(victim+" genoss die Schwerelosigkeit", victim+" sprang", victim+" fand eine Abgrund und wollte nicht mehr"); //Fallschaden hoch
            else if (cause.contains("was impaled on a stalagmite")) message = gre("Die Stalagmite, oder was Stalagtite?, egal, war eine gute Lösung für "+victim); //Stalagmite
            else if (cause.contains("was squashed")) message = gre("Alles Gute kommt von oben, so auch bei "+victim, "Wir haben das Thema "+victim+" begraben"); //Fallender block
            else if (cause.contains("was skewered")) message = gre(victim+" wurde zum Grillspieß, muss ich das weiter ausführen?"); //Stalagtite
            else if (cause.contains("went up in flames")) message = gre(victim+" war zu warm", victim+" wusste genau wo es brannte"); //In Feuer
            else if (cause.contains("burned to death")) message = gre(victim+" war feuer und flamme"); //verbrennen
            else if (cause.contains("went off with a bang")) message = gre(victim+" liebt böller", "Ein Feuerwerk ging für "+victim+" auf!"); //feuerwerk
            else if (cause.contains("tried to swim in lava")) message = gre(victim+" wollte sich eine Vorstellung von Hawaii", "in Freiheitseinheiten erschien "+victim+" die Temperatur viel niedriger"); //lava
            else if (cause.contains("was struck by lightning")) message = gre("Eine elektrisierende Erfahrung wurde "+victim+" zuteil", "Jemand hätte "+victim+" von Leitungssicherheit erzählen sollen"); //blitz
            else if (cause.contains("the floor was lava")) message = gre(victim+" trat auf einen heißen Stein", victim+" wollte sich eine Vorstellung von Hawaii machen"); //magmablock
            else if (cause.contains("was killed by magic")) message = gre(victim+" starb imaginär", "ERROR"); //magie
            else if (cause.contains("froze to death")) message = gre(victim+" zog sich eine Erkältung zu", "Wer außer "+victim+" ist so blöd in Schnee stecken zu bleiben?"); //puderschnee
            else if (cause.contains("suffocated")) message = gre(victim+" ging mit dem Kopf durch die Wand, versuchte es zumindest"); //ersticken in block
            else if (cause.contains("was squished")) message = gre("Die Massentierhaltung"); //entity cap
            else if (cause.contains("was poked to death")) message = gre("Ein Busch (er)stach "+victim); //beerenbusch

        }
        else { //Mit Mörder
            if (cause.contains("was shot by")) message = gre(victim+" wurde von "+killer+" ge360noscoped", killer+" spielte murika mit "+victim); //Pfeil
            else if (cause.contains("walked into a cactus")) message = gre("Beim Kampf mit "+killer+" machte "+victim+" bekanntschaft mit einem Kaktus"); //Kaktus
            else if (cause.contains("drowned")) message = gre(killer+" ließ "+victim+" untergehen"); //Ertrinken
            else if (cause.contains("was blown up")) message = gre(killer+" verarbeitete "+victim+" zu Hela Gewürzketchup", killer+" sprengte "+victim); //Explosion
            else if (cause.contains("hit the ground")) message = gre(killer+" stürzte "+victim+" von einer Klippe", victim+" sah keinen Ausweg vor "+killer); //Fallen
            else if (cause.contains("was impaled on a stalagmite")) message = gre("selbst sich aufzuspießen war "+victim+" lieber als mit "+killer+"zusammen zu sein"); //Stalagmite
            else if (cause.contains("walked into fire")) message = gre(victim+" wurde von "+killer+" eingeäschert"); //kampf, dann feuer
            else if (cause.contains("was burnt to a crisp")) message = gre(killer+"röstete"+victim); //kampf während feuer
            else if (cause.contains("went off with a bang")) message = gre(killer+" zündete ein Feuerwerk für "+victim); //feuerwerk
            else if (cause.contains("tried to swim in lava")) message = gre(killer+" war eine zu große Beleidigung für "+victim); //lava
            else if (cause.contains("was struck by lightning")) message = gre("Im Kampf gegen "+killer+" ereilte "+victim+" ein Geistesblitz"); //blitz
            else if (cause.contains("walked into danger zone")) message = gre(victim+" spielte Heißluftballon für "+killer); //magmablock
            else if (cause.contains("was killed by magic")) message = gre("ERROR"); //magie
            else if (cause.contains("was killed by") && cause.contains("using")) message = gre("Was hast du getan, "+killer+"?"); //tod mit magie
            else if (cause.contains("was frozen to death")) message = gre("<"+victim+"> Brrrrrrrr..."); //puderschnee
            else if (cause.contains("was slain by")) message = gre(victim+" hatte eine unangenehme Begegnung mit "+killer, killer+" versuchte den Messerkill an "+victim); //UMBRINGEN
            else if (cause.contains("suffocated")) message = gre(victim+" fand wollte sich vor "+killer+" in solides Material retten"); //ersticken in block
            else if (cause.contains("was squished")) message = gre(killer+" wurde es zu eng mit "+victim+", er drehte durch"); //entity cap nach kampf
            else if (cause.contains("was poked to death")) message = gre("Wahrscheinlich "+killer+" hat "+victim+"in einen Busch geschubst"); //beerenbusch
            else if (cause.contains("trying to hurt")) message = gre("Für "+victim+" war "+killer+" ein wahrer Kaktus"); //dornen
            else if (cause.contains("was impaled by")) message = gre(killer+" entdeckte den Kannibalismus und aß"+victim, victim+" lernte "+killer+" als absoluten Spießer kennen"); //dreizack
        }
        message = "§c" + message;
        Engine.news.add(message);
    }

    //Spielertod verarbeiten
    @EventHandler
    public void death(@NotNull PlayerDeathEvent event) { //Bei Spielertod
        Player player = event.getEntity();
        if(player.isOp()) {event.setDeathMessage(null); return;} //BITTE ENTKOMMENTIEREN
        deathCount++;

        //Todesnachricht kreieren und speichern
        create_death_message(player.getName(), (player.getKiller()==null)?"ERROR":player.getKiller().getName(), event.getDeathMessage());

        //Effekte
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 100F, 0.5F);
        player.getWorld().dropItemNaturally(player.getLocation(), Item.create(Material.EGG, 2, "§cKniescheibe", "Eine wertvolle Ressource\nVon:" + player.getDisplayName()));
        Volcano.erupt(Random.rint(40, 85), Random.rint(1,2));

        //Eliminieren!
        event.setDeathMessage(null);
        Bukkit.getBanList(BanList.Type.NAME).addBan(String.valueOf(player.getUniqueId()), "§4Du bist Tod!\n§g Danke fürs Spielen.", null, null);
        player.kickPlayer("§4Du bist gestorben!\n§gDu hast 'gut' gekämpft.\nDanke fürs Spielen");

        if (event.getDeathMessage().contains(lastDeath)) return;
        lastDeath = event.getDeathMessage();
    }
}
