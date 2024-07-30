package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NetherTeleportChoice extends Choice.ChoiceRunnable {
    public NetherTeleportChoice() {
        super("You get teleported to the nether");
    }

    @Override
    public void run() {
        getPlayer().teleport(Bukkit.getWorld("world_nether").getSpawnLocation());
    }
}
