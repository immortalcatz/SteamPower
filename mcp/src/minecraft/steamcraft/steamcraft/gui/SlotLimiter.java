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
    boolean craftSlot;
    EntityPlayer thePlayer = null;

    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack[] stacks)
    {
    	this(par2IInventory, par3, par4, par5, stacks, false, null);
    }
    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack stack)
    {
        this(par2IInventory, par3, par4, par5, new ItemStack[] {stack}, false, null);
    }

    public SlotLimiter(TileEntityResearchTable par2TileEntityResearchTable, int i, int j, int k, Item item)
    {
        this(par2TileEntityResearchTable, i, j, k, new ItemStack(item), false, null);
    }


    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack[] stacks, boolean craft, EntityPlayer player)
    {
        super(par2IInventory, par3, par4, par5);
        this.stacks = stacks;
        this.craftSlot = craft;
        this.thePlayer = player;
    }
    public SlotLimiter(IInventory par2IInventory, int par3, int par4, int par5, ItemStack stack, boolean craft, EntityPlayer player)
    {
        this(par2IInventory, par3, par4, par5, new ItemStack[] {stack}, craft, player);
    }
    public SlotLimiter(TileEntityResearchTable par2TileEntityResearchTable, int i, int j, int k, Item item, boolean craft, EntityPlayer player)
    {
        this(par2TileEntityResearchTable, i, j, k, new ItemStack(item), craft, player);
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

    @Override
	public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
    	if (craftSlot) {
        this.onCrafting(par2ItemStack);
        super.onPickupFromSlot(par1EntityPlayer, par2ItemStack);
    	}
    }

    @Override
	protected void onCrafting(ItemStack par1ItemStack)
    {
        par1ItemStack.onCrafting(this.thePlayer.worldObj, this.thePlayer, 1);
    }
}