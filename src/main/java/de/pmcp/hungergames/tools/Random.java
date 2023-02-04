package de.pmcp.hungergames.tools;

public class Random {
    public static int rint(int min, int max) {
        int number = new java.util.Random().nextInt((max - min) + 1) + min;
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
    public static double rouble(double min, double max) {
        double number = min + (max -min) * new java.util.Random().nextDouble();
        //Bukkit.getLogger().info(String.valueOf(number));
        return number;
    }
}
