package dimitriye98.steamcraft.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import buildcraft.core.DefaultProps;

import dimitriye98.steamcraft.common.SteamCraft;
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
    }

    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture(SteamCraft.guiLocation+"boiler.png");
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
            this.drawTexturedModalRect(var5 + 35, var6 + 25 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
        }

        var7 = this.furnaceInventory.getWater() +3;
        //System.out.println(var7);
        this.drawTexturedModalRect(var5 + 78, var6 + 14 + 35 - var7, 176, 49 - var7, 20, var7 + 2);
    }
    
	private void displayGauge(int j, int k, int line, int col, int squaled, int liquidId, int liquidMeta) {
		int liquidImgIndex = 0;

		if (liquidId <= 0)
			return;
		if (liquidId < Block.blocksList.length && Block.blocksList[liquidId] != null) {
			ForgeHooksClient.bindTexture(Block.blocksList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Block.blocksList[liquidId].blockIndexInTexture;
		} else if (Item.itemsList[liquidId] != null) {
			ForgeHooksClient.bindTexture(Item.itemsList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Item.itemsList[liquidId].getIconFromDamage(liquidMeta);
		} else
			return;

		int imgLine = liquidImgIndex / 16;
		int imgColumn = liquidImgIndex - imgLine * 16;

		int start = 0;

		while (true) {
			int x = 0;

			if (squaled > 16) {
				x = 16;
				squaled -= 16;
			} else {
				x = squaled;
				squaled = 0;
			}

			drawTexturedModalRect(j + col, k + line + 58 - x - start, imgColumn * 16, imgLine * 16 + (16 - x), 16, 16 - (16 - x));
			start = start + 16;

			if (x == 0 || squaled == 0) {
				break;
			}
		}

		int i = mc.renderEngine.getTexture(DefaultProps.TEXTURE_PATH_GUI + "/combustion_engine_gui.png");

		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(j + col, k + line, 176, 0, 16, 60);
	}
}