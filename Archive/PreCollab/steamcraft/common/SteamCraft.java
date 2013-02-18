package dimitriye98.steamcraft.common;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PostInit;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;

import dimitriye98.steamcraft.block.*;

@Mod(modid="SteamCraftD98", name="SteamCraft", version="0.1.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)
public class SteamCraft {

        // The instance of your mod that Forge uses.
        @Instance("SteamCraft")
        public static SteamCraft instance;
        
        // Says where the client and server 'proxy' code is loaded.
        @SidedProxy(clientSide="dimitriye98.steamcraft.client.ClientProxy", serverSide="dimitriye98.steamcraft.common.CommonProxy")
        public static CommonProxy proxy;
        public static Block steam = new BlockSteam(3000);
        
        @PreInit
        public void preInit(FMLPreInitializationEvent event) {
                // Stub Method
        }
        
        @Init
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                GameRegistry.registerBlock(steam, "steam");
        }
        
        @PostInit
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
}