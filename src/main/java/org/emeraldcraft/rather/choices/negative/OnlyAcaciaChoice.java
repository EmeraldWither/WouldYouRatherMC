package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class OnlyAcaciaChoice extends Choice.ChoiceRunnable implements Listener {

    public OnlyAcaciaChoice() {
        super("You can only place acacia planks.", "only-acacia");
    }

    @Override
    public void run() {
        registerEvents();
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if (!event.getPlayer().equals(getPlayer())) return;
        if(event.getBlock().getType() != Material.ACACIA_PLANKS) event.setCancelled(true);
    }
}
