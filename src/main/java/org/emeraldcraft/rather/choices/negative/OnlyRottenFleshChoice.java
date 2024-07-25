package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.Material.ROTTEN_FLESH;

public class OnlyRottenFleshChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    @EventHandler
    public void onFoodEach(FoodLevelChangeEvent event) {
        if(!(event.getEntity() instanceof Player player)) return;
        if(this.player != player) return;
        if(event.getItem() == null) return;
        if(event.getItem().getType() != ROTTEN_FLESH) {
            event.setCancelled(true);
            player.sendMessage(Component.text("Remember, you can only eat rotten flesh!").color(NamedTextColor.RED));
        }


    }

}
