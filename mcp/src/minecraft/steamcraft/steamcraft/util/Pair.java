package steamcraft.steamcraft.util;

public class Pair<L, R> {

	private L left;
	private R right;

	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() {
		return this.left;
	}

	public void setLeft(L left) {
		this.left = left;
	}

	public R getRight() {
		return this.right;
	}

	public void setRight(R right) {
		this.right = right;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) return false;
		Pair pairO = (Pair)o;
		if (left.equals(pairO.left) && right.equals(pairO.right)) return true;
		return false;
	}

	@Override
	public int hashCode() {
		return left.hashCode() ^ right.hashCode();
	}

}
