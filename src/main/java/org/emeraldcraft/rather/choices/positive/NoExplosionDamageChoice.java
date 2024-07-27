package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoExplosionDamageChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        registerEvents();
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(!getPlayer().equals(event.getEntity())) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
            return;
        event.setCancelled(true);
    }

}
