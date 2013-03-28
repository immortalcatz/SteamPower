package steamcraft.steamcraft.api.recipes;

import java.util.ArrayList;
import java.util.Set;

import net.minecraft.item.ItemStack;
import steamcraft.steamcraft.api.util.OreStack;
import steamcraft.steamcraft.api.util.Pair;

public class MetallurgyRecipes{
    private static final MetallurgyRecipes metallurgyBase = new MetallurgyRecipes();

    public static final MetallurgyRecipes metallurgy() {
        return metallurgyBase;
    }

    private final ArrayList<Pair<ItemStack, Set<OreStack>>> recipeList = new ArrayList();

    /**
     * Creates a metallurgy recipe.
     * NOTE: Behavior is undefined if ingredient set has redundant stacks.
     * @param result the alloy result
     * @param ingredients the ingredients for the recipe.
     */
    public void addRecipeMetallurgy(ItemStack result, Set<OreStack> ingredients) {
    	recipeList.add(new Pair(result, ingredients));
    }

    /**
     * Gets the result of alloy smelting the given ingredients.
     * NOTE: Behavior is undefined if ingredient set has redundant stacks.
     * @param ingrediants
     * @return The result of the recipe.
     */
    public ItemStack getMetallurgyResult(Set<OreStack> ingredients) {
    	Pair<ItemStack, Set<OreStack>> recipeMatch = null;
    	for (Pair<ItemStack, Set<OreStack>> recipe : recipeList) {
    		boolean flag = false;
    		for (OreStack stack : recipe.getRight()) {
    			for (OreStack otherStack : ingredients) {
    				if ((otherStack.getMaterial() == stack.getMaterial()) && (otherStack.getAmount() > stack.getAmount())) {
    					flag = true;
    					break;
    				}
    			}
    			if (!flag) {
    				break;
    			}
    		}
    		if (flag) {
    			recipeMatch = recipe;
    			break;
    		}
    	}
    	return (recipeMatch != null ? recipeMatch.getLeft() : null);
    }
}