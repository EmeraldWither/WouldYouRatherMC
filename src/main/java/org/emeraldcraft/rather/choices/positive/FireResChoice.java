package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE;

public class FireResChoice extends Choice.ChoiceRunnable implements Listener {
    public FireResChoice() {
        super("You get fire resistance", "fire-res");
        markActive();
    }

    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }

    @Override
    public void cancel() {
        super.cancel();
        this.getPlayer().removePotionEffect(FIRE_RESISTANCE);
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
        getPlayer().addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }
}
