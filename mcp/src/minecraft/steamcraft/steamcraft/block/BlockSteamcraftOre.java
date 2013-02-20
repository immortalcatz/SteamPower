package steamcraft.steamcraft.block;

import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.common.SteamCraft;
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
