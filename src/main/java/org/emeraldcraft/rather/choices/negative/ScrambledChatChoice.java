package org.emeraldcraft.rather.choices.negative;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ScrambledChatChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;

    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) -> {
            if (player.equals(source)) {
                return message.decorate(TextDecoration.OBFUSCATED).append(Component.text("thing added"));
            }
            return message;
        });
    }

}
