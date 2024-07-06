package org.emeraldcraft.rather.choices.negative;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class InstantHighTeleportChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        player.teleport(player.getLocation().add(0, 250, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
