package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoSprintChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        super.registerEvents();
    }
    @EventHandler
    public void onPlayerSprint(PlayerMoveEvent event) {
        if(event.getPlayer().equals(getPlayer())) {
            if(getPlayer().isSprinting()) {
                getPlayer().setWalkSpeed(0.08F);
                getPlayer().sendMessage(Component.text("You cannot sprint.").color(NamedTextColor.DARK_RED));
            }
            else {
                getPlayer().setWalkSpeed(0.2F);
            }

        }
    }
}
