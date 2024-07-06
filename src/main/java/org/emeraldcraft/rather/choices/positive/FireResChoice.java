package org.emeraldcraft.rather.choices.positive;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.emeraldcraft.rather.choiceapi.Choice;

public class FireResChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        player.addPotionEffect(new PotionEffect(org.bukkit.potion.PotionEffectType.FIRE_RESISTANCE, PotionEffect.INFINITE_DURATION, 1));
    }
}
