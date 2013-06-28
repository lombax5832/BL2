package BL2.tile.base;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import BL2.client.gui.GuiBL2Inventory;
import BL2.container.ContainerBL2Inventory;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class TileEntityBL2 extends TileEntity{
    
    @SideOnly(Side.CLIENT)
    public GuiBL2Inventory getGui(InventoryPlayer inventoryPlayer){
        return null;
    }
    
    public ContainerBL2Inventory getContainer(InventoryPlayer inventoryPlayer){
        return null;
    }
    
    public String getGuiBackground(){
        return null;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound){
        super.readFromNBT(nbttagcompound);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound){
        super.writeToNBT(nbttagcompound);
    }
}
