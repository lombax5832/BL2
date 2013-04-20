package BL2.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import BL2.lib.Constants;
import net.minecraft.item.Item;

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
        
        guns = new ItemGun(Constants.gunId).setUnlocalizedName("Gun");
        bullets = new ItemBullets(Constants.bulletId).setUnlocalizedName("Bullets");
        bandoiler = new ItemBandoiler(Constants.bandoilerId).setUnlocalizedName("Bandoiler");
        shield = new ItemArmorShield(Constants.shieldId, 0, 1)
                .setUnlocalizedName("ItemArmorShield");
        grenade = new ItemGrenade(Constants.grenadeId).setUnlocalizedName("Grenade");
        temp = new ItemTemp(Constants.tempId).setUnlocalizedName("Temp");
        
        bucketCrudeEridium = (new ItemBucketEridium(Constants.crudeBucketId)).setUnlocalizedName("bucketCrudeEridium").setContainerItem(Item.bucketEmpty);
        LanguageRegistry.addName(bucketCrudeEridium, "Crude Eridium Bucket");
    }
}
