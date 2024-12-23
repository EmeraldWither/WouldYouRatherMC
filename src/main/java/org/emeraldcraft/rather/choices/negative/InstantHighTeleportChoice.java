package org.emeraldcraft.rather.choices.negative;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class InstantHighTeleportChoice extends Choice.ChoiceRunnable {
    public InstantHighTeleportChoice() {
        super("You get teleported to y=250", "y250-choice");
    }

    @Override
    public void run() {
        Player player = getPlayer();
        player.teleport(player.getLocation().add(0, 250, 0), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }
}
