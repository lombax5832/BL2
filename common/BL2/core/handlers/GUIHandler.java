package BL2.core.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import BL2.container.ContainerAmmoCrafter;
import BL2.gui.GuiAmmoCrafter;
import BL2.tile.TileEntityAmmoCrafter;
import cpw.mods.fml.common.network.IGuiHandler;

public class GUIHandler implements IGuiHandler{
    //returns an instance of the Container you made earlier
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityAmmoCrafter){
                    return new ContainerAmmoCrafter(player.inventory, (TileEntityAmmoCrafter) tileEntity);
            }
            return null;
    }

    //returns an instance of the Gui you made earlier
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world,
                    int x, int y, int z) {
            TileEntity tileEntity = world.getBlockTileEntity(x, y, z);
            if(tileEntity instanceof TileEntityAmmoCrafter){
                    return new GuiAmmoCrafter(player.inventory, (TileEntityAmmoCrafter) tileEntity);
            }
            return null;

    }
}
