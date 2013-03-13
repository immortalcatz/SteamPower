package steamcraft.steamcraft.tileentity;

public interface ISteamUser {
	int maxSteam();

	int currentSteam();

	boolean addSteam(int amount, int tier);

	int maxAccepted();
}
