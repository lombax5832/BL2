package BL2.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.MinecraftForge;
import BL2.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BL2Blocks {
    
    //Blocks
    public static Block crudeEridiumStill;
    public static Block crudeEridiumFlowing;
    public static Block refinedEridiumStill;
    public static Block refinedEridiumFlowing;
    public static Block ammoCrafter;
    public static Block ammoCrafterNew;
    
    public static void initialize(){
//        crudeEridiumFlowing = (new BlockCrudeEridiumFlowing(Constants.Eridium, Material.water)).setUnlocalizedName("crudeEridium");
//        LanguageRegistry.addName(crudeEridiumFlowing.setUnlocalizedName("crudeEridiumFlowing"), "Crude Eridium");
//        GameRegistry.registerBlock(crudeEridiumFlowing, "Crude Eridium Flowing");
//        
//        crudeEridiumStill = (new BlockCrudeEridiumStill(Constants.Eridium + 1, Material.water)).setUnlocalizedName("crudeEridium");
//        LanguageRegistry.addName(crudeEridiumStill.setUnlocalizedName("crudeEridiumStill"), "Crude Eridium (Still)");
//        GameRegistry.registerBlock(crudeEridiumStill, "Crude Eridium Still");
        
        ammoCrafter = (new BlockAmmoCrafter(Constants.Eridium+2, Material.rock)).setUnlocalizedName("blockAmmoCrafter");
        LanguageRegistry.addName(ammoCrafter, "Ammo Crafter");
        MinecraftForge.setBlockHarvestLevel(ammoCrafter, "pickaxe", 2);
        GameRegistry.registerBlock(ammoCrafter, "blockAmmoCrafter");
        
        ammoCrafterNew = (new BlockNewAmmoCrafter(Constants.Eridium+3)).setUnlocalizedName("blockAmmoCrafterNew");
        LanguageRegistry.addName(ammoCrafterNew, "New Ammo Crafter");
        MinecraftForge.setBlockHarvestLevel(ammoCrafterNew, "pickaxe", 2);
        GameRegistry.registerBlock(ammoCrafterNew, "blockAmmoCrafterNew");
    }
}
