package BL2.block;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import BL2.BL2Core;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCrudeEridiumStill extends BlockStationary implements ILiquid {

    public BlockCrudeEridiumStill(int i, Material material) {
        super(i, material);

        setHardness(100F);
        setLightOpacity(1);
        
        setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
    }

    @Override
    public int getRenderType() {
        return BL2Core.crudeEridiumModel;
    }

    @Override
    public int stillLiquidId() {
        return BL2Blocks.crudeEridiumStill.blockID;
    }

    @Override
    public boolean isMetaSensitive() {
        return false;
    }

    @Override
    public int stillLiquidMeta() {
        return 0;
    }

    @Override
    public boolean isBlockReplaceable(World world, int i, int j, int k) {
        return true;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister) {
        theIcon = new Icon[] { iconRegister.registerIcon("BL2:crude_eridium"),
                iconRegister.registerIcon("BL2:crude_eridium_flow") };
    }

}
