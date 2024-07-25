package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoExplosionDamageChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(this.player == null) return;
        if(!this.player.equals(event.getEntity())) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
            return;
        event.setCancelled(true);
    }

}
