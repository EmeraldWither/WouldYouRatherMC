package org.emeraldcraft.rather.choices.negative;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.Sound.ENTITY_ITEM_BREAK;

public class NoArmorChoice extends Choice.ChoiceRunnable implements Listener {
    public NoArmorChoice() {
        super("You can no longer wear any armor.");
    }

    @Override
    public void run() {
        PlayerInventory inventory = getPlayer().getInventory();
        inventory.setArmorContents(null);
        super.registerEvents();
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        Player ePlayer = event.getPlayer();
        if(!ePlayer.equals(getPlayer())) return;
        ePlayer.getInventory().setArmorContents(null);
        ePlayer.playSound(ePlayer, ENTITY_ITEM_BREAK, 1F, 1F);
        World world = ePlayer.getLocation().getWorld();
        world.spawnParticle(Particle.LARGE_SMOKE, ePlayer.getLocation(), 10);

        ePlayer.sendMessage("om nom nom. i ate your armor.");
    }

}
