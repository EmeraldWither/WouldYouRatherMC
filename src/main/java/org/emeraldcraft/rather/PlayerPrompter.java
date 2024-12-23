package org.emeraldcraft.rather;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.Choices;
import org.emeraldcraft.rather.choices.negative.RemoveRandomPositiveChoice;
import org.emeraldcraft.rather.choices.positive.RemoveRandomNegativeChoice;
import org.emeraldcraft.rather.inventory.WouldYouRatherInventory;

import java.util.Random;

public class PlayerPrompter {

    public static void promptPlayer(Player player) {
//        removePreviousChoices(player);
        Choice[] option1 = new Choice[2];
        option1[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
        option1[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];

        Choice[] option2 = new Choice[2];
        option2[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
        option2[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];


        Inventory inventory = new WouldYouRatherInventory(JavaPlugin.getPlugin(WouldYouRatherPlugin.class),option1, option2, WouldYouRatherInventory.INVENTORY_NAME).getInventory();
        WouldYouRatherPlugin.getInstance().getPlayerChoices().proposeOptions(player, new Choice[][]{option1, option2}, inventory);
        assert player != null;
        player.openInventory(inventory);
    }
    private static void removePreviousChoices(Player player) {
        RemoveRandomPositiveChoice randomPositiveChoice = new RemoveRandomPositiveChoice();
        randomPositiveChoice.setPlayer(player);
        randomPositiveChoice.run();

        RemoveRandomNegativeChoice randomNegativeChoice = new RemoveRandomNegativeChoice();
        randomNegativeChoice.setPlayer(player);
        randomNegativeChoice.run();
    }
}
