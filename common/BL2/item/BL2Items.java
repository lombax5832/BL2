package BL2.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import BL2.lib.ItemIds;

public class BL2Items {
    
    // Items
    public static Item guns;
    public static Item bullets;
    public static Item bandoiler;
    public static Item shield;
    public static Item grenade;
    public static Item temp;
    public static Item components;
    public static Item bucketCrudeEridium;
    public static Item bucketRefinedEridium;
    
    public static void initialize() {
        
        guns = new ItemGun(ItemIds.GUN_ID).setUnlocalizedName("gun");
        
        bullets = new ItemBullets(ItemIds.BULLET_ID).setUnlocalizedName("bullets");
        
        bandoiler = new ItemBandoiler(ItemIds.BANDOLIER_ID).setUnlocalizedName("bandoiler");
        
        shield = new ItemArmorShield(ItemIds.SHIELD_ID, 0, 1).setUnlocalizedName("itemArmorShield");
        
        grenade = new ItemGrenade(ItemIds.GRENADE_ID).setUnlocalizedName("grenade");
        
        temp = new ItemTemp(ItemIds.TEMP_ID).setUnlocalizedName("temp");
        
        components = new ItemComponents(ItemIds.COMPONENTS_ID).setUnlocalizedName("components");
        
//        bucketCrudeEridium = (new ItemBucketEridium(ItemIds.CRUDE_BUCKET_ID)).setUnlocalizedName("bucketCrudeEridium").setContainerItem(Item.bucketEmpty);
//        LanguageRegistry.addName(bucketCrudeEridium, "Crude Eridium Bucket");
    }
    
    public static ItemStack getItemStack(Item item, int amount, int meta){
        
        ItemStack stack = new ItemStack(item, amount, meta);
        
        return stack;
    }
    
    public static ItemStack getItemStack(Item item,  int meta){
        
        ItemStack stack = new ItemStack(item, 1, meta);
        
        return stack;
    }
}
