package BL2.core.config;

import java.io.File;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class BL2MainConfig {

    public static BL2Configuration mainConfig;
    
    public void loadConfig(FMLPreInitializationEvent event) {
        mainConfig = new BL2Configuration(new File(event.getModConfigurationDirectory(), "BL2/main.conf"));
        try {
            mainConfig.load();
        } finally {
            
        }
    }
    
}
