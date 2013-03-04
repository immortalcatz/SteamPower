package steamcraft.steamcraft.api.util;

import net.minecraft.item.ItemStack;

public class ItemAnonymous {
	private int itemID;
	private int metadata;
	private int amount;

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

	public ItemAnonymous() {
		super();
	}

	public ItemAnonymous(ItemStack stack) {
		itemID = stack.itemID;
		metadata = stack.getItemDamage();
		amount = stack.stackSize;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof ItemAnonymous)) return false;
		ItemAnonymous nodeO = (ItemAnonymous)o;
		if (nodeO.itemID == this.itemID && nodeO.metadata == this.metadata && nodeO.amount == this.amount) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return (amount ^ itemID) ^ (metadata * 31);
	}
}
