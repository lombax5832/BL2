package BL2.tile.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.ForgeDirection;

public abstract class TileEntityBL2Inventory extends TileEntityBL2 implements ISidedInventory{
    
    protected String inventoryName;
    protected boolean inventoryHasName = false;
    
    protected ItemStack[] inventory;
    
    protected TileEntityBL2Inventory(){
        inventory = new ItemStack[getSizeInventory()];
    }
    
    @Override
    public String getInvName(){
        return inventoryHasName ? inventoryName : inventoryName;
    }
    
    @Override
    public boolean isInvNameLocalized(){
        return inventoryHasName;
    }
    
    public void setInvName(String name){
        this.inventoryName = name;
        this.inventoryHasName = name != null && name.length() > 0;
    }
    
    @Override
    public void updateEntity(){
        super.updateEntity();
    }
    
    @Override
    public ItemStack getStackInSlot(int slot){
        return inventory[slot];
    }
    
    @Override
    public void openChest(){}
    
    @Override
    public void closeChest(){}
    
    protected void onBL2InventoryChanged(){}
    
    @Override
    public ItemStack decrStackSize(int slot, int size)
    {
        if(inventory[slot] != null)
        {
            if(inventory[slot].stackSize <= size)
            {
                ItemStack itemstack = inventory[slot];
                inventory[slot] = null;
                onBL2InventoryChanged();
                return itemstack;
            }
            ItemStack itemstack1 = inventory[slot].splitStack(size);
            if(inventory[slot].stackSize == 0)
            {
                inventory[slot] = null;
            }
            onBL2InventoryChanged();
            return itemstack1;
        }
        else
        {
            onBL2InventoryChanged();
            return null;
        }
    }
    
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemstack){
        inventory[slot] = itemstack;
        if(itemstack != null && itemstack.stackSize > getInventoryStackLimit())
        {
            itemstack.stackSize = getInventoryStackLimit();
        }
        onBL2InventoryChanged();
    }
    
    @Override
    public int getInventoryStackLimit(){
        return 64;
    }
    
    @Override
    public boolean isStackValidForSlot(int slot, ItemStack itemstack){
        return true;
    }
    
    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer){
        if(worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this){
            return false;
        }
        return entityplayer.getDistanceSq(xCoord + 0.5D, yCoord + 0.5D, zCoord + 0.5D) <= 64D;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound){
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
        inventory = new ItemStack[getSizeInventory()];
        for(int i = 0; i < nbttaglist.tagCount(); i++)
        {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
            int j = nbttagcompound1.getByte("Slot") & 0xff;
            if(j >= 0 && j < inventory.length)
            {
                ItemStack s = new ItemStack(0, 0, 0);
                s.readFromNBT(nbttagcompound1);
                inventory[j] = s;
            }
        }
        onBL2InventoryChanged();
        
        for(int i = 0; i < getSizeInventory(); i++)
        {
            if(inventory[i] != null && inventory[i].getItem() == null)
            {
                inventory[i] = null;
            }
        }
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound){
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();
        for(int i = 0; i < inventory.length; i++)
        {
            if(inventory[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                inventory[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }
        nbttagcompound.setTag("Items", nbttaglist);
    }
    
    @Override
    public ItemStack getStackInSlotOnClosing(int slot){
        return null;
    }
    
    public boolean shouldDropSlotWhenBroken(int slot){
        return true;
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide(int side){
        int start = getStartInventorySide(ForgeDirection.getOrientation(side));
        int size = getSizeInventorySide(ForgeDirection.getOrientation(side));
        
        int[] slots = new int[size];
        for(int i = 0; i < size; i++)
        {
            slots[i] = i + start;
        }
        return slots;
    }
    
    public int getStartInventorySide(ForgeDirection side){
        return 0;
    }
    
    public int getSizeInventorySide(ForgeDirection side){
        return getSizeInventory();
    }
    
    @Override
    public boolean canInsertItem(int slot, ItemStack itemstack, int side)
    {
        return this.isStackValidForSlot(slot, itemstack);
    }
    
    @Override
    public boolean canExtractItem(int slot, ItemStack itemstack, int side)
    {
        return false;
    }
}
