package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class EnderPearlEggsChoice implements Choice.ChoiceRunnable, Listener {

    private Player player;

    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEggLand(ProjectileHitEvent event) {
        if(this.player == null) return;
        if (event.getEntity().getType() != EntityType.EGG) return;
        if(!(event.getEntity().getShooter() instanceof Player player)) return;
        if(!player.equals(this.player)) return;


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
