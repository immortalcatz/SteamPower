package boiler;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBoilerOre extends Block
{
    public BlockBoilerOre(int id, int texture)
    {
        super(id, texture, Material.rock);
    }

    @Override
    public String getTextureFile()
    {
        return CommonProxy.BLOCK_PNG;
    }
}
