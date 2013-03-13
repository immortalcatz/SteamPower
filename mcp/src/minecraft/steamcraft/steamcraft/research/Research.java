package steamcraft.steamcraft.research;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.HashMultimap;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Research {
    /**
     * String token for research (unique)
     */
    public String token;

    /**
     * The display name of the research
     */
    public String name;

    /**
     * List of usually 2-5 items needed for this research
     */
    public List<ItemStack> researchItems = new ArrayList<ItemStack>();

    /**
     * Information about this research.
     */
	public List<String> description = new ArrayList<String>();

    /**
     * Recipes in the description
     */
	public List<ResearchRecipe> descRecipes = new ArrayList<ResearchRecipe>();

    /**
     * Stuff that must be researched before this research
     */
    public Research[] prerequisites = null;

    /**
     * ItemStack used for this research's icon
     */
    public ItemStack icon;

    //main
    public Research(String par1, String par2, Research[] par3, ItemStack par4, Object... par5)
    {
    	List par6 = new ArrayList<ItemStack>();
    	for (Object obj:par5) {
    		if (obj instanceof Block) {
    			par6.add(new ItemStack((Block) obj, 1));
    		}
    		else if (obj instanceof Item)
    		{
    			par6.add(new ItemStack((Item) obj, 1));
        	}
    		else
    		{
            	par6.add(obj);
    		}
    	}
    	this.token = par1;
    	this.name = par2;
    	this.researchItems = par6;
    	this.prerequisites = par3;
    	this.icon = par4;
    }

    //register
    public Research registerResearch(){
    	ResearchDictionary.researchList.put(this.token, this);
    	return this;
    }

    //add description page
    public Research addDescriptionPage(String desc){
    	this.description.add(desc);
    	return this;
    }

    //add recipe
    public Research addRecipe(ResearchRecipe rec) {
    	this.descRecipes.add(rec);
    	return this;
    }


    public Boolean doesContain(List<ItemStack> items, ItemStack stack) {
    	Research[] pre = this.prerequisites;
    	if (pre != null) {
    	mainLoop1: for (Research currResearch:pre) {
    		for (int i = 0; i < stack.stackTagCompound.getTags().size() - 1; i ++) {
    			if(stack.stackTagCompound.getCompoundTag(Integer.toString(i)).getString("Research").equals(currResearch.token)) {
    				continue mainLoop1;
    			}
    			System.out.println(stack.stackTagCompound.getCompoundTag(Integer.toString(i)).getString("Research"));
    			System.out.println(currResearch.token);
    			System.out.println(stack.stackTagCompound.getCompoundTag(Integer.toString(i)).getString("Research").equals(currResearch.token));
    		}
    		return false;
    	}
    	}
    	List<ItemStack> stuff = this.researchItems;
    	mainLoop2: for (ItemStack item:items) {
        	for (ItemStack researchItem:stuff) {
        		if(item.isItemEqual(researchItem)) {
        			//remainingItems.remove(researchItem);
        			continue mainLoop2;
        		}
        	}
        	return false;
    	}
    	return true;
    }

	public boolean doesContainAll(List<ItemStack> items, ItemStack stack) {
    	Research[] pre = this.prerequisites;
    	if (pre != null) {
    	mainLoop1: for (Research currResearch:pre) {
    		for (int i = 0; i < stack.stackTagCompound.getTags().size() - 1; i ++) {
    			if(stack.stackTagCompound.getCompoundTag(Integer.toString(i)).getString("Research").equals(currResearch.token)) {
    				continue mainLoop1;
    			}
    		}
    		return false;
    	}
    	}
		List<ItemStack> remainingItems = this.researchItems;
    	mainLoop: for (ItemStack researchItem:remainingItems) {
        	for (ItemStack item:items) {
        		if(item.isItemEqual(researchItem)) {
        			continue mainLoop;

        		}
        	}
        	return false;
    	}
    	return true;

    	}
}
