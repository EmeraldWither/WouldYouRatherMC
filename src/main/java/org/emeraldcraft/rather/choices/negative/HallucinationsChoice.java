package org.emeraldcraft.rather.choices.negative;

import org.bukkit.*;
import org.emeraldcraft.rather.choiceapi.Choice;

public class HallucinationsChoice extends Choice.ChoiceRunnable {
    private int randomNoisesID;
    private int fakeDiamondsID;

    public HallucinationsChoice() {
        super("You sometimes hallucinate things that arent there...");
    }

    @Override
    public void run() {
        randomNoisesID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            int num = (int) (Math.random() * 3);
            Sound sound = Sound.ENTITY_ZOMBIE_HURT;
            if (num == 0) {
                if(getPlayer().getLocation().getY() > 5) num = (int) (Math.random() * 3);
                else sound = Sound.BLOCK_SCULK_SHRIEKER_SHRIEK;
            }
            if (num == 1) sound = Sound.ENTITY_CREEPER_PRIMED;
            if (num == 2) sound = Sound.ENTITY_PHANTOM_SWOOP;
            Location loc = getPlayer().getLocation().add(Math.random() * 20, Math.random() * 5, Math.random() * 20);
            getPlayer().playSound(loc, sound, 1.0f, 1.0f);

        }, 0, 20 * 60 * 2);

        fakeDiamondsID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            Location lookLoc = getPlayer().getLocation().multiply(15).add(0, 10, 0);
            getPlayer().getWorld().spawnParticle(Particle.HAPPY_VILLAGER, lookLoc, 10);
            if(getPlayer().hasLineOfSight(lookLoc)) {
                getPlayer().getWorld().getBlockAt(lookLoc).setType(Material.BEDROCK);
            }
        }, 0, 5);
    }



}
