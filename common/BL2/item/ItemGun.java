package BL2.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import BL2.BL2Core;
import BL2.entity.EntityBullet;
import BL2.lib.Colors;

public class ItemGun extends Item {
    public static final String[] gunNames = new String[] { "", "Pistol", "SMG",
            "Assault Rifle", "Rocket Launcher", "Sniper", "Shotgun" };
    public static final String[] Companies = new String[] { "Dahl", "Tediore",
            "Jakobs", "Maliwan", "Bandit", "Hyperion", "Vladof", "Torgue" };

    Icon[] icons = new Icon[6];

    public ItemGun(int par1) {
        super(par1);
        maxStackSize = 1;
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
        this.setHasSubtypes(true);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int i, CreativeTabs tabs, List l) {
        for (int j = 1; j < 7; j++) {
            if (j != 1 && j != 4 && j != 6) {
                ItemStack stack = new ItemStack(this, 1, j);
                // GunAtributes atr = new GunAtributes(stack);
                l.add(stack);
                // atr.save(stack);
            }
        }
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (int i = 1; i < 7; i++) {
            icons[i - 1] = ir.registerIcon("BL2:" + gunNames[i]);
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1 - 1];
    }

    public String s(int par1) {
        String ifS = "";
        if (par1 > 1) {
            ifS = "s";
        }
        return ifS;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.clear();
        GunAtributes atr = new GunAtributes(par1ItemStack);
        boolean hasAmp = hasAmp(par2EntityPlayer);
        int Amp = calcAmp(par2EntityPlayer);
        if (hasAmp) {
        } else {
        }
        String gunName = new StringBuilder().append("\u00a76")
                .append(par1ItemStack.getDisplayName()).toString();
        // par3List.add(gunNames[par1ItemStack.getItemDamage()]);
        par3List.add(gunName);
        if(atr.incendiary){
            String incendiary = new StringBuilder().append(Colors.COLOR_RED).append("Incendiary").toString();
            par3List.add(incendiary);
        }
        if(atr.cryo){
            String cryo = new StringBuilder().append(Colors.COLOR_CYAN).append("Cryogenic").toString();
            par3List.add(cryo);
        }
        par3List.add(Companies[atr.Company]);
        calcDmg(atr, hasAmp, Amp, par3List);
        par3List.add("DPS: " + getDPS(par1ItemStack, par2EntityPlayer)
                + " Hearts/second");
        par3List.add("Ammo: " + getBulletsLeftInfo(par1ItemStack));
        par3List.add("Consumes " + atr.ammoPerShot + " bullet"
                + s(atr.ammoPerShot) + " per shot.");
        par3List.add("RPM: " + 20 / atr.firetime * 60);
        par3List.add("Reload: " + (float) (atr.reloadtime / 20) + " seconds");
        if (atr.explosive == true) {
            par3List.add("Bullets explode on impact.");
        }
        par3List.add("Created by: " + atr.creator);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void calcDmg(GunAtributes atr, boolean hasAmp, int Amp, List list) {
        String Damage = "Damage: ";
        int damage = atr.damage / 2;
        String damageColor = "";
        String damagePerShotColor = "";
        Amp /= 2;
        if (hasAmp) {
            Damage = Damage + "(" + damage;
        } else {
            Damage = Damage + damage;
        }
        if (hasAmp) {
            Damage = Damage + " + " + Amp + " Amp Damage" + ")";
        }
        if (atr.pellets > 1) {
            Damage = Damage + "x" + atr.pellets + " ";
        }

        if (damage >= 4) {
            damageColor = "\u00a76";
        } else if (damage >= 1) {
            damageColor = "\u00a72";
        }
        String dmgShot = "Damage Per Shot: ";
        int dmg = 0;

        if (hasAmp || atr.pellets > 1) {
            damageColor = "\u00a77";

            if (hasAmp && atr.pellets > 1) {
                dmg = (damage + Amp) * atr.pellets;
            } else if (hasAmp) {
                dmg = damage + Amp;
            } else if (atr.pellets > 1) {
                dmg = damage * atr.pellets;
            }

            int dmgPerShot = dmg;

            if (dmgPerShot >= 15) {
                damagePerShotColor = "\u00a76";
            } else if (dmgPerShot >= 1) {
                damagePerShotColor = "\u00a72";
            }
        }

        String damageString = new StringBuilder().append(damageColor)
                .append(Damage).toString();

        list.add(damageString);

        if (hasAmp || atr.pellets > 1) {
            String damagePerShotString = new StringBuilder()
                    .append(damagePerShotColor).append(dmgShot).append(dmg)
                    .toString();
            list.add(damagePerShotString);
        }
    }

    /**
     * Returns bullets left
     * @param par1ItemStack: Itemstack of gun that you're returning current ammo for
     * @author lombax5832
     * @
     */
    public int getBulletsLeft(ItemStack par1ItemStack) {
        int Bulletsleft = new GunAtributes(par1ItemStack).bulletsleft - 1;
        if (Bulletsleft < 0)
            return 0;
        else
            return Bulletsleft;
    }

    public float getDPS(ItemStack par1ItemStack, EntityPlayer player) {
        GunAtributes atr = new GunAtributes(par1ItemStack);
        float damage = (float) atr.damage / 2;
        int pellets = atr.pellets;
        float firetime = atr.firetime;
        float DPS = (damage + calcAmp(player) * pellets) * (20 / firetime);
        float RDPS = (float) ((double) Math.round(DPS * 100) / 100);

        return RDPS;
    }

    public String getBulletsLeftInfo(ItemStack par1ItemStack) {
        int BulletsLeft = getBulletsLeft(par1ItemStack);
        String Info = null;
        Info = BulletsLeft + "/"
                + (new GunAtributes(par1ItemStack).clipsize - 1);
        return Info;
    }

    public float getReloadLeft(ItemStack par1ItemStack) {
        float Reloadleft = (float) (new GunAtributes(par1ItemStack).reloadticker - 1) / 20;
        if (Reloadleft < 0)
            return 0;
        else
            return Reloadleft;
    }

    public String getReloadLeftInfo(ItemStack par1ItemStack) {
        float ReloadLeft = getReloadLeft(par1ItemStack);
        String Info = null;
        Info = ReloadLeft + "/"
                + (new GunAtributes(par1ItemStack).reloadtime - 1) / 20;
        return Info;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        GunAtributes atr = new GunAtributes(par1ItemStack);
        atr.using = true;
        // System.out.println(System.currentTimeMillis() - atr.lasttick);
        atr.lasttick = System.currentTimeMillis();
        atr.save(par1ItemStack);
        return par1ItemStack;
    }

    public boolean noAmmo(GunAtributes atr) {
        if (atr.bulletsleft <= 1)
            return true;
        return false;
    }

    public boolean fullAmmo(GunAtributes atr) {
        if (atr.bulletsleft == atr.clipsize)
            return true;
        return false;
    }

    public static void reload(ItemStack par1ItemStack) {
        GunAtributes atr = new GunAtributes(par1ItemStack);
        atr.reloadticker = atr.reloadtime;
        atr.save(par1ItemStack);
    }

    public boolean reloading(ItemStack par1ItemStack) {
        GunAtributes atr = new GunAtributes(par1ItemStack);
        if (atr.reloadticker <= atr.reloadtime && atr.reloadticker != 0)
            return true;
        return false;
    }
    
    public void sendReload(GunAtributes atr){
        if (BL2Core.proxy.isClient()) {
            if ((atr.bulletsleft <= 1 && atr.reloadticker == 0) || BL2.core.handlers.BL2KeyHandler.reloadKey.pressed && !fullAmmo(atr)) {
                BL2Core.nethandler.sendReloaderPacket();
                return;
            }
        }
        else
        {
            return;
        }
    }
    
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, int i, int j, int k, EntityPlayer player)
    {
      return true;
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
      return true;
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
      return true;
    }
    
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }
    
    @Override
    public String getItemDisplayName(ItemStack par1ItemStack){
        return gunNames[par1ItemStack.getItemDamage()];
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World,
            Entity par3Entity, int par4, boolean par5) {
        GunAtributes atr = new GunAtributes(par1ItemStack);
        if (atr.creator == "") {
            atr.creator = par3Entity.getEntityName();
        }

        if(((EntityPlayer) par3Entity).capabilities.isCreativeMode)
            atr.bulletsleft = atr.clipsize;
        
        if (par3Entity instanceof EntityPlayer
                && ((EntityPlayer) par3Entity).getHeldItem() == par1ItemStack) {
                sendReload(atr);
            
        }

        if (par3Entity instanceof EntityPlayer
                && ((EntityPlayer) par3Entity).getHeldItem() == par1ItemStack
                && atr.reloadticker > 0) {
            boolean var5 = ((EntityPlayer) par3Entity).capabilities.isCreativeMode;

            if (var5
                    || canReload((EntityPlayer) par3Entity,
                            ((EntityPlayer) par3Entity).getHeldItem()
                                    .getItemDamage(), par1ItemStack) == true) {
                atr.reloadticker--;

                if (atr.reloadticker <= 0) {
                    int needed = bulletsNeeded(par1ItemStack);
                    for (int i = 0; i < needed; i++) {

                        if (var5
                                || reload((EntityPlayer) par3Entity,
                                        ((EntityPlayer) par3Entity)
                                                .getHeldItem().getItemDamage(),
                                        par1ItemStack)) {
                            atr.bulletsleft++;
                        }
                    }
                }
            }

            atr.save(par1ItemStack);
        } else {
            atr.reloadticker = 0;
            atr.save(par1ItemStack);
        }

        if (this.reloading(par1ItemStack) == false && atr.using
                && System.currentTimeMillis() - atr.lasttick < 250
                && par3Entity instanceof EntityPlayer
                && ((EntityPlayer) par3Entity).getHeldItem() == par1ItemStack) {
            if (atr.reloadticker > 0)
                return;

            if (atr.fireticker < atr.firetime) {
                atr.fireticker++;
                atr.save(par1ItemStack);
                return;
            }

            boolean var5 = ((EntityPlayer) par3Entity).capabilities.isCreativeMode;
            
            if (var5
                    || consumeBullet((EntityPlayer) par3Entity, atr, par1ItemStack))
            {
                atr.fireticker = 0;
                if(!var5)
                    atr.bulletsleft -= atr.ammoPerShot;

                for (int i = 0; i < atr.pellets; i++) {

                    int ampDmg = calcAmp((EntityPlayer) par3Entity);
                    EntityBullet var8 = new EntityBullet(par2World,
                            (EntityPlayer) par3Entity, atr.bulletspeed,
                            atr.damage + ampDmg, atr.explosive,
                            atr.explosivepower, atr.accuracy, atr.knockback, atr.incendiary, atr.cryo);

                    if (!par2World.isRemote) {
                        par2World.spawnEntityInWorld(var8);
                    }

                }
                par3Entity.rotationPitch -= atr.recoil * .75F;
            }

            atr.save(par1ItemStack);
        } else {
            atr.fireticker = atr.firetime - 1;
            atr.save(par1ItemStack);
        }
    }

    public int bulletsNeeded(ItemStack is) {
        GunAtributes atr = new GunAtributes(is);
        return atr.clipsize - atr.bulletsleft;
    }

    public boolean consumeBullet(EntityPlayer player, GunAtributes atr,
            ItemStack is) {
        if (atr.bulletsleft > 1)
            return true;
        return false;
    }

    public boolean canReload(EntityPlayer player, int type, ItemStack is) {
        ItemStack stack = null;

        for (int i = 0; i < 36; i++) {
            stack = player.inventory.getStackInSlot(i);

            if (stack != null && stack.getItemDamage() == type) {
                if (stack.itemID == BL2Items.bandoiler.itemID) {
                    ItemBandoiler.BandStor stor = new ItemBandoiler.BandStor(
                            stack);

                    if (stor.bullets >= 1)
                        return true;
                } else if (stack.itemID == BL2Items.bullets.itemID)
                    return true;
            }
        }

        return false;
    }

    public boolean hasAmp(EntityPlayer player) {
        ItemStack stack = null;

        for (int i = 0; i < 4; i++) {
            stack = player.inventory.armorItemInSlot(i);

            if (stack != null && stack.getItemDamage() == 5) {
                if (stack.itemID == BL2Items.shield.itemID)
                    return true;
            }
        }

        return false;
    }

    public int calcAmp(EntityPlayer player) {
        ItemStack stack = null;

        for (int i = 0; i < 4; i++) {
            stack = player.inventory.armorItemInSlot(i);

            if (stack != null && stack.getItemDamage() == 5) {
                if (stack.itemID == BL2Items.shield.itemID) {
                    ItemArmorShield.ShieldAtributes str = new ItemArmorShield.ShieldAtributes(
                            stack);

                    if (str.charge == str.maxcharge)
                        return str.amp;
                    else
                        return 0;
                }
            }
        }

        return 0;
    }

    public boolean reload(EntityPlayer player, int type, ItemStack is) {
        ItemStack stack = null;
        GunAtributes atr = new GunAtributes(is);

        for (int i = 0; i < 36; i++) {
            stack = player.inventory.getStackInSlot(i);

            if (stack != null && stack.getItemDamage() == type) {
                if (stack.itemID == BL2Items.bandoiler.itemID) {
                    ItemBandoiler.BandStor stor = new ItemBandoiler.BandStor(
                            stack);

                    if (stor.bullets >= 1) {
                        stor.bullets--;
                        atr.bulletsleft++;
                        stor.save(stack);
                        atr.save(is);
                        return true;
                    }
                } else if (stack.itemID == BL2Items.bullets.itemID) {
                    stack.stackSize--;
                    atr.bulletsleft++;
                    atr.save(is);

                    if (stack.stackSize <= 0) {
                        player.inventory.setInventorySlotContents(i, null);
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public String getTextureFile() {
        return "/BL2/textures/Items.png";
    }

    public static void genAny(GunAtributes atr) {
        float g = (float) Math.random() * 100;

        if (g < 17) {
            genPistol(atr);
        } else if (g < 33) {
            genSMG(atr);
        } else if (g < 50) {
            genAR(atr);
        } else if (g < 67) {
            genRocketLauncher(atr);
        } else if (g < 83) {
            genSniper(atr);
        } else {
            genShotgun(atr);
        }
    }

    public static void genSmgAssault(GunAtributes atr) {
        float g = (float) Math.random() * 100;

        if (g < 35) {
            genSMG(atr);
        } else if (g < 70) {
            genAR(atr);
        } else {
            genSniper(atr);
        }
    }

    public static void genPistol(GunAtributes atr) {
        atr.guntype = 1;
        atr.ammotype = atr.guntype;
        atr.clipsize *= .5;

    }

    public static void genSMG(GunAtributes atr) {
        atr.guntype = 2;
        atr.ammotype = atr.guntype;
        atr.accuracy *= 2;
    }

    public static void genAR(GunAtributes atr) {
        atr.guntype = 3;
        atr.ammotype = atr.guntype;
        atr.accuracy *= .75;
        atr.firetime *= 1.5;
    }

    public static void genRocketLauncher(GunAtributes atr) {
        atr.guntype = 4;
        atr.ammotype = atr.guntype;
        atr.clipsize *= .1;
        atr.bulletspeed *= .33;
        atr.explosivepower *= 2;
        atr.explosive = true;
    }

    public static void genSniper(GunAtributes atr) {
        atr.guntype = 5;
        atr.ammotype = atr.guntype;
        atr.clipsize = (int) (Math.random() * (15 - 5) + 5) + 1;

        if (atr.clipsize <= 8) {
            atr.damage *= 3.5;
            atr.recoil = 8;
        } else {
            atr.damage *= 1.5;
            atr.recoil = 4;
        }

        atr.reloadtime *= 2;
        // atr.recoil *= recoil;
        atr.accuracy = 1000F;
        atr.firetime *= 5;
    }

    public static void genShotgun(GunAtributes atr) {
        atr.guntype = 6;
        atr.ammotype = atr.guntype;
        atr.knockback *= 8;
        atr.damage *= 1;
        atr.pellets = (int) (Math.random() * (10 - 5) + 5);
        atr.firetime = (int) (Math.random() * (15 - 10) + 10);
        atr.clipsize *= .25;
        atr.recoil *= 3;
        atr.accuracy *= .4;
    }

    public static ItemStack getRandomGun() {
        ItemStack re = new ItemStack(BL2Items.guns);
        GunAtributes atr = new GunAtributes(re);
        atr.incendiary = false;
        atr.cryo = false;
        /*
         * set the random atributes here :D
         * 
         * to set fire time to a random number between x and y, atr.firetime =
         * (Math.random() * (y - x)) + x;
         */

        atr.Company = (int) Math.round(Math.random() * 7);
        atr.firetime = (int) Math.round(Math.random() * (15 - 5) + 5);

        // Dahl = 0, Tediore = 1, Jakobs = 2, Maliwan = 3, Bandit = 4, Hyerion =
        // 5, Vladof = 6, Torgue = 7
        // pistol = 0, smg = 1, assault rifle = 2, rocket launcher = 3, sniper =
        // 4, shotgun = 5

        atr.firetime = (int) (Math.random() * (5 - 2) + 2);
        atr.explosivepower = (float) (Math.random() * (3 - 2) + 2);
        atr.clipsize = (int) (Math.random() * (45 - 20) + 20) + 1;
        atr.reloadtime = (int) (Math.random() * (100 - 40) + 40) + 1;
        // atr.ammoPerShot = (int) ((Math.random() * (3 - 1)) + 1);
        atr.damage = (int) (Math.random() * (6 - 2) + 2) + 1;
        
        float f = (float) Math.random() * 100;
        if (f<20){
            atr.incendiary = true;
        }else if(f<40){
            atr.cryo = true;            
        }
        
        float i = (float) Math.random() * 100;

        if (i < 30) {
            atr.ammoPerShot = 2;
            // chance = num
        } else {
            atr.ammoPerShot = 1;
            // chance = 100% - num x - 1
        }
        if (atr.Company == 0) {
            genSmgAssault(atr);
            atr.clipsize = 3 + 1;
            atr.firetime = 1;
            atr.ammoPerShot = 1;
            atr.reloadtime = 20 + 1;
        }
        if (atr.Company == 1) {
            genSmgAssault(atr);

            // atr.explosive = true;
            atr.throwtoreload = true;
        }
        if (atr.Company == 2) {
            genSmgAssault(atr);
            atr.damage *= 1.5;
        }
        if (atr.Company == 3) {
            genSmgAssault(atr);
            atr.damage *= .75;
            atr.reloadtime *= .5;
        }
        // Dahl = 0, Tediore = 1, Jakobs = 2, Maliwan = 3, Bandit = 4, Hyerion =
        // 5, Vladof = 6, Torgue = 7
        // pistol = 0, smg = 1, assault rifle = 2, rocket launcher = 3, sniper =
        // 4, shotgun = 5
        if (atr.Company == 4) {
            genSmgAssault(atr);

            atr.clipsize *= 2;
        }
        if (atr.Company == 5) {
            genSmgAssault(atr);
            atr.bulletspeed *= 1.5;
        }
        // Dahl = 0, Tediore = 1, Jakobs = 2, Maliwan = 3, Bandit = 4, Hyerion =
        // 5, Vladof = 6, Torgue = 7
        // pistol = 0, smg = 1, assault rifle = 2, rocket launcher = 3, sniper =
        // 4, shotgun = 5
        if (atr.Company == 6) {
            genSmgAssault(atr);
            atr.firetime = 1;
            atr.clipsize *= 1.25;
            atr.accuracy *= 0.75;
        }
        if (atr.Company == 7) {
            genSmgAssault(atr);
            atr.explosive = true;
            atr.bulletspeed *= .1;
        }
        atr.bulletsleft = atr.clipsize;
        atr.save(re);
        re.setItemDamage(atr.guntype);
        return re;
    }

    public static class GunAtributes {
        /**
         * number of ticks between fires, should be >0
         */
        public String creator = "";
        public int firetime = 2;
        public boolean explosive;
        public float explosivepower = 1.0F;
        public int clipsize = 31 + 1;
        public int ammoPerShot = 1;
        public int Company = 0; // Dahl = 0, Tediore = 1, Jakobs = 2, Maliwan =
                                // 3, Bandit = 4, Hyerion = 5, Vladof = 6,
                                // Torgue = 7
        public int reloadtime = 40 + 1;
        public float bulletspeed = 10;
        public int damage = 4;
        public int ammotype = 2; // pistol = 1, smg = 2, assault rifle = 3,
                                 // rocket launcher = 4, sniper = 5, shotgun = 6
        public boolean throwtoreload = false;
        public int guntype; // pistol = 1, smg = 2, assault rifle = 3, rocket
                            // launcher = 4, sniper = 5, shotgun = 6
        public float accuracy = 1.0F;
        public float recoil = 1.5F;
        public int fireticker;
        public int bulletsleft;
        public int reloadticker = 1;
        public int pellets = 1;
        public int knockback;

        public boolean using;
        public long lasttick;
        
        public boolean incendiary;
        public boolean cryo;

        public GunAtributes(ItemStack it) {
            load(it);
        }

        public void save(ItemStack it) {
            boolean newTag = false;
            NBTTagCompound tag = it.getTagCompound();
            if (tag == null) {
                tag = new NBTTagCompound();
                newTag = true;
            }

            tag.setString("creator", creator);
            tag.setInteger("firetime", firetime);
            tag.setBoolean("explosive", explosive);
            tag.setFloat("explosivepower", explosivepower);
            tag.setInteger("clipsize", clipsize);
            tag.setInteger("ammoPerShot", ammoPerShot);
            tag.setInteger("Company", Company);
            tag.setInteger("reloadtime", reloadtime);
            tag.setFloat("bulletspeed", bulletspeed);
            tag.setInteger("damage", damage);
            tag.setInteger("ammotype", ammotype);
            tag.setBoolean("throwtoreload", throwtoreload);
            tag.setFloat("accuracy", accuracy);
            tag.setFloat("recoil", recoil);
            tag.setInteger("fireticker", fireticker);
            tag.setInteger("bulletsleft", bulletsleft);
            tag.setInteger("reloadticker", reloadticker);
            tag.setInteger("pellets", pellets);
            tag.setInteger("knockback", knockback);

            tag.setBoolean("using", using);
            tag.setLong("lasttick", lasttick);
            
            tag.setBoolean("incendiary", incendiary);
            tag.setBoolean("cryo", cryo);
            if (newTag) {
                it.setTagCompound(tag);
            }
        }

        public void load(ItemStack it) {
            NBTTagCompound tag = it.getTagCompound();

            if (tag == null)
                return;

            creator = tag.getString("creator");
            firetime = tag.getInteger("firetime");
            explosive = tag.getBoolean("explosive");
            explosivepower = tag.getFloat("explosivepower");
            clipsize = tag.getInteger("clipsize");
            ammoPerShot = tag.getInteger("ammoPerShot");
            Company = tag.getInteger("Company");
            reloadtime = tag.getInteger("reloadtime");
            bulletspeed = tag.getFloat("bulletspeed");
            damage = tag.getInteger("damage");
            ammotype = tag.getInteger("ammotype");
            throwtoreload = tag.getBoolean("throwtoreload");
            guntype = it.getItemDamage();
            accuracy = tag.getFloat("accuracy");
            recoil = tag.getFloat("recoil");
            fireticker = tag.getInteger("fireticker");
            bulletsleft = tag.getInteger("bulletsleft");
            reloadticker = tag.getInteger("reloadticker");
            pellets = tag.getInteger("pellets");
            knockback = tag.getInteger("knockback");

            using = tag.getBoolean("using");
            lasttick = tag.getLong("lasttick");
            
            incendiary = tag.getBoolean("incendiary");
            cryo = tag.getBoolean("cryo");
        }
    }
}