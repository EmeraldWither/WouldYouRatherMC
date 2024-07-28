package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.Material.ROTTEN_FLESH;

public class OnlyRottenFleshChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        super.registerEvents();
    }
    @EventHandler
    public void onFoodEach(FoodLevelChangeEvent event) {
        if(!(event.getEntity() instanceof Player player)) return;
        if(getPlayer() != player) return;
        if(event.getItem() == null) return;
        if(event.getItem().getType() != ROTTEN_FLESH) {
            event.setCancelled(true);
            player.sendMessage(Component.text("Remember, you can only eat rotten flesh!").color(NamedTextColor.RED));
        }


    }

}
