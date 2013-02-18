package dimitriye98.steamcraft.common;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy
{
    public static String ITEMS_PNG = "/steamcraft/resources/items.png";
    public static String BLOCK_PNG = "/steamcraft/resources/blocks.png";

    // Client stuff
    public void registerRenderers()
    {
        // Nothing here as the server doesn't render graphics!
    }
    public void addRecipes()
    {
    	
        
    }
    
}