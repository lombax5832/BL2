package BL2.liquid;

import BL2.block.BL2Blocks;
import BL2.item.BL2Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.liquids.LiquidContainerData;
import net.minecraftforge.liquids.LiquidContainerRegistry;
import net.minecraftforge.liquids.LiquidDictionary;
import net.minecraftforge.liquids.LiquidStack;

public class BL2Liquid {

    //LiquidStack
    public static LiquidStack crudeEridiumLiquid;
    public static LiquidStack refinedEridiumLiquid;
    
    public static void initialize(){
        crudeEridiumLiquid = LiquidDictionary.getOrCreateLiquid("Crude Eridium", new LiquidStack(BL2Blocks.crudeEridiumStill, 1));
        
        LiquidContainerRegistry.registerLiquid(new LiquidContainerData(LiquidDictionary.getLiquid("Crude Eridium", LiquidContainerRegistry.BUCKET_VOLUME), new ItemStack(
                BL2Items.bucketCrudeEridium), new ItemStack(Item.bucketEmpty)));
    }
}
