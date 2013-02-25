package steamcraft.steamcraft.tileentity;

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
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.ISidedInventory;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.block.BlockResearchTable;
import steamcraft.steamcraft.item.ItemResearchNotes;

public class TileEntityResearchTable extends TileEntity implements IInventory, ISidedInventory
{
    private ItemStack[] researchStacks = new ItemStack[3];

    @Override
    public int getSizeInventory()
    {
        return this.researchStacks.length;
    }

    @Override
    public ItemStack getStackInSlot(int par1)
    {
        return this.researchStacks[par1];
    }

    @Override
    public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.researchStacks[par1] != null)
        {
            ItemStack var3;

            if (this.researchStacks[par1].stackSize <= par2)
            {
                var3 = this.researchStacks[par1];
                this.researchStacks[par1] = null;
                return var3;
            }
            else
            {
                var3 = this.researchStacks[par1].splitStack(par2);

                if (this.researchStacks[par1].stackSize == 0)
                {
                    this.researchStacks[par1] = null;
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
    public ItemStack getStackInSlotOnClosing(int par1)
    {
        if (this.researchStacks[par1] != null)
        {
            ItemStack var2 = this.researchStacks[par1];
            this.researchStacks[par1] = null;
            return var2;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
    {
        this.researchStacks[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public String getInvName()
    {
        return "container.ResearchTable";
    }

    @Override
    public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList var2 = par1NBTTagCompound.getTagList("Items");
        this.researchStacks = new ItemStack[this.getSizeInventory()];

        for (int var3 = 0; var3 < var2.tagCount(); ++var3)
        {
            NBTTagCompound var4 = (NBTTagCompound)var2.tagAt(var3);
            byte var5 = var4.getByte("Slot");

            if (var5 >= 0 && var5 < this.researchStacks.length)
            {
                this.researchStacks[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList var2 = new NBTTagList();

        for (int var3 = 0; var3 < this.researchStacks.length; ++var3)
        {
            if (this.researchStacks[var3] != null)
            {
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)var3);
                this.researchStacks[var3].writeToNBT(var4);
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

    @Override
    public void updateEntity()
    {
        //entity update, for later use?
    }

    public void research()
    {
        if (!this.worldObj.isRemote)
        {
        	ItemStack stack = new ItemStack(SteamCraft.researchPaper, 1);
        	boolean newResearch = false;
        	if (this.researchStacks[1] != null && this.researchStacks[1].itemID == SteamCraft.researchPaper.itemID) {
        		stack = this.researchStacks[1];
        		newResearch = true;
        	}
            if ((this.researchStacks[2] != null || newResearch) && this.researchStacks[0] != null)
            {
                if ((newResearch || this.researchStacks[2].itemID == Item.paper.itemID)  && (this.researchStacks[1] == null || this.researchStacks[1].stackSize <= 0 || this.researchStacks[1].itemID == SteamCraft.researchPaper.itemID ))
                {
                	if (!newResearch) {
		            if (this.researchStacks[2].stackSize <= 1)
		            {
		            this.researchStacks[2] = null;
		            }
		            else
		            {
		            this.researchStacks[2].stackSize--;
		            }
                	}

                    if (!stack.hasTagCompound())
                      {
              			stack.setTagCompound(new NBTTagCompound());
              			NBTTagList items = new NBTTagList();
              			stack.stackTagCompound.setTag("Contents", items);

                     }
                    NBTTagCompound addition = new NBTTagCompound();
                    ItemStack addStack = new ItemStack(this.researchStacks[0].getItem(), 1);
                    addStack.writeToNBT(addition);
                    if (this.researchStacks[0].stackSize <= 1)
                    {
                    	this.researchStacks[0] = null;
                    }
                    else
                    {
                        this.researchStacks[0].stackSize--;
                    }

            		stack.stackTagCompound.getTagList("Contents").appendTag(addition);

                    this.researchStacks[1] = stack;
                }
            }
        }
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
    {
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : par1EntityPlayer.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
    }

    public int getMetadata()
    {
        return this.worldObj.getBlockMetadata(this.xCoord, this.yCoord, this.zCoord);
    }

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

    @Override
    public void openChest() {}

    @Override
    public void closeChest() {}
}