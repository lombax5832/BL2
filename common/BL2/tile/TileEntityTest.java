//package BL2.tile;
//
//import net.minecraft.entity.player.EntityPlayer;
//import net.minecraft.inventory.IInventory;
//import net.minecraft.item.ItemStack;
//import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.tileentity.TileEntity;
//
//public class TileEntityTest extends TileEntity implements IInventory{
//    
//    public int customField;
//    
//    private ItemStack[] inv;
//    
//    public TileEntityTest(){
//        inv = new ItemStack[2];
//    }
//    
//    @Override
//    public void writeToNBT(NBTTagCompound par1){
//        super.writeToNBT(par1);
//        par1.setInteger("customField", customField);
//    }
//    
//    @Override
//    public void readFromNBT(NBTTagCompound par1){
//        super.readFromNBT(par1);
//        this.customField = par1.getInteger("customField");
//    }
//
//    @Override
//    public int getSizeInventory() {
//        return inv.length;
//    }
//
//    @Override
//    public ItemStack getStackInSlot(int i) {
//        return inv[i];
//    }
//
//    @Override
//    public ItemStack decrStackSize(int slot, int amt) {
//        ItemStack stack = getStackInSlot(slot);
//        if (stack != null) {
//                if (stack.stackSize <= amt) {
//                        setInventorySlotContents(slot, null);
//                } else {
//                        stack = stack.splitStack(amt);
//                        if (stack.stackSize == 0) {
//                                setInventorySlotContents(slot, null);
//                        }
//                }
//        }
//        return stack;
//    }
//
//    @Override
//    public ItemStack getStackInSlotOnClosing(int slot) {
//        ItemStack stack = getStackInSlot(slot);
//        if (stack != null) {
//                setInventorySlotContents(slot, null);
//        }
//        return stack;
//    }
//
//    @Override
//    public void setInventorySlotContents(int slot, ItemStack stack) {
//        inv[slot] = stack;
//        if (stack != null && stack.stackSize > getInventoryStackLimit()) {
//                stack.stackSize = getInventoryStackLimit();
//        }
//    }
//
//    @Override
//    public String getInvName() {
//        return "BL2.TileEntityTest";
//    }
//
//    @Override
//    public boolean isInvNameLocalized() {
//        return false;
//    }
//
//    @Override
//    public int getInventoryStackLimit() {
//        return 64;
//    }
//
//    @Override
//    public boolean isUseableByPlayer(EntityPlayer player) {
//        return worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) == this &&
//        player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
//    }
//
//    @Override
//    public void openChest() {
//    }
//
//    @Override
//    public void closeChest() {
//    }
//
//    @Override
//    public boolean isStackValidForSlot(int i, ItemStack itemstack) {
//        return true;
//    }
//}
