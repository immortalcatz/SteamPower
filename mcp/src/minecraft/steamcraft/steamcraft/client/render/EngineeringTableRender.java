package steamcraft.steamcraft.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.client.ForgeHooksClient;

import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.SteamCraft;
import steamcraft.steamcraft.tileentity.TileEntityEngineeringTable;
import steamcraft.steamcraft.tileentity.TileEntityResearchTable;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class EngineeringTableRender extends TileEntitySpecialRenderer implements ISimpleBlockRenderingHandler
{
    private final ModelEngineeringTable model;
	public static int engineerModelID = RenderingRegistry.getNextAvailableRenderId();

    public EngineeringTableRender()
    {
        model = new ModelEngineeringTable();
    }

    public void renderAModelAt(TileEntityEngineeringTable tile, double d, double d1, double d2, float f)
    {
        int i = 0;
        bindTextureByName(SteamCraft.modelTextureLocation + "engineeringtable.png");
        int meta = tile.getBlockMetadata();
        int flip = 0;

        switch (meta)
        {
            default:
            case 2:
                flip = 0;
                break;

            case 3:
                flip = 180;
                break;

            case 4:
                flip = 90;
                break;

            case 5:
                flip = 270;
                break;
        }

        GL11.glPushMatrix(); //start
        GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
        GL11.glRotatef(0 + flip, 0.0F, 1.0F, 0.0F); //change the first 0 in like 90 to make it rotate 90 degrees.
        GL11.glScalef(1.0F, -1F, -1F); // to make your block have a normal positioning. comment out to see what happens
        model.renderModel(0.0625F); //renders and 0.0625F is exactly 1/16. every block has 16 entitys/pixels. if you make the number 1, every pixel will be as big as a block. make it 0.03125 and your block will be half as big as a normal one.
        GL11.glPopMatrix(); //end
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        renderAModelAt((TileEntityEngineeringTable) tileentity, d, d1, d2, f);
    }

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		ForgeHooksClient.bindTexture(SteamCraft.modelTextureLocation + "engineeringtable.png", 0);
		GL11.glPushMatrix();
		GL11.glTranslated(0, -0.5 + (24 / 16.0), 0);
		GL11.glScalef(1.0F, -1F, -1F);
		model.renderModel(0.0625F);
		GL11.glPopMatrix();

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int getRenderId() {
		// TODO Auto-generated method stub
		return engineerModelID;
	}
}