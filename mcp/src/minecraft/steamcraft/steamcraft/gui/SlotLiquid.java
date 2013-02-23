package steamcraft.steamcraft.gui;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

import cpw.mods.fml.common.registry.GameRegistry;

public class SlotLiquid extends Slot
{
    private LiquidStack liquid;

    public SlotLiquid(IInventory inventory, int slotIndex, int displayX, int displayY, LiquidStack liquid)
    {
        super(inventory, slotIndex, displayX, displayY);
        this.liquid = liquid;
    }

    @Override
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        if (LiquidContainerRegistry.containsLiquid(par1ItemStack, liquid)){
        	return true;
        }
        else
        {
        	return false;
        }
    }
}