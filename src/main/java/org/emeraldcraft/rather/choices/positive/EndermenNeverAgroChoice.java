package org.emeraldcraft.rather.choices.positive;

import com.destroystokyo.paper.event.entity.EndermanAttackPlayerEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.emeraldcraft.rather.choiceapi.Choice;

public class EndermenNeverAgroChoice extends Choice.ChoiceRunnable implements Listener {
    public EndermenNeverAgroChoice() {
        super("Enderman will not agro you if you look at them in the eyes", "endermen-never-agro");
    }

    @Override
    public void run() {
        registerEvents();
    }
    @EventHandler
    public void onEndermanAgro(EndermanAttackPlayerEvent event) {
        event.setCancelled(true);
    }
}
