package dimitriye98.steamcraft.block;

import dimitriye98.steamcraft.SteamCraft;
import dimitriye98.steamcraft.common.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSteamcraftOre extends Block
{
    public BlockSteamcraftOre(int id, int texture)
    {
        super(id, texture, Material.rock);
    }

    @Override
    public String getTextureFile()
    {
        return SteamCraft.BLOCKS_PNG;
    }
}
