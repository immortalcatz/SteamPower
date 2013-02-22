package steamcraft.steamcraft.gui;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.AchievementList;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.common.registry.GameRegistry;

public class SlotLimiter extends Slot
{
    int requiredID;

    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, int itemID)
    {
        super(par2IInventory, par3, par4, par5);
        this.requiredID = itemID;
    }

    @Override
	public boolean isItemValid(ItemStack par1ItemStack)
    {
        if (par1ItemStack.itemID == this.requiredID){
        	return true;
        }
        else
        {
        	return false;
        }
    }



}