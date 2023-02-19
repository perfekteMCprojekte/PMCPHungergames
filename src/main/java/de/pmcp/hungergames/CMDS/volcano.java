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
        switch (args[0]) {
        case "erupt":
            if (Volcano.isErupting) { return true;}
            try {
                Volcano.erupt(Integer.parseInt(args[1])*4, Integer.parseInt(args[2]));
                sender.sendMessage("§e[§6Hungergames§e]§7 Eruption begonnen");
                return true;
            } catch (NumberFormatException e) { return false; }
        case "stop":
            Volcano.isErupting = false;
            sender.sendMessage("§e[§6Hungergames§e]§7 Eruption abgebrochen");
            return true;
        case "setpos":
            Volcano.loc = Bukkit.getPlayer(sender.getName()).getLocation();
            sender.sendMessage("§e[§6Hungergames§e]§7 Vulkankoordinaten gesetzt auf X:"+Volcano.loc.getBlockX()+", Y:"+Volcano.loc.getBlockY()+", Z:"+Volcano.loc.getBlockZ());
            return true;
        default:
            sender.sendMessage("§e[§6Hungergames§e]§7 volcano [erupt]<seconds><strength 1-5> , [stop] , [setpos]");
            break;
        }
        return true;
    }
}
