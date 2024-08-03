package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.HashMap;

public class HallucinationsChoice extends Choice.ChoiceRunnable implements Listener {
    public static final int DIAMOND_HALLUCINATION_RADIUS = 64;
    public static final int DIAMOND_UNDO_HALLUCINATION_RADIUS = 24;
    private int randomNoisesID;
    private int fakeDiamondsID;

    private int swappedItemsID;
    private final HashMap<ItemStack, ItemStack> swappedWoodenItems = new HashMap<>();

    public HallucinationsChoice() {
        super("You sometimes hallucinate things that arent there...");
    }

    @Override
    public void run() {
        registerEvents();
        randomNoisesID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            if(getPlayer() == null) return;
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
            if(getPlayer() == null) return;
            convertGlowLichen(getPlayer());
            convertDiamondOre(getPlayer());
        }, 0, 5);

        swappedItemsID = Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            if(getPlayer() == null) return;
            for(int i = 0; i < getPlayer().getInventory().getContents().length; i++) {
                ItemStack item = getPlayer().getInventory().getContents()[i];
                if(item == null) continue;
                String itemStr = item.getType().toString();
                if(itemStr.endsWith("_SHOVEL") || itemStr.endsWith("_PICKAXE") ||itemStr.endsWith("_AXE") || itemStr.endsWith("_SWORD") || itemStr.endsWith("_HOE")) {
                    ItemStack itemStack = new ItemStack(Material.valueOf("WOODEN_" + itemStr.split("_")[1]));
                    ItemMeta itemMeta = itemStack.getItemMeta();
                    itemMeta.setCustomModelData((int) (Math.random() * 1000));
                    itemStack.setItemMeta(itemMeta);
                    swappedWoodenItems.put(itemStack, item);
                    getPlayer().getInventory().setItem(i, itemStack);
                }
            }
        }, 20 * 60 * 2, 20 * 60 * 4);

    }

    @Override
    public void cancel() {
        super.cancel();
        Bukkit.getScheduler().cancelTask(randomNoisesID);
        Bukkit.getScheduler().cancelTask(fakeDiamondsID);
        Bukkit.getScheduler().cancelTask(swappedItemsID);
        convertGlowLichen(getPlayer());
        fixInventory();

    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent event) {
        fixInventory();
    }

    public void fixInventory() {
        for(int i = 0; i < getPlayer().getInventory().getContents().length; i++) {
            ItemStack item = getPlayer().getInventory().getContents()[i];
            if(item == null) continue;
            if(swappedWoodenItems.containsKey(item)) {
                getPlayer().getInventory().setItem(i, swappedWoodenItems.get(item));
                swappedWoodenItems.remove(item);
            }
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;
        ItemStack item = event.getItemDrop().getItemStack();
        if(swappedWoodenItems.containsKey(item)) {
            event.getItemDrop().setItemStack(swappedWoodenItems.get(item));
            swappedWoodenItems.remove(item);
        }
    }

    @EventHandler
    public void onWoodToolItemChange(InventoryClickEvent event) {
        if(!event.getWhoClicked().equals(getPlayer())) return;
        if(event.getCurrentItem() == null) return;
        ItemStack item = event.getCurrentItem();
        if(swappedWoodenItems.containsKey(item)) {
            getPlayer().getInventory().setItem(event.getSlot(), swappedWoodenItems.get(item));
            event.setCancelled(true);
            getPlayer().updateInventory();
            swappedWoodenItems.remove(item);
        }
    }

    @EventHandler
    public void onWoodToolSelect(PlayerItemHeldEvent event) {
        if(!event.getPlayer().equals(getPlayer())) return;
        ItemStack item = event.getPlayer().getInventory().getItem(event.getNewSlot());
        if(item == null) return;
        if(swappedWoodenItems.containsKey(item)) {
            event.getPlayer().getInventory().setItem(event.getNewSlot(), swappedWoodenItems.get(item));
            swappedWoodenItems.remove(item);
        }
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
