package org.emeraldcraft.rather.choices.positive;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.emeraldcraft.rather.choiceapi.Choice;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;

@ApiStatus.Experimental
public class ESPChoice extends Choice.ChoiceRunnable {
    private final List<ESPEntity> entities = new ArrayList<>();

    public ESPChoice() {
        super("You have mob ESP", "mob-esp");
    }

    @Override
    public void run() {
        getProtocolLib().addPacketListener(new PacketAdapter(
                getPlugin(),
                PacketType.Play.Server.ENTITY_METADATA
        ) {
            @Override
            public void onPacketSending(PacketEvent event) {
                int id = event.getPacket().getIntegers().read(0);
                for (ESPEntity entity : entities) {
                    if (entity.entity().getEntityId() == id) {
                        entity.addGlow(event);
                        return;
                    }
                }
            }
        });
        markActive();
        Bukkit.getScheduler().scheduleSyncRepeatingTask(getPlugin(), () -> {
            List<Entity> espEntities = new ArrayList<>();
            entities.forEach(e -> espEntities.add(e.entity));
            List<Entity> nearbyEntities = getPlayer().getNearbyEntities(10, 10, 10);
            nearbyEntities.removeAll(espEntities);
            nearbyEntities.forEach(e -> {
                ESPEntity espEntity = new ESPEntity(e, getPlayer());
                getProtocolLib().updateEntity(e, List.of(getPlayer()));
                entities.add(espEntity);
            });

            espEntities.removeAll(nearbyEntities);
            espEntities.forEach(entity -> {
                entities.removeIf(e -> e.entity().equals(entity));
                getProtocolLib().updateEntity(entity, List.of(getPlayer()));
            });

        }, 0, 10);
    }

    public record ESPEntity(Entity entity, Player player) {

        public void addGlow(PacketEvent event) {
            event.getPacket().getDataValueCollectionModifier()
                    .write(0, List.of(new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), buildByte(true))));
        }

        public void removeGlow() {
            getProtocolLib().updateEntity(entity, List.of(player));
        }

        private byte buildByte(boolean glow) {
            byte flag = 0;
            flag |= (byte) (entity.isVisualFire() ? 0x01 : 0);
            flag |= (byte) (entity.isSneaking() ? 0x02 : 0);
            flag |= (byte) (entity.isInvisible() ? 0x20 : 0);
            flag |= (byte) (glow ? 0x40 : 0);
            return flag;
        }

        private void sendPacket(byte data) {
            Player player = player();

            PacketContainer packet = getProtocolLib().createPacket(PacketType.Play.Server.ENTITY_METADATA);
            packet.getIntegers().write(0, entity.getEntityId());
            WrappedDataValue glowValue = new WrappedDataValue(0, WrappedDataWatcher.Registry.get(Byte.class), data);
            packet.getDataValueCollectionModifier().write(0, List.of(glowValue));
            getProtocolLib().sendServerPacket(player, packet);
        }
    }
}
