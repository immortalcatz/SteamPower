package boiler;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockOre;
import net.minecraft.block.BlockOreStorage;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "BoilerMod", name = "BoilerMod", version = "1.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class BoilerMod
{
    @Instance("BoilerMod")//<<<Must be the same as your modid
    public static BoilerMod instance;

    private GuiHandler guiHandler = new GuiHandler();

//furnace
    public static Block yourFurnaceIdle;
    public static Block yourFurnaceActive;
    public static Item musket = (new ItemFirearm(7005, 20.0F, 84,0.1F, 5.0F)).setIconCoord(3, 0).setItemName("musket").setCreativeTab(CreativeTabs.tabCombat);
    public static Item pistol = (new ItemFirearm(7008, 15.0F, 42,0.3F, 2.0F)).setIconCoord(7, 0).setItemName("pistol").setCreativeTab(CreativeTabs.tabCombat);
    public static Item musketBall = (new ItemBoiler(7006)).setIconCoord(4, 0).setItemName("musketBall").setCreativeTab(CreativeTabs.tabCombat);
    public static Item itemMech = (new ItemMech(7009)).setIconCoord(4, 0).setItemName("itemMech");
    public static Item musketCartridge = (new ItemBoiler(7007)).setIconCoord(5, 0).setItemName("musketCartridge").setCreativeTab(CreativeTabs.tabCombat);
    public static Item ingotZinc = (new ItemBoiler(7001)).setIconCoord(1, 0).setItemName("ingotZinc").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotBrass = (new ItemBoiler(7002)).setIconCoord(2, 0).setItemName("ingotBrass").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotCopper = (new ItemBoiler(7000)).setIconCoord(0, 0).setItemName("ingotCopper").setCreativeTab(CreativeTabs.tabMaterials);
    public final static Block copperOre = new BlockBoilerOre(152, 0).setHardness(2.75F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("copperOre").setCreativeTab(CreativeTabs.tabBlock);
    public final static Block zincOre = new BlockBoilerOre(153, 1).setHardness(2.5F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("zincOre").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockCopper = new BlockBoilerStorage(154, 16).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockZinc = new BlockBoilerStorage(156, 17).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockBrass = new BlockBoilerStorage(155, 18).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockFurnaceExtension = new BlockFurnaceExtension(157, 4).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("furnaceExtension").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block blockForge = new BlockForge(158, 4, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForge").setTickRandomly(true);
    public static final Block blockForgeOn = new BlockForge(160, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeOn").setTickRandomly(true);
    public static final Block blockForgeMain = new BlockForgeMain(159, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeMain").setRequiresSelfNotify();

    @SidedProxy(clientSide = "boiler.ClientProxy", serverSide = "boiler.CommonProxy")
    public static CommonProxy proxy;

    @cpw.mods.fml.common.Mod.PreInit
    public void PreInit(FMLPreInitializationEvent event)
    {
		MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    @Init
    public void load(FMLInitializationEvent event)
    {
    	EntityRegistry.registerModEntity(boiler.EntityMusketBall.class, "MusketBall", 0, BoilerMod.instance, 100, 1, true);
    	EntityRegistry.registerModEntity(boiler.EntityMech.class, "Mech", 1, BoilerMod.instance, 100, 5, true);
        GameRegistry.registerWorldGenerator(new WorldGenBoiler());
//Furnaces
        yourFurnaceIdle = new BlockBoiler(150, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabMisc);
        yourFurnaceActive = new BlockBoiler(151, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify();
//RegFurnace
        GameRegistry.registerBlock(yourFurnaceIdle, "yourFurnaceIdle");
        GameRegistry.registerBlock(yourFurnaceActive, "yourFurnaceActive");
        LanguageRegistry.addName(yourFurnaceIdle, "YourFurnace Idle");
        GameRegistry.registerBlock(copperOre, "copperOre");
        LanguageRegistry.addName(copperOre, "Copper Ore");
        MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);
        GameRegistry.registerBlock(zincOre, "zincOre");
        LanguageRegistry.addName(zincOre, "Zinc Ore");
        MinecraftForge.setBlockHarvestLevel(zincOre, "pickaxe", 1);
        GameRegistry.registerBlock(blockCopper, "blockCopper");
        LanguageRegistry.addName(blockCopper, "Copper Block");
        MinecraftForge.setBlockHarvestLevel(blockCopper, "pickaxe", 1);
        GameRegistry.registerBlock(blockZinc, "blockZinc");
        LanguageRegistry.addName(blockZinc, "Zinc Block");
        MinecraftForge.setBlockHarvestLevel(blockZinc, "pickaxe", 1);
        GameRegistry.registerBlock(blockBrass, "blockBrass");
        LanguageRegistry.addName(blockBrass, "Brass Block");
        MinecraftForge.setBlockHarvestLevel(blockBrass, "pickaxe", 1);
        GameRegistry.registerBlock(blockFurnaceExtension, "blockFurnaceExtension");
        LanguageRegistry.addName(blockFurnaceExtension, "Furnace Extension");
        MinecraftForge.setBlockHarvestLevel(blockFurnaceExtension, "pickaxe", 0);
        GameRegistry.registerBlock(blockForge, "blockForge");
        LanguageRegistry.addName(blockForge, "blockForge");
        MinecraftForge.setBlockHarvestLevel(blockForge, "pickaxe", 0);
        GameRegistry.registerBlock(blockForgeOn, "blockForgeOn");
        LanguageRegistry.addName(blockForgeOn, "blockForgeOn");
        MinecraftForge.setBlockHarvestLevel(blockForgeOn, "pickaxe", 0);
        GameRegistry.registerBlock(blockForgeMain, "blockForgeMain");
        LanguageRegistry.addName(blockForgeMain, "blockForgeMain");
        MinecraftForge.setBlockHarvestLevel(blockForgeMain, "pickaxe", 0);
        LanguageRegistry.addName(ingotCopper, "Copper Ingot");
        LanguageRegistry.addName(ingotZinc, "Zinc Ingot");
        LanguageRegistry.addName(ingotBrass, "Brass Ingot");
        LanguageRegistry.addName(musket, "Flintlock Musket");
        LanguageRegistry.addName(pistol, "Flintlock Pistol");
        LanguageRegistry.addName(musketBall, "Musket Ball");
        LanguageRegistry.addName(itemMech, "Mech");
        LanguageRegistry.addName(musketCartridge, "Musket Cartridge");
//GameRegistry.addRecipe(new ItemStack(extruderIdle, 1), new Object[] {"BBB", "BBB", "BBB", 'B', this.blackIngot});
        //YourFurnaceRecipes.smelting().addSmelting(this.blackOre.blockID, new ItemStack(this.blackIngot), 0.1F);//Add recipes this way or in the recipe class
        NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
        GameRegistry.registerTileEntity(TileEntityBoiler.class, "tileEntityBoiler");
        GameRegistry.registerTileEntity(TileEntityForge.class, "tileEntityForge");
        proxy.registerRenderers();
        proxy.addRecipes();
    }

    @cpw.mods.fml.common.Mod.PostInit
    public void PostInit(FMLPostInitializationEvent event)
    {
    }
}