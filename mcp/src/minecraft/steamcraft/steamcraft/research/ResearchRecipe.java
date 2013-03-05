package steamcraft.steamcraft.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ResearchRecipe {
	public String type;

	public List<ItemStack> items;

	public int position;

	public int page;

	public ResearchRecipe (String par1, int par2, int par3, Object... par5) {
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
	    	this.type = par1;
	    	this.page = par2;
	    	this.position = par3;
	    	this.items = par6;
	}
}
