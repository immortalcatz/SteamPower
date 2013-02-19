package dimitriye98.steamcraft.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.liquids.ILiquidTank;
import net.minecraftforge.liquids.ITankContainer;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidStack;
import net.minecraftforge.liquids.LiquidTank;

public class TileEntityBoiler extends TileEntity implements ITankContainer {
	
	private LiquidTank waterTank;
	private static int maxWater = LiquidContainerRegistry.BUCKET_VOLUME * 10;
	
    public int boilerBurnTime = 0;
    public int currentItemBurnTime = 0;

	public TileEntityBoiler() {
		waterTank = new LiquidTank(maxWater);
	}
	
	@Override
	public int fill(ForgeDirection from, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public int fill(int tankIndex, LiquidStack resource, boolean doFill) {
		return 0;
	}

	@Override
	public LiquidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public LiquidStack drain(int tankIndex, int maxDrain, boolean doDrain) {
		return null;
	}

	@Override
	public ILiquidTank[] getTanks(ForgeDirection direction) {
		return new ILiquidTank[] {waterTank};
	}

	@Override
	public ILiquidTank getTank(ForgeDirection direction, LiquidStack type) {
		return waterTank;
	}
	
}
