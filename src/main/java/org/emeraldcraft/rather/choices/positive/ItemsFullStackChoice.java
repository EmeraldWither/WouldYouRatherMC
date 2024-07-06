package org.emeraldcraft.rather.choices.positive;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ItemsFullStackChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        for(ItemStack item : player.getInventory().getContents()) {
            if(item == null) continue;
            item.setAmount(item.getMaxStackSize());
        }
    }
}
