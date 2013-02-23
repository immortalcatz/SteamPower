package steamcraft.steamcraft.event;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class EventHandler {
	@ForgeSubscribe
	public void handleBreakSpeed(PlayerEvent.BreakSpeed event) {
		//event.newSpeed = event.originalSpeed * 10.0F;
	}
}
