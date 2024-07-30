package org.emeraldcraft.rather.choices.positive;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;
import org.emeraldcraft.rather.choices.NullChoice;

import java.util.List;
import java.util.Random;

public class RemoveRandomNegativeChoice extends Choice.ChoiceRunnable {
    public RemoveRandomNegativeChoice() {
        super("A random negative choice is removed.");
    }

    @Override
    public void run() {
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        List<Choice.ChoiceRunnable[]> choices = playerChoices.getSelectedOptions().get(getPlayer());
        while(true) {
            int randomIndex = new Random().nextInt(choices.size());
            if(!choices.get(randomIndex)[1].isActiveChoice()) continue;
            choices.get(randomIndex)[1].cancel();
            getPlayer().sendMessage(Component.text("You removed the following choice: \"" + choices.get(randomIndex)[1].getDescription() + "\"!").color(NamedTextColor.GREEN));
            choices.get(randomIndex)[1] = new NullChoice();
            return;
        }
    }
}
