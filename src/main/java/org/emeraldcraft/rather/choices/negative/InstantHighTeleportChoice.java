package org.emeraldcraft.rather.choices.negative;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class InstantHighTeleportChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        Player player = getPlayer();
        player.teleport(player.getLocation().add(0, 250, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
