package org.emeraldcraft.rather.commands;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.PlayerPrompter;
import org.emeraldcraft.rather.WouldYouRatherPlugin;

import static net.kyori.adventure.text.format.NamedTextColor.GREEN;

public class StartCommand {
    public int run(CommandContext<CommandSourceStack> context){
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(WouldYouRatherPlugin.class), () -> {
            for(Player player : Bukkit.getOnlinePlayers()){
                PlayerPrompter.promptPlayer(player);
            }
        }, 20 * 30 * 5, 20 * 60 * 5);
        context.getSource().getSender().sendMessage(Component.text("You have successfully started the game. The current timing is 5 mins.").color(GREEN));
        return 0;
    }
}
