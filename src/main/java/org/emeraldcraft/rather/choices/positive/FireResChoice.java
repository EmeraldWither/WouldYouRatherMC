package org.emeraldcraft.rather.choices.positive;

import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

public class FireResChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }
}
