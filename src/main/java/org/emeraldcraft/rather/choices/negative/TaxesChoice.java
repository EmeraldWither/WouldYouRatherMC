package org.emeraldcraft.rather.choices.negative;

import com.samjakob.spigui.buttons.SGButton;
import com.samjakob.spigui.menu.SGMenu;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.jetbrains.annotations.NotNull;

public class TaxesChoice extends Choice.ChoiceRunnable implements Listener {
    private final SGMenu menu = WouldYouRatherPlugin.getInstance().getSpiGUI().create(ChatColor.RED + "Time to Pay Taxes.", 6);
    private ItemStack insertedItem = null;
    private final int[] taxes = new int[2];
    private boolean validatedItems = false;

    public TaxesChoice() {
        super("You have to start paying taxes", "taxes");
    }

    @Override
    public void run() {
        calculateTaxes();
        createMenu();
        Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), this::showMenu, 2);
    }

    private void createMenu() {
        for (int i = 0; i < 54; i++) {
            menu.addButton(new SGButton(createIconItem(Material.GRAY_STAINED_GLASS_PANE, 1, Component.text(""))));
        }

        SGButton submitButton = getSubmitButton();
        SGButton inputButton = getInputButton(submitButton);

        SGButton diamondsAmount = new SGButton(createIconItem(Material.DIAMOND, taxes[0], Component.text("You need " + taxes[0] + " diamonds.").color(NamedTextColor.AQUA)));
        SGButton ironAmount = new SGButton(createIconItem(Material.IRON_INGOT, taxes[1], Component.text("You need " + taxes[1] + " iron ingots.").color(NamedTextColor.GRAY)));

        //create buttons
        menu.setButton(11, diamondsAmount);
        menu.setButton(15, ironAmount);

        menu.setButton(31, submitButton);
        menu.setButton(22, inputButton);
        menu.setOnClose(sgMenu -> Bukkit.getScheduler().scheduleSyncDelayedTask(getPlugin(), () -> {
            if(validatedItems) {
                validatedItems = false;
                return;
            }
            if(getPlayer().getOpenInventory().getTopInventory().getHolder() instanceof SGMenu) return;
            getPlayer().openInventory(sgMenu.getInventory());
        }, 10));
    }

    private SGButton getSubmitButton() {
        SGButton submitButton = new SGButton(createIconItem(Material.GRAY_CONCRETE, 1, Component.text("Place an item in the slot above.").color(NamedTextColor.GRAY)));
        submitButton.setListener(event -> {
            if (insertedItem == null) return;
            if (verifyInsertedItem()) {
                validatedItems = true;
                processTaxReturn();
                getPlayer().closeInventory();
                getPlayer().sendMessage("You have successfully paid your taxes.");
            }
        });
        return submitButton;
    }

    private ItemStack createIconItem(Material mat, int amount, Component description) {
        ItemStack item = new ItemStack(mat, amount);
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.displayName(description);
        item.setItemMeta(itemMeta);
        return item;
    }

    private void showMenu() {
        getPlayer().openInventory(menu.getInventory());
    }

    private @NotNull SGButton getInputButton(SGButton submitButton) {
        SGButton inputButton = new SGButton(new ItemStack(Material.AIR));
        inputButton.setListener(event -> {
            ItemStack cursor = event.getCursor();
            if (cursor.getType() == Material.AIR) {
                return;
            }
            ItemStack currentItem = event.getCurrentItem();
            inputButton.setIcon(cursor);
            event.setCursor(currentItem);
            insertedItem = cursor;
            if (verifyInsertedItem()) {
                submitButton.setIcon(createIconItem(Material.GREEN_WOOL, 1, Component.text("You can finish your tax return.").color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD)));
            }
            else
                submitButton.setIcon(createIconItem(Material.RED_WOOL, 1, Component.text("Please provide a valid item or right amount for your taxes.").color(NamedTextColor.RED).decorate(TextDecoration.BOLD)));
            menu.refreshInventory(getPlayer());
        });
        return inputButton;
    }

    private boolean verifyInsertedItem() {
        if(insertedItem.getType() == Material.DIAMOND) return insertedItem.getAmount() >= taxes[0];
        if(insertedItem.getType() == Material.IRON_INGOT) return insertedItem.getAmount() >= taxes[1];
        return false;
    }

    private void calculateTaxes() {
        int diamonds = 0;
        int iron = 0;
        for(ItemStack item : getPlayer().getInventory().getContents()) {
            if (item != null && item.getType() == Material.IRON_INGOT) {
                iron += item.getAmount();
            }
            if (item != null && item.getType() == Material.DIAMOND) {
                diamonds += item.getAmount();
            }
        }
        taxes[0] = (int) (diamonds * (Math.random() * 0.5));
        taxes[1] = (int) (iron * (Math.random() * 0.8));
    }

    private void processTaxReturn() {
        if (insertedItem.getType() == Material.IRON_INGOT) {
            getPlayer().getInventory().addItem(new ItemStack(Material.IRON_INGOT, insertedItem.getAmount() - taxes[1]));
        }
        if (insertedItem.getType() == Material.DIAMOND) {
            getPlayer().getInventory().addItem(new ItemStack(Material.DIAMOND, insertedItem.getAmount() - taxes[0]));
        }
    }
}