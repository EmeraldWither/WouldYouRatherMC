package org.emeraldcraft.rather.choices.negative;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.ArrayList;

public class RandomStructureSpawnChoice extends Choice.ChoiceRunnable {
    public RandomStructureSpawnChoice() {
        super("A random structure will spawn on you");
    }

    @Override
    public void run() {
        ArrayList<NamespacedKey> structures = new ArrayList<>();
        RegistryAccess.registryAccess().getRegistry(RegistryKey.STRUCTURE).forEach(structure -> structures.add(RegistryAccess.registryAccess().getRegistry(RegistryKey.STRUCTURE).getKey(structure)));
        NamespacedKey namespacedKey = structures.get((int) (Math.random() * structures.size()));
        Location l = getPlayer().getLocation();
        //jank solution becuase the bukkit structure api is hot garbage
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "place structure " + namespacedKey.getNamespace() + ":" + namespacedKey.getKey() + " " + ((int) l.getX()) + " " + ((int) l.getY()) + " " + ((int) l.getZ()));
        getPlayer().sendMessage(Component.text("The following structure has been spawned: " + namespacedKey.asMinimalString()));
    }
}
