package steamcraft.steamcraft.client;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.client.render.RenderBoiler;
import steamcraft.steamcraft.client.render.RenderMech;
import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.entity.EntityMech;
import steamcraft.steamcraft.entity.EntityMusketBall;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        MinecraftForgeClient.preloadTexture(SteamCraft.ITEMS_PNG);
        MinecraftForgeClient.preloadTexture(SteamCraft.BLOCKS_PNG);
        RenderingRegistry.registerEntityRenderingHandler(EntityMusketBall.class, new RenderBoiler(4));
        RenderingRegistry.registerEntityRenderingHandler(EntityMech.class, new RenderMech());
    }

}