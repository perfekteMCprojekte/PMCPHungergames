package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.Timer;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class freeze implements CommandExecutor {
    public static boolean isfreeze = true;
    //Wird bei Spielstart deaktiviert

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (isfreeze) {
            isfreeze = false;
            Timer.timerPaused = false;
            Bukkit.getLogger().info("§7Es wurde entpauseriert.");
        } else {
            isfreeze = true;
            Timer.timerPaused = true;
            Bukkit.getLogger().info("§7Es wurde pauseriert.");
        }
        return false;
    }
}
