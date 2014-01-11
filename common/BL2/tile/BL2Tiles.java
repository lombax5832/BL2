package BL2.tile;

import BL2.lib.Constants;
import cpw.mods.fml.common.registry.GameRegistry;

public class BL2Tiles {
    
    public static void registerTiles(){
//        GameRegistry.registerTileEntity(TileEntityAmmoCrafter.class, Constants.TILE_AMMO_CRAFTER_NAME);
        GameRegistry.registerTileEntity(TileEntityNewAmmoCrafter.class, Constants.TILE_NEW_AMMO_CRAFTER_NAME);
    }
}
