package org.emeraldcraft.rather.choices.negative;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.*;
import org.emeraldcraft.rather.choiceapi.Choice;

import java.util.Collection;
import java.util.List;

/**
 * this code absolutely sucks
 */
public class AllItemsDeCraftedChoice extends Choice.ChoiceRunnable {
    @Override
    public void run() {
        Player player = getPlayer();
        ItemStack[] items = player.getInventory().getContents();
        for (int i = 0; i < items.length; i++) {
            ItemStack item = items[i];
            if(item == null || item.getType() == Material.AIR) continue;
            List<Recipe> recipes = Bukkit.getRecipesFor(item);
            for(int j = 0; j < item.getAmount(); j++) {
                if(recipes.isEmpty()) {
                    player.getWorld().dropItem(player.getLocation(), item);
                    break;
                }
                if(recipes.getFirst() instanceof ShapedRecipe recipe) {
                    for (RecipeChoice ingredient : recipe.getChoiceMap().values()) {
                        if(ingredient == null) continue;
                        player.getWorld().dropItem(player.getLocation(), ingredient.getItemStack());
                    }
                }
                else if(recipes.getFirst() instanceof ShapelessRecipe recipe) {
                    for (RecipeChoice ingredient : recipe.getChoiceList()) {
                        if(ingredient == null) continue;
                        //validate the recipe
                        if(validateShapelessRecipe(item, ingredient.getItemStack())) {
                            player.getWorld().dropItem(player.getLocation(), ingredient.getItemStack());
                            j+=getShapelessRecipe(item, ingredient.getItemStack());
                            continue;
                        }
                    }
                }
                else if(recipes.getFirst() instanceof SmithingRecipe recipe) {
                    player.getWorld().dropItem(player.getLocation(), recipe.getBase().getItemStack());
                    player.getWorld().dropItem(player.getLocation(), recipe.getAddition().getItemStack());
                }
                else {
                    System.out.println(recipes.getFirst().getClass().getName());
                }
            }
            items[i] = new ItemStack(Material.AIR);
        }
        player.getInventory().setContents(items);
    }

    private boolean validateShapelessRecipe(ItemStack original, ItemStack recipeItem) {
        List<Recipe> recipesFor = Bukkit.getRecipesFor(recipeItem);
        Recipe first = recipesFor.getFirst();
        if(first == null) return false;
        if(!(first instanceof ShapedRecipe recipe)) return false;
        Collection<RecipeChoice> choiceList = recipe.getChoiceMap().values();
        return choiceList.size() <= original.getAmount();
    }

    private int getShapelessRecipe(ItemStack original, ItemStack recipeItem) {
        List<Recipe> recipesFor = Bukkit.getRecipesFor(recipeItem);
        Recipe first = recipesFor.getFirst();
        if(!(first instanceof ShapedRecipe recipe)) return -1;
        Collection<RecipeChoice> choiceList = recipe.getChoiceMap().values();
        return choiceList.size();
    }
}
