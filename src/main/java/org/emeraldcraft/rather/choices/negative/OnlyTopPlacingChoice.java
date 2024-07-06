package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class OnlyTopPlacingChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(!event.getPlayer().equals(player)) return;
        Block blockPlaced = event.getBlockPlaced();
        Block blockAgainst = event.getBlockAgainst();

        if(blockAgainst.getFace(blockPlaced) != BlockFace.UP && blockAgainst.getFace(blockPlaced) != BlockFace.DOWN) {
            event.setCancelled(true);
        }

    }
}
