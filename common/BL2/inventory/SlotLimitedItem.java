package BL2.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotLimitedItem extends Slot
{

    private ItemStack allowedStack;
    
    public SlotLimitedItem(IInventory builder, int par3, int par4, int par5, ItemStack stack)
    {
        super(builder, par3, par4, par5);
        allowedStack = stack;
    }

    /**
     * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
     */
    public boolean isItemValid(ItemStack stack)
    {
        return (allowedStack.copy() == stack.copy());
    }
}