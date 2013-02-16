package boiler;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.entity.RenderBoat;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
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