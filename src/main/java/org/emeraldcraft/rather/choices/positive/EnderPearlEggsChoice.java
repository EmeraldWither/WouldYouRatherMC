package org.emeraldcraft.rather.choices.positive;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class EnderPearlEggsChoice extends Choice.ChoiceRunnable implements Listener {
    public EnderPearlEggsChoice() {
        super("You can use eggs as ender pearls.", "egg-enderpearl");
    }

    @Override
    public void run() {
        super.registerEvents();
    }

    @EventHandler
    public void onEggLand(ProjectileHitEvent event) {
        if (event.getEntity().getType() != EntityType.EGG) return;
        if(!(event.getEntity().getShooter() instanceof Player player)) return;
        if(!player.equals(getPlayer())) return;


        if(event.getHitBlock() != null) {
            player.teleport(event.getHitBlock().getLocation());
        }
        else if (event.getHitEntity() != null) {
            player.teleport(event.getHitEntity().getLocation());
        }
        else {
            player.teleport(event.getEntity().getLocation());
        }
    }
}
