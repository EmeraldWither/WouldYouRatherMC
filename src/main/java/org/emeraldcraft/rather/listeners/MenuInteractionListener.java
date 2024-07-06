package org.emeraldcraft.rather.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.emeraldcraft.rather.PlayerChoices;
import org.emeraldcraft.rather.WouldYouRatherInventory;
import org.emeraldcraft.rather.WouldYouRatherPlugin;

import java.util.Arrays;

public class MenuInteractionListener implements Listener{
    @EventHandler
    public void onInventoryEvent(InventoryClickEvent event) {
        if(!(event.getInventory().getHolder() instanceof WouldYouRatherInventory)) return;

        System.out.println(event.getSlot());

        //check for valid slot
        if(Arrays.stream(WouldYouRatherInventory.OPTION_ONE_SELECTION_AREA).anyMatch(slot -> slot == event.getSlot())) {
            PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
            playerChoices.processChoiceOne(((Player) event.getWhoClicked()));

            event.getWhoClicked().closeInventory();
        }
        else if(Arrays.stream(WouldYouRatherInventory.OPTION_TWO_SELECTION_AREA).anyMatch(slot -> slot == event.getSlot())){
            PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
            playerChoices.processChoiceTwo(((Player) event.getWhoClicked()));
            event.getWhoClicked().closeInventory();

        }
        else {
            System.out.println("failed both checks");
        }
        event.setCancelled(true);
    }
}
