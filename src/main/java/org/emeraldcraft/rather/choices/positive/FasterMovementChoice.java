package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.*;

public class FasterMovementChoice extends org.emeraldcraft.rather.choiceapi.Choice.ChoiceRunnable implements Listener {
    public FasterMovementChoice() {
        super("You move faster", "faster-movement");
        markActive();
    }

    @Override
    public void run() {
        this.getPlayer().addPotionEffect(new PotionEffect(SPEED, Integer.MAX_VALUE, 1));
    }

    @Override
    public void cancel() {
        super.cancel();
        this.getPlayer().removePotionEffect(SPEED);
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
        this.getPlayer().addPotionEffect(new PotionEffect(SPEED, Integer.MAX_VALUE, 1));
    }
}
