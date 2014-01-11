package BL2.tile.base;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.Type;

public abstract class TileEntityBL2Powered extends TileEntityBL2Inventory implements IPowerReceptor{
    protected int energyStored;
    protected int energyActivation;
    
    
    
    private int progress;
    
    public int maxprogress;
    
    //Buildcraft
    protected PowerHandler powerProvider;
    
    protected TileEntityBL2Powered(int activationEnergy){
        super();
        this.energyActivation = activationEnergy;
        maxprogress=energyActivation;
        powerProvider = new PowerHandler(this, Type.MACHINE);
        powerProvider.configure(2, 25, 200, 4000);
        powerProvider.configurePowerPerdition(0, 0);
    }
    
    public void updateEntity(){
        super.updateEntity();
        
        if(worldObj.isRemote)
            return;
        
        energyStored = Math.min((int)powerProvider.getEnergyStored(), getEnergyStoredMax());
        
        if(powerProvider != null){
            powerProvider.update();
            
        }
    }
    
    public int getWorkDone()
    {
        return progress;
    }
    
    public abstract int getWorkMax();
    
    public void setWorkDone(int work)
    {
        progress = work;
    }
    
    public void setEnergyStored(int energy){
        energyStored = energy;
    }
    
    public int getEnergyStored(){
        return energyStored;
    }
    
    public abstract int getEnergyStoredMax();
    
    //NBT
    @Override
    public void writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        
        nbt.setInteger("energyStored", energyStored);
        nbt.setInteger("progress", progress);

        powerProvider.writeToNBT(nbt);
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt){
        super.readFromNBT(nbt);
        
        energyStored = Math.min(nbt.getInteger("energyStored"), getEnergyStoredMax());
        progress = Math.min(nbt.getInteger("progress"), getWorkMax());
        
        powerProvider.readFromNBT(nbt);
        powerProvider.configure(2, 25, 200, 4000);
    }

    public int getEnergyLeftScaled(int scale){
    	return (int) ((float)(energyStored) / (float)(this.getEnergyStoredMax())*scale);
    }

	public int getProgress() {
		return progress;
	}	

}
