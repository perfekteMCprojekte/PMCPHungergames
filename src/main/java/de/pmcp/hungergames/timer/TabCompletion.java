package de.pmcp.hungergames.timer;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;

//Autovervollständigung für Timerbefehle
public class TabCompletion implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (command.getName().equals("launchtimer")) {
            if (args.length == 1)
                return Arrays.asList((LaunchTimer.timerActive ? "cancel" : "start"), (LaunchTimer.timerPaused ? "resume" : "pause"), "set", "status");
            else if (args[0].equals("set") && args.length == 2)
                return Arrays.asList("10", "60", "300", "600", "1800");
        }
        else if (command.getName().equals("basetimer")) {
            if (args.length == 1)
                return Arrays.asList((BaseTimer.timerActive ? "cancel" : "start"), (BaseTimer.timerPaused ? "resume" : "pause"), "set", "status");
            else if (args[0].equals("set") && args.length == 2)
                return Arrays.asList("1200", "2400", "3600", "4800", "6000", "7200", "8400");

        }
        return Arrays.asList();
    }
}
