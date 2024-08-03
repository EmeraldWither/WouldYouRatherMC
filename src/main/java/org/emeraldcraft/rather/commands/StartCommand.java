package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.emeraldcraft.rather.choices.Choices;
import org.emeraldcraft.rather.inventory.WouldYouRatherInventory;

import java.util.Random;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class StartCommand {
    public int run(CommandContext<CommandSourceStack> context){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(WouldYouRatherPlugin.class), () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                Choice[] option1 = new Choice[2];
                Random ran = new Random();
                option1[0] = Choices.POSITIVE[ran.nextInt(Choices.POSITIVE.length)];
                option1[1] = Choices.NEGATIVE[ran.nextInt(Choices.NEGATIVE.length)];
                ran.nextInt();
                Choice[] option2 = new Choice[2];
                option2[0] = Choices.POSITIVE[ran.nextInt(Choices.POSITIVE.length)];
                option2[1] = Choices.NEGATIVE[ran.nextInt(Choices.NEGATIVE.length)];

                Inventory inventory = new WouldYouRatherInventory(JavaPlugin.getPlugin(WouldYouRatherPlugin.class),option1, option2, WouldYouRatherInventory.INVENTORY_NAME).getInventory();
                WouldYouRatherPlugin.getInstance().getPlayerChoices().proposeOptions(player, new Choice[][]{option1, option2}, inventory);
                player.openInventory(inventory);
            }
        }, 20 * 60 * 2, 20 * 60 * 5);
        context.getSource().getSender().sendMessage(Component.text("You have successfully started the game. The current timing is 5 mins.").color(GREEN));
        return 0;
    }
}
