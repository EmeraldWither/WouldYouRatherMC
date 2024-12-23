package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;
import org.emeraldcraft.rather.choices.ChoiceRemover;
import org.emeraldcraft.rather.choices.NullChoice;

import java.util.List;
import java.util.Random;

public class RemoveRandomNegativeChoice extends Choice.ChoiceRunnable {
    public RemoveRandomNegativeChoice() {
        super("A random negative choice is removed.", "remove-random-negative-choice");
    }

    @Override
    public void run() {
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        List<Choice.ChoiceRunnable[]> choices = playerChoices.getSelectedOptions().get(getPlayer());
        if(choices == null || choices.isEmpty()) return;
        int maxTries = 0;
        while(maxTries < 100) {
            int randomIndex = new Random().nextInt(choices.size());
            if(choices.get(randomIndex)[1] instanceof NullChoice) {
                maxTries++;
                continue;
            }
            ChoiceRemover.removeChoice(randomIndex, false, getPlayer(), getPlugin());
            return;
        }
        Bukkit.broadcastMessage("ERROR: Could not remove a negative choice (all nullchoice instance)");
    }
}
