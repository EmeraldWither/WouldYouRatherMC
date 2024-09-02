package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Random;

public class DecayingWorldChoice extends Choice.ChoiceRunnable {
    private final Random random = new Random();
    private int id;

    public DecayingWorldChoice() {
        super("The world decays around you");
    }

    @Override
    public void run() {
        markActive();
        id = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), this::decay, 0, 2);
    }

    @Override
    public void cancel() {
        super.cancel();
        Bukkit.getScheduler().cancelTask(id);
    }

    private void decay() {
        int x = random.nextInt(-2, 2);
        int z = random.nextInt(-2, 2);
        int y = random.nextInt(-4, 2);
        getPlayer().getLocation().add(x, y + 2, z).getBlock().setType(Material.AIR);
    }
}
