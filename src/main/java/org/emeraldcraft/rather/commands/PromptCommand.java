package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.emeraldcraft.rather.PlayerPrompter;

@SuppressWarnings("UnstableApiUsage")
public class PromptCommand {
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        PlayerPrompter.promptPlayer(context.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(context.getSource()).getFirst());
        return 0;
    }

}
