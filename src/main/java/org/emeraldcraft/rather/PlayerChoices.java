package org.emeraldcraft.rather;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerChoices {
    private final HashMap<Player, Choice[][]> proposedOptions = new HashMap<>();
    public void processChoiceOne(Player player) {
        Choice[][] choices = proposedOptions.get(player);
        choices[0][0].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        choices[0][1].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        proposedOptions.remove(player);
    }
    public void processChoiceTwo(Player player) {
        Choice[][] choices = proposedOptions.get(player);
        choices[1][0].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        choices[1][1].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        proposedOptions.remove(player);
    }

    public void proposeOptions(Player player, Choice[][] choices) {
        proposedOptions.put(player, choices);
    }
}
