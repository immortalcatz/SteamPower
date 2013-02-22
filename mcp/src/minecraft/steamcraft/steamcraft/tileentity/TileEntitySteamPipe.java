package steamcraft.steamcraft.tileentity;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import dimitriye98.core.logic.*;
import dimitriye98.core.logic.LogicMap.LogicNode;
import dimitriye98.core.util.WorldUtil;
public class TileEntitySteamPipe extends TileEntity implements ILogicConnector {

	@Override
	public String getType() {
		return "Steam";
	}

	@Override
	public boolean handlePassedData(LogicMap map, Object[]... data) {
		return false;
	}

	@Override
	public DataHandlingResult handlePassingData(LogicMap map, Object[]... data) {
		return null;
	}

	@Override
	public LogicNode handleForkingData(LogicMap map, Object[]... data) {
		return null;
	}

	@Override
	public ArrayList<ILogicConnector> calculateConnectedNodes() {
		ArrayList<ILogicConnector> out = new ArrayList<ILogicConnector>();
		Vec3[] adjacent = WorldUtil.getAdjacentCoordinates(worldObj, xCoord, yCoord, zCoord);
		for (Vec3 temp : adjacent) {
			TileEntity tempTE = worldObj.getBlockTileEntity((int)temp.xCoord, (int)temp.yCoord, (int)temp.zCoord);
			if (tempTE instanceof TileEntitySteamPipe) {
				out.add((ILogicConnector)tempTE);
			}
		}
		return out;
	}

}
