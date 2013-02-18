package boiler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBoilerStorage extends Block
{
    public BlockBoilerStorage(int id, int texture)
    {
        super(id, texture, Material.iron);
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }
}
