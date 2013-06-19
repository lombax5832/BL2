package BL2.core.config;

import java.io.File;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import BL2.lib.Constants;

public class BL2Configuration extends Configuration{
    
    public BL2Configuration(File file) {
        super(file);
    }

    @Override
    public void save() {
        Property versionProp = get(CATEGORY_GENERAL, "version", Constants.VERSION);
        versionProp.set(Constants.VERSION);
        super.save();
    }
}