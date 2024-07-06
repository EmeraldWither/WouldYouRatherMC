package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ChunkDeleteChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        Chunk chunk = player.getLocation().getChunk();
        for(int x = 0; x < 16; x++) {
            for(int z = 0; z < 16; z++) {
               for(int y = -64; y < 320; y++) {
                   chunk.getBlock(x, y, z).setType(Material.AIR);
               }
            }
        }
    }
}