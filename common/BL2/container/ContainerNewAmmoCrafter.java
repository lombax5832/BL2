//package BL2.container;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//import BL2.inventory.SlotOnlyDisplay;
//import BL2.inventory.SlotOnlyTake;
//import BL2.tile.base.TileEntityBL2Powered;
//
//public class ContainerNewAmmoCrafter extends ContainerBL2Powered{
//
//    public ContainerNewAmmoCrafter(TileEntityBL2Powered te, InventoryPlayer inv)
//    {
//        super(te, inv);
//    }
//    
//    @Override
//    protected void addSlots(){
//        //Inputs
//        addSlotToContainer(new Slot(inventory, 0, 48, 35));
//        
//        //Selection
//        addSlotToContainer(new SlotOnlyDisplay(inventory, 3, 75, 18));
//        
//        //Processing
//        addSlotToContainer(new SlotOnlyDisplay(inventory, 4, 75, 51));  
//        
//        //Output
//        addSlotToContainer(new SlotOnlyTake(inventory, 5, 106, 35));
//    }
//    
//    @Override
//    public ItemStack transferStackInSlot(EntityPlayer player, int slot){
//        /*
//        ItemStack stack = null;
//        Slot slotObject = (Slot) inventorySlots.get(slot);
//        int machInvSize = inventory.getSizeInventory();
//        
//        if(slotObject != null && slotObject.getHasStack())
//        {
//            ItemStack stackInSlot = slotObject.getStack();
//            stack = stackInSlot.copy();
//            
//            if(slot < machInvSize)
//            {
//                if(!mergeItemStack(stackInSlot, machInvSize, inventorySlots.size(), true))
//                {
//                    return null;
//                }
//            }
//            else if(!mergeItemStack(stackInSlot, 0, machInvSize, false))
//            {
//                return null;
//            }
//            
//            if(stackInSlot.stackSize == 0)
//            {
//                slotObject.putStack(null);
//            }
//            else
//            {
//                slotObject.onSlotChanged();
//            }
//            
//            if(stackInSlot.stackSize == stack.stackSize)
//            {
//                return null;
//            }
//            
//            slotObject.onPickupFromSlot(player, stackInSlot);
//        }
//        
//        return stack;
//        */
//        return null;
//    }
//}
