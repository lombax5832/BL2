package BL2.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import BL2.BL2Core;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class RenderCrudeEridium implements ISimpleBlockRenderingHandler {

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {

		if (block.getRenderType() != BL2Core.crudeEridiumModel)
			return true;

		renderer.renderBlockFluids(block, x, y, z);
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		return false;
	}

	@Override
	public int getRenderId() {
		return BL2Core.crudeEridiumModel;
	}

}
