package steamcraft.steamcraft.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.block.BlockSteam;

import dimitriye98.core.util.WorldUtil;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TileEntitySteam extends TileEntity {
	private int pressure = 1;
	
	public int getPressure() {
		return pressure;
	}

	@Override
	public void updateEntity() {
		if (!(worldObj.getBlockId(xCoord, yCoord, zCoord) == SteamCraft.steam.blockID)) {
			return;
		}
		if (BlockSteam.getPressure(worldObj, xCoord, yCoord + 1, zCoord) < BlockSteam.getPressure(worldObj, xCoord, yCoord, zCoord)) {
			worldObj.setBlockMetadata(xCoord, yCoord + 1, zCoord, worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord) + 1);
			worldObj.setBlockMetadata(xCoord, yCoord, zCoord, worldObj.getBlockMetadata(xCoord, yCoord, zCoord) - 1);
		}
		int[][] sidePressures = new int[4][];
		sidePressures[0] = new int[] {xCoord - 1, yCoord, zCoord, BlockSteam.getPressure(worldObj, xCoord-1, yCoord, zCoord)};
		sidePressures[1] = new int[] {xCoord + 1, yCoord, zCoord, BlockSteam.getPressure(worldObj, xCoord+1, yCoord, zCoord)};
		sidePressures[2] = new int[] {xCoord, yCoord, zCoord - 1, BlockSteam.getPressure(worldObj, xCoord, yCoord, zCoord-1)};
		sidePressures[3] = new int[] {xCoord, yCoord, zCoord + 1, BlockSteam.getPressure(worldObj, xCoord, yCoord, zCoord+1)};
		ArrayList targets = new ArrayList<int[]>(0);
		for (int index = 0; index <= 3; index++) {
			if ((sidePressures[index][3] < 8) || ((BlockSteam.getPressure(worldObj, xCoord, yCoord, zCoord) > 8) && (sidePressures[index][3] < 17))) {
				targets.add(sidePressures[index]);
			}
		}
		for (int index = 1; index < sidePressures.length; index++) {
			
		}
	}
	
	private int addPressureRaw(int extraPressure) {
		int temp = 16 - this.pressure;
		if (temp < extraPressure) {
			this.pressure = 16;
			return (extraPressure - temp);
		}
		this.pressure += extraPressure;
		return 0;
	}
	
	public int addPressure(int extraPressure) {
		extraPressure = addPressureRaw(extraPressure);
		if (extraPressure == 0) {
			return 0;
		}
		
		Vec3[] sides = WorldUtil.getAdjacentCoordinates(worldObj, xCoord, yCoord, zCoord);
		
		for (int i = 0; i <= 5; i++) {
			extraPressure = BlockSteam.addPressureRaw(worldObj, (int)(sides[i].xCoord), (int)(sides[i].yCoord), (int)(sides[i].zCoord), extraPressure);
			if (extraPressure == 0) {
				return 0;
			}
		}
		
		return extraPressure;
	}
}
