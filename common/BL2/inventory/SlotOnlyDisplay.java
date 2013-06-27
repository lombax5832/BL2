package BL2.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOnlyDisplay extends Slot
{

    public SlotOnlyDisplay(IInventory builder, int par3, int par4, int par5)
    {
        super(builder, par3, par4, par5);
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return false;
    }
    
    public boolean canTakeStack(EntityPlayer par1EntityPlayer)
    {
        return false;
    }
}