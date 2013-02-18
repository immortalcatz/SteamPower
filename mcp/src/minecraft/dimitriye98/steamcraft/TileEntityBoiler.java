package dimitriye98.steamcraft;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityBoiler extends TileEntity implements IInventory, ISidedInventory
{
    private ItemStack[] furnaceItemStacks = new ItemStack[3];

    public int furnaceBurnTime = 0;
    
    public int waterLoaded = 0;
    
    public int steam = 0;
    
    public int maxSteam = 250;

    public int currentItemBurnTime = 0;

    public int furnaceCookTime = 0;
    
    public Player player;

    public int getSizeInventory()
    {
        return this.furnaceItemStacks.length;
    }

    public ItemStack getStackInSlot(int par1)
    {
        return this.furnaceItemStacks[par1];
    }

    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.furnaceItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.furnaceItemStacks[par1].stackSize <= par2)
            {
                var3 = this.furnaceItemStacks[par1];
                this.furnaceItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.furnaceItemStacks[par1].splitStack(par2);

                if (this.furnaceItemStacks[par1].stackSize == 0)
                {
                    this.furnaceItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.furnaceItemStacks[par1] != null)
        {
            ItemStack var2 = this.furnaceItemStacks[par1];
            this.furnaceItemStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.furnaceItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    public String getInvName()
    {
        return "container.boiler";
    }

    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.furnaceItemStacks.length)
            {
                this.furnaceItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.furnaceBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.waterLoaded = par1NBTTagCompound.getShort("Water");
        this.steam = par1NBTTagCompound.getShort("Steam");
        this.furnaceCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
    }

    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
        par1NBTTagCompound.setShort("Water", (short)this.waterLoaded);
        par1NBTTagCompound.setShort("Steam", (short)this.steam);
        par1NBTTagCompound.setShort("CookTime", (short)this.furnaceCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.furnaceItemStacks.length; ++var3)
        {
            if (this.furnaceItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.furnaceItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    public int getCookProgressScaled(int par1)
    {
        return this.furnaceCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
    }
    
    @SideOnly(Side.CLIENT)

    public int getWater()
    {
        return (int) (this.waterLoaded / 0.93333333333);
    }
    
    public int getSteam()
    {
        return (int) (this.steam / 8.33333333333);
    }

    public boolean isBurning()
    {
        return this.furnaceBurnTime > 0;
    }
    
	@Override
	public Packet getDescriptionPacket()
    {
    	NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, compound);
    }
	
	@Override
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt)
	{
		NBTTagCompound compundTag = pkt.customParam1;
		this.waterLoaded = compundTag.getShort("Water");
		this.steam = compundTag.getShort("Steam");
				
	}
	
	public void packetMe(EntityPlayer par5EntityPlayer)
	{
		PacketDispatcher.sendPacketToPlayer(this.getDescriptionPacket(), (Player)par5EntityPlayer);
	}

    public void updateEntity()
    {
        boolean var1 = this.furnaceBurnTime > 0;
        boolean var2 = false;

        if (this.furnaceBurnTime > 0)
        {
            --this.furnaceBurnTime;
        }
        
    	if (!(this.furnaceItemStacks[2] == null)) {
    	if ((this.furnaceItemStacks[2].itemID == Item.bucketWater.itemID) && (this.waterLoaded < 31)) {
    		this.waterLoaded = this.waterLoaded + 1;
    		System.out.println(this.waterLoaded);
    		if (!this.worldObj.isRemote)
            {
            ((WorldServer)worldObj).getPlayerManager().getOrCreateChunkWatcher(this.xCoord>>4, this.zCoord>>4, false).sendToAllPlayersWatchingChunk(this.getDescriptionPacket());
            }
    		this.furnaceItemStacks[2] = new ItemStack(Item.bucketEmpty);
    		
    		
    	} 
    	}
    	
    	 if (this.isBurning() && this.waterLoaded > 0)
         {
             ++this.furnaceCookTime;

             if (this.furnaceCookTime == 200)
             {
                 this.furnaceCookTime = 0;
                 --this.waterLoaded;
                 this.steam = this.steam + 10;
                 System.out.println(this.waterLoaded);
                 if (!this.worldObj.isRemote){
                 ((WorldServer)worldObj).getPlayerManager().getOrCreateChunkWatcher(this.xCoord>>4, this.zCoord>>4, false).sendToAllPlayersWatchingChunk(this.getDescriptionPacket());
                 }
                 var2 = true;
             }
         }
         else
         {
             this.furnaceCookTime = 0;
         }
    	 
        if (!this.worldObj.isRemote)
        {
        	

        	
            if (this.furnaceBurnTime == 0 && this.waterLoaded > 0 && this.steam < this.maxSteam)
            {
                this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                if (this.furnaceBurnTime > 0)
                {
                    var2 = true;

                    if (this.furnaceItemStacks[1] != null)
                    {
                        --this.furnaceItemStacks[1].stackSize;

                        if (this.furnaceItemStacks[1].stackSize == 0)
                        {
                            this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItemStack(furnaceItemStacks[1]);
                        }
                    }
                }
            }

           

            if (var1 != this.furnaceBurnTime > 0)
            {
                var2 = true;
                BlockBoiler.updateFurnaceBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

  

    

    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
        {
            return 0;
        }
        else
        {
            int var1 = par0ItemStack.getItem().itemID;
            Item var2 = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
                {
                    return 150;
                }

                if (var3.blockMaterial == Material.wood)
                {
                    return 300;
                }
            }

            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
            {
                return 200;
            }

            if (var2 instanceof ItemSword && ((ItemSword) var2).func_77825_f().equals("WOOD"))
            {
                return 200;
            }

            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD"))
            {
                return 200;
            }

            if (var1 == Item.stick.itemID)
            {
                return 100;
            }

            if (var1 == Item.coal.itemID)
            {
                return 1600;
            }

            if (var1 == Item.bucketLava.itemID)
            {
                return 20000;
            }

            if (var1 == Block.sapling.blockID)
            {
                return 100;
            }

            if (var1 == Item.blazeRod.itemID)
            {
                return 2400;
            }

            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest() {}

    public void closeChest() {}

    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN)
        {
            return 1;
        }

        if (side == ForgeDirection.UP)
        {
            return 0;
        }

        return 2;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
        return 1;
    }
}