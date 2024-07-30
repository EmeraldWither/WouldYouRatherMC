package org.emeraldcraft.rather.choices.positive;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class DoubleDropsChoice extends Choice.ChoiceRunnable implements Listener {
    public DoubleDropsChoice() {
        super("You get double the drops");
    }

    @Override
    public void run() {
        super.registerEvents();
    }
    @EventHandler
    public void onBlockMine(BlockDropItemEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;

        event.getItems().forEach(item -> item.getItemStack().setAmount(item.getItemStack().getAmount() * 2));
    }
}
