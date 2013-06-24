package BL2.recipe;

import java.util.logging.Level;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import BL2.core.helper.LogHelper;
import BL2.item.BL2Items;

import cpw.mods.fml.common.Loader;

public class GT_Recipes {
    
    //Checks to see if Gregtech is loaded
    private static boolean isGTLoaded(){
        if(Loader.isModLoaded("gregtechmod")){
            return true;
        }
        return false;
    }
    
    public static void addGTRecipes(){
        if(isGTLoaded()){
            try {
                LogHelper.log(Level.INFO, "Loaded Gregtech");
                
                gregtechmod.api.GregTech_API.addAssemblerRecipe(new ItemStack(Block.blockIron), new ItemStack(Block.blockDiamond), new ItemStack(BL2Items.temp), 100, 20);
                
            }
            catch (Exception e) {
                LogHelper.log(Level.SEVERE, "Could not load Gregtech");
                e.printStackTrace(System.err);
            }
        }else{
            LogHelper.log(Level.SEVERE, "Could not load Gregtech");
        }
    }
    
}