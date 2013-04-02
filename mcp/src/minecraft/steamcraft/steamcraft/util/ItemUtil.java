package steamcraft.steamcraft.util;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemUtil {
	public static boolean checkOreMatch(ItemStack item, OreStack ore) {
		return ((OreDictionary.getOreID(item) == OreStack.getOreID(ore)));
	}

	public static boolean checkOreMatch(ItemStack itemA, ItemStack itemB) {
		return ((OreDictionary.getOreID(itemA) == OreDictionary.getOreID(itemB)) && !(OreDictionary.getOreID(itemA) == -1));
	}

	public static boolean checkItemMatch(ItemStack itemA, ItemStack itemB) {
		return ((itemA.itemID == itemB.itemID) && ((itemA.getItem().isItemTool(itemA) && itemB.getItem().isItemTool(itemB)) || (itemA.getItemDamage() == itemB.getItemDamage())));
	}
}
