package org.emeraldcraft.rather.choices;

import org.emeraldcraft.rather.choiceapi.Choice;

public class NullChoice extends Choice.ChoiceRunnable {
    public NullChoice() {
        super("This choice was removed!");
    }

    @Override
    public void run() {}
}
