package org.emeraldcraft.rather.choices.negative;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class CantTouchGrassChoice extends Choice.ChoiceRunnable implements Listener {

    public CantTouchGrassChoice() {
        super("You cant touch grass");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().add(0, -1, 0).getBlock().getType().toString().contains("GRASS")) {
            event.getPlayer().setVelocity(getPlayer().getVelocity().setY(3).multiply(50));
        }
    }

    @Override
    public void run() {
        super.registerEvents();
    }
}
