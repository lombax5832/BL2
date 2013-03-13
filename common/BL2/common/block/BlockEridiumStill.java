package BL2.common.block;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;

public class BlockEridiumStill extends BlockStationary {

    public BlockEridiumStill(int id) {

        super(id, Material.water);
        this.blockHardness = 100F;
        this.setLightOpacity(3);
        this.setBlockName("eridiumStill");
        this.setCreativeTab(BL2.common.CreativeTabBL2.tabBL2);
        this.disableStats();
        this.setRequiresSelfNotify();
        this.blockIndexInTexture = (12*16)+13;
    }

    @Override
    public String getTextureFile() {

        return "/BL2/textures/blocks.png";
    }

}