package BL2.core.helper;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import BL2.item.BL2Items;
import BL2.item.ItemGun;

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
            
            if(random < 0.02d){
                
                entity.entityDropItem(ItemGun.getRandomGun().copy(), 0);
                
            }
        }
    }
    
    public static void dropAmmo(EntityPlayer player, EntityLiving entity){
        
        if(GenericMethods.isHostileEntity(entity)){
            random = Math.random();
            
            if(random < 0.05d){
                
                ItemStack ammoType = AmmoDropHelper.ammoDrop();
                int ammoNum = AmmoDropHelper.ammoAmmount(ammoType);                
                
                for(int x = 1; x < ammoNum; x++){
                    entity.entityDropItem(ammoType.copy(), 0);
                }
            }
        }
    }
    
public static void dropEridium(EntityPlayer player, EntityLiving entity){
        
        if(GenericMethods.isHostileEntity(entity)){
            random = Math.random();
            
            if(random < 0.02d){
                ItemStack stack = BL2Items.getItemStack(BL2Items.components, 1);
                entity.entityDropItem(stack.copy(), 0);
            }
        }
    }
}
