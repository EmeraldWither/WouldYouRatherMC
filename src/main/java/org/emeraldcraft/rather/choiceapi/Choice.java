package org.emeraldcraft.rather.choiceapi;

import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.choices.negative.RemoveRandomPositiveChoice;
import org.emeraldcraft.rather.choices.positive.RemoveRandomNegativeChoice;

import java.util.Random;
import java.util.UUID;

import static net.kyori.adventure.text.format.NamedTextColor.RED;

public record Choice(ChoiceProvider runnable) {

    @Getter
    public abstract static class ChoiceRunnable {

        private final String description;
        private Player player;
        private UUID playerUUID;
        private final String id;

        private BukkitTask expireTask;

        @Getter
        private static final WouldYouRatherPlugin plugin = ((WouldYouRatherPlugin) JavaPlugin.getProvidingPlugin(WouldYouRatherPlugin.class));
        private boolean activeChoice = false;

        public ChoiceRunnable(String description, String id){
            this.description = description;
            this.id = id;
            generateExpireTask();
        }

        private void generateExpireTask() {
            expireTask = Bukkit.getScheduler().runTaskLater(getPlugin(), () -> {
                if(getPlayer() == null) return;
                if(this instanceof RemoveRandomNegativeChoice || this instanceof RemoveRandomPositiveChoice) return;
                cancel();
                player.sendMessage(Component.text("Your choice of \"" + description + "\" has expired!").color(RED));
            }, 20 * 60 * (new Random().nextInt(5) + 5));
        }

        public void registerEvents() {
            getPlugin().getServer().getPluginManager().registerEvents(((Listener) this), getPlugin());
        }

        public void cancel() {
            deRegisterListener();
        }

        public Player getPlayer() {
            if(player == null) player = Bukkit.getPlayer(playerUUID);
            return player;
        }
        public void setPlayer(Player player) {
            this.player = player;
            this.playerUUID = player.getUniqueId();
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

        public static ProtocolManager getProtocolLib() {
            return getPlugin().getProtocolLib();
        }

        protected void markActive() {
            activeChoice = true;
        }

        public abstract void run();
    }
}
