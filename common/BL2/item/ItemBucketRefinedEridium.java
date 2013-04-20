package BL2.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import BL2.BL2Core;

public class ItemBucketRefinedEridium extends ItemBucket {

    public ItemBucketRefinedEridium(int i) {
        super(i, BL2Core.refinedEridiumFlowing.blockID);
        this.setCreativeTab(BL2.creativetab.CreativeTabBL2.tabBL2);
    }

    @Override
    public String getItemDisplayName(ItemStack itemstack) {
        return "Refined Eridium Bucket";
    }

    @Override
    public void registerIcons(IconRegister ir) {
        itemIcon = ir.registerIcon("BL2:" + "RefinedEridiumBucket");
    }
}
