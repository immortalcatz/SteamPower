package steamcraft.steamcraft.recipes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class MetallurgyRecipes
{
    private static final MetallurgyRecipes metallurgyBase = new MetallurgyRecipes();

    public static final MetallurgyRecipes metallurgy() {
        return metallurgyBase;
    }

    private class RecipeNode {
    	public int itemID;
    	public int metadata;
    	public int amount;

    	public RecipeNode(int itemID, int metadata, int amount) {
    		this.itemID = itemID;
    		this.metadata = metadata;
    		this.amount = amount;
    	}

    	public RecipeNode() {
			super();
		}

		@Override
    	public boolean equals(Object o) {
    		if (o instanceof RecipeNode) {
    			if (((RecipeNode) o).itemID == this.itemID && ((RecipeNode) o).metadata == this.metadata && ((RecipeNode) o).amount > this.amount)
					return true;
    		}
    		return false;
    	}

    	@Override
    	public int hashCode() {
    		return (31 * metadata) ^ itemID;
    	}
    }

    private final Map<HashSet<RecipeNode>, ItemStack> recipes;

    private MetallurgyRecipes() {
    	recipes = new HashMap();
    }

    public void addAlloy(Collection<List<Integer>> ingredients, ItemStack result) throws IllegalArgumentException {
    	HashMap<List<Integer>, Integer> temp = new HashMap();
    	Iterator iter = ingredients.iterator();
    	while (iter.hasNext()) {
    		List<Integer> current = (List<Integer>)iter.next();
    		if (current.size() < 3) throw new IllegalArgumentException();
    		List<Integer> key = Arrays.asList(current.get(0), current.get(1));
    		if (!temp.containsKey(key)) {
    			temp.put(key, current.get(2));
    		} else {
    			temp.put(key, temp.get(key) + current.get(2));
    		}
    	}
    	iter = temp.keySet().iterator();
    	while (iter.hasNext()) {
    		List<Integer> current = (List<Integer>)iter.next();
    		RecipeNode node = new RecipeNode();
    		node.itemID = current.get(0);
    		node.metadata = current.get(1);
    		node.amount = temp.get(current);
    	}
    }

    public Map getAlloyList() {
    	return recipes;
    }

    public ItemStack getMetallurgyResult(ItemStack... ingredients) {
    	HashSet<RecipeNode> temp = new HashSet();
    	for (ItemStack ingredient : ingredients) {
    		boolean contains = false;
    		for (RecipeNode node : temp) {
    			if (node.itemID == ingredient.itemID && node.metadata == ingredient.getItemDamage()) {
    				node.amount += ingredient.stackSize;
    				contains = true;
    			}
    		}
    		if (!contains) {
    			temp.add(new RecipeNode(ingredient.itemID, ingredient.getItemDamage(), ingredient.stackSize));
    		}
    	}
    	return recipes.get(temp);
    }
}