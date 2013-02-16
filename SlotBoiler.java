package boiler;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class SlotBoiler extends Slot
{
    private EntityPlayer thePlayer;
    private int field_75228_b;

    public SlotBoiler(EntityPlayer par1EntityPlayer, IInventory par2IInventory, int par3, int par4, int par5)
    {
        super(par2IInventory, par3, par4, par5);
        this.thePlayer = par1EntityPlayer;
    }

    public boolean isItemValid(ItemStack par1ItemStack)
    {
        if (par1ItemStack.equals(new ItemStack(Item.bucketWater))){
        	return true;
        			
        }
        else
        {
        	return false;
        }
    }

  
    
}