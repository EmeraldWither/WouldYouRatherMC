package org.emeraldcraft.rather.choices.positive;

import org.emeraldcraft.rather.choiceapi.Choice;

public class NoHungerChoice extends Choice.ChoiceRunnable {
    public NoHungerChoice() {
        super("You will no longer go hungry");
    }

    @Override
    public void run() {
        getPlayer().setFoodLevel(99999);
    }
}
