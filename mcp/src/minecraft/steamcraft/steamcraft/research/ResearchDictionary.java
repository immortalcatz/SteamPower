package steamcraft.steamcraft.research;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import thaumcraft.api.research.ResearchItem;

public class ResearchDictionary {
    public static Map<String, Research> researchList = new HashMap<String, Research>();

    /**
     * Gets the name of the research corresponding to the input token.
     */
    public static String getNameFromToken(String par1) {
    	if (researchList.containsKey(par1)){
    		return researchList.get(par1).name;
    	}
    	else
    	{
    		return "Incomplete";
    	}
    }

    /**
     * Gets the description of the research corresponding to the input token.
     */
    public static String getDescFromToken(String par1, int par2) {
    	if (researchList.containsKey(par1)){
        	return researchList.get(par1).description.get(par2);
    	}
    	else
    	{
    		return "Incomplete";
    	}
    }


    /**
     * Gets a list of recipes to be displayed in the research GUI.
     */
    public static List<ResearchRecipe> getRecipesFromToken(String par1) {
    	if (researchList.containsKey(par1)){
        	return researchList.get(par1).descRecipes;
    	}
    	else
    	{
    		return null;
    	}
    }



    /**
     * Gets the research corresponding to a list of items. Returns the first one it finds, make sure there is only one left.
     */
    public static String getResearchFromItems(List<ItemStack> items) {
    	for (Research research:researchList.values()) {
    		if (research.doesContain(items)) {
    			return research.token;
    		}
    	}
    	return null;
    }

    /**
     * Returns a boolean that tells if the current research is complete
     */
    public static Boolean isResearchComplete(String token, List<ItemStack> items) {
    	if (researchList.get(token).doesContainAll(items)) {
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }

    /**
     * Gets the number of researches applying to a list of items. 0 is a failed research
     */
    public static Integer getNumResearchFromItems(List<ItemStack> items) {
    	int i = 0;
    	for (Research research:researchList.values()) {
    		boolean contains = research.doesContain(items);
    		if (contains) {
    			i++;
    		}
    	}
    	return i;
    }

}
