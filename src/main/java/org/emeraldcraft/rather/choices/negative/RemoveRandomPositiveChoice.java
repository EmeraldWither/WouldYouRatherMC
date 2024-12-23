package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;
import org.emeraldcraft.rather.choices.ChoiceRemover;
import org.emeraldcraft.rather.choices.NullChoice;

import java.util.List;
import java.util.Random;

public class RemoveRandomPositiveChoice extends Choice.ChoiceRunnable {
    public RemoveRandomPositiveChoice() {
        super("A random positive choice is removed.", "remove-random-positive-choice");
    }

    @Override
    public void run() {
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        List<Choice.ChoiceRunnable[]> choices = playerChoices.getSelectedOptions().get(getPlayer());
        if(choices == null || choices.isEmpty()) return;
        int maxTries = 0;
        while(maxTries < 100) {
            int randomIndex = new Random().nextInt(choices.size());
            if(choices.get(randomIndex)[0] instanceof NullChoice) {
                maxTries++;
                continue;
            }
            ChoiceRemover.removeChoice(randomIndex, true, getPlayer(), getPlugin());
            return;
        }
        Bukkit.broadcastMessage("ERROR: Could not remove a negative choice (all nullchoice instance)");

    }
}
