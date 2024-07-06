package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NetherTeleportChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        player.teleport(Bukkit.getWorld("world_nether").getSpawnLocation());
    }
}
