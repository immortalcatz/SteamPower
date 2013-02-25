package steamcraft.steamcraft.item;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.client.ClientProxy;
import steamcraft.steamcraft.entity.EntityMusketBall;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemFirearm extends Item
{
    public float damage;
    public int reloadTime;
    public float accuracy;
    public float knockback;
    public boolean shotgun;

    public ItemFirearm(int par1, float par2, int par3, float par4, float par5, boolean par6)
    {
        super(par1);
        this.maxStackSize = 1;
        this.setMaxDamage(384);
        this.damage = par2;
        this.reloadTime = par3;
        this.accuracy = par4;
        this.knockback = par5;
        this.shotgun = par6;
    }

    @Override
	public String getTextureFile()
    {
        return SteamCraft.ITEMS_PNG;
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    @Override
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        NBTTagCompound nbt = par1ItemStack.getTagCompound();

        if (nbt.getBoolean("loaded"))
        {
            int var6 = this.getMaxItemUseDuration(par1ItemStack) - par4;
            ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, var6);
            MinecraftForge.EVENT_BUS.post(event);

            if (event.isCanceled())
            {
                return;
            }

            var6 = event.charge;
            float var7 = var6 / 20.0F;
            var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;

            if (var7 < 0.1D)
            {
                return;
            }

            if (var7 > 1.0F)
            {
                var7 = 1.0F;
            }

            EntityMusketBall var8 = new EntityMusketBall(par2World, par3EntityPlayer, 2.0F, (1.0F + accuracy) - var7, damage);

            if (var7 == 1.0F)
            {
            }

            int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (var9 > 0)
            {
                var8.setDamage(var8.getDamage() + var9 * 0.5D + 0.5D);
            }

            int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (var10 > 0)
            {
                var8.setKnockbackStrength(var10);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                var8.setFire(100);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.explode", (knockback * (2F / 5F)), 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);

            for (int i = 1; i < 16; i++)
            {
                par2World.spawnParticle("smoke", par3EntityPlayer.posX + itemRand.nextFloat() - 0.5F, par3EntityPlayer.posY + (itemRand.nextFloat() / 2) - 0.25F, par3EntityPlayer.posZ + itemRand.nextFloat() - 0.5F, 0.0D, 0.01D, 0.0D);
            }

            if (!par2World.isRemote)
            {
                if (shotgun)
                {
                    for (int i = 1; i < 21; i++)
                    {
                        EntityMusketBall var12 = new EntityMusketBall(par2World, par3EntityPlayer, 2.0F, (1.0F + accuracy) - var7, damage);
                        par2World.spawnEntityInWorld(var12);
                    }
                }
                else
                {
                    par2World.spawnEntityInWorld(var8);
                }

                //par3EntityPlayer.rotationPitch = par3EntityPlayer.rotationPitch + 100.0F;
                //Minecraft minecraft = Minecraft.getMinecraft();
                //minecraft.entityRenderer.
            }

            nbt.setBoolean("loaded", false);

            if (par2World.isRemote && !par3EntityPlayer.capabilities.isCreativeMode)
            {
                float thiskb = this.knockback;
                boolean crouching = par3EntityPlayer.isSneaking();

                if (crouching)
                {
                    thiskb = thiskb / 2;
                }

                par3EntityPlayer.rotationPitch = par3EntityPlayer.rotationPitch - (thiskb * 3F);
                par3EntityPlayer.motionZ = -MathHelper.cos((par3EntityPlayer.rotationYaw) * (float)Math.PI / 180.0F) * (thiskb * (4F / 50F));
                par3EntityPlayer.motionX = MathHelper.sin((par3EntityPlayer.rotationYaw) * (float)Math.PI / 180.0F) * (thiskb * (4F / 50F));
            }

            // par3EntityPlayer.inventory.setInventorySlotContents(par3EntityPlayer.inventory.currentItem, new ItemStack(BoilerMod.musketEmpty));
        }
        else
        {
            if (nbt.getBoolean("done"))
            {
                //done = false;
                //par3EntityPlayer.inventoryContainer.putStackInSlot(par3EntityPlayer.inventory.currentItem + 36, new ItemStack(BoilerMod.musket, 1));
                //par3EntityPlayer.inventoryContainer.detectAndSendChanges();
                nbt.setBoolean("loaded", true);
                nbt.setBoolean("done", false);
            }
        }
    }

    @Override
	public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        NBTTagCompound nbt = par1ItemStack.getTagCompound();
        boolean var5 = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (var5 || par3EntityPlayer.inventory.hasItem(SteamCraft.musketCartridge.itemID))
        {
            if (nbt.getBoolean("done") == false)
            {
                if (var5)
                {
                }
                else
                {
                    par3EntityPlayer.inventory.consumeInventoryItem(SteamCraft.musketCartridge.itemID);
                }

                nbt.setBoolean("done", true);
                par2World.playSoundAtEntity(par3EntityPlayer, "random.click", 1F, par2World.rand.nextFloat() * 0.1F + 0.9F);
            }
        }

        return par1ItemStack;
    }

    @Override
	public boolean isFull3D()
    {
        return true;
    }

    /**
     * How long it takes to use or consume an item
     */
    @Override
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        if (!par1ItemStack.hasTagCompound())
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = par1ItemStack.getTagCompound();
            nbt.setBoolean("loaded", false);
            //nbt.setBoolean("done", false);
        }

        NBTTagCompound nbt = par1ItemStack.getTagCompound();

        if ((nbt.getBoolean("loaded")) || (nbt.getBoolean("done")))
        {
            return 72000;
        }
        else
        {
            return reloadTime;
        }
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        if (!par1ItemStack.hasTagCompound())
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = par1ItemStack.getTagCompound();
            nbt.setBoolean("loaded", false);
            //nbt.setBoolean("done", false);
        }

        NBTTagCompound nbt = par1ItemStack.getTagCompound();

        if (nbt.getBoolean("loaded"))
        {
            return EnumAction.bow;
        }
        else
        {
            return EnumAction.block;
        }
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    @Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        if (!par1ItemStack.hasTagCompound())
        {
            par1ItemStack.setTagCompound(new NBTTagCompound());
            NBTTagCompound nbt = par1ItemStack.getTagCompound();
            nbt.setBoolean("loaded", false);
            nbt.setBoolean("done", false);
        }

        NBTTagCompound nbt = par1ItemStack.getTagCompound();

        if (nbt.getBoolean("loaded"))
        {
            ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
            MinecraftForge.EVENT_BUS.post(event);

            if (event.isCanceled())
            {
                return event.result;
            }

            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }
        else
        {
            if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(SteamCraft.musketCartridge.itemID))
            {
                par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
            }
        }

        return par1ItemStack;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    @Override
	public int getItemEnchantability()
    {
        return 1;
    }
}