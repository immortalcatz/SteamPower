package steamcraft.steamcraft.block;

import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.common.SteamCraft;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockSteamcraftStorage extends Block
{
    public BlockSteamcraftStorage(int id, int texture)
    {
        super(id, texture, Material.iron);
    }

    @Override
    public String getTextureFile()
    {
        return SteamCraft.BLOCKS_PNG;
    }
}
