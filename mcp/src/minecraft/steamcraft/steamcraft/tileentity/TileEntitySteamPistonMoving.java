package steamcraft.steamcraft.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Facing;
import steamcraft.steamcraft.SteamCraft;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntitySteamPistonMoving extends TileEntity
{
    private int storedBlockID;
    private int storedMetadata;

    /** the side the front of the piston is on */
    private int storedOrientation;

    /** if this piston is extending or not */
    private boolean extending;
    private final boolean shouldHeadBeRendered;
    private float progress;

    /** the progress in (de)extending */
    private float lastProgress;
    private final List pushedObjects = new ArrayList();


    public TileEntitySteamPistonMoving(int par1, int par2, int par3, boolean par4, boolean par5)
    {
        this.storedBlockID = par1;
        this.storedMetadata = par2;
        this.storedOrientation = par3;
        this.extending = par4;
        this.shouldHeadBeRendered = par5;
    }

    public int getStoredBlockID()
    {
        return this.storedBlockID;
    }

    /**
     * Returns block data at the location of this entity (client-only).
     */
    @Override
	public int getBlockMetadata()
    {
        return this.storedMetadata;
    }

    /**
     * Returns true if a piston is extending
     */
    public boolean isExtending()
    {
        return this.extending;
    }

    /**
     * Returns the orientation of the piston as an int
     */
    public int getPistonOrientation()
    {
        return this.storedOrientation;
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldRenderHead()
    {
        return this.shouldHeadBeRendered;
    }

    /**
     * Get interpolated progress value (between lastProgress and progress) given the fractional time between ticks as an
     * argument.
     */
    public float getProgress(float par1)
    {
        if (par1 > 1.0F)
        {
            par1 = 1.0F;
        }

        return this.lastProgress + (this.progress - this.lastProgress) * par1;
    }

    private void updatePushedObjects(float par1, float par2)
    {
        if (this.extending)
        {
            par1 = 1.0F - par1;
        }
        else
        {
            --par1;
        }

        AxisAlignedBB var3 = SteamCraft.steamPistonMoving.getAxisAlignedBB(this.worldObj, this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, par1, this.storedOrientation);

        if (var3 != null)
        {
            List var4 = this.worldObj.getEntitiesWithinAABBExcludingEntity((Entity)null, var3);

            if (!var4.isEmpty())
            {
                this.pushedObjects.addAll(var4);
                Iterator var5 = this.pushedObjects.iterator();

                while (var5.hasNext())
                {
                    Entity var6 = (Entity)var5.next();
                    var6.moveEntity(par2 * Facing.offsetsXForSide[this.storedOrientation], par2 * Facing.offsetsYForSide[this.storedOrientation], par2 * Facing.offsetsZForSide[this.storedOrientation]);
                }

                this.pushedObjects.clear();
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public float getOffsetX(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * Facing.offsetsXForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * Facing.offsetsXForSide[this.storedOrientation];
    }

    @SideOnly(Side.CLIENT)
    public float getOffsetY(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * Facing.offsetsYForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * Facing.offsetsYForSide[this.storedOrientation];
    }

    @SideOnly(Side.CLIENT)
    public float getOffsetZ(float par1)
    {
        return this.extending ? (this.getProgress(par1) - 1.0F) * Facing.offsetsZForSide[this.storedOrientation] : (1.0F - this.getProgress(par1)) * Facing.offsetsZForSide[this.storedOrientation];
    }

    /**
     * removes a pistons tile entity (and if the piston is moving, stops it)
     */
    public void clearPistonTileEntity()
    {
        if (this.lastProgress < 1.0F && this.worldObj != null)
        {
            this.lastProgress = this.progress = 1.0F;
            this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            this.invalidate();

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == SteamCraft.steamPistonMoving.blockID)
            {
                this.worldObj.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata);
            }
        }
    }

    /**
     * Allows the entity to update its state. Overridden in most subclasses, e.g. the mob spawner uses this to count
     * ticks and creates a new spawn inside its implementation.
     */
    @Override
	public void updateEntity()
    {
        this.lastProgress = this.progress;

        if (this.lastProgress >= 1.0F)
        {
            this.updatePushedObjects(1.0F, 0.25F);
            this.worldObj.removeBlockTileEntity(this.xCoord, this.yCoord, this.zCoord);
            this.invalidate();

            if (this.worldObj.getBlockId(this.xCoord, this.yCoord, this.zCoord) == SteamCraft.steamPistonMoving.blockID)
            {
                this.worldObj.setBlockAndMetadataWithNotify(this.xCoord, this.yCoord, this.zCoord, this.storedBlockID, this.storedMetadata);
            }
        }
        else
        {
            this.progress += 0.5F;

            if (this.progress >= 1.0F)
            {
                this.progress = 1.0F;
            }

            if (this.extending)
            {
                this.updatePushedObjects(this.progress, this.progress - this.lastProgress + 0.0625F);
            }
        }
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.storedBlockID = par1NBTTagCompound.getInteger("blockId");
        this.storedMetadata = par1NBTTagCompound.getInteger("blockData");
        this.storedOrientation = par1NBTTagCompound.getInteger("facing");
        this.lastProgress = this.progress = par1NBTTagCompound.getFloat("progress");
        this.extending = par1NBTTagCompound.getBoolean("extending");
    }

    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("blockId", this.storedBlockID);
        par1NBTTagCompound.setInteger("blockData", this.storedMetadata);
        par1NBTTagCompound.setInteger("facing", this.storedOrientation);
        par1NBTTagCompound.setFloat("progress", this.lastProgress);
        par1NBTTagCompound.setBoolean("extending", this.extending);
    }
}
