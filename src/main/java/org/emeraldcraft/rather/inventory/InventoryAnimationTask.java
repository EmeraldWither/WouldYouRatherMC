package org.emeraldcraft.rather.inventory;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.logging.Level;

public class InventoryAnimationTask implements Runnable {
    private int i = 0;
    private final Player player;

    public InventoryAnimationTask(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        if(!(player.getOpenInventory().getTopInventory().getHolder() instanceof WouldYouRatherInventory)) return;
        if(player.isDead()) return;
        if (i % 2 == 0) {
            player.getOpenInventory().setTitle(ChatColor.WHITE + WouldYouRatherInventory.INVENTORY_RAW_NAME_ONE);
        } else {
            player.getOpenInventory().setTitle(ChatColor.WHITE + WouldYouRatherInventory.INVENTORY_RAW_NAME_TWO);
        }
        i++;
    }
}
