package de.pmcp.hungergames.tools;

import org.bukkit.Bukkit;

public class Random {
    public static int rint(int min, int max) {
        //Bukkit.broadcastMessage(min + ", "+ max);
        if (min >= max) {Bukkit.getLogger().info("Fehler beim generieren random ints: Bitte Code überprüfen"); return 0;}
        int number = new java.util.Random().nextInt((max - min) + 1) + min;
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
    public static double rouble(double min, double max) {
        if (min >= max) {Bukkit.getLogger().info("Fehler beim generieren random doubles: Bitte Code überprüfen"); return 0;}
        double number = min + (max -min) * new java.util.Random().nextDouble();
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
}
