package BL2.common.handler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface IItemTickListener {
	boolean onTick(EntityPlayer var1, ItemStack var2);
}
