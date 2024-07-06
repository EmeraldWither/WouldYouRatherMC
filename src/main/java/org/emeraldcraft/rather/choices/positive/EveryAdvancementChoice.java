package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class EveryAdvancementChoice implements Choice.ChoiceRunnable {
    @Override
    public void run(Plugin plugin, Player player) {
        for (@NotNull Iterator<Advancement> it = Bukkit.advancementIterator(); it.hasNext(); ) {
            Advancement advancement = it.next();
            AdvancementProgress progress = player.getAdvancementProgress(advancement);
            for (String criterion : progress.getRemainingCriteria()) {
                progress.awardCriteria(criterion);
            }
        }
    }
}
