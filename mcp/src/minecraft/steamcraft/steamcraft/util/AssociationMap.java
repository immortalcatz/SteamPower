package steamcraft.steamcraft.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

public class AssociationMap<K, V> extends AbstractMap {

	private final HashMap<K, V> valMap;
	private final HashMap<V, K> keyMap;

	public AssociationMap() {
		valMap = new HashMap();
		keyMap = new HashMap();
	}

	private class EntrySet implements Set {

		private class SetIterator implements Iterator {

			private final Iterator iter = valMap.entrySet().iterator();

			@Override
			public boolean hasNext() {
				return iter.hasNext();
			}

			private Entry previous;
			@Override
			public Object next() {
				previous = (Entry) iter.next();
				return previous;
			}

			@Override
			public void remove() throws IllegalStateException {
				if (previous == null) throw new IllegalStateException();
				keyMap.remove(previous.getValue());
				valMap.remove(previous.getKey());
				previous = null;
			}

		}

		@Override
		public Iterator iterator() {
			return new SetIterator();
		}

		@Override
		public int size() {
			return valMap.size();
		}

		@Override
		public boolean add(Object e) {
			if (!(e instanceof Entry)) throw new ClassCastException();
			keyMap.put((V)((Entry) e).getValue(), (K)((Entry) e).getKey());
			V previous = valMap.put((K)((Entry) e).getKey(), (V)((Entry) e).getValue());
			if (previous != null) return true;
			return false;
		}

		@Override
		public boolean addAll(Collection c) throws ClassCastException {
			for (Object o : c) {

			}
			boolean out = false;

			return out;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub

		}

		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean containsAll(Collection c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean removeAll(Collection c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean retainAll(Collection c) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Object[] toArray(Object[] a) {
			// TODO Auto-generated method stub
			return null;
		}

	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object put(Object key, Object value) throws ClassCastException {
		try {

		}
	}

}
