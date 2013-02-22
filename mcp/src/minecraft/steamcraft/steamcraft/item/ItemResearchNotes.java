package dimitriye98.steamcraft.item;

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
	
	public void onCreated(ItemStack notes, World planetEarth, EntityPlayer yoursTruly) {
		//NBTTagList items = new NBTTagList();
		//notes.stackTagCompound.setTag("Contents", items);
	}
	

	
	public void addItem(ItemStack notes, ItemStack researched, boolean successful) {
		NBTTagCompound addition = new NBTTagCompound();
		researched.writeToNBT(addition);
		notes.stackTagCompound.getTagList("Contents").appendTag(addition);
	}

}
