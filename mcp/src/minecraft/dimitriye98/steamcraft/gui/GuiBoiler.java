package dimitriye98.steamcraft.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import dimitriye98.steamcraft.tileentity.TileEntityBoiler;

public class GuiBoiler extends GuiContainer
{
    private TileEntityBoiler furnaceInventory;

    public GuiBoiler(InventoryPlayer par1InventoryPlayer, TileEntityBoiler par2TileEntityBoiler)
    {
        super(new ContainerBoiler(par1InventoryPlayer, par2TileEntityBoiler));
        this.furnaceInventory = par2TileEntityBoiler;
    }

    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("Boiler"), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
        this.fontRenderer.drawString(Integer.toString(furnaceInventory.steam), 90, 12, 4210752);
        this.fontRenderer.drawString(Integer.toString(furnaceInventory.waterLoaded), 90, 18, 4210752);
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture("/boiler/boiler.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;
        int var8;

        if (this.furnaceInventory.isBurning())
        {
            var7 = this.furnaceInventory.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 56, var6 + 36 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.furnaceInventory.getWater() +3;
        var8 = this.furnaceInventory.getSteam() +3;
        //System.out.println(var7);
        this.drawTexturedModalRect(var5 + 78, var6 + 14 + 35 - var7, 176, 49 - var7, 20, var7 + 2);
        this.drawTexturedModalRect(var5 + 103, var6 + 14 + 35 - var8, 196, 49 - var8, 20, var8 + 2);
    }
}