package steamcraft.steamcraft.research;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Collection;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ChatAllowedCharacters;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.SteamCraft;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

class GuiButtonSelect extends GuiButton
{

	public GuiButtonSelect(int par1, int par2, int par3, int par4, int par5, String par6Str) {
		super(par1, par2, par3, par4, par5, par6Str);
	}

	public GuiButtonSelect(int par1, int par2, int par3, String par6Str) {
		super(par1, par2, par3,  par6Str);
	}

    public void drawCenteredStringWithoutShadow(FontRenderer par1FontRenderer, String par2Str, int par3, int par4, int par5)
    {
        par1FontRenderer.drawString(par2Str, par3 - par1FontRenderer.getStringWidth(par2Str) / 2 - 10, par4, par5);
    }

	@Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            FontRenderer var4 = par1Minecraft.fontRenderer;
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, par1Minecraft.renderEngine.getTexture(SteamCraft.guiLocation + "paperbuttons.png"));
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            int var5 = this.getHoverState(this.field_82253_i);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + var5 * 20, this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + var5 * 20, this.width / 2, this.height);
            this.mouseDragged(par1Minecraft, par2, par3);
            int var6 = 00000000;

            this.drawCenteredStringWithoutShadow(var4, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, var6);
        }
    }

}
class GuiButtonNextPage extends GuiButton
{
    /**
     * True for pointing right (next page), false for pointing left (previous page).
     */
    private final boolean nextPage;

    public GuiButtonNextPage(int par1, int par2, int par3, boolean par4)
    {
        super(par1, par2, par3, 23, 13, "");
        this.nextPage = par4;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            boolean var4 = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture(par1Minecraft.renderEngine.getTexture("/gui/book.png"));
            int var5 = 0;
            int var6 = 192;

            if (var4)
            {
                var5 += 23;
            }

            if (!this.nextPage)
            {
                var6 += 13;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, var5, var6, 23, 13);
        }
    }
}

class GuiButtonBookmark extends GuiButton
{
    /**
     * True for pointing right (next page), false for pointing left (previous page).
     */
    public boolean nextPage;

    public GuiButtonBookmark(int par1, int par2, int par3, boolean par4)
    {
        super(par1, par2, par3, 10, 104, "");
        this.nextPage = par4;
    }

    /**
     * Draws this button to the screen.
     */
    @Override
	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            boolean var4 = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            par1Minecraft.renderEngine.bindTexture(par1Minecraft.renderEngine.getTexture(SteamCraft.guiLocation + "researchpaper.png"));
            int var5 = 209;
            int var6 = 13;

            if (this.nextPage)
            {
                var6 += 91;
                var5 += 13;
            }

            this.drawTexturedModalRect(this.xPosition, this.yPosition, var5, 0, 10, var6);
        }
    }
}


@SideOnly(Side.CLIENT)
public class GuiScreenResearch extends GuiContainer
{
    /** The player editing the book */
    private final EntityPlayer editingPlayer;
    private final ItemStack itemstack;

    /** Update ticks since the gui was opened */
    private int updateCount;
    public final int bookImageWidth = 192;
    public final int bookImageHeight = 192;
    public boolean index = true;
    private boolean isBook;
    public int currPage = 0;
    private int maxResearch = 1;
    private String researchKey;
    private final String bookTitle = "";
    private int totalPages;
    private int currentResearch = 1;
    private GuiButtonNextPage buttonNextPage;
    private GuiButtonNextPage buttonPreviousPage;
    private GuiButtonNextPage buttonIndex;
    private GuiButton buttonMenu1;
    private GuiButton buttonMenu2;
    private GuiButton buttonMenu3;
    private GuiButton buttonMenu4;
    private GuiButton buttonMenu5;
    private GuiButtonBookmark bookmark;
    private int bookmarkpage;
    private int complete = 0;

