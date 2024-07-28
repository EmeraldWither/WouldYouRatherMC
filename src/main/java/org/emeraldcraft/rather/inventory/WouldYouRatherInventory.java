package org.emeraldcraft.rather.inventory;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.jetbrains.annotations.NotNull;

public class WouldYouRatherInventory implements InventoryHolder {
    public static final String INVENTORY_BITMAP_CHARACTER_ONE = "ㇺ";
    public static final String INVENTORY_BITMAP_CHARACTER_TWO = "⣥";

    public static final String NEGATIVE_SPACE = "七";
    public static final String INVENTORY_RAW_NAME_ONE = NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE
            + INVENTORY_BITMAP_CHARACTER_ONE;
    public static final String INVENTORY_RAW_NAME_TWO = NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE
            + INVENTORY_BITMAP_CHARACTER_TWO;
    public static final Component INVENTORY_NAME =
            Component.text(
                            NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE
                                    + INVENTORY_BITMAP_CHARACTER_ONE)
                    .color(TextColor.color(255, 255, 255));

    public static final int[] OPTION_ONE_SELECTION_AREA = {28, 29,30, 37, 38,39, 46, 47,48};
    public static final int[] OPTION_ONE_POSITIVE_AREA = {28, 29, 30};
    public static final int[] OPTION_ONE_NEGATIVE_AREA = {46, 47, 48};

    public static final int[] OPTION_TWO_SELECTION_AREA = {32,33, 34,41, 42, 43,50, 51, 52};
    public static final int[] OPTION_TWO_POSITIVE_AREA = {33, 34, 35};
    public static final int[] OPTION_TWO_NEGATIVE_AREA = {51, 52, 53};

    private final Inventory inventory;

    public WouldYouRatherInventory(WouldYouRatherPlugin plugin, Choice[] option1, Choice[] option2, Component inventoryName) {
        this.inventory = plugin.getServer().createInventory(this, 54, inventoryName);
        for (int i : OPTION_ONE_POSITIVE_AREA) {
            ItemStack item = createItem(option1[0].description(), TextColor.color(0, 255, 0));
            this.inventory.setItem(i, item);
        }
        for (int i : OPTION_ONE_NEGATIVE_AREA) {
            ItemStack item = createItem(option1[1].description(), TextColor.color(255, 0, 0));
            this.inventory.setItem(i, item);
        }



        for (int i : OPTION_TWO_POSITIVE_AREA) {
            ItemStack item = createItem(option2[0].description(), TextColor.color(0, 255, 0));
            this.inventory.setItem(i, item);
        }
        for (int i : OPTION_TWO_NEGATIVE_AREA) {
            ItemStack item = createItem(option2[1].description(), TextColor.color(255, 0, 0));
            this.inventory.setItem(i, item);
        }
    }

    public ItemStack createItem(String description, TextColor color) {
        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setCustomModelData(1010);
        itemMeta.displayName(Component.text(description).color(color));
        item.setItemMeta(itemMeta);
        return item;
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
