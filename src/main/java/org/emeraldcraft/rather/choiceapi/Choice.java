package org.emeraldcraft.rather.choiceapi;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public record Choice(ChoiceProvider runnable, String description, boolean positive, String id) {

    public interface ChoiceRunnable {
        void run(Plugin plugin, Player player);
    }
}
