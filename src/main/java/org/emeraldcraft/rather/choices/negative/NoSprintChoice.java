package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoSprintChoice extends Choice.ChoiceRunnable implements Listener {
    public NoSprintChoice() {
        super("You can no longer sprint", "no-sprint");
    }

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

    @EventHandler
    public void onSwim(EntityToggleSwimEvent event) {
        if (!event.getEntity().equals(getPlayer())) return;
        ((Player) event.getEntity()).setSprinting(false);
    }

    @Override
    public void cancel() {
        deRegisterListener();
        getPlayer().setWalkSpeed(0.1F);
    }
}
