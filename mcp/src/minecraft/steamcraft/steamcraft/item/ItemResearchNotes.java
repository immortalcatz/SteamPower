package steamcraft.steamcraft.item;

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

	//so dimitriye you're probably wondering where the heck this code is.
	//it didn't work very well so go look in the research method in the tile entity

}
