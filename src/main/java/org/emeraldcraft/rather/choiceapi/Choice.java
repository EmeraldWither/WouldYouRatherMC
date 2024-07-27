package org.emeraldcraft.rather.choiceapi;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

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
            plugin.getServer().getPluginManager().registerEvents(((Listener) this), plugin);
        }

        public abstract void run();
    }
}
