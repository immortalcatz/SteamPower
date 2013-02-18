package dimitriye98.steamcraft;

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
import dimitriye98.steamcraft.block.BlockBoiler;
import dimitriye98.steamcraft.block.BlockSteamcraftOre;
import dimitriye98.steamcraft.block.BlockSteamcraftStorage;
import dimitriye98.steamcraft.block.BlockForge;
import dimitriye98.steamcraft.block.BlockForgeMain;
import dimitriye98.steamcraft.block.BlockForgePiece;
import dimitriye98.steamcraft.common.CommonProxy;
import dimitriye98.steamcraft.event.EventHandler;
import dimitriye98.steamcraft.gui.GuiHandler;
import dimitriye98.steamcraft.item.ItemSteamcraft;
import dimitriye98.steamcraft.item.ItemSteamcraftArmor;
import dimitriye98.steamcraft.item.ItemFirearm;
import dimitriye98.steamcraft.item.ItemMech;
import dimitriye98.steamcraft.item.ItemWrench;
import dimitriye98.steamcraft.tileentity.TileEntityBoiler;
import dimitriye98.steamcraft.tileentity.TileEntityForge;
import dimitriye98.steamcraft.worldgen.WorldGenBoiler;
import net.minecraftforge.common.EnumHelper;

@Mod(modid = "SteamCraft", name = "SteamCraft", version = "0.0.1")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class SteamCraft
{
    @Instance("SteamCraft")//<<<Must be the same as your modid
    public static SteamCraft instance;

    private GuiHandler guiHandler = new GuiHandler();

//furnace
    public static Block yourFurnaceIdle;
    public static Block yourFurnaceActive;
    public static Item musket = (new ItemFirearm(7005, 20.0F, 84,0.1F, 5.0F, false)).setIconCoord(3, 0).setItemName("musket").setCreativeTab(CreativeTabs.tabCombat);
    public static Item pistol = (new ItemFirearm(7008, 15.0F, 42,0.3F, 2.0F, false)).setIconCoord(7, 0).setItemName("pistol").setCreativeTab(CreativeTabs.tabCombat);
    public static Item blunderbuss = (new ItemFirearm(7019, 20.0F, 84,3.5F, 15.0F, true)).setIconCoord(8, 0).setItemName("blunderbuss").setCreativeTab(CreativeTabs.tabCombat);
    public static Item musketBall = (new ItemSteamcraft(7006)).setIconCoord(4, 0).setItemName("musketBall").setCreativeTab(CreativeTabs.tabCombat);
    public static Item itemMech = (new ItemMech(7009)).setIconCoord(4, 0).setItemName("itemMech");
    public static Item musketCartridge = (new ItemSteamcraft(7007)).setIconCoord(5, 0).setItemName("musketCartridge").setCreativeTab(CreativeTabs.tabCombat);
    public static Item ingotZinc = (new ItemSteamcraft(7001)).setIconCoord(1, 0).setItemName("ingotZinc").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotBrass = (new ItemSteamcraft(7002)).setIconCoord(2, 0).setItemName("ingotBrass").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item ingotCopper = (new ItemSteamcraft(7000)).setIconCoord(0, 0).setItemName("ingotCopper").setCreativeTab(CreativeTabs.tabMaterials);
    public static Item toolWrench = (new ItemWrench(7010)).setIconCoord(6, 0).setItemName("toolWrench").setCreativeTab(CreativeTabs.tabTools);
    public final static Block copperOre = new BlockSteamcraftOre(152, 0).setHardness(2.75F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("copperOre").setCreativeTab(CreativeTabs.tabBlock);
    public final static Block zincOre = new BlockSteamcraftOre(153, 1).setHardness(2.5F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("zincOre").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockCopper = new BlockSteamcraftStorage(154, 16).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockZinc = new BlockSteamcraftStorage(156, 17).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockBrass = new BlockSteamcraftStorage(155, 18).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
    public static final Block blockFurnaceExtension = new BlockForgePiece(157, 4).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("furnaceExtension").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
    public static final Block blockForge = new BlockForge(158, 4, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForge").setTickRandomly(true);
    public static final Block blockForgeOn = new BlockForge(160, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeOn").setTickRandomly(true);
    public static final Block blockForgeMain = new BlockForgeMain(159, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeMain").setRequiresSelfNotify();
    
    public static Item copperHelmet;
    public static Item copperTorso;
    public static Item copperLegs;
    public static Item copperBoots;

    public static Item brassHelmet;
    public static Item brassTorso;
    public static Item brassLegs;
    public static Item brassBoots;
    
    @SidedProxy(clientSide = "dimitriye98.steamcraft.client.ClientProxy", serverSide = "dimitriye98.steamcraft.client.CommonProxy")
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
    	copperHelmet = (new ItemSteamcraftArmor(7011, materialCopper, 4, 0, false, "/steamcraft/resources/armor/copper_1.png", "/steamcraft/resources/armor/copper_2.png").setItemName("copperHelmet").setIconCoord(0, 1).setCreativeTab(CreativeTabs.tabCombat));
        copperTorso = (new ItemSteamcraftArmor(7012, materialCopper, 4, 1, false, "/steamcraft/resources/armor/copper_1.png", "/steamcraft/resources/armor/copper_2.png").setItemName("copperTorso").setIconCoord(0, 2).setCreativeTab(CreativeTabs.tabCombat));
        copperLegs = (new ItemSteamcraftArmor(7013,materialCopper, 4, 2, true, "/steamcraft/resources/armor/copper_1.png", "/steamcraft/resources/armor/copper_2.png").setItemName("copperLegs").setIconCoord(0, 3).setCreativeTab(CreativeTabs.tabCombat));
        copperBoots = (new ItemSteamcraftArmor(7014, materialCopper, 4, 3, false, "/steamcraft/resources/armor/copper_1.png", "/steamcraft/resources/armor/copper_2.png").setItemName("copperLegs").setIconCoord(0, 4).setCreativeTab(CreativeTabs.tabCombat));
        
       // IRON(15, new int[]{2, 6, 5, 2}, 9),
        EnumArmorMaterial materialBrass = EnumHelper.addArmorMaterial("spBrass", 20, new int[]{2, 5, 4, 1}, 9);
    	brassHelmet = (new ItemSteamcraftArmor(7015, materialBrass, 4, 0, false, "/steamcraft/resources/armor/brass_1.png", "/steamcraft/resources/armor/brass_2.png").setItemName("brassHelmet").setIconCoord(1, 1).setCreativeTab(CreativeTabs.tabCombat));
        brassTorso = (new ItemSteamcraftArmor(7016, materialBrass, 4, 1, false, "/steamcraft/resources/armor/brass_1.png", "/steamcraft/resources/armor/brass_2.png").setItemName("brassTorso").setIconCoord(1, 2).setCreativeTab(CreativeTabs.tabCombat));
        brassLegs = (new ItemSteamcraftArmor(7017,materialBrass, 4, 2, true, "/steamcraft/resources/armor/brass_1.png", "/steamcraft/resources/armor/brass_2.png").setItemName("brassLegs").setIconCoord(1, 3).setCreativeTab(CreativeTabs.tabCombat));
        brassBoots = (new ItemSteamcraftArmor(7018, materialBrass, 4, 3, false, "/steamcraft/resources/armor/brass_1.png", "/steamcraft/resources/armor/brass_2.png").setItemName("brassLegs").setIconCoord(1, 4).setCreativeTab(CreativeTabs.tabCombat));
        
    	EntityRegistry.registerModEntity(dimitriye98.steamcraft.entity.EntityMusketBall.class, "MusketBall", 0, SteamCraft.instance, 100, 1, true);
    	EntityRegistry.registerModEntity(dimitriye98.steamcraft.entity.EntityMech.class, "Mech", 1, SteamCraft.instance, 100, 5, true);
        GameRegistry.registerWorldGenerator(new WorldGenBoiler());
//Furnaces
        yourFurnaceIdle = new BlockBoiler(150, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabMisc);
        yourFurnaceActive = new BlockBoiler(151, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify();
//RegFurnace
        GameRegistry.registerBlock(yourFurnaceIdle, "yourFurnaceIdle");
        MinecraftForge.setBlockHarvestLevel(yourFurnaceIdle, "pickaxe", 0);
        GameRegistry.registerBlock(yourFurnaceActive, "yourFurnaceActive");
        MinecraftForge.setBlockHarvestLevel(yourFurnaceActive, "pickaxe", 0);
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
        
        LanguageRegistry.addName(brassHelmet, "Brass Helmet");
        LanguageRegistry.addName(brassTorso, "Brass Chestplate");
        LanguageRegistry.addName(brassLegs, "Brass Leggings");
        LanguageRegistry.addName(brassBoots, "Brass Boots");
        
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
    	GameRegistry.addShapelessRecipe(new ItemStack(SteamCraft.musketBall, 1, 0), Block.cobblestone);
        GameRegistry.addShapelessRecipe(new ItemStack(SteamCraft.musketCartridge, 1, 0), Item.gunpowder, Item.paper, SteamCraft.musketBall);
        GameRegistry.addSmelting(SteamCraft.copperOre.blockID, new ItemStack(SteamCraft.ingotCopper), 0.5F);
        GameRegistry.addSmelting(SteamCraft.zincOre.blockID, new ItemStack(SteamCraft.ingotZinc), 0.5F);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blockCopper, 1), "XXX", "XXX", "XXX", 'X', "ingotCopper"));
        GameRegistry.addShapelessRecipe(new ItemStack(SteamCraft.ingotCopper, 9, 0), SteamCraft.blockCopper);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blockZinc, 1), "XXX", "XXX", "XXX", 'X', "ingotZinc"));

        GameRegistry.addShapelessRecipe(new ItemStack(SteamCraft.ingotZinc, 9, 0), blockZinc);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blockBrass, 1), "XXX", "XXX", "XXX", 'X', "ingotBrass"));
        GameRegistry.addShapelessRecipe(new ItemStack(SteamCraft.ingotBrass, 9, 0), blockBrass);
        CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blockFurnaceExtension, 1), "XFX", "FYF", "XFX", 'X', Block.cobblestone, 'F', "ingotCopper", 'Y', Item.ingotIron));
    }

    @cpw.mods.fml.common.Mod.PostInit
    public void PostInit(FMLPostInitializationEvent event)
    {
    }
}