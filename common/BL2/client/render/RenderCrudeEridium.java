package BL2.client.render;

import net.minecraft.block.BlockStationary;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.liquids.ILiquid;
import buildcraft.BuildCraftCore;
import buildcraft.BuildCraftEnergy;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderCrudeEridium extends BlockStationary implements ILiquid {

    public RenderCrudeEridium(int i, Material material) {
        super(i, material);

        setHardness(100F);
        setLightOpacity(3);
    }

    @Override
    public int getRenderType() {
        return BuildCraftCore.oilModel;
    }

    @Override
    public int stillLiquidId() {
        return BuildCraftEnergy.oilStill.blockID;
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
        this.theIcon = new Icon[] { iconRegister.registerIcon("buildcraft:oil"), iconRegister.registerIcon("buildcraft:oil_flow") };
    }

}
