package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.Material.AIR;
import static org.bukkit.entity.EntityType.*;

public class LeavesToCreepersChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            Location location = player.getLocation();
            for(int x = -3; x < 3; x++) {
                for(int y = -3; y < 3; y++) {
                    for(int z = -3; z < 3; z++) {
                        Block block = location.clone().add(x, y, z).getBlock();
                        if(block.getType().name().contains("LEAVES")) {
                            block.setType(AIR);
                            location.getWorld().spawnEntity(block.getLocation(), CREEPER);
                        }
                    }
                }

            }

        }, 0, 5);
    }
}
