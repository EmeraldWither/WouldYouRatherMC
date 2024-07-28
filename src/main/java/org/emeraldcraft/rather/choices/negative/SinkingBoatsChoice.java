package org.emeraldcraft.rather.choices.negative;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

public class SinkingBoatsChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        super.registerEvents();
    }

    @EventHandler
    public void onBoatMove(VehicleMoveEvent event) {
        if (!event.getVehicle().getPassengers().contains(getPlayer())) return;
        event.getVehicle().setVelocity(event.getVehicle().getVelocity().setY(-0.2));
    }
}
