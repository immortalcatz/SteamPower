package steamcraft.steamcraft;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.stats.Achievement;
import net.minecraftforge.common.AchievementPage;
import net.minecraftforge.common.EnumHelper;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import steamcraft.steamcraft.block.BlockBoiler;
import steamcraft.steamcraft.block.BlockForge;
import steamcraft.steamcraft.block.BlockForgeMain;
import steamcraft.steamcraft.block.BlockForgePiece;
import steamcraft.steamcraft.block.BlockResearchTable;
import steamcraft.steamcraft.block.BlockSteam;
import steamcraft.steamcraft.block.BlockSteamcraftOre;
import steamcraft.steamcraft.block.BlockSteamcraftStorage;
import steamcraft.steamcraft.common.CommonProxy;
import steamcraft.steamcraft.gui.GuiHandler;
import steamcraft.steamcraft.item.ItemFirearm;
import steamcraft.steamcraft.item.ItemMech;
import steamcraft.steamcraft.item.ItemResearchNotes;
import steamcraft.steamcraft.item.ItemSteamcraft;
import steamcraft.steamcraft.item.ItemSteamcraftArmor;
import steamcraft.steamcraft.item.ItemWrench;
import steamcraft.steamcraft.packet.PacketHandler;
import steamcraft.steamcraft.tileentity.TileEntityBoiler;
import steamcraft.steamcraft.tileentity.TileEntityForge;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
import steamcraft.steamcraft.worldgen.WorldGenBoiler;
import thaumcraft.api.EnumTag;
import thaumcraft.api.ObjectTags;
import thaumcraft.api.ThaumcraftApi;
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
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid = "SteamCraft", name = "SteamCraft", version = "0.0.1")
@NetworkMod(clientSideRequired=true, serverSideRequired=false,
channels={"SteamCraft"}, packetHandler = PacketHandler.class)
public class SteamCraft {

	// The instance of your mod that Forge uses.
	@Instance("SteamCraft")
	public static SteamCraft instance;

	//Texture Files
	public static String textureLocation = "/steamcraft/steamcraft/resources/";
	public static String ITEMS_PNG = textureLocation+"items.png";
	public static String BLOCKS_PNG =  textureLocation+"blocks.png";
	public static String armorLocation = textureLocation+"armor/";
	public static String guiLocation = textureLocation+"gui/";
	public static String modelTextureLocation = textureLocation+"model/";

	//Blocks
	public static Block boilerIdle;
	public static Block boilerActive;
	public static Block copperOre;
	public static Block zincOre;
	public static Block blockCopper;
	public static Block blockZinc;
	public static Block blockBrass;
	public static Block blockFurnaceExtension;
	public static Block blockForge;
	public static Block blockForgeOn;
	public static Block blockForgeMain;
	public static Block steam;
	public static Block researchTable;

	//Items
	public static Item musketBall;
	public static Item itemMech;
	public static Item musketCartridge;
	public static Item ingotZinc;
	public static Item ingotBrass;
	public static Item ingotCopper;
	public static Item stock;
	public static Item musketBarrel;
	public static Item blunderbussBarrel;
	public static Item flintlock;

	public static Item researchPaper;

		//Tools
	public static Item musket;
	public static Item pistol;
    public static Item blunderbuss;
	public static Item toolWrench;

		//Armor
	public static Item copperHelmet;
	public static Item copperTorso;
	public static Item copperLegs;
	public static Item copperBoots;

	public static Item brassHelmet;
	public static Item brassTorso;
	public static Item brassLegs;
	public static Item brassBoots;

