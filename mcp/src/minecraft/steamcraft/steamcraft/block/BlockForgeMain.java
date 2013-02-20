package steamcraft.steamcraft.block;

import java.util.List;
import java.util.Random;

import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.common.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityForge;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockForgeMain extends BlockContainer
{
    private Random furnaceRand = new Random();

    private final boolean isActive;

    private static boolean keepFurnaceInventory = false;

    public BlockForgeMain(int par1, int par2, boolean par3)
    {
        super(par1, Material.rock);
        this.isActive = par3;
    }

    public int idDropped(int par1, Random random, int zero)
    {
        return SteamCraft.blockFurnaceExtension.blockID;
    }

    public int quantityDropped(Random par1Random)
    {
        return 3;
    }

    public void onBlockAdded(World par1World, int par2, int par3, int par4)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
    }

    private void setDefaultDirection(World par1World, int par2, int par3, int par4)
    {
    }

    //@SideOnly(Side.CLIENT)

    //public void randomDisplayTick(World par1World, int par2, int par3, int par4, Random par5Random)
//	{
//	}

    //public int getBlockTextureFromSide(int par1)
    //{
//	         return par1 == 1 ? this.blockIndexInTexture + 17 : (par1 == 0 ? this.blockIndexInTexture + 17 : (par1 == 3 ? this.blockIndexInTexture - 1 : this.blockIndexInTexture));
    //}

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        if (par1World.isRemote)
        {
            return true;
        }
        else if (!par5EntityPlayer.isSneaking())
        {
            TileEntityForge var10 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3, par4);

            if (var10 != null)
            {
                par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2, par3, par4);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public static void updateFurnaceBlockState(boolean par0, World par1World, int par2, int par3, int par4)
    {
//	         int var5 = par1World.getBlockMetadata(par2, par3, par4);
//	         TileEntity var6 = par1World.getBlockTileEntity(par2, par3, par4);
//	         keepFurnaceInventory = true;
//
//	         if (par0)
//	         {
//	                 par1World.setBlockWithNotify(par2, par3, par4, BoilerMod.yourFurnaceActive.blockID);
//	         }
//	         else
//	         {
//	                 par1World.setBlockWithNotify(par2, par3, par4, BoilerMod.yourFurnaceIdle.blockID);
//	         }
//
//	         keepFurnaceInventory = false;
//	         par1World.setBlockMetadataWithNotify(par2, par3, par4, var5);
//
//	         if (var6 != null)
//	         {
//	                 var6.validate();
//	                 par1World.setBlockTileEntity(par2, par3, par4, var6);
//	         }
    }

    public TileEntity createNewTileEntity(World par1World)
    {
        return new TileEntityForge();
    }

