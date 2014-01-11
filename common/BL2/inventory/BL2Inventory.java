//package BL2.inventory;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.entity.player.InventoryPlayer;
//import net.minecraft.inventory.Container;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.nbt.NBTTagList;
//import net.minecraft.tileentity.TileEntity;
//import net.minecraft.world.World;
//
//public abstract class BL2Inventory extends TileEntity
//implements IInventory
//{
//protected ItemStack[] inventory;
//protected String invName;
//public int mode;
//
//public BL2Inventory(int invSize)
//{
//    inventory = new ItemStack[invSize];
//}
//
//@Override
//public ItemStack getStackInSlot(int slot)
//{
//    return inventory[slot];
//}
//
//public boolean isStackInSlot(int slot)
//{
//    return inventory[slot] != null;
//}
//
//@Override
//public int getSizeInventory()
//{
//    return inventory.length;
//}
//
//@Override
//public int getInventoryStackLimit ()
//{
//    return 64;
//}
//
//public boolean canDropInventorySlot(int slot)
//{
//    return true;
//}
//
//@Override
//public void setInventorySlotContents(int slot, ItemStack itemstack)
//{
//    inventory[slot] = itemstack;
//    if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
//    {
//        itemstack.stackSize = getInventoryStackLimit();
//    }
//}
//
//public ItemStack getInventorySlotContents(int slot)
//{
//    return inventory[slot].copy();
//}
//
//@Override
//public ItemStack decrStackSize(int slot, int quantity)
//{
//    if (inventory[slot] != null)
//    {
//        if (inventory[slot].stackSize <= quantity)
//        {
//            ItemStack stack = inventory[slot];
//            inventory[slot] = null;
//            return stack;
//        }
//        ItemStack split = inventory[slot].splitStack(quantity);
//        if (inventory[slot].stackSize == 0)
//        {
//            inventory[slot] = null;
//        }
//        return split;
//    }
//    else
//    {
//        return null;
//    }
//}
//
//public void setMode(int mode){
//    this.mode = mode;
//}
//
//public int getMode(){
//    return this.mode;
//}
//
//@Override
//public boolean isUseableByPlayer(EntityPlayer entityplayer)
//{
//    if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
//        return false;
//    
//    else
//        return entityplayer.getDistance((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
//    
//}
//
//public abstract Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z);
//
//@Override
//public void readFromNBT(NBTTagCompound tags)
//{
//    super.readFromNBT(tags);
//    this.invName = tags.getString("InvName");
//    NBTTagList nbttaglist = tags.getTagList("Items");
//    inventory = new ItemStack[getSizeInventory()];
//    for (int iter = 0; iter < nbttaglist.tagCount(); iter++)
//    {
//        NBTTagCompound tagList = (NBTTagCompound)nbttaglist.tagAt(iter);
//        byte slotID = tagList.getByte("Slot");
//        if (slotID >= 0 && slotID < inventory.length)
//        {
//            inventory[slotID] = ItemStack.loadItemStackFromNBT(tagList);
//        }
//    }
//    mode=tags.getInteger("Mode");
//    if(mode==0)
//        mode=2;
//}
//
//@Override
//public void writeToNBT(NBTTagCompound tags)
//{
//    super.writeToNBT(tags);
//    if (invName != null)
//        tags.setString("InvName", invName);
//    NBTTagList nbttaglist = new NBTTagList();
//    for (int iter = 0; iter < inventory.length; iter++)
//    {
//        if (inventory[iter] != null)
//        {
//            NBTTagCompound tagList = new NBTTagCompound();
//            tagList.setByte("Slot", (byte)iter);
//            inventory[iter].writeToNBT(tagList);
//            nbttaglist.appendTag(tagList);
//        }
//    }
//    tags.setTag("Items", nbttaglist);
//    if(mode==0){
//        tags.setInteger("Mode", 2);
//    }else{
//        tags.setInteger("Mode", mode);
//    }
//}
//
//public ItemStack getStackInSlotOnClosing (int slot) { return null; }
//public void openChest () {}
//public void closeChest () {}
//
//protected abstract String getDefaultName();
//
//public String getInvName()
//{
//    return this.isInvNameLocalized() ? this.invName : getDefaultName();
//}
//
//public boolean isInvNameLocalized()
//{
//    return this.invName != null && this.invName.length() > 0;
//}
//
//@Override
//public boolean isStackValidForSlot (int slot, ItemStack itemstack)
//{
//    if (slot < getSizeInventory())
//    {
//        if (inventory[slot] == null || itemstack.stackSize + inventory[slot].stackSize <= getInventoryStackLimit())
//        return true;
//    }
//    return false;
//}
//}