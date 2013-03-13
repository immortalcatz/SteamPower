package steamcraft.steamcraft.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import steamcraft.steamcraft.research.ContainerResearchBook;
import steamcraft.steamcraft.research.GuiResearchBook;
import steamcraft.steamcraft.research.GuiScreenResearch;
import steamcraft.steamcraft.research.InventoryResearchBook;
import steamcraft.steamcraft.tileentity.TileEntityBoiler;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityForge;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
import cpw.mods.fml.common.network.IGuiHandler;

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
            case 5:
                return null;
            case 6:
            	return new ContainerResearchBook(player.inventory, player.inventory.mainInventory[player.inventory.currentItem], new InventoryResearchBook(player.inventory.mainInventory[player.inventory.currentItem]));
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
            case 6:
            	return new GuiResearchBook(player.inventory, player.inventory.mainInventory[player.inventory.currentItem]);

        }

        return null;
    }
}