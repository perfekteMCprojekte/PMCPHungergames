package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.Death;
import de.pmcp.hungergames.game.Engine;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class engine implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String string, @NotNull String[] args) {
        if (args.length == 0) return false;

        switch (args[0]) {
        case "setday":
            try {
                Engine.day = Integer.parseInt(args[1]);
                Bukkit.broadcastMessage("§e[§6Hungergames§e]§7 Tag auf "+Engine.day+" gesetzt");
                return true;
            } catch (NumberFormatException e) { return false; }
        case "setdeaths":
            try {
                Death.deathCount = Integer.parseInt(args[1]);
                sender.sendMessage("§e[§6Hungergames§e]§7 Tode auf "+Death.deathCount+" gesetzt");
                return true;
            } catch (NumberFormatException e) { return false; }
        case "shownews":
            sender.sendMessage("§7Nachrichten: §8"+Engine.news);
            return true;
        case "clearnews":
            if (args.length == 2 && args[1].equals("confirm")){
                Engine.news.clear();
                Bukkit.broadcastMessage("§e[§6Hungergames§e]§7 Nachrichten wurden zurückgesetzt");
            }
            else sender.sendMessage("§7 Um löschen der Nachrichten zu bestätigen nutze \n§f/engine clearnews confirm §7");
            return true;
        default:
            sender.sendMessage("§e[§6Hungergames§e]§7 Ungültige Aktion: §o" + args[0]);
            return false;
        }
    }
}
