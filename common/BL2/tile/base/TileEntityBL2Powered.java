package BL2.tile.base;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.ForgeDirection;
import BL2.Utils.PowerProviderAdvanced;
import buildcraft.api.power.IPowerProvider;
import buildcraft.api.power.IPowerReceptor;

public abstract class TileEntityBL2Powered extends TileEntityBL2Inventory implements IPowerReceptor{
    private int energyStored;
    protected int energyActivation;
    
    private int progress;
    
    //Buildcraft
    private IPowerProvider powerProvider;
    
    protected TileEntityBL2Powered(int activationEnergy){
        super();
        this.energyActivation = activationEnergy;
        powerProvider = new PowerProviderAdvanced();
        powerProvider.configure(25, 10, 10, 1, 4000);
    }
    
    public void updateEntity(){
        super.updateEntity();
        
        energyStored = Math.min(energyStored, getEnergyStoredMax());
        
        if(worldObj.isRemote)
            return;
        
        if(getPowerProvider() != null){
            getPowerProvider().update(this);
            
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
        powerProvider.configure(25, 10, 10, 1, 4000);
    }
    
    //Buildcraft Power Methods
    @Override
    public void setPowerProvider(IPowerProvider provider){
        powerProvider = provider;
    }
    
    @Override
    public IPowerProvider getPowerProvider(){
        return powerProvider;
    }
    
    @Override
    public int powerRequest(ForgeDirection from){
        return Math.max((getEnergyStoredMax() - getEnergyStored()), 0);
    }
    
    @Override
    public final void doWork(){}
}
