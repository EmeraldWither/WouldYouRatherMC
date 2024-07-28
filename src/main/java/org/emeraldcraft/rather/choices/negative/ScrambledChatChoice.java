package org.emeraldcraft.rather.choices.negative;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.emeraldcraft.rather.choiceapi.Choice;

public class ScrambledChatChoice extends Choice.ChoiceRunnable implements Listener {
    @Override
    public void run() {
        super.registerEvents();
    }

    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) -> {
            if (getPlayer().equals(source)) {
                return message.decorate(TextDecoration.OBFUSCATED).append(message);
            }
            return message;
        });
    }

}
