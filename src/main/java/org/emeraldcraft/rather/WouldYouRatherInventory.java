package org.emeraldcraft.rather;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WouldYouRatherInventory implements InventoryHolder {
    public static final String INVENTORY_BITMAP_CHARACTER = "ㇺ";
    public static final String NEGATIVE_SPACE = "七";
    public static final Component INVENTORY_NAME =
            Component.text(
                            NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE + NEGATIVE_SPACE
                                    + INVENTORY_BITMAP_CHARACTER)
                    .color(TextColor.color(255, 255, 255));

    public static final int[] OPTION_ONE_SELECTION_AREA = {10, 11, 19, 20, 28, 29, 37, 38};
    public static final int[] OPTION_TWO_SELECTION_AREA = {16, 17, 25, 26, 34, 35, 43, 44};

    private final Inventory inventory;

    public WouldYouRatherInventory(WouldYouRatherPlugin plugin, Choice[] option1, Choice[] option2) {
        this.inventory = plugin.getServer().createInventory(this, 54, INVENTORY_NAME);
        for (int i : OPTION_ONE_SELECTION_AREA) {
            ItemStack item = new ItemStack(Material.MAP);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(1010);
            itemMeta.displayName(Component.text("Would you rather...").color(TextColor.color(255, 255, 255)));
            item.setItemMeta(itemMeta);

            List<Component> lore = new ArrayList<>();
            lore.add(Component.text(option1[0].description()).color(TextColor.color(0, 255, 0)));
            lore.add(Component.text("BUT").color(TextColor.color(110, 100, 255)));
            lore.add(Component.text(option1[1].description()).color(TextColor.color(255, 0, 0)));
            item.lore(lore);

            this.inventory.setItem(i, item);
        }
        for (int i : OPTION_TWO_SELECTION_AREA) {
            ItemStack item = new ItemStack(Material.MAP);
            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setCustomModelData(1010);
            itemMeta.displayName(Component.text("Would you rather...").color(TextColor.color(255, 255, 255)));
            item.setItemMeta(itemMeta);

            List<Component> lore = new ArrayList<>();
            lore.add(Component.text(option2[0].description()).color(TextColor.color(0, 255, 0)));
            lore.add(Component.text("BUT").color(TextColor.color(110, 100, 255)));
            lore.add(Component.text(option2[1].description()).color(TextColor.color(255, 0, 0)));
            item.lore(lore);
            this.inventory.setItem(i, item);
        }

    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }
}
