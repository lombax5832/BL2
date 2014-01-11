//package BL2.container;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.Slot;
//import net.minecraft.item.ItemStack;
//import BL2.tile.base.TileEntityBL2Inventory;
//import cpw.mods.fml.relauncher.Side;
//import cpw.mods.fml.relauncher.SideOnly;
//
//public class ContainerBL2Inventory extends Container{
//
//    protected TileEntityBL2Inventory inventory;
//    
//    public ContainerBL2Inventory(TileEntityBL2Inventory tileentity, InventoryPlayer inv) {
//        inventory = tileentity;
//        if(inventory.getSizeInventory() > 0){
//            addSlots();
//        }
//        bindPlayerInventory(inv);
//    }
//    
//    protected void addSlots()
//    {
//        addSlotToContainer(new Slot(inventory, 0, 8, 15));
//        addSlotToContainer(new Slot(inventory, 1, 26, 15));
//        addSlotToContainer(new Slot(inventory, 2, 44, 15));
//        addSlotToContainer(new Slot(inventory, 3, 8, 33));
//        addSlotToContainer(new Slot(inventory, 4, 26, 33));
//        addSlotToContainer(new Slot(inventory, 5, 44, 33));
//        addSlotToContainer(new Slot(inventory, 6, 8, 51));
//        addSlotToContainer(new Slot(inventory, 7, 26, 51));
//        addSlotToContainer(new Slot(inventory, 8, 44, 51));
//    }
//
//    @Override
//    public boolean canInteractWith(EntityPlayer entityplayer) {
//        return inventory.isUseableByPlayer(entityplayer);
//    }
//    
//    @Override
//    public void detectAndSendChanges(){
//        
//        super.detectAndSendChanges();
//    }
//    
//    @Override
//    @SideOnly(Side.CLIENT)
//    public void updateProgressBar(int var, int value){
//        super.updateProgressBar(var, value);
//    }
//    
//    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer)
//    {
//        for (int i = 0; i < 3; i++)
//        {
//            for (int j = 0; j < 9; j++)
//            {
//                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
//            }
//        }
//        
//        for (int i = 0; i < 9; i++)
//        {
//            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 84 + 58));
//        }
//    }
//    
//    
//    
//    @Override
//    public ItemStack transferStackInSlot(EntityPlayer player, int slot){
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
//    }
//
//}
