package steamcraft.steamcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.steamcraft.tileentity.TileEntitySteamPipe;

public class BlockSteamPipe extends Block {

	public BlockSteamPipe(int id, Material material) {
		super(id, material);
	}

	@Override
    public TileEntity createTileEntity(World world, int metadata) {
		return new TileEntitySteamPipe();
	}

}
