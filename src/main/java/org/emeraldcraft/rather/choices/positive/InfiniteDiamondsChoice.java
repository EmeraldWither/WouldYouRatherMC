package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class InfiniteDiamondsChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onBlockMine(BlockBreakEvent event) {

        if(this.player == null) return;
        if(!event.getPlayer().equals(this.player)) return;

        event.setDropItems(false);
        Location location = event.getBlock().getLocation();
        location.getWorld().dropItem(location, new ItemStack(Material.DIAMOND));
    }
}