    public GuiScreenResearch(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack, boolean book)
    {
    	super(new ContainerResearch(par1EntityPlayer.inventory));
        this.editingPlayer = par1EntityPlayer;
        this.itemstack = par2ItemStack;


        if (par2ItemStack.hasTagCompound())
        {
            NBTTagCompound var4 = par2ItemStack.getTagCompound();

            this.researchKey = par2ItemStack.stackTagCompound.getCompoundTag("1").getString("Research");
            this.complete = par2ItemStack.stackTagCompound.getCompoundTag("1").getInteger("Complete");
            if (complete == 1) {
            this.totalPages = ResearchDictionary.researchList.get(researchKey).description.size();
            }
            this.maxResearch = par2ItemStack.stackTagCompound.getTags().size() - 1;
            this.bookmarkpage = par2ItemStack.stackTagCompound.getInteger("SelectedResearch");
            this.index = book;
            if (!(bookmarkpage > maxResearch)) {
            	this.index = false;
            	this.currentResearch = bookmarkpage;
                this.researchKey = itemstack.stackTagCompound.getCompoundTag(Integer.toString(bookmarkpage)).getString("Research");
                this.complete = itemstack.stackTagCompound.getCompoundTag(Integer.toString(bookmarkpage)).getInteger("Complete");
            	if (itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getInteger("Complete") == 1) {
                this.totalPages = ResearchDictionary.researchList.get(researchKey).description.size();
            	}
            }
            this.isBook = book;
            if (this.index) {
                this.totalPages = (this.maxResearch - (this.maxResearch % 5))/5 + 1;
            }

            System.out.println(bookmarkpage);

        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
	public void updateScreen()
    {
        super.updateScreen();
        ++this.updateCount;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    @Override
	public void initGui()
    {
        this.controlList.clear();
        //Keyboard.enableRepeatEvents(true);

        int var1 = (this.width - this.bookImageWidth) / 2;
        int var2 = (this.height - this.bookImageHeight - 40) / 2;
        this.controlList.add(this.buttonNextPage = new GuiButtonNextPage(1, var1 + 120, var2 + 154, true));
        this.controlList.add(this.buttonPreviousPage = new GuiButtonNextPage(2, var1 + 38, var2 + 154, false));
        this.controlList.add(this.buttonIndex = new GuiButtonNextPage(8, var1 + 28, var2 + 10, false));
        this.controlList.add(this.buttonMenu1 = new GuiButtonSelect(3, var1 + 35, var2 + 30, 110, 20, ""));
        this.controlList.add(this.buttonMenu2 = new GuiButtonSelect(4, var1 + 35, var2 + 55, 110, 20, ""));
        this.controlList.add(this.buttonMenu3 = new GuiButtonSelect(5, var1 + 35, var2 + 80, 110, 20, ""));
        this.controlList.add(this.buttonMenu4 = new GuiButtonSelect(6, var1 + 35, var2 + 105, 110, 20, ""));
        this.controlList.add(this.buttonMenu5 = new GuiButtonSelect(7, var1 + 35, var2 + 130, 110, 20, ""));
        this.controlList.add(this.bookmark = new GuiButtonBookmark(9, var1 + 142, var2 + 7, false));
        this.updateButtons();
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
	public void onGuiClosed()
    {
    }

    private void updateButtons()
    {
    	if (index) {
	        this.buttonIndex.drawButton = false;
        	this.bookmark.drawButton = false;
	        this.buttonNextPage.drawButton = false;
	        this.buttonPreviousPage.drawButton = false;
	        int x = this.maxResearch;
	        if (x > ((this.currPage+1) * 5)) {
		        this.buttonNextPage.drawButton = true;
	        }
	        if (this.currPage > 0) {
	        	x = x - (this.currPage * 5);
		        this.buttonPreviousPage.drawButton = true;
	        }

	        this.buttonMenu1.drawButton = x > 0;
	        this.buttonMenu2.drawButton = x > 1;
	        this.buttonMenu3.drawButton = x > 2;
	        this.buttonMenu4.drawButton = x > 3;
	        this.buttonMenu5.drawButton = x > 4;
	        this.buttonMenu1.displayString = ResearchDictionary.getNameFromToken(this.itemstack.stackTagCompound.getCompoundTag(Integer.toString(1+(this.currPage*5))).getString("Research"));
	        this.buttonMenu2.displayString = ResearchDictionary.getNameFromToken(this.itemstack.stackTagCompound.getCompoundTag(Integer.toString(2+(this.currPage*5))).getString("Research"));
	        this.buttonMenu3.displayString = ResearchDictionary.getNameFromToken(this.itemstack.stackTagCompound.getCompoundTag(Integer.toString(3+(this.currPage*5))).getString("Research"));
	        this.buttonMenu4.displayString = ResearchDictionary.getNameFromToken(this.itemstack.stackTagCompound.getCompoundTag(Integer.toString(4+(this.currPage*5))).getString("Research"));
	        this.buttonMenu5.displayString = ResearchDictionary.getNameFromToken(this.itemstack.stackTagCompound.getCompoundTag(Integer.toString(5+(this.currPage*5))).getString("Research"));
    	}
    	else
    	{
	        this.buttonNextPage.drawButton = this.currPage < (this.totalPages - 1);
	        this.buttonPreviousPage.drawButton = this.currPage > 0;
	        this.buttonIndex.drawButton = isBook;
	        this.buttonMenu1.drawButton = false;
	        this.buttonMenu2.drawButton = false;
	        this.buttonMenu3.drawButton = false;
	        this.buttonMenu4.drawButton = false;
	        this.buttonMenu5.drawButton = false;
	        this.bookmark.drawButton = this.isBook;
		    if (this.isBook) {
	        	this.bookmark.nextPage = (this.currentResearch == this.bookmarkpage);
	        }

    	}
    }

    private void sendBookToServer(boolean par1)
    {

    }

    private void updateBookmark() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
        DataOutputStream outputStream = new DataOutputStream(bos);
        try
        {
            outputStream.writeByte(2);
            outputStream.writeInt(editingPlayer.worldObj.provider.dimensionId);
            outputStream.writeInt(this.bookmarkpage);
            outputStream.writeUTF(editingPlayer.username);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        Packet250CustomPayload packet = new Packet250CustomPayload();
        packet.channel = "SteamCraft";
        packet.data = bos.toByteArray();
        packet.length = bos.size();
        PacketDispatcher.sendPacketToServer(packet);
    }

    /**
     * Fired when a control is clicked. This is the equivalent of ActionListener.actionPerformed(ActionEvent e).
     */
    @Override
	protected void actionPerformed(GuiButton par1GuiButton)
    {

        if (par1GuiButton.enabled)
        {
            if (par1GuiButton.id == 1)
            {
                if (this.currPage < this.totalPages - 1)
                {
                    ++this.currPage;
                }
            }
            else if (par1GuiButton.id == 2)
            {
                if (this.currPage > 0)
                {
                    --this.currPage;
                }
            }
            else if (par1GuiButton.id == 8)
            {
                this.index = true;
                this.currPage = 0;
                this.totalPages = (this.maxResearch - (this.maxResearch % 5))/5 + 1;
            }
            else if (par1GuiButton.id == 9)
            {
                if (this.currentResearch == this.bookmarkpage) {
                	this.bookmarkpage = this.maxResearch + 1;
                	System.out.println("changed");
                }
                else
                {
                	this.bookmarkpage = this.currentResearch;
                	System.out.println("changed");
                }
                editingPlayer.inventory.mainInventory[editingPlayer.inventory.currentItem].stackTagCompound.setInteger("SelectedResearch", this.bookmarkpage);
                updateBookmark();

            }
            else if (par1GuiButton.id > 2)
            {
	                this.index = false;
	                this.currentResearch = par1GuiButton.id + (currPage*5) - 2;
	                NBTTagCompound var4 = itemstack.getTagCompound();
	                this.researchKey = itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getString("Research");
	                this.complete = itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getInteger("Complete");
	            	if (itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getInteger("Complete") == 1) {
	                    this.totalPages = ResearchDictionary.researchList.get(researchKey).description.size();
	                }
	                this.currPage = 0;
            }

            this.updateButtons();
        }
    }

    private void addNewPage()
    {

    }

    private void drawItemStack(ItemStack par1ItemStack, int par2, int par3)
    {
        GL11.glTranslatef(0.0F, 0.0F, 32.0F);
        this.zLevel = 200.0F;
        itemRenderer.zLevel = 200.0F;
        itemRenderer.renderItemAndEffectIntoGUI(this.fontRenderer, this.mc.renderEngine, par1ItemStack, par2, par3);
        itemRenderer.renderItemOverlayIntoGUI(this.fontRenderer, this.mc.renderEngine, par1ItemStack, par2, par3);
        this.zLevel = 0.0F;
        itemRenderer.zLevel = 0.0F;
    }

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2,
			int var3) {
		int var4 = this.mc.renderEngine.getTexture(SteamCraft.guiLocation + "researchpaper.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(var4);
        int var5 = (this.width - this.bookImageWidth) / 2;
        int var6 = (this.height - this.bookImageHeight - 40) / 2;
        this.drawTexturedModalRect(var5, var6, 0, 0, this.bookImageWidth, this.bookImageHeight);

        if (!index) {
        	if (itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getInteger("Complete") == 1) {
		        String var8;
		        var8 = ResearchDictionary.getDescFromToken(researchKey, currPage);
		        String var7;
		        int var9;
		        this.fontRenderer.drawSplitString(var8, var5 + 30, var6 + 0 + 25, 116, 0);
		        this.fontRenderer.drawSplitString(ResearchDictionary.getNameFromToken(this.researchKey), var5 + 55, var6 + 0 + 15, 116, 0);
		        List<ResearchRecipe> recipes = ResearchDictionary.getRecipesFromToken(this.researchKey);
		        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		        for (ResearchRecipe recipe:recipes) {
		        	if (recipe.page == currPage) {
		        		this.mc.renderEngine.bindTexture(this.mc.renderEngine.getTexture(recipe.texlocation));
		        		this.drawTexturedModalRect(var5 + 33, var6+ 30 + (recipe.position*9), 0, 0, recipe.texWidth, recipe.texHeight);
		        	}
		        }
        	}
        	else if (itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getInteger("Complete") == 2) {
		        this.fontRenderer.drawSplitString("Incomplete", var5 + 55, var6 + 0 + 15, 116, 0);
        		for (int i = 0; i < itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getTagList("Contents").tagCount(); i++) {
        			NBTBase currentTag = itemstack.stackTagCompound.getCompoundTag(Integer.toString(this.currentResearch)).getTagList("Contents").tagAt(i);
        			ItemStack thisItem = ItemStack.loadItemStackFromNBT((NBTTagCompound) currentTag);
        			this.drawItemStack(thisItem, var5 + 33 + ((i % 6) * 18), var6+ 30 + ((i - (i % 6)) /6 ) * 18);
        		}
        	}
        }
        else
        {
        	 this.fontRenderer.drawSplitString("Index", var5 + 75, var6 + 0 + 15, 116, 0);
        }

	}
}
