package BL2.core.config;

import java.io.File;

import BL2.lib.ItemIds;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

/**
 * @title BL2MainConfig
 * 
 * Handles the Configuration of ItemIds and BlockIds
 * 
 * @author lombax5832
 *
 */
public class BL2MainConfig {

    public static BL2Configuration mainConfig;
    
    public static void loadConfig(FMLPreInitializationEvent event) {
        mainConfig = new BL2Configuration(new File(event.getModConfigurationDirectory(), "BL2/main.conf"));
        try {
            mainConfig.load();
            
            //Item Configs
            ItemIds.GUN_ID = mainConfig.getItem("Guns", ItemIds.GUN_DEFAULT).getInt();
            ItemIds.BULLET_ID = mainConfig.getItem("Bullets", ItemIds.BULLET_DEFAULT).getInt();
            ItemIds.BANDOLIER_ID = mainConfig.getItem("Bandoiler", ItemIds.BANDOLIER_DEFAULT).getInt();
            ItemIds.SHIELD_ID = mainConfig.getItem("Shield", ItemIds.SHIELD_DEFAULT).getInt();
            ItemIds.GRENADE_ID = mainConfig.getItem("Grenade", ItemIds.GRENADE_DEFAULT).getInt();
            ItemIds.TEMP_ID = mainConfig.getItem("Temp", ItemIds.TEMP_DEFAULT).getInt();
            ItemIds.CRUDE_BUCKET_ID = mainConfig.getItem("Crude_Bucket", ItemIds.CRUDE_BUCKET_DEFAULT).getInt();
        } finally {
            if (mainConfig.hasChanged()) {
                mainConfig.save();
            }
        }
    }
    
}
