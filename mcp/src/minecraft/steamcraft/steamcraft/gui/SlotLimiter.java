package steamcraft.steamcraft.gui;

import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
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
    ItemStack[] stacks;

    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack stack)
    {
        this(par2IInventory, par3, par4, par5, new ItemStack[] {stack});
    }

    public SlotLimiter(IInventory par2IInventory, int i, int j, int k, Item item)
    {
        this(par2IInventory, i, j, k, new ItemStack(item));
    }


    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack[] stacks)
    {
        super(par2IInventory, par3, par4, par5);
        this.stacks = stacks;
    }


    @Override
    public boolean isItemValid(ItemStack item)
    {
        for (ItemStack stack : stacks)
        {
            if (item.itemID == stack.itemID)
            {
                return true;
            }
        }

        return false;
    }


}