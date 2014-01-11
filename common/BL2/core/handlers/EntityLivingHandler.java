package BL2.core.handlers;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import BL2.core.helper.ItemDropHelper;
import BL2.entity.EntityBullet;

/**
 * Borderlands 2
 * 
 * EntityLivingHandler
 * 
 * @author lombax5832
 *
 */
public class EntityLivingHandler {
    
    @ForgeSubscribe
    public void onEntityLivingUpdate(LivingUpdateEvent event){
        
    }
    
    @ForgeSubscribe
    public void onEntityLivingDeath(LivingDeathEvent event){
        //Gun Drops
        if (event.source.getDamageType().equals("player")) {
            ItemDropHelper.dropGun((EntityPlayer) event.source.getSourceOfDamage(), (EntityLiving) event.entityLiving);
        }
        if (event.source.getSourceOfDamage() instanceof EntityArrow) {
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropGun((EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
        if (event.source.getSourceOfDamage() instanceof EntityBullet) {
            if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropGun((EntityPlayer) ((EntityBullet) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
        //End Gun Drops
        
        //Ammo Drops
        if (event.source.getDamageType().equals("player")) {
            ItemDropHelper.dropAmmo((EntityPlayer) event.source.getSourceOfDamage(), (EntityLiving) event.entityLiving);
        }
        if (event.source.getSourceOfDamage() instanceof EntityArrow) {
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropAmmo((EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
        if (event.source.getSourceOfDamage() instanceof EntityBullet) {
            if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropAmmo((EntityPlayer) ((EntityBullet) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
        //End Ammo Drops
        
        //Eridium Drops
        if (event.source.getDamageType().equals("player")) {
            ItemDropHelper.dropEridium((EntityPlayer) event.source.getSourceOfDamage(), (EntityLiving) event.entityLiving);
        }
        if (event.source.getSourceOfDamage() instanceof EntityArrow) {
            if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityArrow) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropEridium((EntityPlayer) ((EntityArrow) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
        if (event.source.getSourceOfDamage() instanceof EntityBullet) {
            if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity != null) {
                if (((EntityBullet) event.source.getSourceOfDamage()).shootingEntity instanceof EntityPlayer) {
                    ItemDropHelper.dropEridium((EntityPlayer) ((EntityBullet) event.source.getSourceOfDamage()).shootingEntity, (EntityLiving) event.entityLiving);
                }
            }
        }
    }
}
