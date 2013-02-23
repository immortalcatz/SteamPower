package dimitriye98.steamcraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import dimitriye98.steamcraft.client.render.RenderBoiler;
import dimitriye98.steamcraft.client.render.RenderMech;
import dimitriye98.steamcraft.client.render.ResearchTableRender;
import dimitriye98.steamcraft.common.CommonProxy;
import dimitriye98.steamcraft.common.SteamCraft;
import dimitriye98.steamcraft.entity.EntityMech;
import dimitriye98.steamcraft.entity.EntityMusketBall;
import dimitriye98.steamcraft.tileentity.TileEntityResearchTable;
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        MinecraftForgeClient.preloadTexture(SteamCraft.ITEMS_PNG);
        MinecraftForgeClient.preloadTexture(SteamCraft.BLOCKS_PNG);
        RenderingRegistry.registerEntityRenderingHandler(EntityMusketBall.class, new RenderBoiler(4));
        RenderingRegistry.registerEntityRenderingHandler(EntityMech.class, new RenderMech());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityResearchTable.class, new ResearchTableRender());

    }

}