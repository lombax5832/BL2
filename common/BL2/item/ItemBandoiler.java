package BL2.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemBandoiler extends Item {
    public static final String[] ammoTypes = new String[] { "", "Pistol",
            "SMG", "Assault Rifle", "Rocket Launcher", "Sniper", "Shotgun" };

    Icon[] icons = new Icon[6];

    public ItemBandoiler(int id) {
        super(id);
        maxStackSize = 1;
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
        this.setHasSubtypes(true);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int i, CreativeTabs tabs, List l) {
        for (int j = 1; j < 7; j++) {
            ItemStack stack = new ItemStack(this, 1, j);
            BandStor stor = new BandStor(stack);
            l.add(stack);
            stor.bullets = maxbullets[stack.getItemDamage()];
            stor.save(stack);
        }
    }

    @Override
    public void registerIcons(IconRegister ir) {
        for (int i = 1; i < 7; i++) {
            icons[i - 1] = ir.registerIcon("BL2:" + ammoTypes[i] + "Bandoiler");
        }
    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1 - 1];
    }

    public float getDamageForItemStack(ItemStack stack) {
        BandStor stor = new BandStor(stack);

        return (maxbullets[stack.getItemDamage()] - stor.bullets)
                / (float) maxbullets[stack.getItemDamage()];
    }

    public String getTextureFile() {
        return "/BL2/textures/items.png";
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.clear();

        par3List.add(ammoTypes[par1ItemStack.getItemDamage()]
                + " Ammo Bandoiler");
        par3List.add(new BandStor(par1ItemStack).bullets + "/"
                + maxbullets[par1ItemStack.getItemDamage()]);

    }

    public boolean hasBullets(EntityPlayer player, int type) {
        ItemStack stack = null;
        for (int i = 0; i < 36; i++) {
            stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.itemID == BL2Items.bullets.itemID
                    && stack.getItemDamage() == type)
                return true;
        }
        return false;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        BandStor stor = new BandStor(par1ItemStack);
        if (!hasBullets(par3EntityPlayer, par1ItemStack.getItemDamage())
                || stor.bullets == maxbullets[par1ItemStack.getItemDamage()]) {
            while (true) {
                if (stor.bullets > 64) {
                    if (par3EntityPlayer.inventory
                            .addItemStackToInventory(new ItemStack(
                                    BL2Items.bullets, 64, par1ItemStack
                                            .getItemDamage()))) {
                        System.out.println(true);
                        stor.bullets -= 64;
                    } else {
                        break;
                    }
                } else {
                    if (par3EntityPlayer.inventory
                            .addItemStackToInventory(new ItemStack(
                                    BL2Items.bullets, stor.bullets,
                                    par1ItemStack.getItemDamage()))) {
                        stor.bullets = 0;
                    }
                    break;
                }
            }
        } else {
            ItemStack stack = null;
            for (int i = 0; i < 36; i++) {
                stack = par3EntityPlayer.inventory.getStackInSlot(i);
                if (stack != null
                        && stor.bullets < maxbullets[par1ItemStack
                                .getItemDamage()]
                        && stack.itemID == BL2Items.bullets.itemID
                        && stack.getItemDamage() == par1ItemStack
                                .getItemDamage()) {
                    int o = Math.min(stack.stackSize,
                            maxbullets[par1ItemStack.getItemDamage()]
                                    - stor.bullets);
                    stack.stackSize -= o;
                    stor.bullets += o;
                    if (stack.stackSize <= 0) {
                        par3EntityPlayer.inventory.setInventorySlotContents(i,
                                null);
                    }
                }
            }
        }
        stor.save(par1ItemStack);
        return par1ItemStack;
    }

    @Override
    public boolean isFull3D() {
        return true;
    }

    public static final int[] maxbullets = new int[] { 0, 400, 400, 320, 24,
            100, 100 };

    public static class BandStor {
        // pistol = 0, smg = 1, assault rifle = 2, rocket launcher = 3, sniper =
        // 4, shotgun = 5
        public int bullets;

        public BandStor(ItemStack it) {
            load(it);
        }

        public void save(ItemStack it) {
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("bullets", bullets);

            it.setTagCompound(tag);
        }

        public void load(ItemStack it) {
            NBTTagCompound tag = it.getTagCompound();
            if (tag == null)
                return;
            bullets = tag.getInteger("bullets");
        }
    }
}
