package BL2.client.render;

import java.awt.Color;

import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import BL2.common.item.ItemArmorShield.Vector;

public class ShieldFX extends EntityFX {
    private Entity parent;
    private Vector v;
    float smokeParticleScale;

    public ShieldFX(World par1World, Entity p, ItemStack stack, double par2,
            double par4, double par6, Color c) {
        super(par1World, par2, par4, par6, 0.0D, 0.0D, 0.0D);
        parent = p;
        v = new Vector(par2, par4 + 1, par6);
        particleRed = c.getRed() / 255F;
        particleGreen = c.getGreen() / 255F;
        particleBlue = c.getBlue() / 255F;
        particleScale *= 0.75F;
        smokeParticleScale = particleScale;
        particleMaxAge = 20;
        noClip = true;
    }

    @Override
    public void renderParticle(Tessellator par1Tessellator, float par2,
            float par3, float par4, float par5, float par6, float par7) {
        float var8 = (particleAge + par2) / 200 * 32.0F;

        if (var8 < 0.0F) {
            var8 = 0.0F;
        }

        if (var8 > 1.0F) {
            var8 = 1.0F;
        }

        particleScale = smokeParticleScale * var8;
        super.renderParticle(par1Tessellator, par2, par3, par4, par5, par6,
                par7);
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        posX = parent.posX + v.x;
        posY = parent.posY + v.y;
        posZ = parent.posZ + v.z;

        if (particleAge++ >= particleMaxAge) {
            this.setDead();
        }

        this.setParticleTextureIndex(7 - particleAge * 8 / particleMaxAge);
    }
}
