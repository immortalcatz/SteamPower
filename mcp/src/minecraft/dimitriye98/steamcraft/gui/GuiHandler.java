package dimitriye98.steamcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import dimitriye98.steamcraft.tileentity.TileEntityBoiler;
import dimitriye98.steamcraft.tileentity.TileEntityForge;
import dimitriye98.steamcraft.tileentity.TileEntityResearchTable;

public class GuiHandler implements IGuiHandler
{
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return new ContainerForge(player.inventory, (TileEntityForge) tile_entity);
            case 1:
                return new ContainerBoiler(player.inventory, (TileEntityBoiler) tile_entity);
            case 2:
                return new ContainerResearchTable(player.inventory, (TileEntityResearchTable) tile_entity);
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity tile_entity = world.getBlockTileEntity(x, y, z);

        switch (id)
        {
            case 0:
                return new GuiForge(player.inventory, (TileEntityForge) tile_entity);
            case 1:
                return new GuiBoiler(player.inventory, (TileEntityBoiler) tile_entity);
            case 2:
                return new GuiResearchTable(player.inventory, (TileEntityResearchTable) tile_entity);
        }

        return null;
    }
}