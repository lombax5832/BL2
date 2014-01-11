//package BL2.container;
//
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.ICrafting;
//import BL2.tile.base.TileEntityBL2Powered;
//
//
///* Progress bar vals:
// * 0: energy
// * 1: progress
// */
//public class ContainerBL2Powered extends ContainerBL2Inventory{
//    public ContainerBL2Powered(TileEntityBL2Powered te, InventoryPlayer inv){
//        super(te,inv);
//    }
//    
//    @Override
//    public void detectAndSendChanges(){
//        super.detectAndSendChanges();
//        for(int i = 0; i < crafters.size(); i++)
//        {
//            ((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 0, ((TileEntityBL2Powered)inventory).getEnergyStored());
//            ((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 1, ((TileEntityBL2Powered)inventory).getWorkDone());
//        }
//    }
//    
//    @Override
//    public void updateProgressBar(int var, int value)
//    {
//        super.updateProgressBar(var, value);
//        
//        switch(var){
//            case 0: ((TileEntityBL2Powered)inventory).setEnergyStored(value);
//            case 1: ((TileEntityBL2Powered)inventory).setWorkDone(value);
//        }
//    }
//}
