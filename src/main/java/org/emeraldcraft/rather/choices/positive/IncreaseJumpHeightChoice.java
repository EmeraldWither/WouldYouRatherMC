package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.potion.PotionEffectType.JUMP_BOOST;

public class IncreaseJumpHeightChoice extends Choice.ChoiceRunnable implements Listener {
    public IncreaseJumpHeightChoice() {
        super("You get jump boost");
    }

    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(JUMP_BOOST, Integer.MAX_VALUE, 1));
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
        getPlayer().addPotionEffect(new PotionEffect(JUMP_BOOST, Integer.MAX_VALUE, 1));
    }
}
