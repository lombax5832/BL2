package BL2.common.item;

import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import BL2.BL2Core;

public class ItemBucketRefinedEridium extends ItemBucket {

	public ItemBucketRefinedEridium(int i) {
		super(i, BL2Core.refinedEridiumFlowing.blockID);
		iconIndex = 0 * 16 + 1;
		setCreativeTab(BL2Core.tabBL2);
	}

	@Override
	public String getItemDisplayName(ItemStack itemstack) {
		return "Refined Eridium Bucket";
	}

	@Override
	public String getTextureFile()
    {
        return "/BL2/textures/items.png";
    }

}
