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
                return Arrays.asList((DayTimer.timerPaused ? "run" : "pause"), "set", "status", "setday");
            else if (args.length == 2){
                if (args[0].equals("set")) return Arrays.asList("-600", "-60", "0", "600", "1200", "1800", "2400", "2395");
                if (args[0].equals("setday")) return Arrays.asList("0", "3", "7");
            }

        }
        return Arrays.asList();
    }
}
