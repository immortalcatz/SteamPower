package dimitriye98.steamcraft.block;

import dimitriye98.steamcraft.SteamCraft;
import dimitriye98.steamcraft.common.CommonProxy;
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
