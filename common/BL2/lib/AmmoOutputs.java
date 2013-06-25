package BL2.lib;

import BL2.item.BL2Items;
import net.minecraft.item.ItemStack;

public class AmmoOutputs {

    public static ItemStack[] ammoOutputs;
    public static int[] ammoOutputsAmt;
    public static ItemStack pistol;
    public static ItemStack smg;
    public static ItemStack assaultRifle;
    public static ItemStack rocketLauncher;
    public static ItemStack sniper;
    public static ItemStack shotgun;
    
    public static void addOutputs(){
        
        pistol = BL2Items.getItemStack(BL2Items.bullets, 1, 1);
        smg = BL2Items.getItemStack(BL2Items.bullets, 24, 2);
        assaultRifle = BL2Items.getItemStack(BL2Items.bullets, 32, 3);
        rocketLauncher = BL2Items.getItemStack(BL2Items.bullets, 1, 4);
        sniper = BL2Items.getItemStack(BL2Items.bullets, 8, 5);
        shotgun = BL2Items.getItemStack(BL2Items.bullets, 1, 6);
        
        ammoOutputs = new ItemStack[] {smg, assaultRifle, sniper};
        ammoOutputsAmt = new int[] {24, 32, 8};
        
    }
    
}
