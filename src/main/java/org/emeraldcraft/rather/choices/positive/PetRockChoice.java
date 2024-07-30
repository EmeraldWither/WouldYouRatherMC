package org.emeraldcraft.rather.choices.positive;

import io.papermc.paper.event.entity.EntityToggleSitEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.joml.AxisAngle4f;
import org.joml.Vector3f;

public class PetRockChoice extends Choice.ChoiceRunnable implements Listener {
    public PetRockChoice() {
        super("You get a pet rock that defends you");
    }

    @Override
    public void run() {
        Location loc = getPlayer().getLocation();
        BlockDisplay display = loc.getWorld().spawn(loc.setDirection(new Vector(0, 90, 0)), BlockDisplay.class);
        display.setBlock(Material.STONE_BUTTON.createBlockData());
        display.setTransformation(new Transformation(new Vector3f(-0.6f, -0.45f, -1.7f), new AxisAngle4f(), new Vector3f(1f, 1f, 1f), new AxisAngle4f()));
        display.customName(Component.text("Rocky").color(NamedTextColor.GRAY));
        display.setCustomNameVisible(true);

        Wolf wolf = loc.getWorld().spawn(loc.toCenterLocation(), Wolf.class, false, wolf1 -> {});
        wolf.setInvisible(true);
        wolf.setOwner(getPlayer());
        wolf.addPassenger(display);
        wolf.customName(Component.text("Rocky").color(NamedTextColor.DARK_GRAY));
        wolf.getPersistentDataContainer().set(new NamespacedKey("wouldyourather", "wolf"), PersistentDataType.BOOLEAN, true);

        registerEvents();
    }

    @EventHandler
    public void onDogSit(EntityToggleSitEvent event){
        if(event.getEntity().getPersistentDataContainer().has(new NamespacedKey("wouldyourather", "wolf"), PersistentDataType.BOOLEAN)) {
           event.setCancelled(true);
        }
    }

    @EventHandler
    public void onWolfDeath(EntityDeathEvent event) {
        if(event.getEntity().getPersistentDataContainer().has(new NamespacedKey("wouldyourather", "wolf"), PersistentDataType.BOOLEAN)) {
            event.getDrops().clear();
            if(!event.getEntity().getPassengers().isEmpty()){
                return;
            }
            BlockDisplay blockDisplay = (BlockDisplay) event.getEntity().getPassengers().getFirst();
            blockDisplay.remove();
            event.getDrops().add(new ItemStack(blockDisplay.getBlock().getMaterial()));
        }
    }

}
