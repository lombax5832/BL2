package BL2.recipe;

import gregtechmod.api.GregTech_API;

import java.util.logging.Level;

import buildcraft.BuildCraftCore;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import BL2.core.helper.LogHelper;
import BL2.item.BL2Items;
import cpw.mods.fml.common.Loader;

public class GT_Recipes {
    
    //Checks to see if Gregtech is loaded
    private static boolean isGTLoaded(){
        if(Loader.isModLoaded("GregTech_Addon")){
            return true;
        }
        return false;
    }
    
    public static void addGTRecipes(){
        if(isGTLoaded()){
            try {
                //Shield Core Recipe
                GregTech_API.addAssemblerRecipe(new ItemStack(Item.diamond), GregTech_API.getGregTechItem(3, 1, 50), BL2Items.getItemStack(BL2Items.components, 1, 0), 100, 20);
                
                //Shield Recipes
                GregTech_API.addAssemblerRecipe(BL2Items.getItemStack(BL2Items.components, 1, 0), new ItemStack(BuildCraftCore.ironGearItem), BL2Items.getItemStack(BL2Items.shield, 1, 1), 200, 2);
            } catch (Throwable e) {}
        }else{
            LogHelper.log(Level.SEVERE, "Could not load Gregtech");
        }
    }
    
}