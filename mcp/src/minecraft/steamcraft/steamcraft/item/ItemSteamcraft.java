package steamcraft.steamcraft.item;

import steamcraft.steamcraft.SteamCraft;
import net.minecraft.item.Item;
import net.minecraft.src.*;

public class ItemSteamcraft extends Item
{
    public ItemSteamcraft(int i)
    {
        super(i);
    }

    public String getTextureFile()
    {
        return SteamCraft.ITEMS_PNG;
    }
}