package org.emeraldcraft.rather.choices.negative;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.emeraldcraft.rather.choiceapi.Choice;

public class PermBlindnessChoice extends Choice.ChoiceRunnable implements Listener {
    public PermBlindnessChoice() {
        super("You will have permanent blindness");
    }

    @Override
    public void run() {
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
        super.registerEvents();
    }

    @EventHandler
    public void onEffectChange(EntityPotionEffectEvent event) {
        if (event.getEntity() instanceof Player plyr) {
            if (getPlayer() == null) {
                return;
            }
            if (!event.getEntity().equals(getPlayer())) {
                return;
            }
            if(event.getNewEffect() != null && event.getNewEffect().getType() == PotionEffectType.BLINDNESS) return;
            if (event.getModifiedType() == PotionEffectType.BLINDNESS) {
                plyr.sendMessage(Component.text("You cant escape the blindness.").color(TextColor.color(255, 255, 0)).decorate(TextDecoration.UNDERLINED));
                getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
            }
        }
    }

    @EventHandler
    public void onDeath(PlayerPostRespawnEvent e) {
        if(!e.getPlayer().equals(getPlayer())) return;
        getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, PotionEffect.INFINITE_DURATION, 1, true, true));
    }

    @Override
    public void cancel() {
        deRegisterListener();
        getPlayer().removePotionEffect(PotionEffectType.BLINDNESS);
    }
}
