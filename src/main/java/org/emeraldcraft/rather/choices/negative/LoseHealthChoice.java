package org.emeraldcraft.rather.choices.negative;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Objects;

public class LoseHealthChoice extends Choice.ChoiceRunnable {

    public LoseHealthChoice() {
        super("You lose 25% of your health.", "lose-health");
    }

    @Override
    public void run() {
        markActive();
        getPlayer().registerAttribute(Attribute.GENERIC_MAX_HEALTH);
        Objects.requireNonNull(getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).setBaseValue(Objects.requireNonNull(getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH)).getBaseValue() * 0.75);
    }
    @Override
    public void cancel() {
        AttributeInstance attribute = getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
        assert attribute != null;
        attribute.setBaseValue(20);
    }
}
