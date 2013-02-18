package boiler;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockFurnaceExtension extends Block
{
    public BlockFurnaceExtension(int id, int texture)
    {
        super(id, texture, Material.iron);
        setCreativeTab(CreativeTabs.tabDecorations);
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        if (metadata == 0)
        {
            switch (side)
            {
                case 0:
                    return 2;

                case 1:
                    return 2;

                default:
                    return 3;
            }
        }
        else
        {
            switch (side)
            {
                case 0:
                    return 2;

                case 1:
                    return 2;

                default:
                    return 2;
            }
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLiving entityliving)
    {
        super.onBlockAdded(par1World, par2, par3, par4);
        //this.unifyAdjacentChests(par1World, par2, par3, par4);
        int var5 = par1World.getBlockId(par2, par3, par4 - 1);
        int var6 = par1World.getBlockId(par2, par3, par4 + 1);
        int var7 = par1World.getBlockId(par2 - 1, par3, par4);
        int var8 = par1World.getBlockId(par2 + 1, par3, par4);
        int var9 = par1World.getBlockId(par2, par3 - 1, par4);
        int var10 = par1World.getBlockId(par2, par3 + 1, par4);
        double x = entityliving.posX;
        boolean k = true;
        int l = 0;

        if (x > par2)
        {
            l = 2;
            k = false;
        }

        double z = entityliving.posZ;
        boolean n = false;
        int m = 3;

        if (z > par4)
        {
            m = 1;
            n = true;
        }

        //int l = (MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3);

        // Z-1
        if (var5 == this.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 - 1);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 - 1);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                }
            }
            else if (var9 ==  Block.stoneOvenIdle.blockID || var9 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 - 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                }
            }
            else if (var10 == Block.stoneOvenIdle.blockID || var10 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 - 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                }
            }
        }
        else if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 - 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 - 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 - 1, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                }
            }
        }
        // Z + 1
        else if (var6 == this.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 + 1);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 + 1);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                }
            }
            else if (var9 ==  Block.stoneOvenIdle.blockID || var9 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 + 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                }
            }
            else if (var10 == Block.stoneOvenIdle.blockID || var10 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 + 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                }
            }
        }
        else if (var6 ==  Block.stoneOvenIdle.blockID || var6 ==  Block.stoneOvenActive.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 - 1, par4 + 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2, par3 + 1, par4 + 1);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4 + 1, BoilerMod.blockForgeMain.blockID, getActualSide(12, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, l, k));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4 + 1, BoilerMod.blockForge.blockID, getActualSide(4, l, k));
                }
            }
        }
        // X-1
        else if (var7 == this.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 - 1, par4);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 + 1, par4);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                }
            }
            else if (var9 ==  Block.stoneOvenIdle.blockID || var9 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 - 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                }
            }
            else if (var10 == Block.stoneOvenIdle.blockID || var10 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 + 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                }
            }
        }
        else if (var7 ==  Block.stoneOvenIdle.blockID || var7 ==  Block.stoneOvenActive.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 - 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 - 1, par3 + 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 - 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                }
            }
        }
        // X+1
        else if (var8 == this.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 - 1, par4);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 + 1, par4);

                if (var5 ==  Block.stoneOvenIdle.blockID || var5 ==  Block.stoneOvenActive.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                }
            }
            else if (var9 ==  Block.stoneOvenIdle.blockID || var9 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 - 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                }
            }
            else if (var10 == Block.stoneOvenIdle.blockID || var10 ==  Block.stoneOvenActive.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 + 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                }
            }
        }
        else if (var8 ==  Block.stoneOvenIdle.blockID || var8 ==  Block.stoneOvenActive.blockID)
        {
            if (var9 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 - 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 - 1, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 - 1, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                }
            }
            else if (var10 == this.blockID)
            {
                var5 = par1World.getBlockId(par2 + 1, par3 + 1, par4);

                if (var5 == this.blockID)
                {
                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, BoilerMod.blockForge.blockID, getActualSide(8, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3, par4, BoilerMod.blockForgeMain.blockID, getActualSide(12, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(0, m, n));
                    par1World.setBlockAndMetadataWithNotify(par2 + 1, par3 + 1, par4, BoilerMod.blockForge.blockID, getActualSide(4, m, n));
                }
            }
        }
    }

    public int getActualSide(int p, int m, boolean k)
    {
        if (k)
        {
            switch (p)
            {
                default:
                case (4):
                    return 0 + m;

                case (8):
                    return 12 + m;

                case (12):
                    return 8 + m;

                case (0):
                    return 4 + m;
            }
        }
        else
        {
            return p + m;
        }
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }

    public void onBlockUpdate()
    {
    }
}
