package steamcraft.steamcraft.api;

public interface ISteamTank {

	/**
	 * Gets the current amount of steam in the tank.
	 * @return the amount of steam
	 */
	int getSteam();

	/**
	 * Gets the capacity of the tank.
	 * @return the capacity
	 */
	int getCapacity();

	/**
	 * Fills the tank with a certain amount of steam.
	 * @param amount the amount of steam to fill
	 * @return the leftover steam
	 */
	int fill(int amount, boolean virtual);

	/**
	 * Drains a certain amount of steam from the tank
	 * @param amount the amount to drain
	 * @return the amount actually drained
	 */
	int drain(int amount, boolean virtual);

}
