package BL2.tile;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import BL2.Utils.PowerProviderAdvanced;
import BL2.container.ContainerAmmoCrafter;
import BL2.inventory.BL2Inventory;
import BL2.lib.AmmoOutputs;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public class TileEntityAmmoCrafter extends BL2Inventory implements ISidedInventory, IPowerReceptor, IMachine{

    public IPowerProvider powerProvider;
    
    //Drawing power
    public boolean isWorking = true;
    
    //Doing work
    public boolean active = false;
    public boolean activeNextTick = false;
    
    //mj per tick, and mj per cycle
    public final int MJPerCycle = 400;
    public int MJPerTick = 10;
    
    public final int MaxMJStored = 4000;
    
    public int progress = 0;
    
    public final int maxProgress = MJPerCycle;
    
    //MJ that client sees
    protected int MJToDisplay;
    
    public TileEntityAmmoCrafter()
    {
        super(6);
        powerProvider = new PowerProviderAdvanced();
        powerProvider.configure(20, 25, 100, 25, MaxMJStored);
    }

    @Override
    public String getDefaultName ()
    {
        return "machine.ammoCrafter";
    }

    @Override
    public Container getGuiContainer (InventoryPlayer inventoryplayer, World world, int x, int y, int z)
    {
        return new ContainerAmmoCrafter(inventoryplayer, this);
    }
    
    public boolean canUpdate(){
        return true;
    }
    
    @Override
    public void updateEntity() {
        if(true){
            super.updateEntity();
            
            if(activeNextTick){
                activeNextTick = false;
                inventory[3]=decrStackSize(0, 1);
            }
            
            setEnergy(Math.min(getEnergy(), MaxMJStored));
            
            if(worldObj.isRemote)
            {
                return;
            }
            
            if(getPowerProvider() != null)
            {
                getPowerProvider().update(this);
                
                if(canCraft())
                {
                    active = true;
                    activeNextTick = true;
                }
                
            }
            if(active){
                progress += (int)(getPowerProvider().useEnergy(1, MJPerTick, true));
                if(progress>=maxProgress){
                    ItemStack stack = inventory[2].copy();
                    stack.stackSize = AmmoOutputs.ammoOutputsAmt[AmmoOutputs.ammoDmgVals[inventory[2].getItemDamage()]];
                    this.setInventorySlotContents(1, stack);
                    this.setInventorySlotContents(3, null);
                    progress=0;
                    active=false;
                }
            }
            this.MJToDisplay = (int) this.powerProvider.getEnergyStored();
//            System.out.println(this.canCraft());
        }
    }
    
    public boolean canCraft(){
        if(inventory[0] != null && inventory[2] != null && inventory[3]==null && inventory[1] == null && inventory[0].getItem() == new ItemStack(Item.ingotIron).getItem() && getEnergy() >= MJPerCycle && !active)
            return true;
        return false;
    }
    
    public void removeEnergy(int value){
        this.powerProvider.useEnergy(0, value, false);
    }
    
    //Getting and setting the displayed Energy
    public int getEnergy(){
        return this.MJToDisplay;
    }
    
    public void setEnergy(int value){
        this.MJToDisplay = value;
    }
    
    //Getting and Setting the progress/work
    public int getProgress(){
        return this.progress;
    }
    
    public void setProgress(int value){
        this.progress = value;
    }
    
    @Override
    public void onInventoryChanged()
    {
        if (inventory[2] == null){
            this.setInventorySlotContents(2, AmmoOutputs.ammoOutputs[getCrafting()].copy());
        }
        if(crafting == 10){
            crafting = AmmoOutputs.ammoDmgVals[inventory[2].getItemDamage()];
        }
        super.onInventoryChanged();
    }
    private int crafting = this.mode;
    
    public void setToCraft(int craft){
        this.crafting = craft;
        this.mode = craft;
        this.onInventoryChanged();
    }
    
    public int getCrafting(){
        return this.mode;
    }
    
    @Override
    public ItemStack decrStackSize(int slot, int quantity)
    {
//        if (slot == 1)
//        {
//            super.decrStackSize(0, 1);
//            if (inventory[0] == null)
//                return super.decrStackSize(slot, quantity);
//            else
//                return inventory[1].copy();
//        }
//        else
//        {
            ItemStack ret = super.decrStackSize(slot, quantity);
//            if (inventory[0] == null)
//                super.decrStackSize(1, 64);
            return ret;
//        }
    }
    
    @SuppressWarnings("unused")
    public void altDecrStackSize(int slot, int quantity)
    {
        if (inventory[slot] != null)
        {
            if (inventory[slot].stackSize <= quantity)
            {
                ItemStack stack = inventory[slot];
                inventory[slot] = null;
                return;
            }
            ItemStack split = inventory[slot].splitStack(quantity);
            if (inventory[slot].stackSize == 0)
            {
                inventory[slot] = null;
            }
            return;
        }
    }
    
    @Override
    public boolean canDropInventorySlot(int slot)
    {
        switch(slot){
            case 0: return true;
            case 1: return true;
        }
        return false;
    }
    
    @Override
    public int[] getAccessibleSlotsFromSide (int side)
    {
        int[] canAccessFrom;
        canAccessFrom = new int[] {0, 1};
        return canAccessFrom;
    }

    @Override
    public boolean canInsertItem (int i, ItemStack itemstack, int j)
    {
        if(i==0)
            return true;
        return false;
    }

    @Override
    public boolean canExtractItem (int i, ItemStack itemstack, int j)
    {
        if(i==1)
            return true;
        return false;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tags)
    {
        super.readFromNBT(tags);
        
        powerProvider.readFromNBT(tags);
        powerProvider.configure(20, 25, Integer.MAX_VALUE, 25, MaxMJStored);
        
        this.invName = tags.getString("InvName");
        NBTTagList nbttaglist = tags.getTagList("Items");
        inventory = new ItemStack[getSizeInventory()];
        for (int iter = 0; iter < nbttaglist.tagCount(); iter++)
        {
            NBTTagCompound tagList = (NBTTagCompound)nbttaglist.tagAt(iter);
            byte slotID = tagList.getByte("Slot");
            if (slotID >= 0 && slotID < inventory.length)
            {
                inventory[slotID] = ItemStack.loadItemStackFromNBT(tagList);
            }
        }
        mode=tags.getInteger("Mode");
        if(mode==0)
            mode=2;
        active=tags.getBoolean("Active");
    }

    @Override
    public void writeToNBT(NBTTagCompound tags)
    {
        super.writeToNBT(tags);
        
        powerProvider.writeToNBT(tags);
        
        if (invName != null)
            tags.setString("InvName", invName);
        NBTTagList nbttaglist = new NBTTagList();
        for (int iter = 0; iter < inventory.length; iter++)
        {
            if (inventory[iter] != null)
            {
                NBTTagCompound tagList = new NBTTagCompound();
                tagList.setByte("Slot", (byte)iter);
                inventory[iter].writeToNBT(tagList);
                nbttaglist.appendTag(tagList);
            }
        }
        tags.setTag("Items", nbttaglist);
        if(mode==0){
            tags.setInteger("Mode", 2);
        }else{
            tags.setInteger("Mode", mode);
        }
        tags.setBoolean("Active", active);
    }

    @Override
    public void setPowerProvider(IPowerProvider provider) {
        powerProvider = provider;
    }

    @Override
    public IPowerProvider getPowerProvider() {
        return powerProvider;
    }

    @Override
    public void doWork() {
    }
    
    @Override
    public int powerRequest(ForgeDirection from) {
        if (isActive()){
            return (int) Math.ceil(Math.min(getPowerProvider().getMaxEnergyReceived(), getPowerProvider().getMaxEnergyStored()
                    - getPowerProvider().getEnergyStored()));
        }
        else
            return 0;
    }

    @Override
    public boolean isActive() {
        return isWorking;
    }

    @Override
    public boolean manageLiquids() {
        return false;
    }

    @Override
    public boolean manageSolids() {
        return true;
    }

    @Override
    public boolean allowActions() {
        return false;
    }
    
    public int getMJLeftScaled(int scale){
        return (int) ((float)(this.getEnergy()) / (float)(this.MaxMJStored)*scale);
    }
    
    public int getWorkLeftScaled(int scale){
      return (int) ((float)(this.getProgress()) / (float)(this.maxProgress)*scale);
  }

}
