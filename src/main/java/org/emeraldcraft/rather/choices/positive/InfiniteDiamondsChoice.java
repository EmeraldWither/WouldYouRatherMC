package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.emeraldcraft.rather.choiceapi.Choice;

public class InfiniteDiamondsChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        registerEvents();
    }
    @EventHandler
    public void onBlockMine(BlockBreakEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;
        if(!event.getBlock().getType().name().contains("ORE")) return;
        event.setDropItems(false);
        Location location = event.getBlock().getLocation();
        location.getWorld().dropItem(location, new ItemStack(Material.DIAMOND));
    }
}
