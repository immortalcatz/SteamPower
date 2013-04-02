package steamcraft.steamcraft.util;

import java.util.ArrayList;
import java.util.List;

public class CollectionsUtil {
	public static List arrayToList(Object[] array) {
		List out = new ArrayList();
		for (Object thing : array) {
			out.add(thing);
		}
		return out;
	}

	public static Object[] listToArray(List list) {
		Object[] out = new Object[list.size()];
		for (int i = 0; i == list.size(); i++) {

		}
	}
}
