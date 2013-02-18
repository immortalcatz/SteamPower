package dimitriye98.steamcraft.item;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dimitriye98.steamcraft.common.SteamCraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMusketEmpty extends Item
{
    /** Number of ticks to run while 'EnumAction'ing until result. */
    public int itemUseDuration;
    boolean done;

    public ItemMusketEmpty(int par1)
    {
        super(par1);
        this.itemUseDuration = 64;
        this.maxStackSize = 1;
        done = false;
        //this.setCreativeTab(CreativeTabs.tabFood);
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        //par1ItemStack = new ItemStack(BoilerMod.musket);
        if (!done)
        {
            par2World.playSoundAtEntity(par3EntityPlayer, "random.click", 0.5F, par2World.rand.nextFloat() * 0.1F + 0.9F);
        }

        done = true;
        return par1ItemStack;
    }
    
    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        if (done)
        {
            done = false;
            //par3EntityPlayer.inventoryContainer.putStackInSlot(par3EntityPlayer.inventory.currentItem + 36, new ItemStack(BoilerMod.musket, 1));
           //par3EntityPlayer.inventoryContainer.detectAndSendChanges();
           par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, new ItemStack(SteamCraft.musket, 1));
       
        }
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 64;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.block;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        return par1ItemStack;
    }

    public String getTextureFile()
    {
        return "/boiler/items.png";
    }
}
