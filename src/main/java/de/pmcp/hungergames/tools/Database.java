package de.pmcp.hungergames.tools;

import org.bukkit.Bukkit;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Database implements Serializable {
    public static transient final long serialVersionUID = -1681012206529286330L;


    //Liest daten aus dem Speicher
    public static int get() {
        try {
            FileInputStream filein = new FileInputStream("data.txt");
            GZIPInputStream gzin = new GZIPInputStream(filein);
            BukkitObjectInputStream in = new BukkitObjectInputStream(gzin);
            int data = in.readInt();
            in.close();

            return data;
        }
        catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("FEHLER BEIM LADEN");
            return 0;
        }
    }

    //Schreibt Daten in den Speicher
    public static void write(int data) {
        try {
            FileOutputStream fileout = new FileOutputStream("data.txt"); //Dateioutput definieren
            GZIPOutputStream gzout = new GZIPOutputStream(fileout); //Daten komprimieren
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(gzout); //Daten für Speicherung vorbereiten
            out.writeInt(data);
            out.close(); //Output schließen

        }
        catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("FEHLER BEIM SPEICHERN");
        }
    }

    //Speichert die Variablen über write()
    public static void save_data() {

    }

    //Packt den Inhalt der get() Datenbank in die Variablen
    public static void load_data() {

    }
}
