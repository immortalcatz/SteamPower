package steamcraft.steamcraft.gui;

import net.minecraft.block.Block;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityBoiler;


public class GuiBoiler extends GuiContainer {
	//TODO: Cleanup class with human readable names
	private final TileEntityBoiler boilerEntity;

	public GuiBoiler(InventoryPlayer par1InventoryPlayer, TileEntityBoiler par2TileEntityBoiler) {
		super(new ContainerBoiler(par1InventoryPlayer, par2TileEntityBoiler));
		this.boilerEntity = par2TileEntityBoiler;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		this.fontRenderer.drawString(StatCollector.translateToLocal("Boiler"), 8, 6, 4210752);
		this.fontRenderer.drawString(Integer.valueOf(boilerEntity.getWater()).toString(), 8, 24, 4210752);
		this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		int var4 = this.mc.renderEngine.getTexture(SteamCraft.guiLocation + "boiler_2.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(var4);
		int var5 = (this.width - this.xSize) / 2;
		int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
		int var7;
		int var8;

		if (this.boilerEntity.isBurning()) {
			var7 = this.boilerEntity.getBurnTimeRemainingScaled(12);
            this.drawTexturedModalRect(var5 + 35, var6 + 25 + 12 - var7, 176, 12 - var7, 14, var7 + 2);
		}

		if (this.boilerEntity.getScaledWater(58) > 0) {
			displayGauge(var5, var6, 19, 152, 176, 14, boilerEntity.getScaledWater(58), boilerEntity.getWaterID(), 0);
		}
	}

	private void displayGauge(int j, int k, int line, int col, int u, int v, int squaled, int liquidId, int liquidMeta) {
		int liquidImgIndex = 0;

		if (liquidId <= 0) {
			return;
		}
		if (liquidId < Block.blocksList.length && Block.blocksList[liquidId] != null) {
			System.out.println("Block");
			ForgeHooksClient.bindTexture(Block.blocksList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Block.blocksList[liquidId].blockIndexInTexture;
		} else if (Item.itemsList[liquidId] != null) {
			System.out.println("Item");
			ForgeHooksClient.bindTexture(Item.itemsList[liquidId].getTextureFile(), 0);
			liquidImgIndex = Item.itemsList[liquidId].getIconFromDamage(liquidMeta);
		} else {
			System.out.println("None");
			return;
		}

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

			drawTexturedModalRect(j + col, k + line + 58 - x - start,
					imgColumn * 16, imgLine * 16 + (16 - x), 16, 16 - (16 - x));
			start = start + 16;

			if (x == 0 || squaled == 0) {
				break;
			}
		}

		int i = mc.renderEngine.getTexture(SteamCraft.guiLocation + "boiler_2.png");

		mc.renderEngine.bindTexture(i);
		drawTexturedModalRect(j + col, k + line, u, v, 16, 60);
	}
}