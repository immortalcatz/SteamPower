package steamcraft.steamcraft.client.render;

import org.lwjgl.opengl.GL11;

import steamcraft.steamcraft.tileentity.TileEntityPlating;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;

public class RenderPlating implements ISimpleBlockRenderingHandler {
	public static int platingID = RenderingRegistry.getNextAvailableRenderId();
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		if (modelId == platingID)
		{
			TileEntityPlating tile = (TileEntityPlating) world.getBlockTileEntity(x, y, z);
			if (tile.orientations[0] == 1) {
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if (tile.orientations[1] == 1) {
				renderer.setRenderBounds(0.0F, 0.875F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if (tile.orientations[2] == 1) {
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.125F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if (tile.orientations[3] == 1) {
				renderer.setRenderBounds(0.0F, 0.0F, 0.875F, 1.0F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if (tile.orientations[4] == 1) {
				renderer.setRenderBounds(0.0F, 0.0F, 0.0F,  0.125F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
			if (tile.orientations[5] == 1) {
				renderer.setRenderBounds(0.875F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
				renderer.renderStandardBlock(block, x, y, z);
			}
		}
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return platingID;
	}

	private void renderDo(RenderBlocks renderblocks, Block block, int meta)
	{

		Tessellator tessellator = Tessellator.instance;
		GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, -1F, 0.0F);
		renderblocks.renderBottomFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(0, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 1.0F, 0.0F);
		renderblocks.renderTopFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(1, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, -1F);
		renderblocks.renderEastFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(2, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(0.0F, 0.0F, 1.0F);
		renderblocks.renderWestFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(3, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(-1F, 0.0F, 0.0F);
		renderblocks.renderNorthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(4, meta));
		tessellator.draw();
		tessellator.startDrawingQuads();
		tessellator.setNormal(1.0F, 0.0F, 0.0F);
		renderblocks.renderSouthFace(block, 0.0D, 0.0D, 0.0D, block.getBlockTextureFromSideAndMetadata(5, meta));
		tessellator.draw();
		GL11.glTranslatef(0.5F, 0.5F, 0.5F);
	}


}
