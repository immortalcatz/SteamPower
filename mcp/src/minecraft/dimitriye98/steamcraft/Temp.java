package dimitriye98.steamcraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
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

import dimitriye98.steamcraft.block.BlockBoiler;
import dimitriye98.steamcraft.block.BlockForge;
import dimitriye98.steamcraft.block.BlockForgeMain;
import dimitriye98.steamcraft.block.BlockForgePiece;
import dimitriye98.steamcraft.block.BlockSteamcraftStorage;
import dimitriye98.steamcraft.common.CommonProxy;
import dimitriye98.steamcraft.gui.GuiHandler;
import dimitriye98.steamcraft.item.ItemBoiler;
import dimitriye98.steamcraft.item.ItemFirearm;
import dimitriye98.steamcraft.item.ItemMech;
import dimitriye98.steamcraft.item.ItemWrench;

@Mod(modid = "SteamCraft", name = "SteamCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)
public class SteamCraft {
	
	// The instance of your mod that Forge uses.
	@Instance("SteamCraft")
	public static SteamCraft instance;
	
	//Texture Files
	public static String textureLocation = "/dimitriye98/steamcraft/resources/";
	public static String ITEMS_PNG = textureLocation+"items.png";
	
	//Blocks
	public static final Block yourFurnaceIdle = new BlockBoiler(150, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabMisc);
	public static final Block yourFurnaceActive = new BlockBoiler(151, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify();
	public static final Block blockCopper = new BlockSteamcraftStorage(154, 16).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block blockZinc = new BlockSteamcraftStorage(156, 17).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block blockBrass = new BlockSteamcraftStorage(155, 18).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
	public static final Block blockFurnaceExtension = new BlockForgePiece(157, 4).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("furnaceExtension").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
	public static final Block blockForge = new BlockForge(158, 4, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForge").setTickRandomly(true);
	public static final Block blockForgeOn = new BlockForge(160, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeOn").setTickRandomly(true);
	public static final Block blockForgeMain = new BlockForgeMain(159, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeMain").setRequiresSelfNotify();
	
	//Items
	public static final Item musketBall = (new ItemBoiler(7006)).setIconCoord(4, 0).setItemName("musketBall").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item itemMech = (new ItemMech(7009)).setIconCoord(4, 0).setItemName("itemMech");
	public static final Item musketCartridge = (new ItemBoiler(7007)).setIconCoord(5, 0).setItemName("musketCartridge").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item ingotZinc = (new ItemBoiler(7001)).setIconCoord(1, 0).setItemName("ingotZinc").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item ingotBrass = (new ItemBoiler(7002)).setIconCoord(2, 0).setItemName("ingotBrass").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item ingotCopper = (new ItemBoiler(7000)).setIconCoord(0, 0).setItemName("ingotCopper").setCreativeTab(CreativeTabs.tabMaterials);
	
		//Tools
	public static final Item musket = (new ItemFirearm(7005, 20.0F, 84,0.1F, 5.0F, false)).setIconCoord(3, 0).setItemName("musket").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item pistol = (new ItemFirearm(7008, 15.0F, 42,0.3F, 2.0F, false)).setIconCoord(7, 0).setItemName("pistol").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item toolWrench = (new ItemWrench(7010)).setIconCoord(6, 0).setItemName("toolWrench").setCreativeTab(CreativeTabs.tabTools);
	
		//Armor
	public static final Item copperHelmet;
	public static final Item copperTorso;
	public static final Item copperLegs;
	public static final Item copperBoots;
	
	public static final Item brassHelmet;
	public static final Item brassTorso;
	public static final Item brassLegs;
	public static final Item brassBoots;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "dimitriye98.steamcraft.client.ClientProxy", serverSide = "dimitriye98.steamcraft.client.CommonProxy")
	public static CommonProxy proxy;
	
	private GuiHandler guiHandler = new GuiHandler();
	
	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}
	
	@Init
	public void load(FMLInitializationEvent event) {
		proxy.registerRenderers();
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		// Stub Method
	}
}