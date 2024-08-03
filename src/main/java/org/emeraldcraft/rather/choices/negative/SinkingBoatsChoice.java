package org.emeraldcraft.rather.choices.negative;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class SinkingBoatsChoice extends Choice.ChoiceRunnable implements Listener {
    public SinkingBoatsChoice() {
        super("Boats you ride in sink");
    }

    @Override
    public void run() {
        super.registerEvents();
    }

    @EventHandler
    public void onBoatMove(VehicleMoveEvent event) {
        //TODO: Fix it if there are two people in the boat
        if (!event.getVehicle().getPassengers().contains(getPlayer())) return;
        event.getVehicle().setVelocity(event.getVehicle().getVelocity().setY(-0.2));
    }
}
