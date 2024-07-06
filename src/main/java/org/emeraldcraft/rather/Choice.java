package org.emeraldcraft.rather;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public record Choice(ChoiceRunnable runnable, String description, boolean positive, String id) {

    public interface ChoiceRunnable {
        void run(Plugin plugin, Player player);
    }
}
