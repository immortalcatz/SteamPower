package steamcraft.steamcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import steamcraft.steamcraft.tileentity.TileEntitySteamPistonMoving;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Facing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockSteamPistonMoving extends BlockContainer
{
	private final double power = 6.0D;
    public BlockSteamPistonMoving(int par1)
    {
        super(par1, Material.piston);
        this.setHardness(-1.0F);
    }

    /**
     * Returns a new instance of a block's tile entity class. Called on placing the block.
     */
    @Override
	public TileEntity createNewTileEntity(World par1World)
    {
        return null;
    }

    /**
     * Called whenever the block is added into the world. Args: world, x, y, z
     */
    @Override
	public void onBlockAdded(World par1World, int par2, int par3, int par4) {}

    /**
     * ejects contained items into the world, and notifies neighbours of an update, as appropriate
     */
    @Override
	public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        TileEntity var7 = par1World.getBlockTileEntity(par2, par3, par4);

        if (var7 instanceof TileEntitySteamPistonMoving)
        {
            ((TileEntitySteamPistonMoving)var7).clearPistonTileEntity();
        }
        else
        {
            super.breakBlock(par1World, par2, par3, par4, par5, par6);
        }
    }

    /**
     * Checks to see if its valid to put this block at the specified coordinates. Args: world, x, y, z
     */
    @Override
	public boolean canPlaceBlockAt(World par1World, int par2, int par3, int par4)
    {
        return false;
    }

    @Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity)
    {
        TileEntitySteamPistonMoving var5 = this.getTileEntityAtLocation(par1World, par2, par3, par4);

        if (var5 != null && var5.isExtending())
        {
        	int var8 = var5.getPistonOrientation();
        	if (var8 == 0) {
        		par5Entity.motionY = -power/2;
        	}
        	if (var8 == 1) {
        		par5Entity.motionY = power/2;
        	}
        	if (var8 == 2) {
        		par5Entity.motionZ = -power;
        	}
        	if (var8 == 3) {
        		par5Entity.motionZ = power;
        	}
        	if (var8 == 4) {
        		par5Entity.motionX = -power;
        	}
        	if (var8 == 5) {
        		par5Entity.motionX = power;
        	}
        }
    }

    /**
     * checks to see if you can place this block can be placed on that side of a block: BlockLever overrides
     */
    @Override
	public boolean canPlaceBlockOnSide(World par1World, int par2, int par3, int par4, int par5)
    {
        return false;
    }

    /**
     * The type of render function that is called for this block
     */
    @Override
	public int getRenderType()
    {
        return -1;
    }

    /**
     * Is this block (a) opaque and (b) a full 1m cube?  This determines whether or not to render the shared face of two
     * adjacent blocks and also whether the player can attach torches, redstone wire, etc to this block.
     */
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }

    /**
     * If this block doesn't render as an ordinary block it will return False (examples: signs, buttons, stairs, etc)
     */
    @Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }

    /**
     * Called upon block activation (right click on the block.)
     */
    @Override
	public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (!par1World.isRemote && par1World.getBlockTileEntity(par2, par3, par4) == null)
        {
            par1World.setBlockWithNotify(par2, par3, par4, 0);
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }

    /**
     * Drops the block items with a specified chance of dropping the specified items
     */
    @Override
	public void dropBlockAsItemWithChance(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (!par1World.isRemote)
        {
            TileEntitySteamPistonMoving var8 = this.getTileEntityAtLocation(par1World, par2, par3, par4);

            if (var8 != null)
            {
                Block.blocksList[var8.getStoredBlockID()].dropBlockAsItem(par1World, par2, par3, par4, var8.getBlockMetadata(), 0);
            }
        }
    }

    /**
     * Lets the block know when one of its neighbor changes. Doesn't know which neighbor changed (coordinates passed are
     * their own) Args: x, y, z, neighbor blockID
     */
    @Override
	public void onNeighborBlockChange(World par1World, int par2, int par3, int par4, int par5)
    {
        if (!par1World.isRemote && par1World.getBlockTileEntity(par2, par3, par4) == null)
        {

        }
    }

    /**
     * gets a new TileEntitySteamPistonMoving created with the arguments provided.
     */
    public static TileEntity getTileEntity(int par0, int par1, int par2, boolean par3, boolean par4)
    {
        return new TileEntitySteamPistonMoving(par0, par1, par2, par3, par4);
    }

    /**
     * Returns a bounding box from the pool of bounding boxes (this means this box can change after the pool has been
     * cleared to be reused)
     */
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        TileEntitySteamPistonMoving var5 = this.getTileEntityAtLocation(par1World, par2, par3, par4);

        if (var5 == null)
        {
            return null;
        }
        else
        {
            float var6 = var5.getProgress(0.0F);

            if (var5.isExtending())
            {
                var6 = 1.0F - var6;
            }

            return this.getAxisAlignedBB(par1World, par2, par3, par4, var5.getStoredBlockID(), var6, var5.getPistonOrientation());
        }
    }

    /**
     * Updates the blocks bounds based on its current state. Args: world, x, y, z
     */
    @Override
	public void setBlockBoundsBasedOnState(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        TileEntitySteamPistonMoving var5 = this.getTileEntityAtLocation(par1IBlockAccess, par2, par3, par4);

        if (var5 != null)
        {
            Block var6 = Block.blocksList[var5.getStoredBlockID()];

            if (var6 == null || var6 == this)
            {
                return;
            }

            var6.setBlockBoundsBasedOnState(par1IBlockAccess, par2, par3, par4);
            float var7 = var5.getProgress(0.0F);

            if (var5.isExtending())
            {
                var7 = 1.0F - var7;
            }

            int var8 = var5.getPistonOrientation();
            this.minX = var6.getBlockBoundsMinX() - Facing.offsetsXForSide[var8] * var7;
            this.minY = var6.getBlockBoundsMinY() - Facing.offsetsYForSide[var8] * var7;
            this.minZ = var6.getBlockBoundsMinZ() - Facing.offsetsZForSide[var8] * var7;
            this.maxX = var6.getBlockBoundsMaxX() - Facing.offsetsXForSide[var8] * var7;
            this.maxY = var6.getBlockBoundsMaxY() - Facing.offsetsYForSide[var8] * var7;
            this.maxZ = var6.getBlockBoundsMaxZ() - Facing.offsetsZForSide[var8] * var7;
        }
    }

    public AxisAlignedBB getAxisAlignedBB(World par1World, int par2, int par3, int par4, int par5, float par6, int par7)
    {
        if (par5 != 0 && par5 != this.blockID)
        {
            AxisAlignedBB var8 = Block.blocksList[par5].getCollisionBoundingBoxFromPool(par1World, par2, par3, par4);

            if (var8 == null)
            {
                return null;
            }
            else
            {
                if (Facing.offsetsXForSide[par7] < 0)
                {
                    var8.minX -= Facing.offsetsXForSide[par7] * par6;
                }
                else
                {
                    var8.maxX -= Facing.offsetsXForSide[par7] * par6;
                }

                if (Facing.offsetsYForSide[par7] < 0)
                {
                    var8.minY -= Facing.offsetsYForSide[par7] * par6;
                }
                else
                {
                    var8.maxY -= Facing.offsetsYForSide[par7] * par6;
                }

                if (Facing.offsetsZForSide[par7] < 0)
                {
                    var8.minZ -= Facing.offsetsZForSide[par7] * par6;
                }
                else
                {
                    var8.maxZ -= Facing.offsetsZForSide[par7] * par6;
                }

                return var8;
            }
        }
        else
        {
            return null;
        }
    }

    /**
     * gets the piston tile entity at the specified location
     */
    private TileEntitySteamPistonMoving getTileEntityAtLocation(IBlockAccess par1IBlockAccess, int par2, int par3, int par4)
    {
        TileEntity var5 = par1IBlockAccess.getBlockTileEntity(par2, par3, par4);
        return var5 instanceof TileEntitySteamPistonMoving ? (TileEntitySteamPistonMoving)var5 : null;
    }

    @Override
	@SideOnly(Side.CLIENT)

    /**
     * only called by clickMiddleMouseButton , and passed to inventory.setCurrentItem (along with isCreative)
     */
    public int idPicked(World par1World, int par2, int par3, int par4)
    {
        return 0;
    }
}
