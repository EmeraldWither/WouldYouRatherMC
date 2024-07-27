package org.emeraldcraft.rather.choices.negative;

import org.bukkit.attribute.Attribute;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Objects;

public class LoseHealthChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        getPlayer().registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        Objects.requireNonNull(getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Objects.requireNonNull(getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() * 0.75);
    }
}
