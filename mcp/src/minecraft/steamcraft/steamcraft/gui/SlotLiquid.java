package steamcraft.steamcraft.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;

public class SlotLiquid extends Slot
{
    private LiquidStack liquid;

    public SlotLiquid(IInventory inventory, int slotIndex, int displayX, int displayY, LiquidStack liquid) {
        super(inventory, slotIndex, displayX, displayY);
        this.liquid = liquid;
    }

    public SlotLiquid(IInventory inventory, int slotIndex, int displayX, int displayY) {
        super(inventory, slotIndex, displayX, displayY);
    }

    @Override
    public boolean isItemValid(ItemStack container) {
    	if ((liquid == null) && (LiquidContainerRegistry.isContainer(container))) return true;
    	if (LiquidContainerRegistry.containsLiquid(container, liquid)) return true;
        return false;
    }
}