package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

public class FireResChoice extends Choice.ChoiceRunnable implements Listener {
    public FireResChoice() {
        super("You get fire resistance", "fire-res");
    }

    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
        getPlayer().addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }
}
