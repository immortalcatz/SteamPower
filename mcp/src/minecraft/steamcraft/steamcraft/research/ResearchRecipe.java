package steamcraft.steamcraft.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ResearchRecipe {
	public int position;

	public int page;

	public String texlocation;

	public int texWidth;

	public int texHeight;

	public ResearchRecipe (int par2, int par3, String par4, int par5, int par6) {
	    	this.page = par2;
	    	this.position = par3;
	    	this.texlocation = par4;
	    	this.texWidth = par5;
	    	this.texHeight = par6;
	}
}
