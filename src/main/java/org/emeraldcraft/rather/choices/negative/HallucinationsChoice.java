package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.emeraldcraft.rather.choiceapi.Choice;

public class HallucinationsChoice extends Choice.ChoiceRunnable implements Listener {
    public static final int DIAMOND_HALLUCINATION_RADIUS = 64;
    public static final int DIAMOND_UNDO_HALLUCINATION_RADIUS = 24;
    private int randomNoisesID;
    private int fakeDiamondsID;

    public HallucinationsChoice() {
        super("You sometimes hallucinate things that arent there...");
    }

    @Override
    public void run() {
        registerEvents();
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
            convertGlowLichen(getPlayer());
            convertDiamondOre(getPlayer());
        }, 0, 5);
    }

    @Override
    public void cancel() {
        super.cancel();
        Bukkit.getScheduler().cancelTask(randomNoisesID);
        Bukkit.getScheduler().cancelTask(fakeDiamondsID);
    }

    public void convertDiamondOre(Player player) {
        for(int x = -DIAMOND_UNDO_HALLUCINATION_RADIUS; x < DIAMOND_UNDO_HALLUCINATION_RADIUS; x++) {
            for(int z = -DIAMOND_UNDO_HALLUCINATION_RADIUS; z < DIAMOND_UNDO_HALLUCINATION_RADIUS; z++) {
                for(int y = -DIAMOND_UNDO_HALLUCINATION_RADIUS; y < DIAMOND_UNDO_HALLUCINATION_RADIUS; y++) {
                    Location loc = player.getLocation().add(x, y, z);
                    Block block = loc.getBlock();
                    if(block.getType() == Material.GLOW_LICHEN) {
                        MultipleFacing facing = ((MultipleFacing) block.getBlockData());
                        for (BlockFace face : facing.getFaces()) {
                            player.sendBlockChange(block.getLocation(), block.getBlockData());
                            Location relLoc = block.getRelative(face).getLocation();
                            player.sendBlockChange(relLoc, relLoc.getBlock().getBlockData());
                        }
                    }
                }
            }
        }
    }
    public void convertGlowLichen(Player player) {
        for(int x = -DIAMOND_HALLUCINATION_RADIUS; x < DIAMOND_HALLUCINATION_RADIUS; x++) {
            for(int z = -DIAMOND_HALLUCINATION_RADIUS; z < DIAMOND_HALLUCINATION_RADIUS; z++) {
                for(int y = -20; y < 20; y++) {
                    Location loc = player.getLocation().add(x, y, z);
                    Block block = loc.getBlock();
                    if(loc.distance(player.getLocation()) < DIAMOND_UNDO_HALLUCINATION_RADIUS + 1) continue;
                    if(block.getType() == Material.GLOW_LICHEN) {
                        MultipleFacing facing = ((MultipleFacing) block.getBlockData());
                        for (BlockFace face : facing.getFaces()) {
                            player.sendBlockChange(block.getLocation(), Material.CAVE_AIR.createBlockData());
                            player.sendBlockChange(block.getRelative(face).getLocation(), Material.DEEPSLATE_DIAMOND_ORE.createBlockData());
                        }
                    }
                }
            }
        }
    }



}
