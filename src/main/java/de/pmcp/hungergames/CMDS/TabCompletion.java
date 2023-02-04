package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.DayTimer;
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
        if (command.getName().equals("timer")) {
            if (args.length == 1)
                return Arrays.asList((DayTimer.timerPaused ? "run" : "pause"), "set", "status");
            else if (args[0].equals("set") && args.length == 2)
                return Arrays.asList("-600", "-60", "0", "600", "1200", "1800", "2400");

        }
        return Arrays.asList();
    }
}
