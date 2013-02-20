package dimitriye98.steamcraft.gui;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.tileentity.TileEntityFurnace;

import dimitriye98.steamcraft.tileentity.TileEntityBoiler;

public class ContainerBoiler extends Container
{
	private TileEntityBoiler boiler;
    private int lastBurnTime = 0;
    private int lastItemBurnTime = 0;
	
    public ContainerBoiler(InventoryPlayer playerInventory, TileEntityBoiler boilerEntity)
    {
        this.boiler = boilerEntity;
        this.addSlotToContainer(new Slot(boilerEntity, 0, 34, 41));
        this.addSlotToContainer(new Slot(boilerEntity, 1, 100, 41));

        for (int y = 0; y < 3; y++)
        {
            for (int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
            }
        }

        for (int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerInventory, x, 8 + x * 18, 142));
        }
    }
    
    @Override
    public void addCraftingToCrafters(ICrafting crafter)
    {
        super.addCraftingToCrafters(crafter);
        crafter.sendProgressBarUpdate(this, 0, this.boiler.boilerBurnTime);
        crafter.sendProgressBarUpdate(this, 1, this.boiler.currentItemBurnTime);
    }
    
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int index = 0; index < this.crafters.size(); ++index)
        {
            ICrafting currentCrafter = (ICrafting)this.crafters.get(index);

            if (this.lastBurnTime != this.boiler.boilerBurnTime)
            {
                currentCrafter.sendProgressBarUpdate(this, 0, this.boiler.boilerBurnTime);
            }

            if (this.lastItemBurnTime != this.boiler.currentItemBurnTime)
            {
                currentCrafter.sendProgressBarUpdate(this, 1, this.boiler.currentItemBurnTime);
            }
        }

        this.lastBurnTime = this.boiler.boilerBurnTime;
        this.lastItemBurnTime = this.boiler.currentItemBurnTime;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int key, int value)
    {
        if (key == 1)
        {
            this.boiler.boilerBurnTime = value;
        }

        if (key == 2)
        {
            this.boiler.currentItemBurnTime = value;
        }
    }
    
	@Override
	public boolean canInteractWith(EntityPlayer yoursTruly) {
        return this.boiler.isUseableByPlayer(yoursTruly);
	}
	
	@Override
    public ItemStack transferStackInSlot(EntityPlayer yoursTruly, int slotIndex)
    {
        ItemStack itemOutput = null;
        Slot slot = (Slot)this.inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack())
        {
            ItemStack item = slot.getStack();
            itemOutput = item.copy();

            if (slotIndex != 0 && slotIndex != 1)
            {
                if (TileEntityBoiler.isItemWaterContainer(item))
                {
                    if (!this.mergeItemStack(item, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityBoiler.isItemFuel(item))
                {
                    if (!this.mergeItemStack(item, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 2 && slotIndex < 29)
                {
                    if (!this.mergeItemStack(item, 29, 38, false))
                    {
                        return null;
                    }
                }
                else if (slotIndex >= 29 && slotIndex < 38 && !this.mergeItemStack(item, 2, 29, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(item, 2, 38, false))
            {
                return null;
            }

            if (item.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (item.stackSize == itemOutput.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(yoursTruly, item);
        }

        return itemOutput;
    }
}