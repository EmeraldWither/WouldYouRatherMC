package org.emeraldcraft.rather.choices.positive;

import org.emeraldcraft.rather.choiceapi.Choice;

public class NoHungerChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        getPlayer().setFoodLevel(99999);
    }
}
