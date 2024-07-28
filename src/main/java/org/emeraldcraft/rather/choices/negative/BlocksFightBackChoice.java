package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
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
            Zombie zombie = loc.getWorld().spawn(loc.toCenterLocation(), Zombie.class, false, zombie1 -> {});
            zombie.setInvisible(true);
            zombie.setBaby();
            zombie.setShouldBurnInDay(false);
            zombie.addPassenger(display);
            zombie.setHealth(20.0);
            zombie.customName(Component.text(WordUtils.capitalize(event.getBlock().getType().toString().toLowerCase().replaceAll("_", " "))));
            zombie.getPersistentDataContainer().set(new NamespacedKey("wouldyourather", "zombie"), PersistentDataType.BOOLEAN, true);
            event.setDropItems(false);
        }
    }

    @EventHandler
    public void onEntityKill(EntityDeathEvent event) {
        if(event.getEntity().getPersistentDataContainer().has(new NamespacedKey("wouldyourather", "zombie"), PersistentDataType.BOOLEAN)) {
            event.getDrops().clear();
            BlockDisplay blockDisplay = (BlockDisplay) event.getEntity().getPassengers().getFirst();
            blockDisplay.remove();
            event.getDrops().add(new ItemStack(blockDisplay.getBlock().getMaterial()));
        }
    }

}
