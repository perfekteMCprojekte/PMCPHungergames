package de.pmcp.hungergames.tools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Item {
    public static ItemStack create(Material material, int amount, String name, String text) {
        ItemStack item = new ItemStack(material, amount); //Create Item (with count)
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§r" + name); //add name (meta)
        if (!text.equals("")) meta.setLore(Arrays.asList(text)); //add lore to meta
        item.setItemMeta(meta);
        return item;
    }
}
