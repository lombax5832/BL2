package BL2.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import BL2.BL2Core;

public class ItemBucketEridium extends ItemBucket {

    public ItemBucketEridium(int i) {
        super(i, BL2Core.crudeEridiumFlowing.blockID);
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
    }

    @Override
    public String getItemDisplayName(ItemStack itemstack) {
        return "Eridium Bucket";
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon("BL2:" + "EridiumBucket");
    }

}
