package org.emeraldcraft.rather;

import com.mojang.brigadier.tree.LiteralCommandNode;
import com.samjakob.spigui.SpiGUI;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;
import org.emeraldcraft.rather.commands.ActiveChoicesCommand;
import org.emeraldcraft.rather.commands.PromptCommand;
import org.emeraldcraft.rather.commands.StartCommand;
import org.emeraldcraft.rather.listeners.MenuInteractionListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class WouldYouRatherPlugin extends JavaPlugin {
    @Getter
    private static WouldYouRatherPlugin instance;
    @Getter
    private PlayerChoices playerChoices;
    @Getter
    private SpiGUI spiGUI;
    public static void setInstance(WouldYouRatherPlugin instance) {
        WouldYouRatherPlugin.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        spiGUI = new SpiGUI(this);
        playerChoices = new PlayerChoices();
        @NotNull LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register(
                    getWouldYouRatherCommand(),
                    "Default Would You Rather Command",
                    List.of()
            );
        });
        Bukkit.getPluginManager().registerEvents(new MenuInteractionListener(), this);
    }

    private @NotNull LiteralCommandNode<CommandSourceStack> getWouldYouRatherCommand() {
        return Commands.literal("wouldyourather")
                .then(Commands.literal("prompt")
                        .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("wouldyourather.prompt"))
                        .then(Commands.argument("player", ArgumentTypes.player()).executes(
                                        context -> new PromptCommand().run(context)
                                )
                        )
                )
                .then(Commands.literal("start")
                        .requires(commandSourceStack -> commandSourceStack.getSender().hasPermission("wouldyourather.start"))
                        .executes(context -> new StartCommand().run(context))
                )
                .then(Commands.literal("choices")
                        .executes(context -> new ActiveChoicesCommand().run(context))
                )

                .build();
    }
}
