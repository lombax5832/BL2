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

public class TileEntityAmmoCrafter extends BL2Inventory implements ISidedInventory, IPowerReceptor, BL2.tile.IMachine{

    public IPowerProvider powerProvider;
    
    public boolean isWorking = true;
    
    public int MaxMJStored = 4000;
    
    public float MJStored;
    
    public int progress = 0;
    
    public int maxProgress = 200;
    
    public int loseMJTick = 20;
    public int loseMJTicker = 0;
    public int MJToLose = 10;
    
    public TileEntityAmmoCrafter()
    {
        super(3);
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
            this.MJStored = powerProvider.getEnergyStored();
//            this.powerProvider.update(this);
            if(loseMJTicker >= loseMJTick){
                this.getPowerProvider().update(this);
            }
            loseMJTicker++;
        }
    }
    
    public boolean canCraft(){
        if(inventory[0] != null && inventory[0].getItem() == new ItemStack(Item.ingotIron).getItem())
            return true;
        return false;
    }
    
    public void removeEnergy(int value){
        this.powerProvider.useEnergy(0, value, false);
    }
    
    public int getEnergy(){
        return (int) this.MJStored;
    }
    
    @Override
    public void onInventoryChanged()
    {
        if (inventory[2] == null){
            this.setInventorySlotContents(2, AmmoOutputs.ammoOutputs[getCrafting()].copy());
        }
        if (inventory[0] != null && inventory[0].getItem() == new ItemStack(Item.ingotIron).getItem()){
            ItemStack stack = inventory[2].copy();
            stack.stackSize = AmmoOutputs.ammoOutputsAmt[AmmoOutputs.ammoDmgVals[inventory[2].getItemDamage()]];
            this.setInventorySlotContents(1, stack);
        }else{
            this.setInventorySlotContents(1, null);
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
        if (slot == 1)
        {
            super.decrStackSize(0, 1);
            if (inventory[0] == null)
                return super.decrStackSize(slot, quantity);
            else
                return inventory[1].copy();
        }
        else
        {
            ItemStack ret = super.decrStackSize(slot, quantity);
            if (inventory[0] == null)
                super.decrStackSize(1, 64);
            return ret;
        }
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
        if (slot == 0)
            return true;
        else
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
        if(i==2)
            return true;
        return false;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound tags)
    {
        super.readFromNBT(tags);
        
        powerProvider.readFromNBT(tags);
        powerProvider.configure(25, 10, 10, 1, this.MaxMJStored);
        
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
        MJStored = Math.min(tags.getInteger("energyStored"), this.MaxMJStored);
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
        tags.setInteger("energyStored", (int) MJStored);
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
//        System.out.println(this.MJStored);
//        System.out.println((int) ((float)(this.MJStored) / (float)(this.MaxMJStored)*scale));
//        if(BL2Core.proxy.isClient()){
//        }
        return (int) ((float)(this.MJStored) / (float)(this.MaxMJStored)*scale);
    }

}
