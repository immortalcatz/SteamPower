package steamcraft.steamcraft.client;

import net.minecraftforge.client.MinecraftForgeClient;
import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.client.render.EngineeringTableRender;
import steamcraft.steamcraft.client.render.RenderBoiler;
import steamcraft.steamcraft.client.render.RenderMech;
import steamcraft.steamcraft.client.render.ResearchTableRender;
import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.entity.EntityMech;
import steamcraft.steamcraft.entity.EntityMusketBall;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        MinecraftForgeClient.preloadTexture(SteamCraft.ITEMS_PNG);
        MinecraftForgeClient.preloadTexture(SteamCraft.BLOCKS_PNG);
        MinecraftForgeClient.preloadTexture(SteamCraft.modelTextureLocation + "researchtable.png");
        RenderingRegistry.registerEntityRenderingHandler(EntityMusketBall.class, new RenderBoiler(4));
        RenderingRegistry.registerEntityRenderingHandler(EntityMech.class, new RenderMech());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityResearchTable.class, new ResearchTableRender());
        RenderingRegistry.registerBlockHandler(new ResearchTableRender());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEngineeringTable.class, new EngineeringTableRender());
        RenderingRegistry.registerBlockHandler(new EngineeringTableRender());
    }

}