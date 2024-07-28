package org.emeraldcraft.rather.choiceapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.WouldYouRatherPlugin;

public record Choice(ChoiceProvider runnable, String description, boolean positive, String id) {

    public abstract static class ChoiceRunnable {
        private Player player;
        private Plugin plugin;

        public Player getPlayer() {
            return this.player;
        }
        void setPlayer(Player player) {
            this.player = player;
        }
        public Plugin getPlugin() {
            return plugin;
        }
        public void setPlugin(Plugin plugin) {
            this.plugin = plugin;
        }

        public void registerEvents() {
            getPlugin().getServer().getPluginManager().registerEvents(((Listener) this), getPlugin());
        }

        public abstract void run();
    }
}
