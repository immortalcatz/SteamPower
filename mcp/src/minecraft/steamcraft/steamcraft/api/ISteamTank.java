package steamcraft.steamcraft.api;

public interface ISteamTank {

	/**
	 * Get's the current amount of steam in the tank.
	 * @return
	 */
	int getSteam();

	int getCapacity();

	int fill(int amount);

	int drain(int amount);

}
