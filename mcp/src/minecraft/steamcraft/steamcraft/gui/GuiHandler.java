package steamcraft.steamcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import steamcraft.steamcraft.research.GuiScreenResearch;
import steamcraft.steamcraft.tileentity.TileEntityBoiler;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityForge;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

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
            case 3:
                return new ContainerEngineeringTable(player.inventory, (TileEntityEngineeringTable) tile_entity);
            case 4:
                return null;
            case 5:
                return null;
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
            case 3:
                return new GuiEngineeringTable(player.inventory, (TileEntityEngineeringTable) tile_entity);
            case 4:
                return new GuiScreenResearch(player, player.inventory.mainInventory[player.inventory.currentItem], false);
            case 5:
                return new GuiScreenResearch(player, player.inventory.mainInventory[player.inventory.currentItem], true);

        }

        return null;
    }
}