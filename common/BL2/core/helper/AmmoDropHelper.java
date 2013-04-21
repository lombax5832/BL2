package BL2.core.helper;

import net.minecraft.item.ItemStack;
import BL2.core.math.RandomRange;
import BL2.item.BL2Items;
import BL2.item.ItemBullets;

/**
 * Borderlands 2
 * 
 * AmmoDropHelper
 * 
 * @author lombax5832
 *
 */
public class AmmoDropHelper {

    public static final String[] ammoTypes = new String[] { "", "Pistol", "SMG", "Assault Rifle", "Rocket Launcher", "Sniper", "Shotgun" };
    
    /**
     * @return Returns a random ammo type
     */
    public static ItemStack ammoDrop(){
        
        float random = (float) Math.random() * 100;
        ItemStack ammo = new ItemStack(BL2Items.bullets);
        
        if(random < 40){
            ammo.setItemDamage(2);
            return ammo;
        }
        else if(random < 80){
            ammo.setItemDamage(3);
            return ammo;
        }
        else{
            ammo.setItemDamage(5);
            return ammo;
        }
        
    }
    
    /**
     * @param bullets ItemStack of bullets
     * @return Returns a random amount of bullets based on type of bullets
     */
    public static int ammoAmmount(ItemStack bullets){
        if(bullets.getItem() instanceof ItemBullets){
            int bulletType = bullets.getItemDamage();
            
            if(bulletType == 2){
                return RandomRange.range(10, 20);
            }
            else if(bulletType == 3){
                return RandomRange.range(10, 20);
            }
            else if(bulletType == 5){
                return RandomRange.range(5, 10);
            }
            else{
                return 1;
            }
        }
        return 0;
    }
    
}
