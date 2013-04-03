package BL2.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBullets extends Item {
    public static final String[] ammoTypes = new String[] { "", "Pistol",
            "SMG", "Assault Rifle", "Rocket Launcher", "Sniper", "Shotgun" };

    Icon[] icons = new Icon[7];

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.clear();

        par3List.add(ammoTypes[par1ItemStack.getItemDamage()] + " Ammo");

    }

    public ItemBullets(int id) {
        super(id);
        maxStackSize = 64;
        this.setCreativeTab(BL2.common.CreativeTabBL2.tabBL2);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void getSubItems(int i, CreativeTabs tabs, List l) {
        for (int j = 1; j < 7; j++) {
            ItemStack stack = new ItemStack(this, 64, j);
            l.add(stack);
        }
    }

    @Override
    public void updateIcons(IconRegister ir) {
        for (int i = 1; i < 7; i++) {
            icons[i] = ir.registerIcon("BL2:" + ammoTypes[i] + "Ammo");
        }

    }

    @Override
    public Icon getIconFromDamage(int par1) {
        return icons[par1];
    }

    public String getTextureFile() {
        return "/BL2/textures/items.png";
    }

    @Override
    public boolean isFull3D() {
        return true;
    }
}
