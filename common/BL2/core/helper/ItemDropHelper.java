package BL2.core.helper;

import BL2.item.ItemGun;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

/**
 * Borderlands 2
 * 
 * ItemDropHelper
 * 
 * @author lombax5832
 * 
 */
public class ItemDropHelper {
    
    private static double random;
    
    public static void dropGun(EntityPlayer player, EntityLiving entity){
        
        if(GenericMethods.isHostileEntity(entity)){
            random = Math.random();
            
            if(random < 0.05d){
                
                entity.entityDropItem(ItemGun.getRandomGun().copy(), 0);
                
            }
        }
    }
    
    public static void dropAmmo(EntityPlayer player, EntityLiving entity){
        
        if(GenericMethods.isHostileEntity(entity)){
            random = Math.random();
            
            System.out.println(random);
            
            if(random < 0.3d){
                
                ItemStack ammoType = AmmoDropHelper.ammoDrop();
                int ammoNum = AmmoDropHelper.ammoAmmount(ammoType);
                
                System.out.println(ammoNum);
                
                
                for(int x = 1; x < ammoNum; x++){
                    entity.entityDropItem(ammoType.copy(), 0);
                    System.out.println(x);
                }
            }
        }
    }
}
