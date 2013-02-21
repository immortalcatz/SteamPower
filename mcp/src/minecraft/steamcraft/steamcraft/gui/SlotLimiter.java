package steamcraft.steamcraft.gui;

import steamcraft.steamcraft.tileentity.TileEntityBoiler;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLimiter extends Slot {

	private int itemID;

	public SlotLimiter(IInventory inventory, int slotIndex, int displayX, int displayY, int id) {
		super(inventory, slotIndex, displayX, displayY);
		this.itemID = id;
	}

    @Override
	public boolean isItemValid(ItemStack item)
    {
        return item.itemID == this.itemID;
    }

}
