package dimitriye98.steamcraft.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import dimitriye98.steamcraft.SteamCraft;
import dimitriye98.steamcraft.common.CommonProxy;
import dimitriye98.steamcraft.tileentity.TileEntityForge;

public class BlockForge extends Block
{
    int metadat;
    private boolean active;
    public BlockForge(int id, int texture, boolean on)
    {
        super(id, texture, Material.iron);
        active = on;
    }

    @Override
    public int getBlockTextureFromSideAndMetadata(int side, int metadata)
    {
        metadat = metadata;
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
                            return 23;
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
                    if (active)
                    {
                        switch (data)
                        {
                            case (0):
                            default:
                                return 20;

                            case (4):
                                return 19;

                            case (8):
                                return 22;

                            case (12):
                                return 21;
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
                            return 23;
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
                    if (active)
                    {
                        switch (data)
                        {
                            case (0):
                            default:
                                return 20;

                            case (4):
                                return 19;

                            case (8):
                                return 22;

                            case (12):
                                return 21;
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
                            return 23;
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
                    if (active)
                    {
                        switch (data)
                        {
                            case (0):
                            default:
                                return 20;

                            case (4):
                                return 19;

                            case (8):
                                return 22;

                            case (12):
                                return 21;
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
                            return 23;
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
                    if (active)
                    {
                        switch (data)
                        {
                            case (0):
                            default:
                                return 20;

                            case (4):
                                return 19;

                            case (8):
                                return 22;

                            case (12):
                                return 21;
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
                }

            default:
                return ((meta + 4) % 4) + 8;
        }
    }

    public int idDropped(int par1, Random random, int zero)
    {
        return SteamCraft.blockFurnaceExtension.blockID;
    }

    public int quantityDropped(Random par1Random)
    {
        return 3;
    }

    public boolean onBlockActivated(World par1World, int par2, int par3, int par4, EntityPlayer par5EntityPlayer, int par6, float par7, float par8, float par9)
    {
        int meta = (metadat % 4);

        if (par1World.isRemote)
        {
            return true;
        }
        else if (!par5EntityPlayer.isSneaking())
        {
            TileEntityForge var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3, par4);

            if (var12 != null)
            {
                par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2 + 1, par3, par4);
            }
            else
            {
                var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4);

                if (var12 != null)
                {
                    par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2, par3 - 1, par4);
                }
                else
                {
                    var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3 - 1, par4);

                    if (var12 != null)
                    {
                        par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2 + 1, par3 - 1, par4);
                    }
                    else
                    {
                        var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 - 1, par3, par4);

                        if (var12 != null)
                        {
                            par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2 - 1, par3, par4);
                        }
                        else
                        {
                            var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3, par4 + 1);

                            if (var12 != null)
                            {
                                par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2, par3, par4 + 1);
                            }
                            else
                            {
                                var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4 + 1);

                                if (var12 != null)
                                {
                                    par5EntityPlayer.openGui(SteamCraft.instance, 0, par1World, par2, par3 - 1, par4 + 1);
                                }
                            }
                        }
                    }
                }
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    public void breakBlock(World par1World, int par2, int par3, int par4, int par5, int par6)
    {
        TileEntityForge var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4);

        if (var12 != null)
        {
            int data = metadat % 4;

            switch (data)
            {
                case (0):
                    par1World.setBlockWithNotify(par2 - 1, par3, par4, 0);

                case (1):
                    par1World.setBlockWithNotify(par2 - 1, par3, par4, 0);

                case (2):
                    par1World.setBlockWithNotify(par2, par3, par4 - 1, 0);

                case (3):
                    par1World.setBlockWithNotify(par2 - 1, par3, par4, 0);
            }
        }
        else
        {
            var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3 - 1, par4);

            if (var12 != null)
            {
                par1World.setBlockWithNotify(par2 + 1, par3 - 1, par4, 0);
                par1World.setBlockWithNotify(par2, par3 - 1, par4, 0);
                par1World.setBlockWithNotify(par2 + 1, par3, par4, 0);
            }
            else
            {
                var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 - 1, par3, par4);

                if (var12 != null)
                {
                    par1World.setBlockWithNotify(par2 - 1, par3 + 1, par4, 0);
                    par1World.setBlockWithNotify(par2 - 1, par3, par4, 0);
                    par1World.setBlockWithNotify(par2 , par3 + 1, par4, 0);
                }
                else
                {
                    var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3, par4 + 1);

                    if (var12 != null)
                    {
                        par1World.setBlockWithNotify(par2, par3, par4 + 1, 0);
                        par1World.setBlockWithNotify(par2, par3 + 1, par4 + 1, 0);
                        par1World.setBlockWithNotify(par2, par3 + 1, par4, 0);
                    }
                    else
                    {
                        var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4 + 1);

                        if (var12 != null)
                        {
                            par1World.setBlockWithNotify(par2, par3 - 1, par4 + 1, 0);
                            par1World.setBlockWithNotify(par2, par3 - 1, par4, 0);
                            par1World.setBlockWithNotify(par2, par3, par4 + 1, 0);
                        }
                        else
                        {
                            var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3, par4);

                            if (var12 != null)
                            {
                                par1World.setBlockWithNotify(par2 + 1, par3, par4, 0);
                                par1World.setBlockWithNotify(par2 + 1 , par3 + 1, par4, 0);
                                par1World.setBlockWithNotify(par2, par3 + 1, par4, 0);
                            }
                            else
                            {
                                var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4 - 1);

                                if (var12 != null)
                                {
                                    par1World.setBlockWithNotify(par2, par3 - 1, par4 - 1, 0);
                                    par1World.setBlockWithNotify(par2, par3 - 1, par4, 0);
                                    par1World.setBlockWithNotify(par2, par3, par4 - 1, 0);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }

    public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);
        System.out.println("IT WORKS :)");
        int meta = (metadat % 4);
        TileEntityForge var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3, par4);

//        if (var12 != null)
//        {
//            if (var12.isBurning())
//            {
//                par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//            }
//            else
//            {
//                par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//            }
//        }
//        else
//        {
//            var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4);
//
//            if (var12 != null)
//            {
//                if (var12.isBurning())
//                {
//                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//                }
//                else
//                {
//                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//                }
//            }
//            else
//            {
//                var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 + 1, par3 - 1, par4);
//
//                if (var12 != null)
//                {
//                    if (var12.isBurning())
//                    {
//                        par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//                    }
//                    else
//                    {
//                        par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//                    }
//                }
//                else
//                {
//                    var12 = (TileEntityForge) par1World.getBlockTileEntity(par2 - 1, par3, par4);
//
//                    if (var12 != null)
//                    {
//                        if (var12.isBurning())
//                        {
//                            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//                        }
//                        else
//                        {
//                            par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//                        }
//                    }
//                    else
//                    {
//                        var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3, par4 + 1);
//
//                        if (var12 != null)
//                        {
//                            if (var12.isBurning())
//                            {
//                                par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//                            }
//                            else
//                            {
//                                par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//                            }
//                        }
//                        else
//                        {
//                            var12 = (TileEntityForge) par1World.getBlockTileEntity(par2, par3 - 1, par4 + 1);
//
//                            if (var12 != null)
//                            {
//                                if (var12.isBurning())
//                                {
//                                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForgeOn.blockID, metadat);
//                                }
//                                else
//                                {
//                                    par1World.setBlockAndMetadataWithNotify(par2, par3, par4, SteamCraft.blockForge.blockID, metadat);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }

        if (this.active)
        {
            System.out.println("IT WORKS :D");
        }
    }
}
