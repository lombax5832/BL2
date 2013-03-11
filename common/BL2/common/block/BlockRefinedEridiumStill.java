package BL2.common.block;

import BL2.BL2Core;
import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

public class BlockRefinedEridiumStill extends BlockStationary {

    public BlockRefinedEridiumStill(int id) {

        super(id, Material.water);
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setBlockName("refinedEridiumStill");
        this.setCreativeTab(BL2.common.CreativeTabBL2.tabBL2);
        this.disableStats();
        this.setRequiresSelfNotify();
        this.blockIndexInTexture = (14*16)+13;
    }

    @Override
    public String getTextureFile() {

        return "/BL2/textures/blocks.png";
    }

}