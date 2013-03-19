package steamcraft.steamcraft.block;

import java.util.List;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.client.render.RenderPlating;
import steamcraft.steamcraft.client.render.ResearchTableRender;
import steamcraft.steamcraft.tileentity.TileEntityPlating;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPlating extends BlockContainer{

	public BlockPlating(int par1, int par2) {
		super(par1, par2, Material.iron);
	}

	@Override
	public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
		byte[] orientations = ((TileEntityPlating)par1World.getBlockTileEntity(par2, par3, par4)).orientations;
		if (orientations[0] == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		if (orientations[1] == 1) {
			this.setBlockBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		if (orientations[2] == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		if (orientations[3] == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		if (orientations[4] == 1) {
			this.setBlockBounds(0.0F, 0.0F, 0.0F,  0.125F, 1.0F, 1.0F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
		if (orientations[5] == 1) {
			this.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
			super.addCollidingBlockToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
		}
    }

    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityPlating();
    }

    @Override
    public String getTextureFile()
    {
        return SteamCraft.BLOCKS_PNG;
    }

	@Override
	public boolean shouldSideBeRendered (IBlockAccess par1IBlockAccess, int par2, int par3, int par4, int par5)
	{
		return true;
	}

    @Override
	public int getBlockTextureFromSideAndMetadata (int side, int meta)
	{
    	return 1;
	}

    @Override
	public int getBlockTextureFromSide (int side)
	{
    	return 1;
	}

	@Override
	public int getRenderType ()
	{
		return RenderPlating.platingID;
	}

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }

}
