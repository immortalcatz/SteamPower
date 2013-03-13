package steamcraft.steamcraft.packet;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

import cpw.mods.fml.common.FMLCommonHandler;
// cpw.mods.fml.common.Side;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;

public class PacketHandler implements IPacketHandler
{
    @Override
    public void onPacketData(INetworkManager manager,
            Packet250CustomPayload packet, Player player)
    {
        if (packet.channel.equals("SteamCraft"))
        {
            handleResearch(packet);
        }
    }

    private void handleResearch(Packet250CustomPayload packet)
    {

        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(packet.data));
        byte packetType;
        int dimension;
        byte packetID;

        try
        {
            packetID = inputStream.readByte();
            dimension = inputStream.readInt();
            World world = DimensionManager.getWorld(dimension);

            if (packetID == 1)
            {
                int x = inputStream.readInt();
                int y = inputStream.readInt();
                int z = inputStream.readInt();
                TileEntity te = world.getBlockTileEntity(x, y, z);

                if (te instanceof TileEntityResearchTable)
                {
                    ((TileEntityResearchTable) te).research();
                }
            }
            if (packetID == 2)
            {
            	int bookmarkpage = inputStream.readInt();
            	String playerName = inputStream.readUTF();
            	EntityPlayer player = world.getPlayerEntityByName(playerName);
            	player.inventory.mainInventory[player.inventory.currentItem].stackTagCompound.setInteger("SelectedResearch", bookmarkpage);
            	System.out.println("Bookmarked page is " + Integer.toString(bookmarkpage));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return;
        }
    }
}