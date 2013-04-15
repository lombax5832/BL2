package BL2.common.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTemp extends Item {

    public ItemTemp(int par1) {
        super(par1);
        maxStackSize = 1;
        this.setCreativeTab(BL2.common.CreativeTabBL2.tabBL2);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void addInformation(ItemStack par1ItemStack,
            EntityPlayer par2EntityPlayer, List par3List, boolean par4) {
        par3List.clear();

        par3List.add("Temp");

    }

    @Override
    public void registerIcons(IconRegister par1IconRegister) {
        itemIcon = par1IconRegister.registerIcon("BL2:Temp");
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
            EntityPlayer par3EntityPlayer) {
        par3EntityPlayer.dropPlayerItem(ItemGun.getRandomGun());
        par1ItemStack.stackSize = 0;
        return par1ItemStack;
    }

    @Override
    public boolean isFull3D() {
        return true;
    }
}
