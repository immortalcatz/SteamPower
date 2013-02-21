package steamcraft.steamcraft.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.src.ModLoader;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.network.PacketDispatcher;

import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

public class GuiResearchTable extends GuiContainer
{
    private final TileEntityResearchTable furnaceInventory;

    public GuiResearchTable(InventoryPlayer par1InventoryPlayer, TileEntityResearchTable par2TileEntityResearchTable)
    {
        super(new ContainerResearchTable(par1InventoryPlayer, par2TileEntityResearchTable));
        this.furnaceInventory = par2TileEntityResearchTable;
    }

    @Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        this.fontRenderer.drawString(StatCollector.translateToLocal("ResearchTable"), 60, 6, 4210752);
        this.fontRenderer.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 96 + 2, 4210752);

    }

    @Override
	protected void actionPerformed(GuiButton guibutton)
    {
      if(guibutton.id == 1)
      {
    	  ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
          DataOutputStream outputStream = new DataOutputStream(bos);
          try
          {
              outputStream.writeByte(1);
              outputStream.writeInt(furnaceInventory.worldObj.provider.dimensionId);
              outputStream.writeInt(furnaceInventory.xCoord);
              outputStream.writeInt(furnaceInventory.yCoord);
              outputStream.writeInt(furnaceInventory.zCoord);
          }
          catch (Exception ex)
          {
              ex.printStackTrace();
          }

          Packet250CustomPayload packet = new Packet250CustomPayload();
          packet.channel = "SteamCraft";
          packet.data = bos.toByteArray();
          packet.length = bos.size();

          PacketDispatcher.sendPacketToServer(packet);

      }


    }

    @Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        int var4 = this.mc.renderEngine.getTexture("/dimitriye98/steamcraft/resources/gui/research.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.xSize) / 2;
        int var6 = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
        int var7;
        int var8;

        controlList.clear();
        controlList.add(new GuiButton(1, var5 + 54, var6 + 24, 61,20, "Research"));






    }
}