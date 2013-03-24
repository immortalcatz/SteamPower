package steamcraft.steamcraft.api.util;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAnonymous {
	private int itemID;
	private int metadata;
	private int amount;
	private int oreID;

	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	public int getMetadata() {
		return metadata;
	}

	public void setMetadata(int metadata) {
		this.metadata = metadata;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getOreID() {
		return this.oreID;
	}

	public void setOreID(int oreID) {
		this.oreID = oreID;
	}

	public ItemAnonymous() {
		super();
	}

	public ItemAnonymous(ItemStack stack) {
		itemID = stack.itemID;
		metadata = stack.getItemDamage();
		amount = stack.stackSize;

	}

	public boolean checkMatch(ItemStack stack) {
		if (stack.itemID == this.itemID) {
			if (Item.itemsList[this.itemID].isItemTool(stack) )
		}
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ItemAnonymous)) return false;
		ItemAnonymous nodeO = (ItemAnonymous)o;
		if ((nodeO.itemID == this.itemID && nodeO.metadata == this.metadata && nodeO.amount == this.amount) && nodeO.oreID == this.oreID) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return ((amount ^ itemID) ^ (metadata * 31)) ^ oreID;
	}
}