		//Achievements
	public static Achievement AchResearch;
	public static Achievement AchBrass;
	public static AchievementPage steamAchievementPage;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "steamcraft.steamcraft.client.ClientProxy", serverSide = "steamcraft.steamcraft.common.CommonProxy")
	public static CommonProxy proxy;

	private final GuiHandler guiHandler = new GuiHandler();

	@PreInit
	public void preInit(FMLPreInitializationEvent event) {
		// Stub Method
	}

	@Init
	public void load(FMLInitializationEvent event) {
		//Initialize Blocks
		boilerIdle = new BlockBoiler(150, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabMisc);
		boilerActive = new BlockBoiler(151, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("extruder").setRequiresSelfNotify();
		blockCopper = new BlockSteamcraftStorage(154, 16).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockCopper").setCreativeTab(CreativeTabs.tabBlock);
		blockZinc = new BlockSteamcraftStorage(156, 17).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockZinc").setCreativeTab(CreativeTabs.tabBlock);
		blockBrass = new BlockSteamcraftStorage(155, 18).setHardness(5.0F).setResistance(10.0F).setStepSound(Block.soundMetalFootstep).setBlockName("blockBrass").setCreativeTab(CreativeTabs.tabBlock);
		blockFurnaceExtension = new BlockForgePiece(157, 4).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("furnaceExtension").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);
		blockForge = new BlockForge(158, 4, false).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForge").setTickRandomly(true);
		blockForgeOn = new BlockForge(160, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeOn").setTickRandomly(true);
		blockForgeMain = new BlockForgeMain(159, 4, true).setHardness(3.5F).setStepSound(Block.soundStoneFootstep).setBlockName("blockForgeMain").setRequiresSelfNotify();
		copperOre = new BlockSteamcraftOre(152, 0).setHardness(2.75F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("copperOre").setCreativeTab(CreativeTabs.tabBlock);
		zincOre = new BlockSteamcraftOre(153, 1).setHardness(2.5F).setResistance(15.0F).setStepSound(Block.soundStoneFootstep).setBlockName("zincOre").setCreativeTab(CreativeTabs.tabBlock);
		steam = new BlockSteam(165).setBlockName("blockSteam");
		researchTable = new BlockResearchTable(169).setHardness(2.0F).setStepSound(Block.soundWoodFootstep).setBlockName("researchTable").setRequiresSelfNotify().setCreativeTab(CreativeTabs.tabDecorations);


		//Initialize Items
		musketBall = (new ItemSteamcraft(7006)).setIconCoord(4, 0).setItemName("musketBall").setCreativeTab(CreativeTabs.tabCombat);
		itemMech = (new ItemMech(7009)).setIconCoord(4, 0).setItemName("itemMech");
		musketCartridge = (new ItemSteamcraft(7007)).setIconCoord(5, 0).setItemName("musketCartridge").setCreativeTab(CreativeTabs.tabCombat);
		ingotZinc = (new ItemSteamcraft(7001)).setIconCoord(1, 0).setItemName("ingotZinc").setCreativeTab(CreativeTabs.tabMaterials);
		ingotBrass = (new ItemSteamcraft(7002)).setIconCoord(2, 0).setItemName("ingotBrass").setCreativeTab(CreativeTabs.tabMaterials);
		ingotCopper = (new ItemSteamcraft(7000)).setIconCoord(0, 0).setItemName("ingotCopper").setCreativeTab(CreativeTabs.tabMaterials);

		researchPaper = (new ItemResearchNotes(7022)).setIconCoord(13, 0).setItemName("researchNotes").setCreativeTab(CreativeTabs.tabMisc);

		stock = (new ItemSteamcraft(7003).setIconIndex(9).setItemName("gunStock"));
		musketBarrel = (new ItemSteamcraft(7004).setIconIndex(10).setItemName("musketBarrel"));
		blunderbussBarrel = (new ItemSteamcraft(7020).setIconIndex(12).setItemName("blunderbussBarrel"));
		flintlock = (new ItemSteamcraft(7021).setIconIndex(11).setItemName("flintlock"));

			//Tools
		musket = (new ItemFirearm(7005, 20.0F, 84,0.1F, 5.0F, false)).setIconCoord(3, 0).setItemName("musket").setCreativeTab(CreativeTabs.tabCombat);
		pistol = (new ItemFirearm(7008, 15.0F, 42,0.3F, 2.0F, false)).setIconCoord(7, 0).setItemName("pistol").setCreativeTab(CreativeTabs.tabCombat);
		blunderbuss = (new ItemFirearm(7019, 25.0F, 95,3.5F, 7.5F, true)).setIconCoord(8, 0).setItemName("blunderbuss").setCreativeTab(CreativeTabs.tabCombat);
		toolWrench = (new ItemWrench(7010)).setIconCoord(6, 0).setItemName("toolWrench").setCreativeTab(CreativeTabs.tabTools);

			//Armor
		EnumArmorMaterial materialCopper = EnumHelper.addArmorMaterial("spCopper", 12, new int[]{2, 5, 4, 1}, 9);
		copperHelmet = (new ItemSteamcraftArmor(7011, materialCopper, 4, 0, false, armorLocation+"copper_1.png", armorLocation+"copper_2.png").setItemName("copperHelmet").setIconCoord(0, 1).setCreativeTab(CreativeTabs.tabCombat));
		copperTorso = (new ItemSteamcraftArmor(7012, materialCopper, 4, 1, false, armorLocation+"copper_1.png", armorLocation+"copper_2.png").setItemName("copperTorso").setIconCoord(0, 2).setCreativeTab(CreativeTabs.tabCombat));
		copperLegs = (new ItemSteamcraftArmor(7013,materialCopper, 4, 2, true, armorLocation+"copper_1.png", armorLocation+"copper_2.png").setItemName("copperLegs").setIconCoord(0, 3).setCreativeTab(CreativeTabs.tabCombat));
		copperBoots = (new ItemSteamcraftArmor(7014, materialCopper, 4, 3, false, armorLocation+"copper_1.png", armorLocation+"copper_2.png").setItemName("copperLegs").setIconCoord(0, 4).setCreativeTab(CreativeTabs.tabCombat));

		EnumArmorMaterial materialBrass = EnumHelper.addArmorMaterial("spBrass", 20, new int[]{2, 5, 4, 1}, 9);
		brassHelmet = (new ItemSteamcraftArmor(7015, materialBrass, 4, 0, false, armorLocation+"brass_1.png", armorLocation+"brass_2.png").setItemName("brassHelmet").setIconCoord(1, 1).setCreativeTab(CreativeTabs.tabCombat));
		brassTorso = (new ItemSteamcraftArmor(7016, materialBrass, 4, 1, false, armorLocation+"brass_1.png", armorLocation+"brass_2.png").setItemName("brassTorso").setIconCoord(1, 2).setCreativeTab(CreativeTabs.tabCombat));
		brassLegs = (new ItemSteamcraftArmor(7017,materialBrass, 4, 2, true, armorLocation+"brass_1.png", armorLocation+"brass_2.png").setItemName("brassLegs").setIconCoord(1, 3).setCreativeTab(CreativeTabs.tabCombat));
		brassBoots = (new ItemSteamcraftArmor(7018, materialBrass, 4, 3, false, armorLocation+"brass_1.png", armorLocation+"brass_2.png").setItemName("brassLegs").setIconCoord(1, 4).setCreativeTab(CreativeTabs.tabCombat));

		//Initialize Achievements
		AchResearch = new Achievement(2001, "AchResearch", 1, -2, SteamCraft.researchTable, null).registerAchievement();
		AchBrass  = new Achievement(2002, "AchBrass", 3, 0, SteamCraft.ingotBrass, AchResearch).registerAchievement();
		steamAchievementPage = new AchievementPage("SteamCraft", AchResearch, AchBrass);

		//Registration
		EntityRegistry.registerModEntity(steamcraft.steamcraft.entity.EntityMusketBall.class, "MusketBall", 0, SteamCraft.instance, 100, 1, true);
		EntityRegistry.registerModEntity(steamcraft.steamcraft.entity.EntityMech.class, "Mech", 1, SteamCraft.instance, 100, 5, true);

		GameRegistry.registerWorldGenerator(new WorldGenBoiler());

			//Blocks
		GameRegistry.registerBlock(researchTable, "researchTable"); //Research Table
		MinecraftForge.setBlockHarvestLevel(researchTable, "axe", 0);

		GameRegistry.registerBlock(boilerIdle, "boilerIdle"); //Inactive Boiler
		MinecraftForge.setBlockHarvestLevel(boilerIdle, "pickaxe", 0);

		GameRegistry.registerBlock(boilerActive, "boilerActive"); //Active Boiler
		MinecraftForge.setBlockHarvestLevel(boilerActive, "pickaxe", 0);

		GameRegistry.registerBlock(copperOre, "copperOre"); //Copper Ore
		MinecraftForge.setBlockHarvestLevel(copperOre, "pickaxe", 1);

		GameRegistry.registerBlock(zincOre, "zincOre"); //Zinc Ore
		MinecraftForge.setBlockHarvestLevel(zincOre, "pickaxe", 1);

		GameRegistry.registerBlock(blockCopper, "blockCopper"); //Copper Block
		MinecraftForge.setBlockHarvestLevel(blockCopper, "pickaxe", 1);

		GameRegistry.registerBlock(blockZinc, "blockZinc"); //Zinc Block
		MinecraftForge.setBlockHarvestLevel(blockZinc, "pickaxe", 1);

		GameRegistry.registerBlock(blockBrass, "blockBrass"); //Brass Block
		MinecraftForge.setBlockHarvestLevel(blockBrass, "pickaxe", 1);

		GameRegistry.registerBlock(blockFurnaceExtension, "blockFurnaceExtension"); //Furnace Extension (Forge piece)
		MinecraftForge.setBlockHarvestLevel(blockFurnaceExtension, "pickaxe", 0);

		GameRegistry.registerBlock(blockForge, "blockForge"); //Forge
		MinecraftForge.setBlockHarvestLevel(blockForge, "pickaxe", 0);

		GameRegistry.registerBlock(blockForgeOn, "blockForgeOn"); //Active Forge
		MinecraftForge.setBlockHarvestLevel(blockForgeOn, "pickaxe", 0);

		GameRegistry.registerBlock(blockForgeMain, "blockForgeMain"); //Forge Container Piece
		MinecraftForge.setBlockHarvestLevel(blockForgeMain, "pickaxe", 0);

		GameRegistry.registerBlock(steam, "steam"); //Research Table

				//Tile Entities
		GameRegistry.registerTileEntity(TileEntityBoiler.class, "tileEntityBoiler");
		GameRegistry.registerTileEntity(TileEntityForge.class, "tileEntityForge");
		GameRegistry.registerTileEntity(TileEntityResearchTable.class, "tileEntityResearchTable");

			//Names

				//Blocks
		LanguageRegistry.addName(boilerIdle, "Boiler");
		LanguageRegistry.addName(boilerActive, "Boiler Active");
		LanguageRegistry.addName(copperOre, "Copper Ore");
		LanguageRegistry.addName(zincOre, "Zinc Ore");
		LanguageRegistry.addName(blockCopper, "Copper Block");
		LanguageRegistry.addName(blockZinc, "Zinc Block");
		LanguageRegistry.addName(blockBrass, "Brass Block");
		LanguageRegistry.addName(blockFurnaceExtension, "Furnace Extension");
		LanguageRegistry.addName(blockForge, "blockForge");
		LanguageRegistry.addName(blockForgeOn, "blockForgeOn");
		LanguageRegistry.addName(blockForgeMain, "blockForgeMain");
		LanguageRegistry.addName(researchTable, "Allmighty Table of Research");

				//Items
		LanguageRegistry.addName(ingotCopper, "Copper Ingot");
		LanguageRegistry.addName(ingotZinc, "Zinc Ingot");
		LanguageRegistry.addName(ingotBrass, "Brass Ingot");
		LanguageRegistry.addName(musket, "Flintlock Musket");
		LanguageRegistry.addName(pistol, "Flintlock Pistol");
		LanguageRegistry.addName(blunderbuss, "Blunderbuss");
		LanguageRegistry.addName(musketBall, "Musket Ball");
		LanguageRegistry.addName(itemMech, "Mech");
		LanguageRegistry.addName(musketCartridge, "Musket Cartridge");
		LanguageRegistry.addName(toolWrench, "Brass Wrench");
		LanguageRegistry.addName(stock, "Gun Stock");
		LanguageRegistry.addName(musketBarrel, "Musket Barrel");
		LanguageRegistry.addName(blunderbussBarrel, "Blunderbuss Barrel");
		LanguageRegistry.addName(flintlock, "Flintlock");
		LanguageRegistry.addName(researchPaper, "Research");

		LanguageRegistry.addName(copperHelmet, "Copper Helmet");
		LanguageRegistry.addName(copperTorso, "Copper Chestplate");
		LanguageRegistry.addName(copperLegs, "Copper Leggings");
		LanguageRegistry.addName(copperBoots, "Copper Boots");

		LanguageRegistry.addName(brassHelmet, "Brass Helmet");
		LanguageRegistry.addName(brassTorso, "Brass Chestplate");
		LanguageRegistry.addName(brassLegs, "Brass Leggings");
		LanguageRegistry.addName(brassBoots, "Brass Boots");

			//Achievements
        this.addAchievementName("AchReseach", "Time to Research");
        this.addAchievementDesc("AchReseach", "You built a Time Machine!");

			//Ore Dictionary
		OreDictionary.registerOre("ingotCopper", new ItemStack(ingotCopper));
		OreDictionary.registerOre("ingotBrass", new ItemStack(ingotBrass));
		OreDictionary.registerOre("ingotZinc", new ItemStack(ingotZinc));
		OreDictionary.registerOre("oreCopper", new ItemStack(copperOre));
		OreDictionary.registerOre("oreZinc", new ItemStack(zincOre));

			//Networking
		NetworkRegistry.instance().registerGuiHandler(this, guiHandler);

			//Register Achievements
		AchievementPage.registerAchievementPage(steamAchievementPage);

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
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.stock, 1), "PPP", " PP", 'P', "plankWood"));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.musketBarrel, 1), "III", 'I', Item.ingotIron));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blunderbussBarrel, 1), "III", "III", 'I', "ingotBrass"));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.flintlock, 1), "F  ", "I  ", "III", 'I', Item.ingotIron, 'F', Item.flintAndSteel));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.musket, 1), "B  ", " BF", "  S", 'B', SteamCraft.musketBarrel, 'F', SteamCraft.flintlock, 'S', SteamCraft.stock));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.musket, 1), "B  ", " BF", " P ", 'B', SteamCraft.musketBarrel, 'F', SteamCraft.flintlock, 'P', "plankWood"));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(SteamCraft.blunderbuss, 1), "B  ", " BF", "  S", 'B', SteamCraft.blunderbussBarrel, 'F', SteamCraft.flintlock, 'S', SteamCraft.stock));
	}

	private void addAchievementName(String ach, String name)
	{
	        LanguageRegistry.instance().addStringLocalization("achievement." + ach, "en_US", name);
	}

	private void addAchievementDesc(String ach, String desc)
	{
	        LanguageRegistry.instance().addStringLocalization("achievement." + ach + ".desc", "en_US", desc);
	}

	@PostInit
	public void postInit(FMLPostInitializationEvent event) {
		ThaumcraftApi.registerObjectTag(SteamCraft.ingotZinc.itemID, -1, (new ObjectTags()).add(EnumTag.METAL, 6).add(EnumTag.HEAL, 2));
		ThaumcraftApi.registerObjectTag(SteamCraft.zincOre.blockID, -1, (new ObjectTags()).add(EnumTag.METAL, 5).add(EnumTag.HEAL, 1).add(EnumTag.ROCK, 2));

	}
}