package org.emeraldcraft.rather;

import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.emeraldcraft.rather.choiceapi.PlayerChoices;
import org.emeraldcraft.rather.commands.PromptCommand;
import org.emeraldcraft.rather.listeners.MenuInteractionListener;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@SuppressWarnings("UnstableApiUsage")
public final class WouldYouRatherPlugin extends JavaPlugin {
    private static WouldYouRatherPlugin instance;
    private PlayerChoices playerChoices;

    public static WouldYouRatherPlugin getInstance() {
        return instance;
    }

    public static void setInstance(WouldYouRatherPlugin instance) {
        WouldYouRatherPlugin.instance = instance;
    }

    @Override
    public void onEnable() {
        setInstance(this);
        playerChoices = new PlayerChoices();
        @NotNull LifecycleEventManager<Plugin> manager = this.getLifecycleManager();
        manager.registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            final Commands commands = event.registrar();
            commands.register(
                    getCommand(),
                    "Default Would You Rather Command",
                    List.of()
            );
        });

        Bukkit.getPluginManager().registerEvents(new MenuInteractionListener(), this);

    }

    private @NotNull LiteralCommandNode<CommandSourceStack> getCommand() {
        return Commands.literal("wouldyourather")
                .then(Commands.literal("prompt")
                        .then(Commands.argument("player", ArgumentTypes.player()).executes(
                                context -> new PromptCommand().run(context)
                        )
                    )
                )
                .build();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public PlayerChoices getPlayerChoices() {
        return this.playerChoices;
    }
}
