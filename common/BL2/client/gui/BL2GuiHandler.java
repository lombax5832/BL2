//package BL2.client.gui;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//import BL2.tile.base.TileEntityBL2;
//import cpw.mods.fml.common.network.IGuiHandler;
//
//public class BL2GuiHandler implements IGuiHandler{
//
//    @Override
//    public Object getServerGuiElement(int ID, EntityPlayer player, World world,
//            int x, int y, int z) {
//        if(ID == 1){
//            TileEntity te = world.getBlockTileEntity(x, y, z);
//            if(te instanceof TileEntityBL2){
//                return ((TileEntityBL2)te).getContainer(player.inventory);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public Object getClientGuiElement(int ID, EntityPlayer player, World world,
//            int x, int y, int z) {
//        if(ID == 1){
//            TileEntity te = world.getBlockTileEntity(x, y, z);
//            if(te instanceof TileEntityBL2){
//                return ((TileEntityBL2)te).getGui(player.inventory);
//            }
//        }
//        return null;
//    }
//
//}
