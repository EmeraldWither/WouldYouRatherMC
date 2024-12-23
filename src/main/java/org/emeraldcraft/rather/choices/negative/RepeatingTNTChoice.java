package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.emeraldcraft.rather.choiceapi.Choice;

public class RepeatingTNTChoice extends Choice.ChoiceRunnable {
    public static final int DELAY = 20 * 30;
    private int taskID;
    public RepeatingTNTChoice() {
        super("TNT spawns on you every 30 seconds", "tnt-spawns");
    }

    @Override
    public void run() {
        markActive();
        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            if(getPlayer() == null) return;
            getPlayer().getWorld().spawnEntity(getPlayer().getLocation(), EntityType.TNT);
        },40, DELAY);
    }

    @Override
    public void cancel() {
        Bukkit.getScheduler().cancelTask(taskID);
    }
}
