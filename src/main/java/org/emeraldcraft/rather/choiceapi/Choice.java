package org.emeraldcraft.rather.choiceapi;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public record Choice(ChoiceProvider runnable) {

    @Getter
    public abstract static class ChoiceRunnable {

        private final String description;

        @Setter
        private Player player;
        @Setter
        private Plugin plugin;
        private boolean activeChoice = false;

        public ChoiceRunnable(String description){
            this.description = description;
        }

        public void registerEvents() {
            getPlugin().getServer().getPluginManager().registerEvents(((Listener) this), getPlugin());
        }

        public void cancel() {
            deRegisterListener();
        }

        public Player getPlayer() {
            if(!player.isValid() || !player.isOnline()) player = Bukkit.getPlayer(player.getUniqueId());
            return player;
        }

        /**
         * @return If this choice is active, for example, if it is using a listener to prevent a player from doing something
         */
        public boolean isActiveChoice() {
            return this instanceof Listener || activeChoice;
        }

        protected void deRegisterListener() {
            if(this instanceof Listener) {
                //unregisters ourself as a listener
                HandlerList.unregisterAll(((Listener) this));
            }
        }

        protected void markActive() {
            activeChoice = true;
        }

        public abstract void run();
    }
}
