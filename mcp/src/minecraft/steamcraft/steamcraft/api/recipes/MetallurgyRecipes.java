package steamcraft.steamcraft.api.recipes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.minecraft.item.ItemStack;

public class MetallurgyRecipes
{
    private static final MetallurgyRecipes metallurgyBase = new MetallurgyRecipes();

    public static final MetallurgyRecipes metallurgy() {
        return metallurgyBase;
    }

    private List<RecipeMetallurgy> recipes;

    private MetallurgyRecipes() {
    	recipes = new ArrayList(4);
    }

    public void addAlloy(Collection<ItemStack> ingredients, ItemStack result, float xp) {
    	recipes.add(new RecipeMetallurgy(ingredients, result, xp));
    }

    public List getAlloyList() {
    	return new ArrayList(recipes);
    }

    public RecipeMetallurgy getMetallurgyRecipe(Collection<ItemStack> ingredients) {
    	for (RecipeMetallurgy recipe : recipes) {
    		if (recipe.canSmelt(ingredients)) return recipe;
    	}
    	return null;
    }
}