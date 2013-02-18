package dimitriye98.steamcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockDebugSteamer extends Block {

	public BlockDebugSteamer(int par1, Material par2Material) {
		super(par1, par2Material);
	}
	
	@Override
	public boolean onBlockActivated(World planetEarth, int x, int y, int z, EntityPlayer yoursTruly, int side, float xOffset, float yOffset, float zOffset) {
		
		return true;
	}

}
