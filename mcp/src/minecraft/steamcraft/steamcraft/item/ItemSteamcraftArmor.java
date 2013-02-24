package steamcraft.steamcraft.item;

import steamcraft.steamcraft.SteamCraft;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ItemSteamcraftArmor extends ItemArmor implements IArmorTextureProvider{

	String path1;
	String path2;
	Boolean isLegs;


	public ItemSteamcraftArmor(int par1, EnumArmorMaterial par2EnumArmorMaterial,
			int par3, int par4, boolean legs, String p1, String p2) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		isLegs = legs;
		path1 = p1;
		path2 = p2;
		// TODO Auto-generated constructor stub
	}


	@Override
	public String getArmorTextureFile(ItemStack stack){
	if(isLegs){
		return path2;
	}
	else
	{
		return path1;
	}

	}

    @Override
	public String getTextureFile()
    {
        return SteamCraft.ITEMS_PNG;
    }

}
