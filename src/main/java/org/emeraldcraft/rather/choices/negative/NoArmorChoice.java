package org.emeraldcraft.rather.choices.negative;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class NoArmorChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    @Override
    public void run(Plugin plugin, Player player) {
        PlayerInventory inventory = player.getInventory();
        inventory.setArmorContents(null);
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onArmorChange(PlayerArmorChangeEvent event) {
        if(this.player == null) return;
        if(!event.getPlayer().equals(this.player)) return;
        event.getPlayer().getInventory().setArmorContents(null);
    }

}
