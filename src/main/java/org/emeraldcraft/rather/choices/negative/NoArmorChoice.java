package org.emeraldcraft.rather.choices.negative;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoArmorChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        PlayerInventory inventory = getPlayer().getInventory();
        inventory.setArmorContents(null);
        registerEvents();
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;
        event.getPlayer().getInventory().setArmorContents(null);
    }

}
