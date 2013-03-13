package steamcraft.steamcraft.research;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;
import steamcraft.steamcraft.SteamCraft;
import net.minecraft.inventory.IInventory;

public class InventoryResearchBook implements IInventory{
	private final ItemStack[] researchStacks = new ItemStack[54];
	private ItemStack parentBook;
	public int selected;

    public InventoryResearchBook(ItemStack itemStack) {
		parentBook = itemStack;
	}

    public InventoryResearchBook() {
	}

	@Override
    public int getSizeInventory()
    {
        return this.researchStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.researchStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.researchStacks[par1] != null)
        {
            ItemStack var3;

            if (this.researchStacks[par1].stackSize <= par2)
            {
                var3 = this.researchStacks[par1];
                this.researchStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.researchStacks[par1].splitStack(par2);

                if (this.researchStacks[par1].stackSize == 0)
                {
                    this.researchStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int par1)
    {
    	System.out.println("closed?");
        if (this.researchStacks[par1] != null)
        {
            ItemStack var2 = this.researchStacks[par1];
            this.researchStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.researchStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return "container.ResearchTable";
    }


    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }



    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return true;
    }


    @Override
	public void openChest() {}

    @Override
	public void closeChest() {
    	System.out.println("closed");
    }

	@Override
	public void onInventoryChanged() {
		// TODO Auto-generated method stub

	}
}
