package org.emeraldcraft.rather.choices.positive;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Piglin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PiglinBarterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.List;

import static org.bukkit.persistence.PersistentDataType.BOOLEAN;

public class AlwaysEnderEyesChoice extends Choice.ChoiceRunnable implements Listener {
    private Piglin piglin;

    public AlwaysEnderEyesChoice() {
        super("You always get eyes of enders from Piglin", "always-ender-eyes");
    }

    @Override
    public void run() {
        super.registerEvents();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        if (!getPlayer().equals(event.getPlayer())) return;
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
