package steamcraft.steamcraft.item;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import steamcraft.steamcraft.SteamCraft;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemWrench extends ItemSteamcraft{
	List<Integer> blocks4 = Arrays.asList(Block.stoneOvenActive.blockID, Block.stoneOvenIdle.blockID, SteamCraft.boilerActive.blockID, SteamCraft.boilerIdle.blockID, SteamCraft.researchTable.blockID);
	List<Integer> blocks6= Arrays.asList(Block.pistonBase.blockID, Block.pistonStickyBase.blockID);
 	public ItemWrench(int i) {
		super(i);
	}

    @Override
	public boolean isFull3D()
    {
    	return true;
    }

    @Override
	public boolean onItemUseFirst(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
    	System.out.println(par3World.blockHasTileEntity(par4, par5, par6));
    	 if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
         {
             return true;
         }
         else
         {
         	int var11 = par3World.getBlockId(par4, par5, par6);
            if (blocks4.contains(var11) || blocks6.contains(var11))
            {
             //int var6 = MathHelper.floor_double(par2EntityPlayer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
     		if (par7 == 2) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 2);
     		}

     		if (par7 == 5) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 5);
     		}

     		if (par7 == 3) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 3);
     		}

     		if (par7 == 4) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 4);
     		}
     		if (par7 == 1 && blocks6.contains(var11)) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 1);
     		}
     		if (par7 == 0 && blocks6.contains(var11)) {
     			par3World.setBlockMetadataWithNotify(par4, par5, par6, 0);
     		}


             	//Block block = par3World.get
                 par3World.playSoundEffect(par4 + 0.5D, par5 + 0.5D, par6 + 0.5D, "fire.ignite", 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);


             par1ItemStack.damageItem(1, par2EntityPlayer);
         }
         return true;
         }
    }


}


