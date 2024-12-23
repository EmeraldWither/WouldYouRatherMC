package org.emeraldcraft.rather.choices.positive;

import org.bukkit.inventory.ItemStack;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ItemsFullStackChoice extends Choice.ChoiceRunnable {
    public ItemsFullStackChoice() {
        super("All the items in your inventory become a full stack", "full-stack-choice");
    }

    @Override
    public void run() {
        for(ItemStack item : getPlayer().getInventory().getContents()) {
            if(item == null) continue;
            item.setAmount(item.getMaxStackSize());
        }
    }
}
