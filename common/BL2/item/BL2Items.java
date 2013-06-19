package BL2.item;

import net.minecraft.item.Item;
import BL2.lib.ItemIds;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class BL2Items {
    
    // Items
    public static Item guns;
    public static Item bullets;
    public static Item bandoiler;
    public static Item shield;
    public static Item grenade;
    public static Item temp;
    public static Item bucketCrudeEridium;
    public static Item bucketRefinedEridium;
    
    public static void initialize() {
        
        guns = new ItemGun(ItemIds.GUN_ID).setUnlocalizedName("Gun");
        
        bullets = new ItemBullets(ItemIds.BULLET_ID).setUnlocalizedName("Bullets");
        
        bandoiler = new ItemBandoiler(ItemIds.BANDOLIER_ID).setUnlocalizedName("Bandoiler");
        
        shield = new ItemArmorShield(ItemIds.SHIELD_ID, 0, 1).setUnlocalizedName("ItemArmorShield");
        
        grenade = new ItemGrenade(ItemIds.GRENADE_ID).setUnlocalizedName("Grenade");
        
        temp = new ItemTemp(ItemIds.TEMP_ID).setUnlocalizedName("Temp");
        
        bucketCrudeEridium = (new ItemBucketEridium(ItemIds.CRUDE_BUCKET_ID)).setUnlocalizedName("bucketCrudeEridium").setContainerItem(Item.bucketEmpty);
        LanguageRegistry.addName(bucketCrudeEridium, "Crude Eridium Bucket");
    }
}
