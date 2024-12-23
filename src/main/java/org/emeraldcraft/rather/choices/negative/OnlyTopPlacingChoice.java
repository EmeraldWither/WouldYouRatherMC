package org.emeraldcraft.rather.choices.negative;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class OnlyTopPlacingChoice extends Choice.ChoiceRunnable implements Listener {
    public OnlyTopPlacingChoice() {
        super("You can only place on the top of blocks", "top-placing");
    }

    @Override
    public void run() {
       super.registerEvents();
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;
        Block blockPlaced = event.getBlockPlaced();
        Block blockAgainst = event.getBlockAgainst();

        if(blockAgainst.getFace(blockPlaced) != BlockFace.UP && blockAgainst.getFace(blockPlaced) != BlockFace.DOWN) {
            event.setCancelled(true);
        }

    }
}
