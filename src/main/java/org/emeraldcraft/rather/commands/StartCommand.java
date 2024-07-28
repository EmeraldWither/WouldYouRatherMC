package org.emeraldcraft.rather.commands;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.choices.Choices;
import org.emeraldcraft.rather.inventory.WouldYouRatherInventory;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Random;

public class StartCommand {
    public int run(){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(WouldYouRatherPlugin.class), () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                Choice[] option1 = new Choice[2];
                option1[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
                option1[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];

                Choice[] option2 = new Choice[2];
                option2[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
                option2[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];

                Inventory inventory = new WouldYouRatherInventory(JavaPlugin.getPlugin(WouldYouRatherPlugin.class),option1, option2, WouldYouRatherInventory.INVENTORY_NAME).getInventory();
                WouldYouRatherPlugin.getInstance().getPlayerChoices().proposeOptions(player, new Choice[][]{option1, option2}, inventory);
                player.openInventory(inventory);
            }
        }, 20 * 60 * 2, 20 * 60 * 5);
        return 0;
    }
}
