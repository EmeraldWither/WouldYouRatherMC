package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.NullChoice;

import java.util.List;

public class ActiveChoicesCommand {
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        if (context.getSource().getSender() instanceof ConsoleCommandSender) {
            context.getSource().getSender().sendMessage("Console cannot use this command.");
            return 1;
        }
        Player sender = ((Player) context.getSource().getSender());

        List<Choice.ChoiceRunnable[]> choices = WouldYouRatherPlugin.getInstance().getPlayerChoices().getSelectedOptions().get(sender);
        if (choices == null) {
            sender.sendMessage(
                    Component.text("No choices have been picked yet. (Has the game been started by using ", NamedTextColor.RED).append(
                            Component.text("/wouldyourather start").color(NamedTextColor.WHITE).decorate(TextDecoration.UNDERLINED).clickEvent(ClickEvent.suggestCommand("/wouldyourather start"))
                    ).append(
                            Component.text(")").color(NamedTextColor.RED)
                    )
            );
            return 0;
        }
        sender.sendMessage(Component.text("CHOICES YOU SELECTED").decorate(TextDecoration.ITALIC, TextDecoration.BOLD));
        sender.sendMessage(Component.text("=======================================").color(NamedTextColor.YELLOW));
        for (Choice.ChoiceRunnable[] choice : choices) {
            if(choice[0] instanceof NullChoice) continue;
            String c1 = choice[0].getDescription();
            String c2 = choice[1].getDescription();
            sender.sendMessage(
                    Component.empty()
                            .append(Component.text(c1).color(NamedTextColor.GREEN))
                            .appendNewline()
                            .append(Component.text("BUT").decorate(TextDecoration.ITALIC, TextDecoration.BOLD))
                            .appendNewline()
                            .append(Component.text(c2).color(NamedTextColor.RED))
                            .appendNewline()
                            .append(Component.text("=======================================").color(NamedTextColor.YELLOW))
            );
        }
        return 0;
    }
}
