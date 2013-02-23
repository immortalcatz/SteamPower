package steamcraft.steamcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import steamcraft.steamcraft.common.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

public class ContainerResearchTable extends Container
{
    private final TileEntityResearchTable entityTable;

    public ContainerResearchTable(InventoryPlayer par1InventoryPlayer, TileEntityResearchTable par2TileEntityResearchTable)
    {
        this.entityTable = par2TileEntityResearchTable;
        this.addSlotToContainer(new Slot(par2TileEntityResearchTable, 0, 32, 26));
        this.addSlotToContainer(new SlotLimiter(par2TileEntityResearchTable, 2, 74, 55, Item.paper));
        this.addSlotToContainer(new SlotLimiter(par2TileEntityResearchTable, 1, 125, 26, SteamCraft.researchNotes));
        int var3;

        for (var3 = 0; var3 < 3; ++var3)
        {
            for (int var4 = 0; var4 < 9; ++var4)
            {
                this.addSlotToContainer(new Slot(par1InventoryPlayer, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3)
        {
            this.addSlotToContainer(new Slot(par1InventoryPlayer, var3, 8 + var3 * 18, 142));
        }
    }

    @Override
	public void addCraftingToCrafters(ICrafting par1ICrafting)
    {
        super.addCraftingToCrafters(par1ICrafting);

    }



    @Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return this.entityTable.isUseableByPlayer(par1EntityPlayer);
    }

    @Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
    {
        ItemStack var3 = null;
        Slot var4 = (Slot)this.inventorySlots.get(par2);

        if (var4 != null && var4.getHasStack())
        {
            ItemStack var5 = var4.getStack();
            var3 = var5.copy();

            if (par2 == 2)
            {
                if (!this.mergeItemStack(var5, 3, 39, true))
                {
                    return null;
                }

                var4.onSlotChange(var5, var3);
            }
            else if (par2 != 2 && par2 != 0)
            {
                if (var5.itemID == Item.paper.itemID)
                {
                    if (!this.mergeItemStack(var5, 1, 2, false))
                    {
                        return null;
                    }
                }
                else
                {
                	 if (!this.mergeItemStack(var5,0 , 1, false))
                     {
                         return null;
                     }
                }

            }
            else if (!this.mergeItemStack(var5, 4, 39, false))
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

            if (var5.stackSize == var3.stackSize)
            {
                return null;
            }

            var4.onPickupFromSlot(par1EntityPlayer, var5);
        }

        return var3;
    }
}