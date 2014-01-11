//package BL2.container;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.ICrafting;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//import BL2.inventory.SlotOnlyDisplay;
//import BL2.inventory.SlotOnlyTake;
//import BL2.tile.TileEntityAmmoCrafter;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class ContainerAmmoCrafter extends Container{
//
//    protected TileEntityAmmoCrafter tileEntity;
//    
//    public ContainerAmmoCrafter (InventoryPlayer inventoryPlayer, TileEntityAmmoCrafter tileEntityAmmoCrafter){
//        tileEntity = tileEntityAmmoCrafter;
//
//        //the Slot constructor takes the IInventory and the slot number in that it binds to
//        //and the x-y coordinates it resides on-screen
//        addSlotToContainer(new Slot(tileEntity, 0, 48, 35));
//        
//        addSlotToContainer(new SlotOnlyTake(tileEntity, 1, 106, 35));
//        
//        addSlotToContainer(new SlotOnlyDisplay(tileEntity, 2, 75, 18));
//        
////        addSlotToContainer(new SlotOnlyDisplay(tileEntity, 3, 75, 51));
//        
//        addSlotToContainer(new Slot(tileEntity, 4, 48, 17));
//        
//        addSlotToContainer(new Slot(tileEntity, 5, 48, 53));
//
//        //commonly used vanilla code that adds the player's inventory
//        bindPlayerInventory(inventoryPlayer);
//    }
//    
//    @Override
//    public boolean canInteractWith(EntityPlayer player) {
//            return true;
//    }
//    
//    @Override
//    public void detectAndSendChanges()
//    {
//        super.detectAndSendChanges();
//        for(int i = 0; i < crafters.size(); i++)
//        {
//            ((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 0, (int) ((TileEntityAmmoCrafter) tileEntity).getEnergy());
//            ((ICrafting)crafters.get(i)).sendProgressBarUpdate(this, 1, (int) ((TileEntityAmmoCrafter) tileEntity).getProgress());
//        }
//    }
//    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void updateProgressBar(int var, int value)
//    {
//        super.updateProgressBar(var, value);
//        
//        switch(var){
//            case 0: tileEntity.setEnergy(value);
//            case 1: tileEntity.setProgress(value);
//        }
//    }
//    
//    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
//        for (int i = 0; i < 3; i++) {
//                for (int j = 0; j < 9; j++) {
//                        addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
//                                        8 + j * 18, 84 + i * 18));
//                }
//        }
//
//        for (int i = 0; i < 9; i++) {
//                addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
//        }
//    }
//    
//    @Override
//    public ItemStack transferStackInSlot(EntityPlayer player, int slotID)
//    {
//        return null;
////        ItemStack stack = null;
////        Slot slot = (Slot)this.inventorySlots.get(slotID);
////
////        if (slot != null && slot.getHasStack())
////        {
////            ItemStack slotStack = slot.getStack();
////            stack = slotStack.copy();
////
////            if (slotID < tileEntity.getSizeInventory())
////            {
////                if (!this.mergeItemStack(slotStack, tileEntity.getSizeInventory(), this.inventorySlots.size(), true))
////                {
////                    return null;
////                }
////            }
////            else if (!this.mergeItemStack(slotStack, 0, tileEntity.getSizeInventory()-1, false))
////            {
////                return null;
////            }
////
////            if (slotStack.stackSize == 0)
////            {
////                slot.putStack((ItemStack)null);
////            }
////            else
////            {
////                slot.onSlotChanged();
////            }
////        }
////
////        return stack;
//    }
//
//}
