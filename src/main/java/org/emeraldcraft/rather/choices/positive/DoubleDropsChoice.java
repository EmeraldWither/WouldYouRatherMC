package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class DoubleDropsChoice extends Choice.ChoiceRunnable implements Listener {
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
