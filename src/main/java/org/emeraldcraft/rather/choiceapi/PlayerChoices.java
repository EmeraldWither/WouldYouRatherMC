package org.emeraldcraft.rather.choiceapi;

import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.emeraldcraft.rather.WouldYouRatherPlugin;
import org.emeraldcraft.rather.inventory.InventoryAnimationTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerChoices {
    private final HashMap<Player, Choice[][]> proposedOptions = new HashMap<>();
    @Getter
    private final HashMap<Player, List<Choice.ChoiceRunnable[]>> selectedOptions = new HashMap<>();
    private final HashMap<Player, Inventory> playerInventories = new HashMap<>();
    private final HashMap<Player, Integer> animationTasks = new HashMap<>();

    public void processChoiceOne(Player player) {
        runChoices(0, player);
    }

    private void addSelectedOption(Player player, Choice.ChoiceRunnable[] choices) {
        if(!selectedOptions.containsKey(player)) {
            selectedOptions.put(player, new ArrayList<>());
        }
        selectedOptions.get(player).add(choices);
    }

    private void runChoices(int x, Player player) {
        Choice[][] choices = proposedOptions.get(player);
        proposedOptions.remove(player);
        playerInventories.remove(player);
        Choice.ChoiceRunnable choice = choices[x][0].runnable().getChoice();
        choice.setPlayer(player);
        choice.run();


        Choice.ChoiceRunnable choice2 = choices[x][1].runnable().getChoice();
        choice2.setPlayer(player);
        choice2.run();

        addSelectedOption(player, new Choice.ChoiceRunnable[]{choice, choice2});

        Bukkit.getScheduler().cancelTask(animationTasks.get(player));
        animationTasks.remove(player);
        player.sendMessage(getText(x, choices));
    }

    public void processChoiceTwo(Player player) {
        runChoices(1, player);
    }

    public void proposeOptions(Player player, Choice[][] choices, Inventory inventory) {
        proposedOptions.put(player, choices);
        playerInventories.put(player, inventory);
        if(animationTasks.containsKey(player)) {
            Bukkit.getScheduler().cancelTask(animationTasks.get(player));
            animationTasks.remove(player);
        }
        animationTasks.put(player, Bukkit.getScheduler().scheduleSyncRepeatingTask(WouldYouRatherPlugin.getInstance(), new InventoryAnimationTask(player), 0, 10));
    }

    public Component getText(int option, Choice[][] choices) {
        return Component.newline()
                .append(Component.text("You have chosen option " + option + "!").color(NamedTextColor.WHITE).decorate(TextDecoration.UNDERLINED))
                .appendNewline()
                .append(Component.text(choices[option][0].runnable().getChoice().getDescription()).color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("BUT").color(NamedTextColor.WHITE))
                .appendNewline()
                .append(Component.text(choices[option][1].runnable().getChoice().getDescription()).color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
                .appendNewline()
                .appendNewline();
    }

    public boolean inProgressPlayer(Player player) {
        return proposedOptions.containsKey(player);
    }

    public Inventory getPlayerInventory(Player player) {
        return playerInventories.get(player);
    }
}
