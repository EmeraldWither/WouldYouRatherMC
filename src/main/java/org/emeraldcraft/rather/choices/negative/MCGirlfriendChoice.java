package org.emeraldcraft.rather.choices.negative;

import io.papermc.paper.entity.TeleportFlag;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Random;

import static org.emeraldcraft.rather.choices.negative.MCGirlfriendChoice.MCGirlfriendDialogue.*;

public class MCGirlfriendChoice extends Choice.ChoiceRunnable implements Listener {
    public MCGirlfriendChoice() {
        super("Your mc girlfriend breaks up with you.", "mc-girlfriend");
    }

    public static final class MCGirlfriendDialogue {
        public static final String[] STAGE_ONE = new String[]{
                "So %player%, you know I have been thinking..."
        };
        public static final String[] STAGE_TWO = new String[]{
                "This whole thing hasn't been working out as I would have imagined, and I think you agree"
        };
        public static final String[] STAGE_THREE = new String[]{
                "So %player%, we're done. Get away from me, and never come talk to me again"
        };
        public static final String[] FORGIVENESS = new String[]{
                "Y'know what %player%? I think we get can get over our differences",
                "On the other hand %player%, after meeting your dad, I think we can stay together"
        };
    }

    private boolean active = false;
    private final Random random = new Random();

    @Override
    public void run() {
        registerEvents();
        runLater(() -> {
            Bukkit.broadcast(getChatMessage(STAGE_ONE));
            runLater(() -> {
                Bukkit.broadcast(getChatMessage(STAGE_TWO));
                runLater(() -> {
                    Bukkit.broadcast(getChatMessage(STAGE_THREE));
                    runLater(() -> {
                        active = true;
                        Bukkit.broadcast(Component.text("%s can no longer look up because of the shame that they have brought...".formatted(getPlayer().getName())).color(NamedTextColor.DARK_RED));
                    }, 20 * 4);
                }, 20 * 3);
            }, 20 * 3);

        }, 15);
    }

    @Override
    public void cancel() {
        super.cancel();
        active = false;
        Bukkit.broadcast(getChatMessage(FORGIVENESS));
    }

    @EventHandler
    public void onPlayerLookUp(PlayerMoveEvent event) {
        if (!event.getPlayer().equals(getPlayer())) return;
        if (!active) return;
        if (event.getTo().getPitch() <= 0F) {
            Location location = getPlayer().getLocation();
            location.setPitch(25F);
            getPlayer().teleport(location, TeleportFlag.Relative.X,
                    TeleportFlag.Relative.Y,
                    TeleportFlag.Relative.Z, TeleportFlag.Relative.YAW);
            getPlayer().sendMessage(Component.text("You cannot look up because of your shame.").color(NamedTextColor.RED));
        }
    }

    private Component getChatMessage(String[] array) {
        String gfText = array[random.nextInt(array.length)].replaceAll("%player%", getPlayer().getName());
        String gfName = "<%s's GF> ".formatted(getPlayer().getName());
        return Component.text(gfName + gfText);
    }

    private void runLater(Runnable runnable, int delay) {
        Bukkit.getScheduler().runTaskLater(getPlugin(), runnable, delay);
    }

}
