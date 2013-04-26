package steamcraft.steamcraft.event;

import java.util.EnumSet;

import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.SteamCraft;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;

import cpw.mods.fml.common.ITickHandler;
import cpw.mods.fml.common.TickType;

public class TickHandler implements ITickHandler {

	private boolean inUse = false;
	private boolean wasInUse = false;
	private float fov = 0;
	private float sensitivity = 0;

	@Override
	public void tickStart(EnumSet<TickType> type, Object... tickData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tickEnd(EnumSet<TickType> type, Object... tickData) {
		wasInUse = inUse;
		inUse = false;
		if(Minecraft.getMinecraft().thePlayer != null){
			ItemStack item = Minecraft.getMinecraft().thePlayer.inventory.getStackInSlot(Minecraft.getMinecraft().thePlayer.inventory.currentItem);
			if (item != null && item.itemID == SteamCraft.toolSpyglass.itemID) {
				if (Minecraft.getMinecraft().thePlayer.isUsingItem() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
					inUse = true;
					Minecraft.getMinecraft().gameSettings.fovSetting = -1.7F;
					Minecraft.getMinecraft().gameSettings.mouseSensitivity = 0.0F;
					this.renderTelescopeOverlay();
				}
			}
		}
		if (!inUse && !wasInUse) {
			fov = Minecraft.getMinecraft().gameSettings.fovSetting;
			sensitivity = Minecraft.getMinecraft().gameSettings.mouseSensitivity;
		}
		if (!inUse && wasInUse) {
			Minecraft.getMinecraft().gameSettings.fovSetting = fov;
			Minecraft.getMinecraft().gameSettings.mouseSensitivity = sensitivity;
		}

	}

	private void renderTelescopeOverlay() {
		GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        ScaledResolution var5 = new ScaledResolution(Minecraft.getMinecraft().gameSettings, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        int par1 = var5.getScaledWidth();
        int par2 = var5.getScaledHeight();
        int par3 = par1-par2;
		GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(SteamCraft.guiLocation + "spyglassblur.png"));
        Tessellator var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(par3/2, par2, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV((par3/2)+par2, par2, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV((par3/2)+par2, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(par3/2, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(SteamCraft.guiLocation + "spyglassfiller.png"));
        var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV(0, par2, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV(par3/2, par2, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV(par3/2, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV(0, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, Minecraft.getMinecraft().renderEngine.getTexture(SteamCraft.guiLocation + "spyglassfiller.png"));
        var3 = Tessellator.instance;
        var3.startDrawingQuads();
        var3.addVertexWithUV((par3/2)+par2, par2, -90.0D, 0.0D, 1.0D);
        var3.addVertexWithUV(par1, par2, -90.0D, 1.0D, 1.0D);
        var3.addVertexWithUV(par1, 0.0D, -90.0D, 1.0D, 0.0D);
        var3.addVertexWithUV((par3/2)+par2, 0.0D, -90.0D, 0.0D, 0.0D);
        var3.draw();

        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glPopAttrib();
	}

	@Override
	public EnumSet<TickType> ticks() {
		return EnumSet.of(TickType.RENDER);
	}

	@Override
	public String getLabel() {
		// TODO Auto-generated method stub
		return null;
	}

}
