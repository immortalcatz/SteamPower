package steamcraft.steamcraft.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

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
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import net.minecraftforge.oredict.OreDictionary;

import steamcraft.steamcraft.block.BlockForgeMain;
import steamcraft.steamcraft.recipes.MetallurgyRecipes;
import steamcraft.steamcraft.util.OreStack;
import steamcraft.steamcraft.util.Pair;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityForge extends TileEntity implements IInventory, ISidedInventory {

	//Constants

	public static final int SLOT_INGREDIENT_ONE = 0;

	public static final int SLOT_INGREDIENT_TWO = 1;

	public static final int SLOT_RESULT = 2;

	public static final int SLOT_FUEL = 3;

	//End Constants

    private ItemStack[] forgeItemStacks = new ItemStack[4];

    public int forgeBurnTime = 0;

    public int currentItemBurnTime = 0;

    public int forgeCookTime = 0;

    @Override
	public int getSizeInventory()
    {
        return this.forgeItemStacks.length;
    }

    @Override
	public ItemStack getStackInSlot(int par1)
    {
        return this.forgeItemStacks[par1];
    }

    @Override
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.forgeItemStacks[par1] != null)
        {
            ItemStack var3;

            if (this.forgeItemStacks[par1].stackSize <= par2)
            {
                var3 = this.forgeItemStacks[par1];
                this.forgeItemStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.forgeItemStacks[par1].splitStack(par2);

                if (this.forgeItemStacks[par1].stackSize == 0)
                {
                    this.forgeItemStacks[par1] = null;
                }

                return var3;
            }
        } else return null;
    }

    @Override
	public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.forgeItemStacks[par1] != null)
        {
            ItemStack var2 = this.forgeItemStacks[par1];
            this.forgeItemStacks[par1] = null;
            return var2;
        } else
			return null;
    }

    @Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.forgeItemStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
	public String getInvName()
    {
        return "container.Forge";
    }

    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.forgeItemStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.forgeItemStacks.length)
            {
                this.forgeItemStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }

        this.forgeBurnTime = par1NBTTagCompound.getShort("BurnTime");
        this.forgeCookTime = par1NBTTagCompound.getShort("CookTime");
        this.currentItemBurnTime = par1NBTTagCompound.getShort("ItemBurnTime");
    }

    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setShort("BurnTime", (short)this.forgeBurnTime);
        par1NBTTagCompound.setShort("ItemBurnTime", (short)this.currentItemBurnTime);
        par1NBTTagCompound.setShort("CookTime", (short)this.forgeCookTime);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.forgeItemStacks.length; ++var3)
        {
            if (this.forgeItemStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.forgeItemStacks[var3].writeToNBT(var4);
                var2.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", var2);
    }

    @Override
	public int getInventoryStackLimit()
    {
        return 64;
    }

    @SideOnly(Side.CLIENT)

    public int getCookProgressScaled(int par1)
    {
        return this.forgeCookTime * par1 / 200;
    }

    @SideOnly(Side.CLIENT)

    public int getBurnTimeRemainingScaled(int par1)
    {
        if (this.currentItemBurnTime == 0)
        {
            this.currentItemBurnTime = 200;
        }

        return this.forgeBurnTime * par1 / this.currentItemBurnTime;
    }

    public boolean isBurning()
    {
        return this.forgeBurnTime > 0;
    }

    @Override
	public void updateEntity()
    {
        boolean var1 = this.forgeBurnTime > 0;
        boolean var2 = false;

        if (this.forgeBurnTime > 0)
        {
            --this.forgeBurnTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.forgeBurnTime == 0 && this.canSmelt())
            {
                this.currentItemBurnTime = this.forgeBurnTime = getItemBurnTime(this.forgeItemStacks[SLOT_FUEL]);

                if (this.forgeBurnTime > 0)
                {
                    var2 = true;

                    if (this.forgeItemStacks[SLOT_FUEL] != null)
                    {
                        --this.forgeItemStacks[SLOT_FUEL].stackSize;

                        if (this.forgeItemStacks[SLOT_FUEL].stackSize == 0)
                        {
                            this.forgeItemStacks[SLOT_FUEL] = this.forgeItemStacks[SLOT_FUEL].getItem().getContainerItemStack(forgeItemStacks[SLOT_FUEL]);
                        }
                    }
                }
            }

            if (this.isBurning() && this.canSmelt())
            {
                ++this.forgeCookTime;

                if (this.forgeCookTime == 200)
                {
                    this.forgeCookTime = 0;
                    this.smeltItem();
                    var2 = true;
                }
            }
            else
            {
                this.forgeCookTime = 0;
            }

            if (var1 != this.forgeBurnTime > 0)
            {
                var2 = true;
                BlockForgeMain.updateFurnaceBlockState(this.forgeBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (var2)
        {
            this.onInventoryChanged();
        }
    }

    private OreStack[] ingredientOres() {
    	int oreIDOne = OreDictionary.getOreID(forgeItemStacks[SLOT_INGREDIENT_ONE]);
    	int oreIDTwo = OreDictionary.getOreID(forgeItemStacks[SLOT_INGREDIENT_TWO]);
    	if (oreIDOne != -1 && oreIDTwo != -1) {
    		OreStack[] out = new OreStack[2];
    		out[SLOT_INGREDIENT_ONE] = new OreStack(OreDictionary.getOreName(oreIDOne), forgeItemStacks[SLOT_INGREDIENT_ONE].stackSize);
    		out[SLOT_INGREDIENT_TWO] = new OreStack(OreDictionary.getOreName(oreIDTwo), forgeItemStacks[SLOT_INGREDIENT_TWO].stackSize);
    		return out;
    	}
    	return null;
    }

    private boolean canSmelt()
    {
    	if (this.forgeItemStacks[SLOT_INGREDIENT_ONE] == null && this.forgeItemStacks[SLOT_INGREDIENT_TWO] == null) return false;
    	Set<OreStack> ores = OreStack.compressCollection(Arrays.asList(ingredientOres()));
    	if (MetallurgyRecipes.metallurgy().getMetallurgyRecipe(ores) != null) return true;
    	if (FurnaceRecipes.smelting().getSmeltingResult(this.forgeItemStacks[SLOT_INGREDIENT_ONE]) != null) return true;
    	if (FurnaceRecipes.smelting().getSmeltingResult(this.forgeItemStacks[SLOT_INGREDIENT_TWO]) != null) return true;
        return false;
    }

    public void smeltItem()
    {
        if (this.canSmelt())
        {
        	ItemStack res;
        	Pair<ItemStack, Set<OreStack>> recipe = MetallurgyRecipes.metallurgy().getMetallurgyRecipe(OreStack.compressCollection(Arrays.asList(ingredientOres())));
        	if (recipe != null) {
        		res = recipe.getLeft();
            	if (this.forgeItemStacks[SLOT_RESULT] == null) {
            		this.forgeItemStacks[SLOT_RESULT] = res.copy();
            	} else if (this.forgeItemStacks[SLOT_RESULT].isItemEqual(res)) {
            		forgeItemStacks[SLOT_RESULT].stackSize += res.stackSize;
            	}
            	this.forgeItemStacks = (ItemStack[]) MetallurgyRecipes.getLeftovers(recipe, Arrays.asList(this.forgeItemStacks)).toArray();
        	} else {
	            int slotID = 0;
	            res = FurnaceRecipes.smelting().getSmeltingResult(this.forgeItemStacks[SLOT_INGREDIENT_ONE]);
	            if (res == null) {
	            	res = FurnaceRecipes.smelting().getSmeltingResult(this.forgeItemStacks[SLOT_INGREDIENT_TWO]);
	            	slotID = 1;
	            }

	            if (res != null) {
	            	if (this.forgeItemStacks[SLOT_RESULT] == null) {
	            		this.forgeItemStacks[SLOT_RESULT] = res.copy();
	            	} else if (this.forgeItemStacks[SLOT_RESULT].isItemEqual(res)) {
	            		forgeItemStacks[SLOT_RESULT].stackSize += res.stackSize;
	            	}
	            	this.forgeItemStacks[slotID].stackSize -= 1;
	            }
        	}
        }
    }

    public static int getItemBurnTime(ItemStack par0ItemStack)
    {
        if (par0ItemStack == null)
			return 0;
		else
        {
            int var1 = par0ItemStack.getItem().itemID;
            Item var2 = par0ItemStack.getItem();

            if (par0ItemStack.getItem() instanceof ItemBlock && Block.blocksList[var1] != null)
            {
                Block var3 = Block.blocksList[var1];

                if (var3 == Block.woodSingleSlab)
					return 225;

                if (var3.blockMaterial == Material.wood)
					return 450;
            }

            if (var2 instanceof ItemTool && ((ItemTool) var2).getToolMaterialName().equals("WOOD"))
				return 300;

            if (var2 instanceof ItemSword && ((ItemSword) var2).getToolMaterialName().equals("WOOD"))
				return 300;

            if (var2 instanceof ItemHoe && ((ItemHoe) var2).func_77842_f().equals("WOOD"))
				return 300;

            if (var1 == Item.stick.itemID)
				return 150;

            if (var1 == Item.coal.itemID)
				return 2400;

            if (var1 == Item.bucketLava.itemID)
				return 30000;

            if (var1 == Block.sapling.blockID)
				return 150;

            if (var1 == Item.blazeRod.itemID)
				return 3600;

            return (int)(GameRegistry.getFuelValue(par0ItemStack) * 1.5);
        }
    }

    public static boolean isItemFuel(ItemStack par0ItemStack)
    {
        return getItemBurnTime(par0ItemStack) > 0;
    }

    @Override
	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
	public void openChest() {}

    @Override
	public void closeChest() {}

    @Override
    public int getStartInventorySide(ForgeDirection side)
    {
        if (side == ForgeDirection.DOWN)
			return 2;

        if (side == ForgeDirection.UP)
			return 0;

        return 3;
    }

    @Override
    public int getSizeInventorySide(ForgeDirection side)
    {
    	if (side == ForgeDirection.UP) return 2;
        return 1;
    }
}