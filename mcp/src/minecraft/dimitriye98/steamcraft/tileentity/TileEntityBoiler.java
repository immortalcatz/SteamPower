package dimitriye98.steamcraft.tileentity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
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
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;
import cpw.mods.fml.common.registry.GameRegistry;

public class TileEntityBoiler extends TileEntity implements ITankContainer, IInventory, ISidedInventory {

	private LiquidTank waterTank;
	private static int maxWater = LiquidContainerRegistry.BUCKET_VOLUME * 10;
	private static LiquidStack tankType = new LiquidStack(Block.waterStill, LiquidContainerRegistry.BUCKET_VOLUME);

	private ItemStack[] boilerItemStacks = new ItemStack[2];

    public int boilerBurnTime = 0;
    public int currentItemBurnTime = 0;

	public TileEntityBoiler() {
		waterTank = new LiquidTank(maxWater);
	}

	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return waterTank.fill(resource, doFill);
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] {waterTank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return waterTank;
	}

    public boolean isBurning()
    {
        return this.boilerBurnTime > 0;
    }

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.boilerBurnTime * par1 / this.currentItemBurnTime;
    }

    public int getScaledWater(int max) {
    	return (waterTank.getLiquid() != null) ? (max*(waterTank.getLiquid().amount/waterTank.getCapacity())) : 0;
    }

    @Override
	public int getSizeInventory()
    {
        return this.boilerItemStacks.length;
    }

    @Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.boilerItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.boilerItemStacks[par1].stackSize <= par2)
            {
                var3 = this.boilerItemStacks[par1];
                this.boilerItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.boilerItemStacks[par1].splitStack(par2);

                if (this.boilerItemStacks[par1].stackSize == 0)
                {
                    this.boilerItemStacks[par1] = null;
                }

                return var3;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
	public ItemStack getStackInSlot(int par1)
    {
        return this.boilerItemStacks[par1];
    }

    @Override
    public void readFromNBT(NBTTagCompound data)
    {
        super.readFromNBT(data);
        NBTTagList inventory = data.getTagList("Items");
        this.boilerItemStacks = new ItemStack[this.getSizeInventory()];

        for (int index = 0; index < inventory.tagCount(); ++index)
        {
            NBTTagCompound item = (NBTTagCompound)inventory.tagAt(index);
            byte slot = item.getByte("Slot");

            if (slot >= 0 && slot < this.boilerItemStacks.length)
            {
                this.boilerItemStacks[slot] = ItemStack.loadItemStackFromNBT(item);
            }
        }

        this.boilerBurnTime = data.getShort("BurnTime");
        this.currentItemBurnTime = data.getShort("ItemBurnTime");
    }

    @Override
    public void writeToNBT(NBTTagCompound data)
    {
        super.writeToNBT(data);
        data.setShort("BurnTime", (short)this.boilerBurnTime);
        data.setShort("ItemBurnTime", (short)this.currentItemBurnTime);
        NBTTagList inventoryTag = new NBTTagList();

        for (int slot = 0; slot < this.boilerItemStacks.length; ++slot)
        {
            if (this.boilerItemStacks[slot] != null)
            {
                NBTTagCompound item = new NBTTagCompound();
                item.setByte("Slot", (byte)slot);
                this.boilerItemStacks[slot].writeToNBT(item);
                inventoryTag.appendTag(item);
            }
        }

        data.setTag("Items", inventoryTag);
    }

    @Override
	public void openChest() {}

    @Override
	public void closeChest() {}

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    public int getWater() {
    	return (this.waterTank.getLiquid() != null) ? (this.waterTank.getLiquid().amount) : 0;
    }

    @Override
    public void updateEntity()
    {
        boolean var1 = this.boilerBurnTime > 0;
        boolean var2 = false;

        if (this.boilerItemStacks[1] != null) {
        	LiquidStack water = LiquidContainerRegistry.getLiquidForFilledItem(this.boilerItemStacks[1]);
        	if (water != null) {
	        	if (water.isLiquidEqual(tankType)) {
	        		if (fill(ForgeDirection.UNKNOWN, water, false) == water.amount) {
	        			fill(ForgeDirection.UNKNOWN, water, true);
	        			this.boilerItemStacks[1].stackSize--;
	                    if (this.boilerItemStacks[1].stackSize == 0)
	                    {
	                        this.boilerItemStacks[1] = this.boilerItemStacks[1].getItem().getContainerItemStack(boilerItemStacks[1]);
	                    }
	        		}
	        	}
        	}
        }

        if (this.boilerBurnTime > 0)
        {
            --this.boilerBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.boilerBurnTime == 0 && this.canBoil())
            {
                this.currentItemBurnTime = this.boilerBurnTime = getItemBurnTime(this.boilerItemStacks[0]);

                if (this.boilerBurnTime > 0)
                {
                    var2 = true;

                    if (this.boilerItemStacks[0] != null)
                    {
                        --this.boilerItemStacks[0].stackSize;

                        if (this.boilerItemStacks[0].stackSize == 0)
                        {
                            this.boilerItemStacks[0] = this.boilerItemStacks[0].getItem().getContainerItemStack(boilerItemStacks[0]);
                        }
                    }
                }
            }

            if (var1 != this.boilerBurnTime > 0)
            {
                var2 = true;
                BlockFurnace.updateFurnaceBlockState(this.boilerBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (this.isBurning() && this.canBoil())
        {
            var2 = true;
            this.waterTank.getLiquid().amount--;
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private boolean canBoil()
    {
    	return (waterTank.getLiquid() != null) ? (waterTank.getLiquid().amount != 0) : false;
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

            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD")) {
				return 200;
			}
            if (var2 instanceof ItemSword && ((ItemSword) var2).getToolMaterialName().equals("WOOD")) {
				return 200;
			}
            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD")) {
				return 200;
			}
            if (var1 == Item.stick.itemID) {
				return 100;
			}
            if (var1 == Item.coal.itemID) {
				return 1600;
			}
            if (var1 == Item.bucketLava.itemID) {
				return 20000;
			}
            if (var1 == Block.sapling.blockID) {
				return 100;
			}
            if (var1 == Item.blazeRod.itemID) {
				return 2400;
			}
            return GameRegistry.getFuelValue(par0ItemStack);
        }
    }

	@Override
	public int getStartInventorySide(ForgeDirection side) {
        if (side == ForgeDirection.DOWN || side == ForgeDirection.UP) {
			return 0;
		}
        return 1;
	}

	@Override
	public int getSizeInventorySide(ForgeDirection side) {
		return 1;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		if (this.boilerItemStacks[slot] != null) {
			ItemStack temp = this.boilerItemStacks[slot];
			this.boilerItemStacks[slot] = null;
			return temp;
		} else {
			return null;
		}
	}

	@Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.boilerItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

	@Override
	public String getInvName() {
		return "container.boiler";
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

}
