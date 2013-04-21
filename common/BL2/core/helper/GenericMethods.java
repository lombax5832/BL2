package BL2.core.helper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.IMob;

public class GenericMethods {
    
    public static boolean isHostileEntity(EntityLiving entity) {

        if (entity instanceof IMob)
            return true;
        else
            return false;
    }
    
}
