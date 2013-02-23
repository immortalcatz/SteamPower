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
import steamcraft.steamcraft.common.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

public class BlockResearchTable extends BlockContainer
{
    private final Random tableRand = new Random();

    public BlockResearchTable(int par1)
    {
        super(par1, Material.wood);
        this.blockIndexInTexture = 45;
    }

    @Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return SteamCraft.researchTable.blockID;
    }

    @Override
	public int getRenderType(){
        return -1;
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
            TileEntityResearchTable var10 = (TileEntityResearchTable) par1World.getBlockTileEntity(par2, par3, par4);

            if (var10 != null)
            {

                par5EntityPlayer.openGui(SteamCraft.instance, 2, par1World, par2, par3, par4);
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
        return new TileEntityResearchTable();
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
        TileEntityResearchTable var7 = (TileEntityResearchTable)par1World.getBlockTileEntity(par2, par3, par4);

        if (var7 != null)
        {
            for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
            {
                ItemStack var9 = var7.getStackInSlot(var8);

                if (var9 != null)
                {
                    float var10 = this.tableRand.nextFloat() * 0.8F + 0.1F;
                    float var11 = this.tableRand.nextFloat() * 0.8F + 0.1F;
                    float var12 = this.tableRand.nextFloat() * 0.8F + 0.1F;

                    while (var9.stackSize > 0)
                    {
                        int var13 = this.tableRand.nextInt(21) + 10;

                        if (var13 > var9.stackSize)
                        {
                            var13 = var9.stackSize;
                        }

                        var9.stackSize -= var13;
                        EntityItem var14 = new EntityItem(par1World, par2 + var10, par3 + var11, par4 + var12, new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                        if (var9.hasTagCompound())
                        {
                            var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                        }

                        float var15 = 0.05F;
                        var14.motionX = (float)this.tableRand.nextGaussian() * var15;
                        var14.motionY = (float)this.tableRand.nextGaussian() * var15 + 0.2F;
                        var14.motionZ = (float)this.tableRand.nextGaussian() * var15;
                        par1World.spawnEntityInWorld(var14);
                    }
                }
            }
        }
        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }
}