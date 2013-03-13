package steamcraft.steamcraft.research;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.gui.SlotLimiter;
import steamcraft.steamcraft.item.*;

public class ContainerResearchBook extends Container
{
    private final int numRows = 6;


    public ContainerResearchBook(IInventory par1IInventory, ItemStack item, InventoryResearchBook researchInventory)
    {
        int var3 = (this.numRows - 4) * 18;
        int var4;
        int var5;

        for (var4 = 0; var4 < this.numRows; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new SlotLimiter(researchInventory, var5 + var4 * 9, 8 + var5 * 18, 18 + var4 * 18, SteamCraft.researchPaper));
            }
        }
        for (int i = 0; i < (item.stackTagCompound.getTags().size() - 1); i ++) {
        	ItemStack newStack = new ItemStack(SteamCraft.researchPaper, 1);
        	newStack.setTagCompound(new NBTTagCompound());
  			NBTTagList items = new NBTTagList();
  			NBTTagCompound index = item.stackTagCompound.getCompoundTag(Integer.toString(i+1));
  			newStack.stackTagCompound.setTag("1", index);
  			newStack.stackTagCompound.setInteger("SelectedResearch", 1);
        	this.putStackInSlot(i, newStack);
        }
        for (var4 = 0; var4 < 3; ++var4)
        {
            for (var5 = 0; var5 < 9; ++var5)
            {
                this.addSlotToContainer(new Slot(par1IInventory, var5 + var4 * 9 + 9, 8 + var5 * 18, 103 + var4 * 18 + var3));
            }
        }

        for (var4 = 0; var4 < 9; ++var4)
        {
            this.addSlotToContainer(new Slot(par1IInventory, var4, 8 + var4 * 18, 161 + var3));
        }
    }

    @Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return true;
    }

    /**
     * Called when a player shift-clicks on a slot. You must override this or you will crash when someone does that.
     */
    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 < this.numRows * 9)
            {
                if (!this.mergeItemStack(var5, this.numRows * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(var5, 0, this.numRows * 9, false))
            {
                return null;
            }

            if (var5.stackSize == 0)
            {
                var4.putStack((ItemStack)null);
            }
            else
            {
                var4.onSlotChanged();
            }
        }

        return var3;
    }


    /**
     * Callback for when the crafting gui is closed.
     */
    @Override
	public void onCraftGuiClosed(EntityPlayer par1EntityPlayer)
    {
    	ItemStack newStack = new ItemStack(SteamCraft.researchBook, 1);
    	newStack.setTagCompound(new NBTTagCompound());
    	int i;
    	int j = 0;
		newStack.stackTagCompound.setInteger("SelectedResearch", 1);
		ItemStack book = par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem];
		for (i = 0; i < 54; i++)
    	{
			if (this.getSlot(i).getHasStack()) {
				j=j+1;
				ItemStack currentItem = this.getSlot(i).getStack();
				NBTTagCompound index = currentItem.stackTagCompound.getCompoundTag("1");
				newStack.stackTagCompound.setTag(Integer.toString(j), index);

				int bookSelected = book.stackTagCompound.getInteger("SelectedResearch");
				if (currentItem.stackTagCompound.getCompoundTag("1").getString("Research") == book.stackTagCompound.getCompoundTag(Integer.toString(bookSelected)).getString("Research")) {
					newStack.stackTagCompound.setInteger("SelectedResearch", j);
				}
			}
    	}
		System.out.println(j);
		par1EntityPlayer.inventory.mainInventory[par1EntityPlayer.inventory.currentItem] = newStack;
        super.onCraftGuiClosed(par1EntityPlayer);
    }

}
