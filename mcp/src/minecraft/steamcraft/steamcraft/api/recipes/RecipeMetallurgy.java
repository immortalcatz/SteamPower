package steamcraft.steamcraft.api.recipes;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import steamcraft.steamcraft.api.util.ItemAnonymous;
import steamcraft.steamcraft.api.util.Pair;

import net.minecraft.item.ItemStack;

public class RecipeMetallurgy {

	private final HashSet<ItemAnonymous> recipe;
	private final ItemStack result;
	private final float xp;

	/**
	 * The constructor
	 * @param recipe a collection of ItemStacks representing the recipe
	 * @param result the result of this recipe
	 * @param xp the amount of XP smelting the recipe gives
	 */
	public RecipeMetallurgy(Collection<ItemStack> recipe, ItemStack result, float xp) {
		HashSet<ItemAnonymous> nodes = new HashSet();
		for (ItemStack item : recipe) {
			boolean flag = false;
			for (ItemAnonymous node : nodes) {
				if (node.getItemID() == item.itemID && node.getMetadata() == item.getItemDamage()) {
					node.setAmount(node.getAmount() + item.stackSize);
					flag = true;
					break;
				}
			}
			if (!flag) {
				ItemAnonymous node = new ItemAnonymous(item);
			}
		}
		this.recipe = nodes;
		this.result = result;
		this.xp = xp;
	}

	public boolean canSmelt(Collection<ItemStack> stuff) {
		HashMap<Pair<Integer, Integer>, Integer> checkMap = new HashMap();
		for (ItemStack item : stuff) {
			Pair key = new Pair(item.itemID, item.getItemDamage());
			if (checkMap.containsKey(key)) {
				checkMap.put(key, checkMap.get(key));
			}
		}
		boolean out = true;
		for (ItemAnonymous node : recipe) {
			boolean flag = false;
			Pair<Integer, Integer> key = new Pair(Integer.valueOf(node.getItemID()), Integer.valueOf(node.getMetadata()));
			if (checkMap.containsKey(key)) {
				int amount = checkMap.get(key);
				if (amount >= node.getAmount()) {
					flag = true;
				}
			}
			if (!flag) {
				out = false;
				break;
			}
		}
		return out;
	}

	public Set<ItemStack> getLeftovers(Collection<ItemStack> stuff) {
		if (!canSmelt(stuff)) return null;
		HashMap<Pair<Integer, Integer>, Integer> checkMap = new HashMap();
		for (ItemStack item : stuff) {
			Pair key = new Pair(item.itemID, item.getItemDamage());
			if (checkMap.containsKey(key)) {
				checkMap.put(key, checkMap.get(key));
			}
		}
		HashSet<ItemAnonymous> nodeOut = new HashSet();
		for (ItemAnonymous node : recipe) {
			ItemAnonymous newNode = new ItemAnonymous();
			newNode.setItemID(node.getItemID());
			newNode.setMetadata(node.getMetadata());
			newNode.setAmount(checkMap.get(new Pair(node.getItemID(), node.getMetadata())) - node.getAmount());
		}
		HashSet<ItemStack> out = new HashSet(stuff);
		for (ItemStack stack : out) {
			for (ItemAnonymous node : nodeOut) {
				if (node.getItemID() == stack.itemID && node.getMetadata() == stack.getItemDamage()) {
					stack.stackSize = node.getAmount();
					nodeOut.remove(node);
					break;
				}
			}
		}
		return out;
	}

	public ItemStack getResult() {
		return this.result;
	}

	public float getXP() {
		return this.xp;
	}

}
