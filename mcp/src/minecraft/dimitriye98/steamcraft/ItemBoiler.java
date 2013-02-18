package dimitriye98.steamcraft;

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
        return "/boiler/items.png";
    }
}