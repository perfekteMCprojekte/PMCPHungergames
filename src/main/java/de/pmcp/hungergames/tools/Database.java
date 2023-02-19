package de.pmcp.hungergames.tools;

import de.pmcp.hungergames.game.Death;
import de.pmcp.hungergames.game.Engine;
import de.pmcp.hungergames.game.Volcano;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Database implements Serializable {
    public static transient final long serialVersionUID = -1681012206529286330L;


    //Liest daten aus dem Speicher
    public static HashMap<String, Object> get() {
        try {
            FileInputStream filein = new FileInputStream("pmcp-data.txt");
            GZIPInputStream gzin = new GZIPInputStream(filein);
            BukkitObjectInputStream in = new BukkitObjectInputStream(filein);
            HashMap<String, Object> data = (HashMap<String, Object>) in.readObject();
            in.close();

            return data;
        }
        catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
            Bukkit.getLogger().info("FEHLER BEIM LADEN");
            return null;
        }
    }

    //Schreibt Daten in den Speicher
    public static void write(HashMap<String, Object> data) {
        try {
            FileOutputStream fileout = new FileOutputStream("pmcp-data.txt"); //Dateioutput definieren
            GZIPOutputStream gzout = new GZIPOutputStream(fileout); //Daten komprimieren
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(fileout); //Daten für Speicherung vorbereiten
            out.writeObject(data);
            out.close(); //Output schließen

        }
        catch (IOException e) {
            e.printStackTrace();
            Bukkit.getLogger().info("FEHLER BEIM SPEICHERN");
        }
    }

    //Speichert die Variablen über write()
    public static void save_data() {
        HashMap<String, Object> data = new HashMap<>();
        data.put("day", Engine.day);
        data.put("deathcount", Death.deathCount);
        data.put("deathmessages", Engine.news);
        data.put("explosives", Engine.explosives);
        data.put("volcanoloc", new double[]{Volcano.loc.getX(), Volcano.loc.getY(), Volcano.loc.getZ()});
        write(data);
    }

    //Packt den Inhalt der get() Datenbank in die Variablen
    public static void load_data() {
        HashMap<String, Object> map = get();
        if (map == null || map.isEmpty()) {Bukkit.getLogger().info("Keine Daten in Datei vorhanden"); return;}
        Engine.day = (int) map.get("day");
        Death.deathCount = (int) map.get("deathcount");
        Engine.news = (ArrayList<String>) map.get("deathmessages");
        Engine.explosives = (ArrayList<int[]>) map.get("explosives");
        double[] x = (double[]) map.get("volcanoloc"); //Ich weiß es ist schlimm
        Volcano.loc.setX(x[0]); Volcano.loc.setY(x[1]); Volcano.loc.setZ(x[2]);
    }
}
