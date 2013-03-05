package steamcraft.steamcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class GuiEngineeringTable extends GuiContainer
{
	private final TileEntityEngineeringTable furnaceInventory;
    public GuiEngineeringTable(InventoryPlayer par1InventoryPlayer, TileEntityEngineeringTable par2TileEntityEngineeringTable)
    {
        super(new ContainerEngineeringTable(par1InventoryPlayer, par2TileEntityEngineeringTable));
        this.furnaceInventory = par2TileEntityEngineeringTable;
    }
    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    @Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.crafting"), 28, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    @Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture(SteamCraft.guiLocation + "engineering.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
    }
}
