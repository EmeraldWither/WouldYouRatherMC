package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.entity.Piglin;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.List;

import static org.bukkit.persistence.PersistentDataType.*;

public class AlwaysEnderEyesChoice implements Choice.ChoiceRunnable, Listener {
    private Player player;
    private Piglin piglin;
    @Override
    public void run(Plugin plugin, Player player) {
        this.player = player;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (this.player == null) return;
        if (!this.player.equals(event.getPlayer())) return;
        if (!(event.getRightClicked() instanceof Piglin piglin)) return;
        if(event.getPlayer().getInventory().getItem(event.getHand()).getType() == Material.GOLD_INGOT) {
            this.piglin = piglin;
        }
    }
    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(event.getItemDrop().getItemStack().getType() == Material.GOLD_INGOT) {
            event.getItemDrop().getPersistentDataContainer().set(new NamespacedKey("wouldyourtherather", "piglin"), BOOLEAN, true);
        }
    }
    @EventHandler
    public void onPlayerBarter(PiglinBarterEvent event) {
        if(!isValid(event.getEntity(), event.getInput())) return;
        List<ItemStack> outcome = event.getOutcome();
        outcome.clear();
        outcome.add(new ItemStack(Material.ENDER_EYE, (int) ((Math.random() * 3) + 1)));
    }
    private boolean isValid(Piglin piglin, ItemStack item) {
        if(!item.getPersistentDataContainer().has(new NamespacedKey("wouldyourtherather", "piglin"))) {
            if(this.piglin == null) return false;
            if(!piglin.equals(this.piglin)) return true;
        }
        return true;
    }
}