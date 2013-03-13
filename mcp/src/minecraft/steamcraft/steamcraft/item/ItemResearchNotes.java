package steamcraft.steamcraft.item;

import java.util.List;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.research.ResearchDictionary;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.world.World;

public class ItemResearchNotes extends ItemSteamcraft {

	public ItemResearchNotes(int par1) {
		super(par1);
	}

	@Override
	public int getItemStackLimit() {
		return 1;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		par3EntityPlayer.openGui(SteamCraft.instance, 4, par2World, 0, 0, 0);
        return par1ItemStack;
    }

	@Override
	@SideOnly(Side.CLIENT)

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
        super.addInformation(par1ItemStack, par2EntityPlayer, par3List, par4);
        if (par1ItemStack.hasTagCompound())
        {
	        int complete = par1ItemStack.stackTagCompound.getCompoundTag("1").getInteger("Complete");
	        switch (complete) {
	        case 0:
	            par3List.add("U+00A7eIncomplete");
	            break;
	        case 1:
	            par3List.add(ResearchDictionary.getNameFromToken(par1ItemStack.stackTagCompound.getCompoundTag("1").getString("Research")));
	            break;
	        case 2:
	            par3List.add("U+00A74Failed");
	            break;
	        }
    	}
    }

	//so dimitriye you're probably wondering where the heck this code is.
	//it didn't work very well so go look in the research method in the tile entity

}
