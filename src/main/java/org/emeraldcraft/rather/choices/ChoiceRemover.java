package org.emeraldcraft.rather.choices;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;

import java.util.List;

public class ChoiceRemover {
    public static void removeChoice(int choicePair, boolean positive, Player player, Plugin plugin){
        int positiveIndex = positive ? 0 : 1;
        PlayerChoices playerChoices = WouldYouRatherPlugin.getInstance().getPlayerChoices();
        List<Choice.ChoiceRunnable[]> choices = playerChoices.getSelectedOptions().get(player);
        Choice.ChoiceRunnable choice = choices.get(choicePair)[positiveIndex];
        if(!choices.get(choicePair)[positiveIndex].isActiveChoice()) return;
        choice.cancel();
        final String description = choice.getDescription();
        choices.get(choicePair)[positiveIndex] = new NullChoice();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> player.sendMessage(Component.text("The following choice was removed: \"" + description + "\"!").color(NamedTextColor.YELLOW)), 5);
    }
}
