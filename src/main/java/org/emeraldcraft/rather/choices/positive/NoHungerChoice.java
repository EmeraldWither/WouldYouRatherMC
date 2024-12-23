package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoHungerChoice extends Choice.ChoiceRunnable implements Listener {
    public NoHungerChoice() {
        super("You will no longer go hungry", "no-hunger");
    }

    @Override
    public void run() {
        getPlayer().setFoodLevel(99999);
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
    }
}
