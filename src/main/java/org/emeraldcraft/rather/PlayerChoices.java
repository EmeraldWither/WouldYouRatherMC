package org.emeraldcraft.rather;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.ChatColor;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class PlayerChoices {
    private final HashMap<Player, Choice[][]> proposedOptions = new HashMap<>();
    private final HashMap<Player, Inventory> playerInventories = new HashMap<>();

    public void processChoiceOne(Player player) {
        Choice[][] choices = proposedOptions.get(player);
        choices[0][0].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        choices[0][1].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        player.sendMessage(getText(1, choices));
        proposedOptions.remove(player);
        playerInventories.remove(player);
    }

    public void processChoiceTwo(Player player) {
        Choice[][] choices = proposedOptions.get(player);
        choices[1][0].runnable().run(WouldYouRatherPlugin.getInstance(), player);
        choices[1][1].runnable().run(WouldYouRatherPlugin.getInstance(), player);

        player.sendMessage(getText(2, choices));

        proposedOptions.remove(player);
        playerInventories.remove(player);

    }

    public void proposeOptions(Player player, Choice[][] choices, Inventory inventory) {
        proposedOptions.put(player, choices);
        playerInventories.put(player, inventory);
    }

    public Component getText(int option, Choice[][] choices) {
        return Component.newline()
                .append(Component.text("You have chosen option " + option + "!").color(NamedTextColor.WHITE).decorate(TextDecoration.UNDERLINED))
                .appendNewline()
                .append(Component.text(choices[option - 1][0].description()).color(NamedTextColor.GREEN).decorate(TextDecoration.BOLD))
                .appendNewline()
                .append(Component.text("BUT").color(NamedTextColor.WHITE))
                .appendNewline()
                .append(Component.text(choices[option - 1][1].description()).color(NamedTextColor.RED).decorate(TextDecoration.BOLD))
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
