package de.pmcp.hungergames.CMDS;

import de.pmcp.hungergames.game.Timer;
import de.pmcp.hungergames.game.Volcano;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//Autovervollständigung für Timerbefehle
public class TabCompletion implements TabCompleter {
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        int len = args.length;
        switch (command.getName()) {
        case "timer": //Timer
            if (len == 1) return List.of((Timer.timerPaused ? "run" : "pause"), "set", "status", "setday");
            else if (len == 2) {
                if (args[0].equals("set")) return List.of("-600", "-60", "0", "600", "1200", "1800", "2400", "2395");
            }
            break;
        case "volcano": //Vulkansteuerung
            if (len==1) return List.of((Volcano.isErupting) ? "stop" : "erupt", "setpos");
            else if (len <= 3 && args[0].equals("erupt"))
                return (len==2) ? List.of("10", "60", "150", "300") : List.of("1", "2", "3", "4", "5");
            break;
        case "engine": //Werte/Enginekontrolle
            if (len==1) return List.of("setday", "setdeaths", "clearnews", "shownews", "reduce");
            else if (len==2) {
                if (args[0].equals("setday")) return List.of("0", "3", "7");
                else if (args[0].equals("setdeaths")) return List.of("0");
            }
            break;
        }
        return List.of();
    }
}
