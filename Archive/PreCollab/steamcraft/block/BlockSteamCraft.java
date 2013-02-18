package dimitriye98.steamcraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Used to check whether or not the block is a SteamCraft block
 * Will contain some useful methods
 * Make sure any class that would extend from Block extends from here as well
 * @author Dimitriye98
 */
public abstract class BlockSteamCraft extends Block {

	public BlockSteamCraft(int par1, int par2, Material par3Material) {
		super(par1, par2, par3Material);
	}

	public BlockSteamCraft(int par1, Material par2Material) {
		super(par1, par2Material);
	}

}
