package BL2.recipe;

import gregtechmod.api.GregTech_API;

import java.util.ArrayList;
import java.util.logging.Level;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import BL2.block.BL2Blocks;
import BL2.core.helper.LogHelper;
import BL2.item.BL2Items;
import buildcraft.BuildCraftCore;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModRecipes {
    
    //Checks to see if Gregtech is loaded
    private static boolean isGTLoaded(){
        if(Loader.isModLoaded("GregTech_Addon")){
            return true;
        }
        return false;
    }
    
    private static boolean isForestryLoaded(){
        if(Loader.isModLoaded("Forestry")){
            return true;
        }
        return false;
    }
    
    static ArrayList<ItemStack> beeCombs = OreDictionary.getOres("beeComb");
    
    public static void addModRecipes(){
        //Load GregTech Recipes
        if(isGTLoaded()){
            try {
                LogHelper.log(Level.INFO, "GregTech Loaded, adding recipes now");
                //Shield Core Recipe
                GregTech_API.addAssemblerRecipe(new ItemStack(Item.diamond), GregTech_API.getGregTechItem(3, 1, 50), BL2Items.getItemStack(BL2Items.components, 1, 0), 100, 20);
                
                //Shield Recipes
                GregTech_API.addAssemblerRecipe(BL2Items.getItemStack(BL2Items.components, 0), new ItemStack(BuildCraftCore.ironGearItem), BL2Items.getItemStack(BL2Items.shield, 1, 1), 200, 2);
                //GregTech_API.addAssemblerRecipe(BL2Items.getItemStack(BL2Items.components, 1, 0), new ItemStack(beeCombs), BL2Items.getItemStack(BL2Items.shield, 1, 1), 200, 2);
                
                GregTech_API.addBenderRecipe(BL2Items.getItemStack(BL2Items.components, 1), BL2Items.getItemStack(BL2Items.components, 2), 40, 20);
                
                GameRegistry.addRecipe(new ShapedOreRecipe(BL2Blocks.ammoCrafter, true, new Object[]{
                        "GEG",
                        "EAE",
                        "GEG",
                        Character.valueOf('A'), "bullet",
                        Character.valueOf('G'), GregTech_API.getGregTechItem(0, 1, 65),
                        Character.valueOf('E'), BL2Items.getItemStack(BL2Items.components, 2)
                    }));
                
            } catch (Throwable e) {}
        }else{
            LogHelper.log(Level.SEVERE, "Could not load GregTech");
        }
        
        //Load Forestry Recipes
        if(isForestryLoaded() && isGTLoaded()){
            try {
                LogHelper.log(Level.INFO, "Forestry and Gregtech Loaded, adding recipes now");
                
                //Bee Shield Recipe
                ArrayList<ItemStack> tList = OreDictionary.getOres("beeComb");
                for (int i = 0; i < tList.size(); i++) {
                    ItemStack tStack = tList.get(i);
                    tStack = tStack.copy();
                    tStack.stackSize = 1;
                    GregTech_API.addAssemblerRecipe(BL2Items.getItemStack(BL2Items.components, 1, 0), tStack, BL2Items.getItemStack(BL2Items.shield, 1, 5), 200, 2);
                }
            } catch (Throwable e) {}
        }
        else{
            LogHelper.log(Level.SEVERE, "Could not load Forestry");
        }
    }
    
}