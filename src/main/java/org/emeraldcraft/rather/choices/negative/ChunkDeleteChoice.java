package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ChunkDeleteChoice extends Choice.ChoiceRunnable {
    public ChunkDeleteChoice() {
        super("The chunk you are standing in is deleted");
    }

    @Override
    public void run() {
        Chunk chunk = getPlayer().getLocation().getChunk();
        int yLow = chunk.getWorld().getMinHeight();
        int yHigh = chunk.getWorld().getMaxHeight();

        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
               for(int y = yLow; y < yHigh; y++) {
                   chunk.getBlock(x, y, z).setType(Material.AIR);
               }
            }
        }
    }
}
