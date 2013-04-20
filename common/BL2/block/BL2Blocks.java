package BL2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import BL2.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BL2Blocks {
    
    //Blocks
    public static Block crudeEridiumStill;
    public static Block crudeEridiumFlowing;
    public static Block refinedEridiumStill;
    public static Block refinedEridiumFlowing;
    
    public static void initialize(){
        crudeEridiumFlowing = (new BlockCrudeEridiumFlowing(Constants.Eridium, Material.water)).setUnlocalizedName("crudeEridium");
        LanguageRegistry.addName(crudeEridiumFlowing.setUnlocalizedName("crudeEridiumFlowing"), "Crude Eridium");
        GameRegistry.registerBlock(crudeEridiumFlowing, "Crude Eridium Flowing");
        
        crudeEridiumStill = (new BlockCrudeEridiumStill(Constants.Eridium + 1, Material.water)).setUnlocalizedName("crudeEridium");
        LanguageRegistry.addName(crudeEridiumStill.setUnlocalizedName("crudeEridiumStill"), "Crude Eridium (Still)");
        GameRegistry.registerBlock(crudeEridiumStill, "Crude Eridium Still");
    }
}
