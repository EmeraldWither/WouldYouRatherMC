package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Display;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.util.Transformation;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class BlocksFightBackChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        registerEvents();
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        if(event.getPlayer().equals(getPlayer())) {
            Location loc = event.getBlock().getLocation();
            BlockDisplay display = loc.getWorld().spawn(loc, BlockDisplay.class);
            display.setBlock(event.getBlock().getBlockData());
            display.setTransformation(new Transformation(new Vector3f(-0.5f, -1.02f, -0.5f), new AxisAngle4f(), new Vector3f(1, 1, 1), new AxisAngle4f()));
            Zombie zombie = loc.getWorld().spawn(loc.toCenterLocation(), Zombie.class);
            zombie.setInvisible(true);
            zombie.setBaby();
            zombie.setShouldBurnInDay(false);
            zombie.addPassenger(display);
        }
    }

}
