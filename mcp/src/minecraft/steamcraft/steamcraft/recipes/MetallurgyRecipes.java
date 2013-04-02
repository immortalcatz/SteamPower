package steamcraft.steamcraft.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import steamcraft.steamcraft.util.OreStack;
import steamcraft.steamcraft.util.Pair;

public class MetallurgyRecipes{
    private static final MetallurgyRecipes metallurgyBase = new MetallurgyRecipes();

    /**
     * Retrieves the primary instance of the class.
     * @return the main instance of the class
     */
    public static final MetallurgyRecipes metallurgy() {
        return metallurgyBase;
    }

    private MetallurgyRecipes() {}

    private final ArrayList<Pair<ItemStack, Set<OreStack>>> recipeList = new ArrayList();

    /**
     * Creates a metallurgy recipe.
     * @param result the alloy result
     * @param ingredients the ingredients for the recipe (should not contain redundant OreStacks)
     */
    public void addMetallurgy(ItemStack result, Set<OreStack> ingredients) {
    	recipeList.add(new Pair(result, ingredients));
    }

    /**
     * Gets the recipe (result - ingredient set pair) of alloy smelting the given ingredients.
     * @param ingrediants the ingredients available for use (MUST not contain redundant OreStacks)
     * @return the recipe
     */
    public Pair<ItemStack, Set<OreStack>> getMetallurgyRecipe(Iterable<OreStack> ingredients) {
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
    	return recipeMatch;
    }

    /**
     * Gets the recipe list.
     * @return the recipe list
     */
    public ArrayList<Pair<ItemStack, Set<OreStack>>> getRecipeList() {
    	return recipeList;
    }

    /**
     * A utility function that computes the leftover ingredients after smelting a single instance of the given alloy recipe.
     * @param recipe the recipe to calculate against
     * @param ingredients the ingredients
     * @return a list of the leftover ItemStacks, will be returned in the order that the ingredients were iterated.
     */
    public static List<ItemStack> getLeftovers(Pair<ItemStack, Set<OreStack>> recipe, Iterable<ItemStack> ingredients) {
    	Map<String, Integer> oreMap = new HashMap(recipe.getRight().size());
    	for (OreStack stack : recipe.getRight()) {
    		oreMap.put(stack.getMaterial(), stack.getAmount());
    	}
    	ArrayList<ItemStack> out = new ArrayList();
    	for (ItemStack stack : ingredients) {
    		ItemStack outStack = stack.copy();
    		int id = OreDictionary.getOreID(outStack);
    		if (!(id == -1)) {
    			String name = OreDictionary.getOreName(id);
    			if (oreMap.containsKey(name)) {
    				if (oreMap.get(name) < outStack.stackSize) {
    					outStack.stackSize -= oreMap.get(name);
    					oreMap.remove(name);
    					out.add(outStack);
    				} else {
    					oreMap.put(name, oreMap.get(name) - outStack.stackSize);
    				}
    			} else {
    				out.add(outStack);
    			}
    		} else {
    			out.add(outStack);
    		}
    	}
    	return out;
    }
}