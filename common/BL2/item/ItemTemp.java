package BL2.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemTemp extends Item {

    public ItemTemp(int par1) {
        super(par1);
        maxStackSize = 1;
        this.setUnlocalizedName("Temp");
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
        
    }

    @Override
    public String getItemDisplayName(ItemStack par1ItemStack){
        return "Temp";
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
