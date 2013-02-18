package dimitriye98.steamcraft;

import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.RenderingRegistry;
public class ClientProxy extends CommonProxy
{
    @Override
    public void registerRenderers()
    {
        MinecraftForgeClient.preloadTexture(ITEMS_PNG);
        MinecraftForgeClient.preloadTexture(BLOCK_PNG);
        RenderingRegistry.registerEntityRenderingHandler(EntityMusketBall.class, new RenderBoiler(4));
        RenderingRegistry.registerEntityRenderingHandler(EntityMech.class, new RenderMech());
    }

}