//	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving par5EntityLiving)
//	{
//	         int var6 = MathHelper.floor_double((double)(par5EntityLiving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
//
//	         if (var6 == 0)
//	         {
//	                 par1World.setBlockMetadataWithNotify(par2, par3, par4, 2);
//	         }
//
//	         if (var6 == 1)
//	         {
//	                 par1World.setBlockMetadataWithNotify(par2, par3, par4, 5);
//	         }
//
//	         if (var6 == 2)
//	         {
//	                 par1World.setBlockMetadataWithNotify(par2, par3, par4, 3);
//	         }
//
//	         if (var6 == 3)
//	         {
//	                 par1World.setBlockMetadataWithNotify(par2, par3, par4, 4);
//	         }
//	}

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        if (!keepFurnaceInventory)
        {
            par1World.setBlockWithNotify(par2 , par3 + 1, par4, 0);
            TileEntityForge var7 = (TileEntityForge)par1World.getBlockTileEntity(par2, par3, par4);
            EntityItem varf = new EntityItem(par1World, (double)((float)par2), (double)((float)par3), (double)((float)par4), new ItemStack(Block.stoneOvenIdle));
            par1World.spawnEntityInWorld(varf);

            if (var7 != null)
            {
                for (int var8 = 0; var8 < var7.getSizeInventory(); ++var8)
                {
                    ItemStack var9 = var7.getStackInSlot(var8);

                    if (var9 != null)
                    {
                        float var10 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var11 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;
                        float var12 = this.furnaceRand.nextFloat() * 0.8F + 0.1F;

                        while (var9.stackSize > 0)
                        {
                            int var13 = this.furnaceRand.nextInt(21) + 10;

                            if (var13 > var9.stackSize)
                            {
                                var13 = var9.stackSize;
                            }

                            var9.stackSize -= var13;
                            EntityItem var14 = new EntityItem(par1World, (double)((float)par2 + var10), (double)((float)par3 + var11), (double)((float)par4 + var12), new ItemStack(var9.itemID, var13, var9.getItemDamage()));

                            if (var9.hasTagCompound())
                            {
                                var14.getEntityItem().setTagCompound((NBTTagCompound)var9.getTagCompound().copy());
                            }

                            float var15 = 0.05F;
                            var14.motionX = (double)((float)this.furnaceRand.nextGaussian() * var15);
                            var14.motionY = (double)((float)this.furnaceRand.nextGaussian() * var15 + 0.2F);
                            var14.motionZ = (double)((float)this.furnaceRand.nextGaussian() * var15);
                            par1World.spawnEntityInWorld(var14);
                        }
                    }
                }
            }
        }

        super.breakBlock(par1World, par2, par3, par4, par5, par6);
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        int meta = (metadata % 4);
        int data = metadata - meta;

        switch (side)
        {
            case (0):
                switch (data)
                {
                    case (0):
                    case (8):
                        if ((meta % 2) == 0)
                        {
                            return 12;
                        }
                        else
                        {
                            return 14;
                        }

                    case (4):
                    case (12):
                        if ((meta % 2) == 0)
                        {
                            return 13;
                        }
                        else
                        {
                            return 15;
                        }

                    default:
                        return 7;
                }

            case (1):
                switch (data)
                {
                    case (0):
                    case (8):
                        if ((meta % 2) == 0)
                        {
                            return 12;
                        }
                        else
                        {
                            return 14;
                        }

                    case (4):
                    case (12):
                        if ((meta % 2) == 0)
                        {
                            return 13;
                        }
                        else
                        {
                            return 15;
                        }

                    default:
                        return 7;
                }

            case (5):
                if ((8 + meta) == 11 || (8 + meta) == 9)
                {
                    switch (data)
                    {
                        case (0):
                        case (4):
                        default:
                            return 12;

                        case (8):
                        case (12):
                            return 13;
                    }
                }
                else if (meta == 0)
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 6;

                        case (4):
                            return 7;

                        case (8):
                            return 8;

                        case (12):
                            return 9;
                    }
                }
                else
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 4;

                        case (4):
                            return 5;

                        case (8):
                            return 10;

                        case (12):
                            return 11;
                    }
                }

            case (3):
                if ((((meta + 1) % 4) + 8) == 11 || (((meta + 1) % 4) + 8) == 9)
                {
                    switch (data)
                    {
                        case (0):
                        case (4):
                        default:
                            return 12;

                        case (8):
                        case (12):
                            return 13;
                    }
                }
                else if (((meta + 1) % 4) == 0)
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 6;

                        case (4):
                            return 7;

                        case (8):
                            return 8;

                        case (12):
                            return 9;
                    }
                }
                else
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 4;

                        case (4):
                            return 5;

                        case (8):
                            return 10;

                        case (12):
                            return 11;
                    }
                }

            case (4):
                if ((((meta + 2) % 4) + 8) == 11 || (((meta + 2) % 4) + 8) == 9)
                {
                    switch (data)
                    {
                        case (0):
                        case (4):
                        default:
                            return 12;

                        case (8):
                        case (12):
                            return 13;
                    }
                }
                else if (((meta + 2) % 4) == 0)
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 6;

                        case (4):
                            return 7;

                        case (8):
                            return 8;

                        case (12):
                            return 9;
                    }
                }
                else
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 4;

                        case (4):
                            return 5;

                        case (8):
                            return 10;

                        case (12):
                            return 11;
                    }
                }

            case (2):
                if ((((meta + 3) % 4) + 8) == 11 || (((meta + 3) % 4) + 8) == 9)
                {
                    switch (data)
                    {
                        case (0):
                        case (4):
                        default:
                            return 12;

                        case (8):
                        case (12):
                            return 13;
                    }
                }
                else if (((meta + 3) % 4) == 0)
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 6;

                        case (4):
                            return 7;

                        case (8):
                            return 8;

                        case (12):
                            return 9;
                    }
                }
                else
                {
                    switch (data)
                    {
                        case (0):
                        default:
                            return 4;

                        case (4):
                            return 5;

                        case (8):
                            return 10;

                        case (12):
                            return 11;
                    }
                }

            default:
                return ((meta + 4) % 4) + 8;
        }
    }

    @Override
    public String getTextureFile()
    {
        return SteamCraft.BLOCKS_PNG;
    }
}