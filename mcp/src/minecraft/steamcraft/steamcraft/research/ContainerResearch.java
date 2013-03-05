package steamcraft.steamcraft.research;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerResearch extends Container
{
    public InventoryCrafting craftMatrix = new InventoryCrafting(this, 3, 3);
    public ContainerResearch(InventoryPlayer par1InventoryPlayer)
    {


    }
    @Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
    {
        return true;
    }


}
