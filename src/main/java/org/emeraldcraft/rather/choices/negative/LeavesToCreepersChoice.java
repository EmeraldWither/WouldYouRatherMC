package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.Material.AIR;
import static org.bukkit.entity.EntityType.CREEPER;

public class LeavesToCreepersChoice extends Choice.ChoiceRunnable {
    private int id;

    public LeavesToCreepersChoice() {
        super("Leaves around you turn into creepers");
    }

    @Override
    public void run() {
        markActive();
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            if(getPlayer() == null) return;

            Location location = getPlayer().getLocation();
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

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(id);
    }
}
