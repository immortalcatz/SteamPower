package steamcraft.steamcraft.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.minecraftforge.oredict.OreDictionary;

public class OreStack {
	private String material;
	private int amount;

	/**
	 * A getter for the material of the OreStack.
	 * @return the material of the OreStack.
	 */
	public String getMaterial() {
		return material;
	}

	/**
	 * A getter for the material of the OreStack.
	 * @return the material of the OreStack.
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * A setter for the amount of the OreStack.
	 * @param amount the desired amount for the OreStack
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * A utility function that can be used to retrieve the ore ID of the given OreStack.
	 * @param ore The OreStack to check.
	 * @return the ore ID
	 */
	public static int getOreID(OreStack ore) {
		return OreDictionary.getOreID(ore.material);
	}

	/**
	 * A utility function that compacts a collection of OreStacks such as to remove duplicate materials.
	 * @param stacks A iterable object filled with OreStacks
	 * @return the compressed collection in the form of a set.
	 */
	public static Set<OreStack> compressCollection(Iterable<OreStack> stacks) {
		Map<String, Integer> outMap = new HashMap();
		Iterator<OreStack> iter = stacks.iterator();
		while (iter.hasNext()) {
			OreStack next = iter.next();
			if (outMap.containsKey(next.material)) {
				outMap.put(next.material, outMap.get(next.material) + next.amount);
			} else {
				outMap.put(next.material, next.amount);
			}
		}
		Set<OreStack> out = new HashSet();
		for (String key : outMap.keySet()) {
			out.add(new OreStack(key, outMap.get(key)));
		}
		return out;
	}

	public OreStack(String material, int amount) {
		this.material = material;
		this.amount = amount;
	}

	public OreStack(String material) {
		this.material = material;
		this.amount = 1;
	}
}
