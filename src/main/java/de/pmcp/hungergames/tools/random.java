package de.pmcp.hungergames.tools;

import org.bukkit.Bukkit;

import java.util.Random;

public class random {
    public static int rint(int min, int max) {
        int number = new Random().nextInt((max - min) + 1) + min;
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
    public static double rouble(double min, double max) {
        double number = min + (max -min) * new Random().nextDouble();
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
}
