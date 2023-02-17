package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.Volcano;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class volcano implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String string, @NotNull String[] args) {
        if (args[0].equals("erupt")) {
            Volcano.erupt(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
            sender.sendMessage("§e[§6Hungergames§e]§7 Eruption begonnen");
            return true;
        }
        else if (args[0].equals("stop")) {
            Volcano.isErupting = false;
            sender.sendMessage("§e[§6Hungergames§e]§7 Eruption abgebrochen");
            return true;
        }
        else if (args[0].equals("setpos")) {
            Volcano.loc = Bukkit.getPlayer(sender.getName()).getLocation();
            sender.sendMessage("§e[§6Hungergames§e]§7 Vulkankoordinaten gesetzt");
            return true;
        }
        else
            sender.sendMessage("§e[§6Hungergames§e]§7 volcano [erupt]<seconds><strength 1-5> , [stop] , [setpos]");
        return true;
    }
}
