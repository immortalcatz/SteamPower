package buildcraft.api.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeDirection;

public interface ISpecialInventory extends IInventory
{
    /**
     * Offers an ItemStack for addition to the inventory.
     *
     * @param stack
     *            ItemStack offered for addition. Do not manipulate this!
     * @param doAdd
     *            If false no actual addition should take place.
     * @param from
     *            Orientation the ItemStack is offered from.
     * @return Amount of items used from the passed stack.
     */
    int addItem(ItemStack stack, boolean doAdd, ForgeDirection from);

    /**
     * Requests items to be extracted from the inventory
     *
     * @param doRemove
     *            If false no actual extraction may occur.
     * @param from
     *            Orientation the ItemStack is requested from.
     * @param maxItemCount
     *            Maximum amount of items to extract (spread over all returned item stacks)
     * @return Array of item stacks extracted from the inventory
     */
    ItemStack[] extractItem(boolean doRemove, ForgeDirection from, int maxItemCount);
}
