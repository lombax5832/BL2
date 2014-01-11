package BL2.tile;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import BL2.client.gui.GuiBL2Inventory;
import BL2.client.gui.GuiNewAmmoCrafter;
import BL2.container.ContainerBL2Inventory;
import BL2.container.ContainerNewAmmoCrafter;
import BL2.tile.base.TileEntityBL2Powered;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityNewAmmoCrafter extends TileEntityBL2Powered{

    private static final int ACTIVATION_ENERGY = 200;
    
    public TileEntityNewAmmoCrafter() {
        super(ACTIVATION_ENERGY);
    }

    @Override
    public String getGuiBackground(){
        return "AmmoCrafter2.png";
    }
    
    @Override
    public void updateEntity(){
        super.updateEntity();
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public GuiBL2Inventory getGui(InventoryPlayer inventoryPlayer)
    {
        return new GuiNewAmmoCrafter(getContainer(inventoryPlayer), this);
    }
    
    @Override
    public ContainerBL2Inventory getContainer (InventoryPlayer inventoryplayer)
    {
        return new ContainerNewAmmoCrafter(this, inventoryplayer);
    }
    
    @Override
    public int getSizeInventory() {
        return 6;
    }

    @Override
    public int getWorkMax() {
        return 100;
    }

    @Override
    public int getEnergyStoredMax() {
        return 4000;
    }
    
    @Override
    public String getInvName(){
        return "Ammo Crafter 2";
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
    }

    @Override
    public int[] getAccessibleSlotsFromSide (int side)
    {
        int[] canAccessFrom;
        canAccessFrom = new int[] {0, 5};
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
        if(i==5)
            return true;
        return false;
    }

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if (i < getSizeInventory())
	    {
	        if (inventory[i] == null || itemstack.stackSize + inventory[i].stackSize <= getInventoryStackLimit())
	        return true;
	    }
	    return false;
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return this.powerProvider.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {
	}

	@Override
	public World getWorld() {
		return this.getWorldObj();
	}
    
}
