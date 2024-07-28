package org.emeraldcraft.rather.choices.positive;

import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

import static org.bukkit.potion.PotionEffectType.JUMP_BOOST;

public class IncreaseJumpHeightChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(JUMP_BOOST, Integer.MAX_VALUE, 1));
    }
}
