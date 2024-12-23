package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.ChoiceRemover;

import java.util.List;

public class RemoveChoice {
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player player = context.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(context.getSource()).getFirst();
        String name = context.getArgument("name", String.class);
        List<Choice.ChoiceRunnable[]> choiceRunnables = WouldYouRatherPlugin.getInstance().getPlayerChoices().getSelectedOptions().get(player);
        if(choiceRunnables == null || choiceRunnables.isEmpty()) {
            player.sendMessage(Component.text("They have no active choices", NamedTextColor.RED));
            return 1;
        }
        for (int i = 0; i < choiceRunnables.size(); i++) {
            if (choiceRunnables.get(i)[0].getId().equals(name) || choiceRunnables.get(i)[1].getId().equals(name)) {
                boolean positive = choiceRunnables.get(i)[0].getId().equals(name);
                ChoiceRemover.removeChoice(i, positive, player, WouldYouRatherPlugin.getInstance());
                return 0;
            }
        }
        player.sendMessage(Component.text("Could not find that ID", NamedTextColor.RED));


        return 1;
    }
}
