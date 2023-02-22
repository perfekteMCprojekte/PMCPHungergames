package de.pmcp.hungergames.CMDS;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;


public class help implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmdLabel, @NotNull String[] args) {
        int page = 1;
        //Versuche Seitenzahl auszulesen
        try { if (args.length > 0) page = Integer.parseInt(args[0]); }
        catch (NumberFormatException e) { return false; }

        //Den richtigen Text ausgeben
        if (cmdLabel.equals("help")) {
            sender.sendMessage("§c§n§ohelp/explanation for PMCP Hungergames project\n" +
                    "§7§oFür die Deutsche Version nutze /hilfe\n" +
                    "§6 This project is a simple adaptation of Hungergames. Your goal is, to be the last person alive on the island. To archive this, you are allowed to team with at most one player. The project takes place once a week in 40-minute sessions (one day). When dying, you are kicked out of the project. Before the day starts you are informed about some events that took place last week and after the day about some that happened at the ended day. Note, that not only hiding underground, but also Kneecaps can be really helpful in some situations. Everything else, you surely will discover yourself by time.\n" +
                    "§4§oPMCP team is wishing fun");
        }
        else if (cmdLabel.equals("hilfe")) {
            sender.sendMessage("§c§n§oHilfe/Erklärung für das PMCP Hungergames Projekt\n" +
                    "§7§oFor a English version please use /help\n" +
                    "§6 Es handelt sich um eine simple Adaption von Hungergames. Ziel ist es, die letzte überlebende Person auf der Insel zu sein. Um das zu erreichen kannst du dich zunächst auch mit max. einer Person verbünden. Das Projekt findet einmal wöchentlich in 40-minütigen Sessions statt, dies entspricht einem Hungergames Tag. Bei Tod scheidest du aus dem Projekt aus. Vor Beginn des Tages wirst du über einige Ereignisse der letzten Woche informiert, nach Tagesende über die des geendeten Tages. Beachte, das nicht nur das unterirdische Verstecken, sondern auch Kniescheiben in manchen Situationen von großem Nutzen sein können. Alles weitere wirst du früher oder später schon selbst herausfinden.\n" +
                    "§4§oDas PMCP Team wünscht viel Spaß (/pmcp)");
        }
        else {
            sender.sendMessage("§7Über PMCP:\n" +
                    "frag selbst den Imperator\n" +
                    "hilfe ich brauche Hilfe beim Programmieren wer kann Java\n" +
                    "das Bauteam ist übrigens auch stark unterbesetzt.");
        }
        return true;
    }
}
