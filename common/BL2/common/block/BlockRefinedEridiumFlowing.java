package BL2.common.block;

import net.minecraft.block.BlockFlowing;
import net.minecraft.block.material.Material;

import BL2.BL2Core;

public class BlockRefinedEridiumFlowing extends BlockFlowing {

    public BlockRefinedEridiumFlowing(int id) {

        super(id, Material.water);
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setCreativeTab(BL2Core.tabBL2);
        this.setBlockName("RefinedEridiumFlowing");
    }

    @Override
    public String getTextureFile() {

        return "/BL2/textures/blocks.png";
    }

}
