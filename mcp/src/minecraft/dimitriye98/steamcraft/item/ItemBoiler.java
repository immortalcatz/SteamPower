package dimitriye98.steamcraft.item;

import dimitriye98.steamcraft.SteamCraft;
import net.minecraft.item.Item;
import net.minecraft.src.*;

public class ItemBoiler extends Item
{
    public ItemBoiler(int i)
    {
        super(i);
    }

    public String getTextureFile()
    {
        return SteamCraft.ITEMS_PNG;
    }
}