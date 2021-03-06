package steamcraft.steamcraft.block;

import java.util.LinkedList;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntitySteam;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class BlockSteam extends Block {

	public BlockSteam(int par1) {
		super(par1, Material.air);
	}

	@Override
	public boolean isAirBlock(World world, int x, int y, int z) {
		return true;
	}

	public static int getPressure(World world, int x, int y, int z) {
		if (world.getBlockId(x, y, z) == 0) {
			return 0;
		} else if (world.getBlockId(x, y, z) == SteamCraft.steam.blockID) {
			return ((TileEntitySteam)world.getBlockTileEntity(x, y, z)).getPressure();
		}
		return 17;
	}
	
	public static int addPressureRaw(World world, int x, int y, int z, int pressure) {
		int targetPressure = getPressure(world, x, y, z);
		if (targetPressure == 0) {
			world.setBlock(x, y, z, SteamCraft.steam.blockID);
			return ((TileEntitySteam)world.getBlockTileEntity(x, y, z)).addPressure(pressure);
		} else if (targetPressure < 17) {
			return ((TileEntitySteam)world.getBlockTileEntity(x, y, z)).addPressure(pressure);
		} else {
			return pressure;
		}
	}
	
	public static void addPressure(World world, int x, int y, int z, int pressure) {
		pressure = addPressureRaw(world, x, y, z, pressure);
		if (pressure > 0) {
			world.createExplosion((Entity)null, x, y, z, (float)4.0, true);
		}
	}
	/*
	@Override
	public boolean hasTileEntity(int metadata) {
		return true;
	}*/

}
