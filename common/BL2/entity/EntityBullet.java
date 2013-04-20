package BL2.entity;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IProjectile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import BL2.explosion.CustomExplosion;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBullet extends Entity implements IProjectile {

    private int inData = 0;
    private int ticksInAir = 0;

    /** The amount of knockback an arrow applies when it hits a mob. */
    private int knockbackStrength;

    public EntityLiving shootingEntity;

    public float explosivepower;
    public int damage;
    public boolean explosive;

    public EntityBullet(World world) {
        super(world);
        this.setSize(0.5F, 0.5F);
    }

    public EntityBullet(World world, double i, double j, double k) {
        super(world);
        this.setSize(0.5F, 0.5F);
        this.setPosition(i, j, k);
        yOffset = 0.0F;
    }

    public EntityBullet(World par1World, EntityLiving par2EntityLiving,
            EntityLiving par3EntityLiving, float par4, float par5) {
        super(par1World);

        posY = par2EntityLiving.posY + par2EntityLiving.getEyeHeight()
                - 0.10000000149011612D;
        double var6 = par3EntityLiving.posX - par2EntityLiving.posX;
        double var8 = par3EntityLiving.posY + par3EntityLiving.getEyeHeight()
                - 0.699999988079071D - posY;
        double var10 = par3EntityLiving.posZ - par2EntityLiving.posZ;
        double var12 = MathHelper.sqrt_double(var6 * var6 + var10 * var10);

        if (var12 >= 1.0E-7D) {
            float var14 = (float) (Math.atan2(var10, var6) * 180.0D / Math.PI) - 90.0F;
            float var15 = (float) -(Math.atan2(var8, var12) * 180.0D / Math.PI);
            double var16 = var6 / var12;
            double var18 = var10 / var12;
            this.setLocationAndAngles(par2EntityLiving.posX + var16, posY,
                    par2EntityLiving.posZ + var18, var14, var15);
            yOffset = 0.0F;
            float var20 = (float) var12 * 0.2F;
            this.setThrowableHeading(var6, var8 + var20, var10, par4, par5);
        }
    }

    public EntityBullet(World world, EntityLiving el, float par3, int dam,
            boolean ex, float expwr, float accuracy, int knockback) {
        super(world);
        // for;
        explosive = ex;
        explosivepower = expwr;
        damage = dam;
        knockbackStrength = knockback;
        shootingEntity = el;

        this.setSize(0.5F, 0.5F);
        this.setLocationAndAngles(el.posX, el.posY + el.getEyeHeight(),
                el.posZ, el.rotationYaw + ((float) Math.random() - .5F) * 6
                        * (1F / accuracy),
                el.rotationPitch + ((float) Math.random() - .5F) * 6
                        * (1F / accuracy));
        posX -= MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI) * 0.16F;
        this.setPosition(posX, posY, posZ);
        yOffset = 0.0F;
        motionX = -MathHelper.sin(rotationYaw / 180.0F * (float) Math.PI)
                * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionZ = MathHelper.cos(rotationYaw / 180.0F * (float) Math.PI)
                * MathHelper.cos(rotationPitch / 180.0F * (float) Math.PI);
        motionY = -MathHelper.sin(rotationPitch / 180.0F * (float) Math.PI);
        this.setThrowableHeading(motionX, motionY, motionZ, par3 * 1.5F, 1.0F);
    }

    @Override
    protected void entityInit() {
        dataWatcher.addObject(16, Byte.valueOf((byte) 0));
    }

    /**
     * Similar to setArrowHeading, it's point the throwable entity to a x, y, z
     * direction.
     */
    @Override
    public void setThrowableHeading(double var1, double var3, double var5,
            float var7, float var8) {
        float var9 = MathHelper.sqrt_double(var1 * var1 + var3 * var3 + var5
                * var5);
        var1 /= var9;
        var3 /= var9;
        var5 /= var9;
        var1 += rand.nextGaussian() * 0.007499999832361937D * var8;
        var3 += rand.nextGaussian() * 0.007499999832361937D * var8;
        var5 += rand.nextGaussian() * 0.007499999832361937D * var8;
        var1 *= var7;
        var3 *= var7;
        var5 *= var7;
        motionX = var1;
        motionY = var3;
        motionZ = var5;
        float var10 = MathHelper.sqrt_double(var1 * var1 + var5 * var5);
        prevRotationYaw = rotationYaw = (float) (Math.atan2(var1, var5) * 180.0D / Math.PI);
        prevRotationPitch = rotationPitch = (float) (Math.atan2(var3, var10) * 180.0D / Math.PI);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Sets the position and rotation. Only difference from the other one is no bounding on the rotation. Args: posX,
     * posY, posZ, yaw, pitch
     */
    public void setPositionAndRotation2(double par1, double par3, double par5,
            float par7, float par8, int par9) {
        this.setPosition(par1, par3, par5);
        this.setRotation(par7, par8);
    }

    @Override
    @SideOnly(Side.CLIENT)
    /**
     * Sets the velocity to the args. Args: x, y, z
     */
    public void setVelocity(double par1, double par3, double par5) {
        motionX = par1;
        motionY = par3;
        motionZ = par5;

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
            float var7 = MathHelper.sqrt_double(par1 * par1 + par5 * par5);
            prevRotationYaw = rotationYaw = (float) (Math.atan2(par1, par5) * 180.0D / Math.PI);
            prevRotationPitch = rotationPitch = (float) (Math.atan2(par3, var7) * 180.0D / Math.PI);
            prevRotationPitch = rotationPitch;
            prevRotationYaw = rotationYaw;
            this.setLocationAndAngles(posX, posY, posZ, rotationYaw,
                    rotationPitch);
        }
    }

    /**
     * Called to update the entity's position/logic.
     */
    @Override
    public void onUpdate() {
        super.onUpdate();

        // EntityPlayer player = (EntityPlayer) worldObj.playerEntities.get(0);
        // System.out.println((player.posX - posX) + " " + (player.posY - posY)
        // + " " + (player.posZ - posZ));
        // System.out.println(posY );

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F) {
            float var1 = MathHelper.sqrt_double(motionX * motionX + motionZ
                    * motionZ);
            prevRotationYaw = rotationYaw = (float) (Math.atan2(motionX,
                    motionZ) * 180.0D / Math.PI);
            prevRotationPitch = rotationPitch = (float) (Math.atan2(motionY,
                    var1) * 180.0D / Math.PI);
        }

        ++ticksInAir;

        if (ticksExisted >= 20) {
            this.setDead();
        }
        Vec3 var17 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY,
                posZ);
        Vec3 var3 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX,
                posY + motionY, posZ + motionZ);
        MovingObjectPosition var4 = worldObj.rayTraceBlocks_do_do(var17, var3,
                false, true);
        var17 = worldObj.getWorldVec3Pool().getVecFromPool(posX, posY, posZ);
        var3 = worldObj.getWorldVec3Pool().getVecFromPool(posX + motionX,
                posY + motionY, posZ + motionZ);

        if (var4 != null) {
            var3 = worldObj.getWorldVec3Pool().getVecFromPool(
                    var4.hitVec.xCoord, var4.hitVec.yCoord, var4.hitVec.zCoord);
        }

        Entity var5 = null;
        List<?> var6 = worldObj.getEntitiesWithinAABBExcludingEntity(
                this,
                boundingBox.addCoord(motionX, motionY, motionZ).expand(1.0D,
                        1.0D, 1.0D));
        double var7 = 0.0D;
        Iterator<?> var9 = var6.iterator();
        float var11;

        while (var9.hasNext()) {
            Entity var10 = (Entity) var9.next();

            if (var10.canBeCollidedWith()
                    && (var10 != shootingEntity || ticksInAir >= 5)) {
                var11 = 0.3F;
                AxisAlignedBB var12 = var10.boundingBox.expand(var11, var11,
                        var11);
                MovingObjectPosition var13 = var12.calculateIntercept(var17,
                        var3);

                if (var13 != null) {
                    double var14 = var17.distanceTo(var13.hitVec);

                    if (var14 < var7 || var7 == 0.0D) {
                        var5 = var10;
                        var7 = var14;
                    }
                }
            }
        }

        if (var5 != null) {
            var4 = new MovingObjectPosition(var5);
        }

        if (var4 != null) {
            if (var4.entityHit != null) {
                int var24 = MathHelper.ceiling_double_int(damage);

                if (this.getIsCritical()) {
                    var24 += rand.nextInt(var24 / 2 + 2);
                }

                DamageSource var22 = null;

                if (shootingEntity == null) {
                    var22 = new EntityDamageSourceIndirect("arrow", this, this)
                            .setProjectile();
                    var4.entityHit.hurtResistantTime = 0;
                } else {
                    var22 = new EntityDamageSourceIndirect("arrow", this,
                            shootingEntity).setProjectile();
                    var4.entityHit.hurtResistantTime = 0;
                }

                if (this.isBurning()) {
                    var4.entityHit.setFire(5);
                }

                if (var4.entityHit.attackEntityFrom(var22, var24)) {
                    if (var4.entityHit instanceof EntityLiving) {
                        if (knockbackStrength > 0) {
                            float var25 = MathHelper.sqrt_double(motionX
                                    * motionX + motionZ * motionZ);

                            if (var25 > 0.0F) {
                                var4.entityHit.addVelocity(motionX
                                        * knockbackStrength
                                        * 0.6000000238418579D / var25, 0.1D,
                                        motionZ * knockbackStrength
                                                * 0.6000000238418579D / var25);

                            }
                        }
                    }
                } else {
                    // this.worldObj.spawnParticle("crit", this.posX +
                    // this.motionX * (double)var21 / 4.0D, this.posY +
                    // this.motionY * (double)var21 / 4.0D, this.posZ +
                    // this.motionZ * (double)var21 / 4.0D, this.motionX,
                    // this.motionY + 0.2D, this.motionZ);
                    motionX *= -0.10000000149011612D;
                    motionY *= -0.10000000149011612D;
                    motionZ *= -0.10000000149011612D;
                    rotationYaw += 180.0F;
                    prevRotationYaw += 180.0F;
                    ticksInAir = 0;
                }
            }
            if (explosive == true) {
                CustomExplosion ex = new CustomExplosion(worldObj, this,
                        lastTickPosX, lastTickPosY, lastTickPosZ,
                        explosivepower);
                ex.doExplosionA();
                ex.doExplosionB(true);
            }
            this.kill();
        }

        if (this.getIsCritical()) {
            for (int var21 = 0; var21 < 4; ++var21) {
                worldObj.spawnParticle("crit", posX + motionX * var21 / 4.0D,
                        posY + motionY * var21 / 4.0D, posZ + motionZ * var21
                                / 4.0D, -motionX, -motionY + 0.2D, -motionZ);
            }
        }

        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        double var20 = MathHelper.sqrt_double(motionX * motionX + motionZ
                * motionZ);
        rotationYaw = (float) (Math.atan2(motionX, motionZ) * 180.0D / Math.PI);

        for (rotationPitch = (float) (Math.atan2(motionY, var20) * 180.0D / Math.PI); rotationPitch
                - prevRotationPitch < -180.0F; prevRotationPitch -= 360.0F) {
            ;
        }

        while (rotationPitch - prevRotationPitch >= 180.0F) {
            prevRotationPitch += 360.0F;
        }

        while (rotationYaw - prevRotationYaw < -180.0F) {
            prevRotationYaw -= 360.0F;
        }

        while (rotationYaw - prevRotationYaw >= 180.0F) {
            prevRotationYaw += 360.0F;
        }

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch)
                * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float var23 = 0.99F;
        var11 = 0.005F;

        if (this.isInWater()) {
            for (int var26 = 0; var26 < 4; ++var26) {
                float var27 = 0.25F;
                worldObj.spawnParticle("bubble", posX - motionX * var27, posY
                        - motionY * var27, posZ - motionZ * var27, motionX,
                        motionY, motionZ);
            }

            var23 = 0.8F;
        }

        motionX *= var23;
        motionY *= 1;// (double)var23;
        motionZ *= var23;
        motionY -= var11;
        this.setPosition(posX, posY, posZ);
        this.doBlockCollisions();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setByte("inData", (byte) inData);
        par1NBTTagCompound.setByte("inGround", (byte) (onGround ? 1 : 0));
        par1NBTTagCompound.setInteger("damage", damage);
        // par1NBTTagCompound.setBoolean("explosive", this.explosive);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
        // this.explosive = par1NBTTagCompound.getBoolean("explosive");
        inData = par1NBTTagCompound.getByte("inData") & 255;
        onGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("damage")) {
            damage = par1NBTTagCompound.getInteger("damage");
        }
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they
     * walk on. used for spiders and wolves to prevent them from trampling crops
     */
    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public float getShadowSize() {
        return 0.0F;
    }

    public void setDamage(int par1) {
        damage = par1;
    }

    public double getDamage() {
        return damage;
    }

    /**
     * Sets the amount of knockback the arrow applies when it hits a mob.
     */
    public void setKnockbackStrength(int par1) {
        knockbackStrength = par1;
    }

    /**
     * If returns false, the item will not inflict any damage against entities.
     */
    @Override
    public boolean canAttackWithItem() {
        return false;
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind
     * it.
     */
    public void setIsCritical(boolean par1) {
        byte var2 = dataWatcher.getWatchableObjectByte(16);

        if (par1) {
            dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 | 1)));
        } else {
            dataWatcher.updateObject(16, Byte.valueOf((byte) (var2 & -2)));
        }
    }

    /**
     * Whether the arrow has a stream of critical hit particles flying behind
     * it.
     */
    public boolean getIsCritical() {
        byte var1 = dataWatcher.getWatchableObjectByte(16);
        return (var1 & 1) != 0;
    }
}
