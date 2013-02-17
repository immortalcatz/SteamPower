package boiler;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
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
import net.minecraftforge.common.EnumHelper;

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
    public static Item toolWrench = (new ItemWrench(7010)).setIconCoord(6, 0).setItemName("toolWrench").setCreativeTab(CreativeTabs.tabTools);
    public final static Block copperOre = new BlockBoilerOre(152, 0).setHardness(2.75F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("copperOre").setCreativeTab(CreativeTabs.tabBlock);
    public final static Block zincOre = new BlockBoilerOre(153, 1).setHardness(2.5F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("zincOre").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockCopper = new BlockBoilerStorage(154, 16).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockZinc = new BlockBoilerStorage(156, 17).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockBrass = new BlockBoilerStorage(155, 18).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockFurnaceExtension = new BlockFurnaceExtension(157, 4).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("furnaceExtension").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block blockForge = new BlockForge(158, 4, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForge").setTickRandomly(true);
    public static final Block blockForgeOn = new BlockForge(160, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeOn").setTickRandomly(true);
    public static final Block blockForgeMain = new BlockForgeMain(159, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeMain").setRequiresSelfNotify();
    
    public static Item copperHelmet;
    public static Item copperTorso;
    public static Item copperLegs;
    public static Item copperBoots;

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
    	EnumArmorMaterial materialCopper = EnumHelper.addArmorMaterial("spCopper", 12, new int[]{2, 5, 4, 1}, 9);
    	copperHelmet = (new ItemBoilerArmor(7011, materialCopper, 4, 0, false, "/boiler/armor/copper_1.png", "/boiler/armor/copper_2.png").setItemName("copperHelmet").setIconCoord(0, 1).setCreativeTab(CreativeTabs.tabCombat));
        copperTorso = (new ItemBoilerArmor(7012, materialCopper, 4, 1, false, "/boiler/armor/copper_1.png", "/boiler/armor/copper_2.png").setItemName("copperTorso").setIconCoord(0, 2).setCreativeTab(CreativeTabs.tabCombat));
        copperLegs = (new ItemBoilerArmor(7013,materialCopper, 4, 2, true, "/boiler/armor/copper_1.png", "/boiler/armor/copper_2.png").setItemName("copperLegs").setIconCoord(0, 3).setCreativeTab(CreativeTabs.tabCombat));
        copperBoots = (new ItemBoilerArmor(7014, materialCopper, 4, 3, false, "/boiler/armor/copper_1.png", "/boiler/armor/copper_2.png").setItemName("copperLegs").setIconCoord(0, 4).setCreativeTab(CreativeTabs.tabCombat));
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
        LanguageRegistry.addName(toolWrench, "Brass Wrench");
        
        LanguageRegistry.addName(copperHelmet, "Copper Helmet");
        LanguageRegistry.addName(copperTorso, "Copper Chestplate");
        LanguageRegistry.addName(copperLegs, "Copper Leggings");
        LanguageRegistry.addName(copperBoots, "Copper Boots");
        
        
        OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
        OreDictionary.registerOre("ingotBrass", new ItemStack(ingotBrass));
        OreDictionary.registerOre("ingotZinc", new ItemStack(ingotZinc));
        OreDictionary.registerOre("oreCopper", new ItemStack(copperOre));
        OreDictionary.registerOre("oreZinc", new ItemStack(zincOre));
//GameRegistry.addRecipe(new ItemStack(extruderIdle, 1), new Object[] {"BBB", "BBB", "BBB", 'B', this.blackIngot});
        //YourFurnaceRecipes.smelting().addSmelting(this.blackOre.blockID, new ItemStack(this.blackIngot), 0.1F);//Add recipes this way or in the recipe class
        NetworkRegistry.instance().registerGuiHandler(this, guiHandler);
        GameRegistry.registerTileEntity(TileEntityBoiler.class, "tileEntityBoiler");
        GameRegistry.registerTileEntity(TileEntityForge.class, "tileEntityForge");
        proxy.registerRenderers();
        addRecipes();
    }
    
    public void addRecipes()
    {
    	GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.musketBall, 1, 0), Block.cobblestone);
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.musketCartridge, 1, 0), Item.gunpowder, Item.paper, BoilerMod.musketBall);
        GameRegistry.addSmelting(BoilerMod.copperOre.blockID, new ItemStack(BoilerMod.ingotCopper), 0.5F);
        GameRegistry.addSmelting(BoilerMod.zincOre.blockID, new ItemStack(BoilerMod.ingotZinc), 0.5F);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BoilerMod.blockCopper, 1), "XXX", "XXX", "XXX", 'X', "ingotCopper"));
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotCopper, 9, 0), BoilerMod.blockCopper);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BoilerMod.blockZinc, 1), "XXX", "XXX", "XXX", 'X', "ingotZinc"));

        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotZinc, 9, 0), blockZinc);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BoilerMod.blockBrass, 1), "XXX", "XXX", "XXX", 'X', "ingotBrass"));
        GameRegistry.addShapelessRecipe(new ItemStack(BoilerMod.ingotBrass, 9, 0), blockBrass);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(BoilerMod.blockFurnaceExtension, 1), "XFX", "FYF", "XFX", 'X', Block.cobblestone, 'F', "ingotCopper", 'Y', Item.ingotIron));
    }

    @cpw.mods.fml.common.Mod.PostInit
    public void PostInit(FMLPostInitializationEvent event)
    {
    }
}