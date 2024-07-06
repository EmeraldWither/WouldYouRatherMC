package org.emeraldcraft.rather.listeners;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
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
        event.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if(!(event.getInventory().getHolder() instanceof WouldYouRatherInventory)) return;
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        if(playerChoices.inProgressPlayer((Player) event.getPlayer())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(
                    WouldYouRatherPlugin.getInstance(),
                    () -> {
                        event.getPlayer().sendMessage(Component.text("nuh uh, pick an option."));
                        event.getPlayer().openInventory(event.getInventory());
                    },
                    1
            );
        }
    }
    @EventHandler
    public void onPlayerRespawn(PlayerPostRespawnEvent event) {
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        if(playerChoices.inProgressPlayer(event.getPlayer())) {
            event.getPlayer().sendMessage(Component.text("nuh uh, pick an option."));
            event.getPlayer().openInventory(playerChoices.getPlayerInventory(event.getPlayer()));
        }
    }
}
