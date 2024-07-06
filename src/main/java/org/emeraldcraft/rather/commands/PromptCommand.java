package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.Choice;
import org.emeraldcraft.rather.Choices;
import org.emeraldcraft.rather.WouldYouRatherInventory;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choices.positive.InfiniteDiamondsChoice;

import java.util.Random;

@SuppressWarnings("UnstableApiUsage")
public class PromptCommand {
    public int run(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        Player sender = context.getArgument("player", PlayerSelectorArgumentResolver.class).resolve(context.getSource()).getFirst();

        Choice[] option1 = new Choice[2];
        option1[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
        option1[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];

        Choice[] option2 = new Choice[2];
        option2[0] = Choices.POSITIVE[new Random().nextInt(Choices.POSITIVE.length)];
        option2[1] = Choices.NEGATIVE[new Random().nextInt(Choices.NEGATIVE.length)];


        Inventory inventory = new WouldYouRatherInventory(JavaPlugin.getPlugin(WouldYouRatherPlugin.class),option1, option2).getInventory();
        WouldYouRatherPlugin.getInstance().getPlayerChoices().proposeOptions(sender, new Choice[][]{option1, option2}, inventory);
        assert sender != null;
        sender.openInventory(inventory);
        return 0;
    }

}
