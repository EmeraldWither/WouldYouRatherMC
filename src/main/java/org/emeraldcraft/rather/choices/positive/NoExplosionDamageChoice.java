package org.emeraldcraft.rather.choices.positive;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoExplosionDamageChoice extends Choice.ChoiceRunnable implements Listener {
    public NoExplosionDamageChoice() {
        super("You no longer take explosion damage", "no-explosion-damage");
    }

    @Override
    public void run() {
        super.registerEvents();
    }
    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        if(!getPlayer().equals(event.getEntity())) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION && event.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION)
            return;
        event.setCancelled(true);
    }

}
