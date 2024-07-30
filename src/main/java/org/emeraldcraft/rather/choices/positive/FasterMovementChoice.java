package org.emeraldcraft.rather.choices.positive;

import org.bukkit.potion.PotionEffect;

import static org.bukkit.potion.PotionEffectType.*;

public class FasterMovementChoice extends org.emeraldcraft.rather.choiceapi.Choice.ChoiceRunnable {
    public FasterMovementChoice() {
        super("You move faster");
    }

    @Override
    public void run() {
        this.getPlayer().addPotionEffect(new PotionEffect(SPEED, Integer.MAX_VALUE, 1));
    }
}
