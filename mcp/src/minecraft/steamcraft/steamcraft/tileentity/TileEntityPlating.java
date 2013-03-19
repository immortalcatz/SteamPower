package steamcraft.steamcraft.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class TileEntityPlating extends TileEntity
{
    /** Note to play */
    public byte[] orientations = new byte[6];

    /** stores the latest redstone state */
    public boolean previousRedstoneState = false;

    /**
     * Writes a tile entity to NBT.
     */
    @Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setByteArray("Orientations", orientations);
    }

    /**
     * Reads a tile entity from NBT.
     */
    @Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readFromNBT(par1NBTTagCompound);
        this.orientations = par1NBTTagCompound.getByteArray("Orientations");
    }

    public void addPlate(int position) {
    	orientations[position] = 1;
    }

    public void removePlate(int position) {
    	orientations[position] = 0;
    }


}
