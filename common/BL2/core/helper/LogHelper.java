package BL2.core.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;

import BL2.lib.Reference;

public class LogHelper {

    private static Logger BL2Logger = Logger.getLogger(Reference.MOD_ID);
    
    public static void init() {

        BL2Logger.setParent(FMLLog.getLogger());
    }

    public static void log(Level logLevel, String message) {

        BL2Logger.log(logLevel, message);
    }
    
}
