package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class CantTouchGrassChoice extends Choice.ChoiceRunnable implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getLocation().getBlock().getType().toString().contains("GRASS")) {
            event.getPlayer().getLocation().add(0, 1, 0).getBlock().setType(Material.FIRE);
        }
    }

    @Override
    public void run() {
        registerEvents();
    }
}
