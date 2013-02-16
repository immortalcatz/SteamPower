package boiler;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class CommonProxy
{
    public static String ITEMS_PNG = "/boiler/items.png";
    public static String BLOCK_PNG = "/boiler/blocks.png";

    // Client stuff
    public void registerRenderers()
    {
        // Nothing here as the server doesn't render graphics!
    }
    public void addRecipes()
    {

        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.musketBall, 1, 0), Block.cobblestone);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.musketCartridge, 1, 0), Item.gunpowder, Item.paper, BoilerMod.musketBall);
        GameRegistry.addSmelting(BoilerMod.copperOre.blockID, new ItemStack(BoilerMod.ingotCopper), 0.5F);
        GameRegistry.addSmelting(BoilerMod.zincOre.blockID, new ItemStack(BoilerMod.ingotZinc), 0.5F);
        GameRegistry.addRecipe(new ItemStack(BoilerMod.blockCopper, 1), "XXX", "XXX", "XXX", 'X', BoilerMod.ingotCopper);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotCopper, 9, 0), BoilerMod.blockCopper);
        GameRegistry.addRecipe(new ItemStack(BoilerMod.blockCopper, 1), "XXX", "XXX", "XXX", 'X', BoilerMod.ingotCopper);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotCopper, 9, 0), BoilerMod.blockCopper);
        GameRegistry.addRecipe(new ItemStack(BoilerMod.blockZinc, 1), "XXX", "XXX", "XXX", 'X', BoilerMod.ingotZinc);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotZinc, 9, 0), BoilerMod.blockZinc);
        GameRegistry.addRecipe(new ItemStack(BoilerMod.blockBrass, 1), "XXX", "XXX", "XXX", 'X', BoilerMod.ingotBrass);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotBrass, 9, 0), BoilerMod.blockBrass);
        GameRegistry.addRecipe(new ItemStack(BoilerMod.blockFurnaceExtension, 1), "XFX", "FYF", "XFX", 'X', Block.cobblestone, 'F', BoilerMod.ingotCopper, 'Y', Item.ingotIron);
    }
}