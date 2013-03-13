package steamcraft.steamcraft.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.research.InventoryResearchBook;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemResearchBook extends ItemResearchNotes{
    public InventoryResearchBook inventory = new InventoryResearchBook();

	public ItemResearchBook(int par1) {
		super(par1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreated(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		par1ItemStack.setTagCompound(new NBTTagCompound());
		par1ItemStack.stackTagCompound.setInteger("SelectedResearch", 1);
		NBTTagCompound index = new NBTTagCompound();
		index.setInteger("Complete", 1);
		index.setString("Research", "basic");
		par1ItemStack.stackTagCompound.setTag("1", index);
    }

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
		if (par3EntityPlayer.isSneaking()) {
		if (!par3EntityPlayer.worldObj.isRemote) {
		par3EntityPlayer.openGui(SteamCraft.instance, 6, par2World, 0, 0, 0);
		}
		}
		else
		{
		par3EntityPlayer.openGui(SteamCraft.instance, 5, par2World, 0, 0, 0);
		}
        return par1ItemStack;
    }

	@Override
	@SideOnly(Side.CLIENT)

    public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
    {
    }


}
