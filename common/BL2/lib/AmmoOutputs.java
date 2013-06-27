package BL2.lib;

import BL2.item.BL2Items;
import net.minecraft.item.ItemStack;

public class AmmoOutputs {
    public static final ItemStack pistol = BL2Items.getItemStack(BL2Items.bullets, 1);
    public static final ItemStack smg = BL2Items.getItemStack(BL2Items.bullets, 2);
    public static final ItemStack assaultRifle = BL2Items.getItemStack(BL2Items.bullets, 3);
    public static final ItemStack rocketLauncher = BL2Items.getItemStack(BL2Items.bullets, 4);
    public static final ItemStack sniper = BL2Items.getItemStack(BL2Items.bullets, 5);
    public static final ItemStack shotgun = BL2Items.getItemStack(BL2Items.bullets, 6);
    
    public static final ItemStack[] ammoOutputs= new ItemStack[] {smg, assaultRifle, sniper};
    public static final int[] ammoDmgVals = new int[] {0, 0, 0, 1, 0, 2};
    public static final int[] ammoOutputsAmt = new int[] {24, 32, 8};
    
}
