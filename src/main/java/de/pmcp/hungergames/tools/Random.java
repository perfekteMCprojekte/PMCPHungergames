package de.pmcp.hungergames.tools;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Random {
    //Integer
    public static int rint(int min, int max) {
        //Bukkit.broadcastMessage(min + ", "+ max);
        if (min >= max) {Bukkit.getLogger().info("Fehler beim generieren random ints: Bitte Code überprüfen"); return 0;}
        int number = new java.util.Random().nextInt((max - min) + 1) + min;
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
    //Double
    public static double rouble(double min, double max) {
        if (min >= max) {Bukkit.getLogger().info("Fehler beim generieren random doubles: Bitte Code überprüfen"); return 0;}
        double number = min + (max -min) * new java.util.Random().nextDouble();
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
    //True/False
    public static boolean coin() {
        return (rint(0, 1) == 0);
    }

    //Random Spieler
    //public static Player player() {
    //    int index = Random.rint(0, Bukkit.getOnlinePlayers().size());
    //    Player playa = Bukkit.getOnlinePlayers().toArray()[index];
    //    return playa;
    //}
}
