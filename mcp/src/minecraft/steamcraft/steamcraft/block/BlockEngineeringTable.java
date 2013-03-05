package steamcraft.steamcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.client.render.EngineeringTableRender;
import steamcraft.steamcraft.client.render.ResearchTableRender;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

public class BlockEngineeringTable extends BlockContainer
{
    private final Random tableRand = new Random();

    public BlockEngineeringTable(int par1)
    {
        super(par1, Material.wood);
        this.blockIndexInTexture = 45;
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return SteamCraft.engineeringTable.blockID;
    }

    @Override
    public String getTextureFile()
    {
        return (SteamCraft.modelTextureLocation + "engineeringtable.png");
    }

    @Override
	public int getBlockTextureFromSide(int i) {
		return i;
	}

	@Override
	public int getRenderType ()
	{
		return EngineeringTableRender.engineerModelID;
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

    @Override
    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        this.setDefaultDirection(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
        if (!par1World.isRemote)
        {
            int var5 = par1World.getBlockId(par2, par3, par4 - 1);
            int var6 = par1World.getBlockId(par2, par3, par4 + 1);
            int var7 = par1World.getBlockId(par2 - 1, par3, par4);
            int var8 = par1World.getBlockId(par2 + 1, par3, par4);
            byte var9 = 3;

            if (Block.opaqueCubeLookup[var5] && !Block.opaqueCubeLookup[var6])
            {
                var9 = 3;
            }

            if (Block.opaqueCubeLookup[var6] && !Block.opaqueCubeLookup[var5])
            {
                var9 = 2;
            }

            if (Block.opaqueCubeLookup[var7] && !Block.opaqueCubeLookup[var8])
            {
                var9 = 5;
            }

            if (Block.opaqueCubeLookup[var8] && !Block.opaqueCubeLookup[var7])
            {
                var9 = 4;
            }

            par1World.setBlockMetadataWithNotify(par2, par3, par4, var9);
        }
    }

    @Override
    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else if (!par5EntityPlayer.isSneaking())
        {
            TileEntityEngineeringTable var10 = (TileEntityEngineeringTable) par1World.getBlockTileEntity(par2, par3, par4);

            if (var10 != null)
            {
                par5EntityPlayer.openGui(SteamCraft.instance, 3, par1World, par2, par3, par4);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityEngineeringTable();
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
    {
        int var6 = MathHelper.floor_double(par5EntityLiving.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;

        if (var6 == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
        }

        if (var6 == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
        }

        if (var6 == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
        }

        if (var6 == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
        }
    }

    @Override
    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        TileEntityEngineeringTable var7 = (TileEntityEngineeringTable)par1World.getBlockTileEntity(par2, par3, par4);



        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}