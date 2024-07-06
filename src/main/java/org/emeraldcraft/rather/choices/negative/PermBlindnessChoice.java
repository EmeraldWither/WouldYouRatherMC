package org.emeraldcraft.rather.choices.negative;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.emeraldcraft.rather.Choice;

public class PermBlindnessChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;

    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onEffectChange(EntityPotionEffectEvent event) {
        if (event.getEntity() instanceof Player plyr) {
            if (this.player == null) {
                return;
            }
            if (!event.getEntity().equals(this.player)) {
                return;
            }
            if (event.getModifiedType() == PotionEffectType.BLINDNESS) {
                plyr.sendMessage(Component.text("You cant escape the blindness.").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.UNDERLINED));
                event.setCancelled(true);
            }
        }
    }
}
